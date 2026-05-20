package com.example.noet.domain.repository

import android.R.attr.content
import android.R.attr.text
import android.R.id.content
import android.graphics.Bitmap
import android.util.Log
import androidx.compose.ui.semantics.text
import com.example.noet.domain.use_case.AiImageResponse
import com.example.noet.domain.use_case.AiResponse
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.google.gson.Gson
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
            Extract all English text from this image. 
            Then translate it into Vietnamese.
            Also, pick the most suitable category from this list: [$categoryListString].
            
            Return ONLY a JSON object:
            {
              "originText": "original english text",
              "translateText": "vietnamese translation",
              "selectedCategory": "category name from list"
            }
        """.trimIndent()
        return try {
            val response = generativeModel.generateContent(
                content {
                    image(bitmap)
                    text(prompt)
                }
            )
            val responseText = response.text?: return null
            val jsonString = responseText.replace("```json", "").replace("```", "").trim()
            gson.fromJson(jsonString, AiImageResponse::class.java)
        }catch (e: Exception) {
            Log.e("AI_DEBUG_PARAGRAPH", "Lỗi phân tích ảnh: ${e.message}")
            return null
        }
    }
}