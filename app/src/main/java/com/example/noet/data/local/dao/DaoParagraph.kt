package com.example.noet.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.noet.data.local.entity.Paragraph
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoParagraph {

    @Query("SELECT * FROM paragraph WHERE id = :id")
    suspend fun getParagraphById(id: Int): Paragraph

    @Query("SELECT * FROM paragraph ORDER BY created_at DESC")
    fun getAllParagraph(): Flow<List<Paragraph>>

    @Query("SELECT * FROM paragraph WHERE category_id = :categoryId")
    fun getParagraphByCategoryId(categoryId: Int): Flow<List<Paragraph>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertParagraph(paragraph: Paragraph)

    @Query("DELETE FROM paragraph WHERE id = :id")
    suspend fun deleteParagraph(id: Int)
}