package com.example.intelliserve.data.model

data class ChatMessage(
    val text: String,
    val sender: MessageSender,
    val timestamp: Long,
    val isPending: Boolean = false,
    val error: String? = null
)

enum class MessageSender {
    USER,
    AI
}
