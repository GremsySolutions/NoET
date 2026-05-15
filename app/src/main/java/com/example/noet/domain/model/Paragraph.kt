package com.example.noet.domain.model

data class Paragraph(
    val id: Int,
    val category_id: Int,
    val isFavorite: Boolean,
    val originText: String,
    val translateText: String,
    val imagePath: String,
)
