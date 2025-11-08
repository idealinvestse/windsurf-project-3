package com.example.intelliserve.data.api.models

import com.google.gson.annotations.SerializedName

data class ChatRequest(
    @SerializedName("model")
    val model: String,
    
    @SerializedName("messages")
    val messages: List<Message>,
    
    @SerializedName("temperature")
    val temperature: Double = 0.7,
    
    @SerializedName("max_tokens")
    val maxTokens: Int? = null,
    
    @SerializedName("stream")
    val stream: Boolean = false
)

data class Message(
    @SerializedName("role")
    val role: String, // "system", "user", or "assistant"
    
    @SerializedName("content")
    val content: String
)

data class ChatResponse(
    @SerializedName("id")
    val id: String,
    
    @SerializedName("object")
    val objectType: String,
    
    @SerializedName("created")
    val created: Long,
    
    @SerializedName("model")
    val model: String,
    
    @SerializedName("choices")
    val choices: List<Choice>,
    
    @SerializedName("usage")
    val usage: Usage?
)

data class Choice(
    @SerializedName("index")
    val index: Int,
    
    @SerializedName("message")
    val message: Message,
    
    @SerializedName("finish_reason")
    val finishReason: String?
)

data class Usage(
    @SerializedName("prompt_tokens")
    val promptTokens: Int,
    
    @SerializedName("completion_tokens")
    val completionTokens: Int,
    
    @SerializedName("total_tokens")
    val totalTokens: Int
)
