package com.example.intelliserve.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.intelliserve.ui.screens.chat.ChatScreen
import com.example.intelliserve.ui.screens.home.HomeScreen
import com.example.intelliserve.ui.screens.settings.SettingsScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable("${Screen.Chat.route}/{modelId}") { backStackEntry ->
            val modelId = backStackEntry.arguments?.getString("modelId") ?: ""
            ChatScreen(navController = navController, modelId = modelId)
        }
        composable(Screen.Settings.route) {
            SettingsScreen(navController = navController)
        }
    }
}
