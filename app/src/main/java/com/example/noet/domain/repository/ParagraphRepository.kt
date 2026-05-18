package com.example.noet.domain.repository

import com.example.noet.data.local.entity.Category
import com.example.noet.domain.model.Paragraph
import kotlinx.coroutines.flow.Flow

interface ParagraphRepository {

    suspend fun getAllParagraph(): List<Paragraph>

    suspend fun getParagraphById(id: Int): Paragraph

    fun getParagraphByCategoryId(categoryId: Int): Flow<List<Paragraph>>

    suspend fun insertParagraph(paragraph: Paragraph)

    suspend fun deleteParagraph(id: Int)

}