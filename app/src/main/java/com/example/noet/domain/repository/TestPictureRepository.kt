package com.example.noet.domain.repository

import com.example.noet.data.local.entity.Vocabulary
import com.example.noet.domain.use_case.AiPictureResponse

interface TestPictureRepository {
    suspend fun generatePicture(): AiPictureResponse?
}