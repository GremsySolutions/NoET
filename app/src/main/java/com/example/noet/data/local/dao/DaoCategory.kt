package com.example.noet.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.noet.data.local.entity.Category
import com.example.noet.data.local.entity.CategoryWithVocabularies
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoCategory {

    @Query("SELECT * FROM category WHERE id = :categoryId")
    fun getCategoryWithVocabularies(categoryId: Int): Flow<CategoryWithVocabularies>

    @Query("SELECT * FROM category")
    suspend fun getAllCategory(): List<Category>

    @Query("SELECT * FROM category WHERE id = :id")
    suspend fun getCategoryById(id: Int): Category

}