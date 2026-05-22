package com.example.noet.domain.repository

import android.graphics.Bitmap
import com.example.noet.data.local.entity.Vocabulary
import com.example.noet.domain.use_case.AiImageResponse
import com.example.noet.domain.use_case.AiMusicResponse
import com.example.noet.domain.use_case.AiPictureResponse
import com.example.noet.domain.use_case.AiResponse

interface AiRepository {
    suspend fun translateAndGenerate(word: String, categories: List<String>): AiResponse?
    suspend fun translateParagraphWithImage(bitmap: Bitmap, categories: List<String>): AiImageResponse?
    suspend fun generatePicture(vocabularies: List<Vocabulary>): AiPictureResponse?
    suspend fun generateMusicSong(vocabularies: List<Vocabulary>): AiMusicResponse?
}