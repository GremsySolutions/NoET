package com.example.noet.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "paragraph")
data class Paragraph(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val category_id: Int,
    val isFavorite: Boolean,
    val originText: String,
    val translateText: String,
    val imagePath: String,
    val created_at: Long = System.currentTimeMillis(),
    val updated_at: Long = System.currentTimeMillis(),
    val deleted_at: Long? = null
)
