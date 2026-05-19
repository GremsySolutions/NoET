package com.example.noet.presentation.navigation

sealed  class Screen (
    val route: String
) {
    data object Home : Screen("Home")
    data object Category : Screen("Category")
    data object Paragraph : Screen("Paragraph")
    data object Test : Screen("Test")
}