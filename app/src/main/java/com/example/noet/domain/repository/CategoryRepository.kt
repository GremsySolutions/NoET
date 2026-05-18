package com.example.noet.domain.repository

import com.example.noet.data.local.entity.Category

interface CategoryRepository {
    suspend fun getAllCategory(): List<Category>
    suspend fun getCategoryById(id: Int): Category

}