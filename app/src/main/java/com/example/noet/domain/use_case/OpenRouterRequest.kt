package com.example.noet.domain.use_case

data class OpenRouterRequest(
    val model: String,
    val messages: List<Message>
)

data class Message(
    val role: String,
    val content: String
)
