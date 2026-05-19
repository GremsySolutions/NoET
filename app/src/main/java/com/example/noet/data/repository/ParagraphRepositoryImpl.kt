package com.example.noet.data.repository

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.example.noet.data.local.dao.DaoCategory
import com.example.noet.data.local.dao.DaoParagraph
import com.example.noet.data.local.entity.Paragraph
import com.example.noet.domain.repository.AiRepository
import com.example.noet.domain.repository.ParagraphRepository
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject

class ParagraphRepositoryImpl @Inject constructor(
    private val paragraphDao: DaoParagraph,
    private val categoryDao: DaoCategory,
    private val aiRepository: AiRepository
): ParagraphRepository {
    override suspend fun getAllParagraph(): List<Paragraph> {
        return paragraphDao.getAllParagraph()
    }

    override suspend fun getParagraphById(id: Int): Paragraph {
        return paragraphDao.getParagraphById(id)
    }

    override fun getParagraphByCategoryId(categoryId: Int): Flow<List<Paragraph>> {
        return getParagraphByCategoryId(categoryId)
    }

    override suspend fun insertParagraph(paragraph: Paragraph) {
        return paragraphDao.insertParagraph(paragraph)
    }

    override suspend fun deleteParagraph(id: Int) {
        return paragraphDao.deleteParagraph(id)
    }

    override suspend fun scanAndSave(
        bitmap: Bitmap,
        context: Context
    ): Boolean {

        Log.d("AI_DEBUG_PARAGRAPH", "Bắt đầu gọi AI cho từ:")
        val fileName = "para_${System.currentTimeMillis()}.jpg"
        val file = File(context.filesDir, fileName)
        file.outputStream().use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, it)
        }
        val allCategories = categoryDao.getAllCategory()
        val categoryNames = allCategories.map { it.name }
        val result = aiRepository.translateParagraphWithImage(bitmap, categoryNames )

        if (result == null) {
            Log.e("AI_DEBUG_PARAGRAPH", "AI trả về NULL - Kiểm tra lại API Key hoặc Internet")

            return false
        }
        Log.d("AI_DEBUG_PARAGRAPH", "AI trả về kết quả: ${result.originText}")

        val selectedCategory = allCategories.find {
            it.name.equals(result.selectedCategory, ignoreCase = true)
        }
        val finalCategoryId = selectedCategory?.id ?: (allCategories.firstOrNull()?.id ?: 1)

        val newParagraph = Paragraph(
            id = 0,
            category_id = finalCategoryId,
            isFavorite = false,
            originText = result.originText,
            translateText = result.translateText,
            imagePath = fileName,
            created_at = System.currentTimeMillis(),
            updated_at = System.currentTimeMillis(),
            deleted_at = null
        )
        paragraphDao.insertParagraph(newParagraph)
        return true
    }
}