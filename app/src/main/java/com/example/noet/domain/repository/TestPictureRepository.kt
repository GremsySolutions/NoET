package com.example.noet.domain.repository

import com.example.noet.domain.use_case.AiMusicResponse
import com.example.noet.domain.use_case.AiPictureResponse

interface TestPictureRepository {
    suspend fun generatePicture(): AiPictureResponse?
    suspend fun generateMusicSong(): AiMusicResponse?
}