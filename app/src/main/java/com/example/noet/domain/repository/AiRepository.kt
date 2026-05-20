package com.example.noet.domain.repository

import android.graphics.Bitmap
import android.util.Log
import com.example.noet.data.local.entity.Vocabulary
import com.example.noet.domain.use_case.AiImageResponse
import com.example.noet.domain.use_case.AiPictureResponse
import com.example.noet.domain.use_case.AiResponse
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.google.gson.Gson
import java.net.URLEncoder
import javax.inject.Inject

class AiRepository @Inject constructor(
    private val generativeModel: GenerativeModel,
    private val gson: Gson
) {
    suspend fun translateAndGenerate(word: String, categories: List<String> ): AiResponse? {
        val categoryListString = categories.joinToString(", ")
        val prompt = """
            Translate the word '$word' to Vietnamese.
            Choose the most suitable category for this word from the following list: [$categoryListString].
            
            Return ONLY a JSON object with these keys:
            - meaningVi: Vietnamese meaning
            - meaningEn: English definition
            - exampleVi: A Vietnamese example sentence
            - exampleEn: The English translation of the example
            - selectedCategory: The name of the chosen category from the list above.
           
            Return ONLY raw JSON, no markdown, no explanation.
        """.trimIndent()
        return try {
            val response = generativeModel.generateContent(prompt)
            val responseText = response.text

            if (responseText == null) {
                Log.e("AI_DEBUG", "Gemini không trả về nội dung (có thể do từ nhạy cảm hoặc lỗi vùng)")
                return null
            }

            val jsonString = responseText
                .replace("```json", "")
                .replace("```", "")
                .trim()
            Log.d("AI_DEBUG", "Kết quả từ Gemini: $jsonString")
            gson.fromJson(jsonString, AiResponse::class.java)

        } catch (e: Exception) {
            Log.e("AI_DEBUG", "LỖI KHI GỌI GEMINI: ${e.message}")
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
            - title: summary lại toàn bộ nội dung văn bản và generate một title với khoảng 2-3 từ 
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
            Create a colorful educational illustration scene using these vocabulary words: $vocabularyText
            Requirements:
                - The scene should match the meanings and contexts of the vocabulary words
                - The illustration style should fit the emotions, objects, and activities in the vocabulary list
                - No text inside image
                - Return Vietnamese title and description
                - Return English-Vietnamese word pairs
                - Return ONLY raw JSON
            JSON format:
                {
                  "imagePrompt": "",
                  "title": "",
                  "description": "",
                  "words": [
                    {
                      "english": "",
                      "vietnamese": ""
                    }
                  ]
                }
            """.trimIndent()
        return try {
            val response = generativeModel.generateContent(prompt)
            val responseText = response.text ?: return null
            val jsonString = responseText
                .replace("```json", "")
                .replace("```", "")
                .trim()
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
}