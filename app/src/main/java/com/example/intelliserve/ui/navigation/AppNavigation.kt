package com.example.intelliserve.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.intelliserve.ui.theme.IntelliserveTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    
    IntelliserveTheme {
        NavGraph(navController = navController)
    }
}
