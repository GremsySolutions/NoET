package com.example.noet.data.repository

import android.util.Log
import com.example.noet.data.local.dao.DaoCategory
import com.example.noet.data.local.dao.DaoVocabulary
import com.example.noet.data.local.entity.Vocabulary
import com.example.noet.data.local.entity.VocabularyWithCategory
import com.example.noet.domain.repository.AiRepository
import com.example.noet.domain.repository.VocabularyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VocabularyRepositoryImpl @Inject constructor(
    private val vocabularyDao: DaoVocabulary,
    private val categoryDao: DaoCategory,
    private val aiRepository: AiRepository
): VocabularyRepository {
    override suspend fun getAllVocabulary(): List<Vocabulary> {
        return vocabularyDao.getAllVocabulary()
    }

    override fun getVocabularyByCategoryId(id: Int): Flow<List<Vocabulary>> {
        return vocabularyDao.getVocabularyByCategoryId(id)
    }

    override suspend fun insertVocabulary(vocabulary: Vocabulary) {
        vocabularyDao.insertVocabulary(vocabulary)
    }

    override suspend fun deleteVocabulary(id: Int) {
        vocabularyDao.deleteVocabulary(id)
    }

    override suspend fun translateAndSave(
        word: String
    ): Boolean {
        Log.d("AI_DEBUG", "Bắt đầu gọi AI cho từ: $word")
        val allCategories = categoryDao.getAllCategory()
        val categoryNames = allCategories.map { it.name }
        val result = aiRepository.translateAndGenerate(word, categoryNames)
        if (result == null) {
            Log.e("AI_DEBUG", "AI trả về NULL - Kiểm tra lại API Key hoặc Internet")
            return false
        }
        Log.d("AI_DEBUG", "AI trả về kết quả: ${result.meaningVi}")
        val selectedCategory = allCategories.find {
            it.name.equals(result.selectedCategory, ignoreCase = true)
        }
        val finalCategoryId = selectedCategory?.id ?: (allCategories.firstOrNull()?.id ?: 1)

        val newVocab = Vocabulary(
            id = 0,
            word = word,
            category_id = finalCategoryId,
            isFavorite = false,
            meaningVi = result.meaningVi,
            meaningEn = result.meaningEn,
            exampleVi = result.exampleVi,
            exampleEn = result.exampleEn,
            created_at = System.currentTimeMillis(),
            updated_at = System.currentTimeMillis(),
            deleted_at = null
        )
        vocabularyDao.insertVocabulary(newVocab)
        Log.d("AI_DEBUG", "Đã chạy lệnh INSERT vào Database")
        return true
    }

    override suspend fun getAllVocabularyWithCategory(): Flow<List<VocabularyWithCategory>> {
        return vocabularyDao.getAllVocabularyWithCategory()
    }

    override fun searchVocabulary(query: String): Flow<List<Vocabulary>> {
        return vocabularyDao.searchVocabulary(query)
    }
}