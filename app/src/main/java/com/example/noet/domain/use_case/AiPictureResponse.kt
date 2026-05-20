package com.example.noet.domain.use_case

data class AiPictureResponse(
    val imagePrompt: String,
    val imageUrl: String?,
    val title: String,
    val description: String,
    val words: List<PaintingWord>
)
data class PaintingWord(
    val english: String,
    val vietnamese: String
)