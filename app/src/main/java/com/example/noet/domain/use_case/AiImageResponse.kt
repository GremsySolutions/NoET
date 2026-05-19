package com.example.noet.domain.use_case

data class AiImageResponse(
    val originText: String,
    val translateText: String,
    val selectedCategory: String
)