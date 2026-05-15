package com.example.noet.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "category")
data class Category(
    @PrimaryKey (autoGenerate = true) val id: Int,
    val name: String,
    val created_at: Timestamp,
    val updated_at: Timestamp,
    val deleted_at: Timestamp
)
