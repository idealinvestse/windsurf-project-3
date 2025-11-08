package com.example.intelliserve.ui.screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.intelliserve.data.model.ChatMessage
import com.example.intelliserve.data.model.MessageSender
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(navController: NavController, modelId: String) {
    var messageText by remember { mutableStateOf("") }
    val messages = remember { mutableStateListOf<ChatMessage>() }
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    // Get model name based on ID (in a real app, this would come from a repository)
    val modelName = when (modelId) {
        "gpt-4" -> "GPT-4"
        "claude-2" -> "Claude 2"
        "llama-2" -> "Llama 2"
        "palm-2" -> "PaLM 2"
        else -> "AI Assistant"
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(modelName) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shadowElevation = 4.dp,
                shape = RoundedCornerShape(24.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = messageText,
                        onValueChange = { messageText = it },
                        placeholder = { Text("Message $modelName...") },
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        maxLines = 3,
                        singleLine = false
                    )
                    
                    IconButton(
                        onClick = {
                            if (messageText.isNotBlank()) {
                                // Add user message
                                val userMessage = ChatMessage(
                                    text = messageText,
                                    sender = MessageSender.USER,
                                    timestamp = System.currentTimeMillis()
                                )
                                messages.add(userMessage)
                                
                                // Simulate AI response (in a real app, this would be an API call)
                                val aiResponse = ChatMessage(
                                    text = "This is a simulated response from $modelName. In a real app, this would be an actual response from the AI model's API.",
                                    sender = MessageSender.AI,
                                    timestamp = System.currentTimeMillis()
                                )
                                
                                // Clear input and scroll to bottom
                                messageText = ""
                                coroutineScope.launch {
                                    // Small delay to simulate network call
                                    kotlinx.coroutines.delay(500)
                                    messages.add(aiResponse)
                                    scrollState.animateScrollToItem(messages.size - 1)
                                }
                            }
                        },
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                    ) {
                        Icon(
                            Icons.Default.Send,
                            contentDescription = "Send",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 8.dp),
            state = scrollState,
            verticalArrangement = Arrangement.Bottom,
            reverseLayout = true
        ) {
            items(messages.reversed()) { message ->
                ChatBubble(message = message)
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

@Composable
private fun ChatBubble(message: ChatMessage) {
    val isUser = message.sender == MessageSender.USER
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
    ) {
        Surface(
            color = if (isUser) 
                MaterialTheme.colorScheme.primary 
            else 
                MaterialTheme.colorScheme.surfaceVariant,
            shape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp,
                bottomStart = if (isUser) 16.dp else 4.dp,
                bottomEnd = if (isUser) 4.dp else 16.dp
            ),
            shadowElevation = 1.dp
        ) {
            Text(
                text = message.text,
                color = if (isUser) 
                    MaterialTheme.colorScheme.onPrimary 
                else 
                    MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(12.dp),
                textAlign = if (isUser) TextAlign.End else TextAlign.Start
            )
        }
    }
}
