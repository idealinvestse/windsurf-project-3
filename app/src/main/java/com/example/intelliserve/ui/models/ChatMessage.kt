package com.example.intelliserve.ui.models

data class ChatMessage(
    val id: String = java.util.UUID.randomUUID().toString(),
    val text: String,
    val isFromUser: Boolean,
    val modelId: String = "",
    val modelName: String = "",
    val timestamp: Long = System.currentTimeMillis()
)
