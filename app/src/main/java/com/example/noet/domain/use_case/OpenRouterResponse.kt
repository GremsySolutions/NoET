package com.example.noet.domain.use_case

data class OpenRouterResponse(
    val choices: List<Choice>
)

data class Choice(
    val message: MessageResponse
)

data class MessageResponse(
    val content: String
)
