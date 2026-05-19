package com.example.noet.data.repository

import com.example.noet.data.local.dao.DaoCategory
import com.example.noet.data.local.entity.Category
import com.example.noet.data.local.entity.CategoryWithVocabularies
import com.example.noet.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: DaoCategory
): CategoryRepository {
    override suspend fun getAllCategory(): List<Category> {
        return categoryDao.getAllCategory()
    }

    override suspend fun getCategoryById(id: Int): Category {
        return categoryDao.getCategoryById(id)
    }

    override suspend fun getCategoryWithVocabularies(): Flow<List<CategoryWithVocabularies>>{
        return categoryDao.getCategoryWithVocabularies()
    }

    override suspend fun getCategoryIdWithVocabularies(id: Int): Flow<CategoryWithVocabularies>{
        return categoryDao.getCategoryIdWithVocabularies(id)
    }
}