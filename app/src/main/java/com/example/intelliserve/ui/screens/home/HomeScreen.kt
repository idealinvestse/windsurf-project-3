package com.example.intelliserve.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.intelliserve.data.model.AIModel
import com.example.intelliserve.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val aiModels = remember {
        listOf(
            AIModel(
                id = "gpt-4",
                name = "GPT-4",
                description = "Most capable model, great for complex tasks",
                icon = Icons.Default.SmartToy
            ),
            AIModel(
                id = "claude-2",
                name = "Claude 2",
                description = "Balanced performance and helpfulness",
                icon = Icons.Default.Psychology
            ),
            AIModel(
                id = "llama-2",
                name = "Llama 2",
                description = "Open source model by Meta",
                icon = Icons.Default.DeveloperMode
            ),
            AIModel(
                id = "palm-2",
                name = "PaLM 2",
                description = "Google's advanced language model",
                icon = Icons.Default.AutoAwesome
            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Intelliserve") },
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.Settings.route) }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "Select an AI Model",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(aiModels) { model ->
                    AIModelItem(
                        model = model,
                        onClick = { navController.navigate("${Screen.Chat.route}/${model.id}") }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AIModelItem(model: AIModel, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = model.icon,
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = model.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = model.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
