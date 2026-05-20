package com.example.noet.domain.repository

import com.example.noet.data.local.entity.Vocabulary
import com.example.noet.data.local.entity.VocabularyWithCategory
import kotlinx.coroutines.flow.Flow

interface VocabularyRepository {

    suspend fun getAllVocabulary(): List<Vocabulary>

    fun getVocabularyByCategoryId(id: Int): Flow<List<Vocabulary>>

    suspend fun insertVocabulary(vocabulary: Vocabulary)

    suspend fun deleteVocabulary(id: Int)
    suspend fun translateAndSave(word: String): Boolean

    suspend fun getAllVocabularyWithCategory(): Flow<List<VocabularyWithCategory>>

    fun searchVocabulary(query: String): Flow<List<Vocabulary>>
}