package com.example.noet.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "vocabulary")
data class Vocabulary(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val word: String,
    val category_id: Int,
    val isFavorite: Boolean,
    val meaningVi: String,
    val meaningEn: String,
    val exampleVi: String,
    val exampleEn: String,
    val created_at: Timestamp,
    val updated_at: Timestamp,
    val deleted_at: Timestamp
)
