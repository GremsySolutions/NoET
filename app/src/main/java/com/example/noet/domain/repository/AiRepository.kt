package com.example.noet.domain.repository

import android.graphics.Bitmap
import android.util.Log
import com.example.noet.data.local.entity.Vocabulary
import com.example.noet.data.remote.AiConfig
import com.example.noet.data.remote.api.OpenRouterApi
import com.example.noet.domain.use_case.AiImageResponse
import com.example.noet.domain.use_case.AiMusicResponse
import com.example.noet.domain.use_case.AiPictureResponse
import com.example.noet.domain.use_case.AiResponse
import com.example.noet.domain.use_case.Message
import com.example.noet.domain.use_case.OpenRouterRequest
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.google.gson.Gson
import retrofit2.HttpException
import java.net.URLEncoder
import javax.inject.Inject
import kotlin.jvm.java

class AiRepository @Inject constructor(
    private val generativeModel: GenerativeModel,
    private val openRouterApi: OpenRouterApi,
    private val gson: Gson
) {
    suspend fun translateAndGenerate(word: String, categories: List<String> ): AiResponse? {
        val categoryListString = categories.joinToString(", ")
        val prompt = """
            Translate the word '$word' to Vietnamese.
            Choose the most suitable category for this word from the following list: [$categoryListString].
            
            Return ONLY a JSON object with these keys:
            - phonetic: IPA phonetic transcription
            - meaningVi: Vietnamese meaning
            - meaningEn: English definition
            - exampleVi: A Vietnamese example sentence
            - exampleEn: The English translation of the example
            - selectedCategory: The name of the chosen category from the list above.
           
            Return ONLY raw JSON, no markdown, no explanation.
        """.trimIndent()
        return try {
            val response = openRouterApi.chatCompletion(
                OpenRouterRequest(
                    model = AiConfig.MODEL,
                    messages = listOf(
                        Message(
                            role = "user",
                            content = prompt
                        )
                    )
                )
            )

            val responseText =
                response.choices.firstOrNull()
                    ?.message
                    ?.content

            if (responseText == null) {
                Log.e("AI_DEBUG", "không trả về nội dung (có thể do từ nhạy cảm hoặc lỗi vùng)")
                return null
            }

            val jsonString = responseText
                .replace("```json", "")
                .replace("```", "")
                .trim()
            Log.d("AI_DEBUG", "Kết quả từ Gemini: $jsonString")
            gson.fromJson(jsonString, AiResponse::class.java)

        } catch (e: HttpException) {
            Log.e("AI_DEBUG", "HTTP ${e.code()} ${e.response()?.errorBody()?.string()}")
            e.printStackTrace()
            null
        }
    }

    suspend fun translateParagraphWithImage(bitmap: Bitmap, categories: List<String>): AiImageResponse? {
        val categoryListString = categories.joinToString(", ")
        val prompt = """
            Bạn là công cụ OCR và dịch thuật.
            
            Hãy đọc nội dung trong ảnh, sau đó trả về JSON hợp lệ.
            
            Yêu cầu:
            - title: summary lại toàn bộ nội dung văn bản và generate một title với khoảng 2-3 từ Tiếng Việt
            - originText: nội dung tiếng Anh trong ảnh, viết ngắn gọn, không cần chép quá dài nguyên văn
            - translateText: bản dịch tiếng Việt đầy đủ, tự nhiên
            - selectedCategory: chọn đúng 1 category phù hợp nhất trong danh sách: [$categoryListString]
            
            Chỉ trả về JSON, không thêm giải thích.
            
            JSON format:
            {
              "title": "",
              "originText": "",
              "translateText": "",
              "selectedCategory": ""
            }
        """.trimIndent()

        return try {
            val response = generativeModel.generateContent(
                content {
                    image(bitmap)
                    text(prompt)
                }
            )
            val responseText = response.text ?: return null
            Log.d("AI_DEBUG_PARAGRAPH", "Raw AI full: $responseText")

            val jsonString = responseText
                .replace("```json", "")
                .replace("```", "")
                .trim()
                .substringAfter("{")
                .substringBeforeLast("}")
                .let { "{$it}" }
            Log.d("AI_DEBUG_PARAGRAPH", "JSON clean: $jsonString")
            val result = gson.fromJson(jsonString, AiImageResponse::class.java)
            Log.d("AI_DEBUG_PARAGRAPH", "Gốc: ${result.originText}")
            Log.d("AI_DEBUG_PARAGRAPH", "Dịch: ${result.translateText}")
            Log.d("AI_DEBUG_PARAGRAPH", "Category: ${result.selectedCategory}")
            result
        } catch (e: Exception) {
            Log.e("AI_DEBUG_PARAGRAPH", "Lỗi phân tích ảnh: ${e.message}", e)
            null
        }
    }

    suspend fun generatePicture(
        vocabularies: List<Vocabulary>
    ): AiPictureResponse? {

        val selectedWords = vocabularies
            .shuffled()
            .take(20)

        val vocabularyText = selectedWords.joinToString(", ") {
            "${it.word} (${it.meaningVi})"
        }
        val prompt = """
            Return ONLY one valid JSON object.
            The first character must be { and the last character must be }.
            Do not wrap JSON in quotes.
            Do not add markdown.
            Do not add explanation.
            
            Vocabulary words:
            $vocabularyText
            
            JSON format:
            {
              "imagePrompt": "",
              "title": "",
              "description": "",
              "words": [
                {
                  "phonetic": "",
                  "english": "",
                  "vietnamese": ""
                }
              ]
            }
            """.trimIndent()
        return try {
            val response = openRouterApi.chatCompletion(
                OpenRouterRequest(
                    model = AiConfig.MODEL,
                    messages = listOf(
                        Message(
                            role = "user",
                            content = prompt
                        )
                    )
                )
            )

            val responseText =
                response.choices.firstOrNull()
                    ?.message
                    ?.content
            val jsonString = responseText
                ?.replace("```json", "")
                ?.replace("```", "")
                ?.trim()
            val result = gson.fromJson(
                jsonString,
                AiPictureResponse::class.java
            )
            val imageUrl =
                "https://image.pollinations.ai/prompt/${
                    URLEncoder.encode(
                        result.imagePrompt,
                        "UTF-8"
                    )
                }"
            result.copy(
                imageUrl = imageUrl
            )
        } catch (e: Exception) {
            Log.e(
                "AI_DEBUG",
                "Generate painting error: ${e.message}"
            )
            null
        }
    }

    suspend fun generateMusicSong(
        vocabularies: List<Vocabulary>
    ): AiMusicResponse? {

        val selectedWords = vocabularies
            .shuffled()
            .take(30)

        val vocabularyText = selectedWords.joinToString(", ") {
            "${it.word} (${it.meaningVi})"
        }

        val prompt = """
            Create a short English learning song using these vocabulary words: $vocabularyText
        
            Requirements:
            - Use the vocabulary naturally in the lyrics
            - The song should be simple, catchy, and easy for English learners
            - Lyrics must be in English
            - Title must be in Vietnamese
            - translateVi must be the Vietnamese translation of the lyrics
            - melody: Suggest a well-known song melody that these lyrics can be sung to (e.g., "Let It Go", "Twinkle Twinkle Little Star", "My love", "Beautifull in white")
            - Do not include musical notes or chords
            - Return ONLY raw JSON
        
            JSON format:
            {
              "title": "",
              "lyrics": "",
              "translateVi": "",
              "melody": ""
            }
        """.trimIndent()
        return try {
            val response = openRouterApi.chatCompletion(
                OpenRouterRequest(
                    model = AiConfig.MODEL,
                    messages = listOf(
                        Message(
                            role = "user",
                            content = prompt
                        )
                    )
                )
            )
            val responseText = response.choices
                .firstOrNull()
                ?.message
                ?.content ?: return null
            val jsonString = responseText
                .replace("```json", "")
                .replace("```", "")
                .trim()
            gson.fromJson(jsonString, AiMusicResponse::class.java)
        } catch (e: Exception) {
            Log.e(
                "AI_DEBUG",
                "Generate music error: ${e.message}",
                e
            )
            null
        }
    }
}