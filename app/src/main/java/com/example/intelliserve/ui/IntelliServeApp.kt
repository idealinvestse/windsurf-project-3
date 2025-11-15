package com.example.intelliserve.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.intelliserve.ui.chat.ChatScreen
import com.example.intelliserve.ui.chat.ChatViewModel
import com.example.intelliserve.ui.models.AIModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntelliServeApp() {
    val navController = rememberNavController()
    
    Scaffold { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "chat",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("chat") {
                val viewModel: ChatViewModel = hiltViewModel()
                val uiState by viewModel.uiState.collectAsState()
                
                ChatScreen(
                    uiState = uiState,
                    onSendMessage = viewModel::sendMessage,
                    onModelSelected = viewModel::selectModel,
                    onClearChat = viewModel::clearChat,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
