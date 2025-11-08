package com.example.intelliserve.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Chat : Screen("chat") {
        fun createRoute(modelId: String) = "chat/$modelId"
    }
    object Settings : Screen("settings")
}
