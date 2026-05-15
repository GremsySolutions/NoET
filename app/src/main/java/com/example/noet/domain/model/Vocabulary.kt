package com.example.noet.domain.model

data class Vocabulary (
    val id: Int,
    val category_id: Int,
    val isFavorite: Boolean,
    val word: String,
    val meaningVi: String,
    val meaningEn: String,
    val exampleVi: String,
    val exampleEn: String
)
