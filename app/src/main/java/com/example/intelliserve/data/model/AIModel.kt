package com.example.intelliserve.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

data class AIModel(
    val id: String,
    val name: String,
    val description: String,
    val icon: ImageVector
)
