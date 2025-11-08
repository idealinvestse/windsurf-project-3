package com.example.intelliserve.data.api

import com.example.intelliserve.data.api.models.ChatRequest
import com.example.intelliserve.data.api.models.ChatResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface XAIApiService {
    @POST("v1/chat/completions")
    @Headers("Content-Type: application/json")
    suspend fun createChatCompletion(
        @Body request: ChatRequest
    ): ChatResponse
}
