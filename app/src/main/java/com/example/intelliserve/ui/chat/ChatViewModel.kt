package com.example.intelliserve.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intelliserve.data.repository.ChatRepository
import com.example.intelliserve.ui.models.AIModel
import com.example.intelliserve.ui.models.ChatMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    init {
        // Initialize with default model
        selectModel(AIModel.GROQ_LLAMA_3_70B)
    }

    fun sendMessage(message: String) {
        if (message.isBlank()) return

        // Add user message
        val userMessage = ChatMessage(
            text = message,
            isFromUser = true,
            modelId = _uiState.value.selectedModel.id
        )
        
        _uiState.update { currentState ->
            currentState.copy(
                messages = currentState.messages + userMessage,
                isLoading = true
            )
        }

        // Get AI response
        viewModelScope.launch {
            try {
                val response = chatRepository.getResponse(
                    message = message,
                    model = _uiState.value.selectedModel
                )
                
                val aiMessage = ChatMessage(
                    text = response,
                    isFromUser = false,
                    modelId = _uiState.value.selectedModel.id,
                    modelName = _uiState.value.selectedModel.displayName
                )
                
                _uiState.update { currentState ->
                    currentState.copy(
                        messages = currentState.messages + aiMessage,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                // Handle error
                val errorMessage = ChatMessage(
                    text = "Error: ${e.message ?: "Unknown error occurred"}",
                    isFromUser = false,
                    modelId = _uiState.value.selectedModel.id,
                    modelName = _uiState.value.selectedModel.displayName
                )
                
                _uiState.update { currentState ->
                    currentState.copy(
                        messages = currentState.messages + errorMessage,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun selectModel(model: AIModel) {
        _uiState.update { it.copy(selectedModel = model) }
    }
}

data class ChatUiState(
    val messages: List<ChatMessage> = emptyList(),
    val selectedModel: AIModel = AIModel.GROQ_LLAMA_3_70B,
    val isLoading: Boolean = false
)
