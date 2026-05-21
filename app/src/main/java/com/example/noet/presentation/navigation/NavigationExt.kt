package com.example.noet.presentation.navigation

import androidx.navigation.NavHostController

fun NavHostController.navigateSingleTop(
    route: String
) {
    navigate(route) {
        launchSingleTop = true
        restoreState = true
        popUpTo(graph.startDestinationId) {
            saveState = true
        }
    }
}