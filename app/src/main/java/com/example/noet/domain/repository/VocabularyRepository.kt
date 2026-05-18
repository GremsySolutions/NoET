package com.example.noet.domain.repository

import com.example.noet.data.local.entity.Vocabulary
import kotlinx.coroutines.flow.Flow

interface VocabularyRepository {

    suspend fun getAllVocabulary(): List<Vocabulary>

    fun getVocabularyByCategoryId(id: Int): Flow<List<Vocabulary>>

    suspend fun insertVocabulary(vocabulary: Vocabulary)

    suspend fun deleteVocabulary(id: Int)
    suspend fun translateAndSave(word: String, categoryId: Int): Boolean
}