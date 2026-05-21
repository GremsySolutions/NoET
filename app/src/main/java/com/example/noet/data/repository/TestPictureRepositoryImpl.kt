package com.example.noet.data.repository

import android.util.Log
import com.example.noet.data.local.dao.DaoVocabulary
import com.example.noet.domain.repository.AiRepository
import com.example.noet.domain.repository.TestPictureRepository
import com.example.noet.domain.use_case.AiMusicResponse
import com.example.noet.domain.use_case.AiPictureResponse
import javax.inject.Inject

class TestPictureRepositoryImpl @Inject constructor(
    private val vocabularyDao: DaoVocabulary,
    private val aiRepository: AiRepository
) : TestPictureRepository {

    override suspend fun generatePicture(): AiPictureResponse? {
        Log.d("AI_DEBUG", "Bắt đầu gọi AI tạo tranh")
        val vocabularies = vocabularyDao.getAllVocabulary()
        val result = aiRepository.generatePicture(vocabularies)
        if (result == null) {
            Log.e("AI_DEBUG", "AI trả về NULL")
            return null
        }
        Log.d("AI_DEBUG", "AI trả về kết quả: ${result.words}")
        return result
    }

    override suspend fun generateMusicSong(): AiMusicResponse? {
        Log.d("AI_DEBUG", "Bắt đầu gọi AI tạo tranh")
        val vocabularies = vocabularyDao.getAllVocabulary()
        val result = aiRepository.generateMusicSong(vocabularies)
        if (result == null) {
            Log.e("AI_DEBUG", "AI trả về NULL")
            return null
        }
        Log.d("AI_DEBUG", "AI trả về kết quả: ${result.title}")
        return result
    }
}