package com.example.noet.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.noet.data.local.entity.Vocabulary
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoVocabulary {

    @Query("SELECT * FROM vocabulary WHERE id = :id")
    suspend fun getVocabularyById(id: Int): Vocabulary

    @Query("SELECT * FROM vocabulary")
    suspend fun getAllVocabulary(): List<Vocabulary>

    @Query("SELECT * FROM vocabulary WHERE category_id = :categoryId")
    fun getVocabularyByCategoryId(categoryId: Int): Flow<List<Vocabulary>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVocabulary(vocabulary: Vocabulary)

    @Query("DELETE FROM vocabulary WHERE id = :id")
    suspend fun deleteVocabulary(id: Int)
}