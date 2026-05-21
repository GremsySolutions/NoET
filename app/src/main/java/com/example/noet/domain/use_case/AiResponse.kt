package com.example.noet.domain.use_case

data class AiResponse(
    val phonetic: String,
    val meaningVi: String,
    val meaningEn: String,
    val exampleVi: String,
    val exampleEn: String,
    val selectedCategory: String? = null
)