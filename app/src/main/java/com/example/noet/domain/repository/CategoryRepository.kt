package com.example.noet.domain.repository

import com.example.noet.data.local.entity.Category
import com.example.noet.data.local.entity.CategoryWithVocabularies
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getAllCategory(): List<Category>
    suspend fun getCategoryById(id: Int): Category
    suspend fun getCategoryWithVocabularies(): Flow<List<CategoryWithVocabularies>>
    suspend fun getCategoryIdWithVocabularies(id: Int): Flow<CategoryWithVocabularies>

}