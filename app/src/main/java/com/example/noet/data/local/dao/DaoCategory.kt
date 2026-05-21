package com.example.noet.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.noet.data.local.entity.Category
import com.example.noet.data.local.entity.CategoryWithVocabularies
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoCategory {

    @Query("SELECT * FROM category ")
    fun getCategoryWithVocabularies(): Flow<List<CategoryWithVocabularies>>

    @Transaction
    @Query("SELECT * FROM category WHERE id = :id")
    fun getCategoryIdWithVocabularies(id: Int): Flow<CategoryWithVocabularies>

    @Query("SELECT * FROM category")
    suspend fun getAllCategory(): List<Category>

    @Query("SELECT * FROM category WHERE id = :id")
    suspend fun getCategoryById(id: Int): Category

}