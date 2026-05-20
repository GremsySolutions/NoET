package com.example.noet.domain.use_case

data class AiImageResponse(
    val title: String,
    val originText: String,
    val translateText: String,
    val selectedCategory: String
)