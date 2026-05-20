package com.example.noet.domain.repository

import android.content.Context
import android.graphics.Bitmap
import com.example.noet.data.local.entity.Paragraph
import kotlinx.coroutines.flow.Flow

interface ParagraphRepository {

    suspend fun getAllParagraph(): Flow<List<Paragraph>>

    suspend fun getParagraphById(id: Int): Paragraph

    fun getParagraphByCategoryId(categoryId: Int): Flow<List<Paragraph>>

    suspend fun insertParagraph(paragraph: Paragraph)

    suspend fun deleteParagraph(id: Int)

    suspend fun scanAndSave(bitmap: Bitmap, context: Context): Boolean
}