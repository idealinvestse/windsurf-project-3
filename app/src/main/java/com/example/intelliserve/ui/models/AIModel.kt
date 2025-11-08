package com.example.intelliserve.ui.models

/**
 * Data class representing an AI model that can be used for chat.
 * @property id Unique identifier for the model
 * @property displayName User-friendly name for the model
 * @property description Description of the model's capabilities
 * @property maxTokens Maximum number of tokens the model can handle
 * @property isLocal Whether the model runs locally on the device
 */
data class AIModel(
    val id: String,
    val displayName: String,
    val description: String,
    val maxTokens: Int,
    val isLocal: Boolean = false
) {
    companion object {
        // Predefined list of available AI models
        val availableModels = listOf(
            GROK_BETA,
            GROQ_LLAMA_3_70B,
            GROQ_LLAMA_3_8B,
            GROQ_MIXTRAL_8X7B,
            GROQ_GEMMA_7B
        )

        // x.ai (Grok) Models
        val GROK_BETA = AIModel(
            id = "grok-beta",
            displayName = "Grok Beta",
            description = "x.ai's powerful conversational AI model",
            maxTokens = 8192
        )

        // Groq Cloud Models
        val GROQ_LLAMA_3_70B = AIModel(
            id = "llama3-70b-8192",
            displayName = "Llama 3 70B (Groq)",
            description = "Meta's Llama 3 70B on Groq's ultra-fast inference",
            maxTokens = 8192
        )

        val GROQ_LLAMA_3_8B = AIModel(
            id = "llama3-8b-8192",
            displayName = "Llama 3 8B (Groq)",
            description = "Faster Llama 3 8B model on Groq infrastructure",
            maxTokens = 8192
        )

        val GROQ_MIXTRAL_8X7B = AIModel(
            id = "mixtral-8x7b-32768",
            displayName = "Mixtral 8x7B (Groq)",
            description = "Mistral's mixture-of-experts model on Groq",
            maxTokens = 32768
        )

        val GROQ_GEMMA_7B = AIModel(
            id = "gemma-7b-it",
            displayName = "Gemma 7B (Groq)",
            description = "Google's Gemma 7B instruction-tuned on Groq",
            maxTokens = 8192
        )
    }
}
