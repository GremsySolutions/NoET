package com.example.noet.domain.repository

import android.util.Log
import com.example.noet.domain.use_case.AiResponse
import com.google.ai.client.generativeai.GenerativeModel
import com.google.gson.Gson
import javax.inject.Inject

class AiRepository @Inject constructor(
    private val generativeModel: GenerativeModel,
    private val gson: Gson
) {
    suspend fun translateAndGenerate(word: String): AiResponse? {
        val prompt = """
            Translate the word '$word' to Vietnamese.
            Return ONLY a JSON object with these keys:
            - meaningVi: Vietnamese meaning
            - meaningEn: English definition
            - exampleVi: A Vietnamese example sentence
            - exampleEn: The English translation of the example
            
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
}