package com.example.intelliserve.data.repository

import com.example.intelliserve.ui.models.AIModel
import javax.inject.Inject

interface ChatRepository {
    /**
     * Get a response from the specified AI model
     * @param message The user's message
     * @param model The AI model to use for generating the response
     * @return The AI's response
     */
    suspend fun getResponse(message: String, model: AIModel): String
}

class ChatRepositoryImpl @Inject constructor(
    private val xAIService: XAIService,
    private val groqService: GroqService
) : ChatRepository {
    
    override suspend fun getResponse(message: String, model: AIModel): String {
        return when (model.id) {
            AIModel.GROK_BETA.id -> xAIService.getResponse(message, model)
            AIModel.GROQ_LLAMA_3_70B.id -> groqService.getResponse(message, model)
            AIModel.GROQ_LLAMA_3_8B.id -> groqService.getResponse(message, model)
            AIModel.GROQ_MIXTRAL_8X7B.id -> groqService.getResponse(message, model)
            AIModel.GROQ_GEMMA_7B.id -> groqService.getResponse(message, model)
            else -> throw IllegalArgumentException("Unsupported model: ${model.id}")
        }
    }
}

// x.ai Service for Grok models
class XAIService @Inject constructor(
    private val apiService: com.example.intelliserve.data.api.XAIApiService
) {
    suspend fun getResponse(message: String, model: AIModel): String {
        try {
            val request = com.example.intelliserve.data.api.models.ChatRequest(
                model = model.id,
                messages = listOf(
                    com.example.intelliserve.data.api.models.Message(
                        role = "user",
                        content = message
                    )
                ),
                temperature = 0.7,
                maxTokens = model.maxTokens
            )
            
            val response = apiService.createChatCompletion(request)
            return response.choices.firstOrNull()?.message?.content 
                ?: "No response from ${model.displayName}"
        } catch (e: Exception) {
            throw Exception("Failed to get response from ${model.displayName}: ${e.message}")
        }
    }
}

// Groq Cloud Service
class GroqService @Inject constructor(
    private val apiService: com.example.intelliserve.data.api.GroqApiService
) {
    suspend fun getResponse(message: String, model: AIModel): String {
        try {
            val request = com.example.intelliserve.data.api.models.ChatRequest(
                model = model.id,
                messages = listOf(
                    com.example.intelliserve.data.api.models.Message(
                        role = "user",
                        content = message
                    )
                ),
                temperature = 0.7,
                maxTokens = model.maxTokens
            )
            
            val response = apiService.createChatCompletion(request)
            return response.choices.firstOrNull()?.message?.content 
                ?: "No response from ${model.displayName}"
        } catch (e: Exception) {
            throw Exception("Failed to get response from ${model.displayName}: ${e.message}")
        }
    }
}
