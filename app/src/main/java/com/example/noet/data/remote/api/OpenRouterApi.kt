package com.example.noet.data.remote.api

import com.example.noet.domain.use_case.OpenRouterRequest
import com.example.noet.domain.use_case.OpenRouterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface OpenRouterApi {

    @POST("chat/completions")
    suspend fun chatCompletion(
        @Body request: OpenRouterRequest
    ): OpenRouterResponse
}