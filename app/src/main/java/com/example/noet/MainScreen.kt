package com.example.noet

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.noet.presentation.navigation.AppNavGraph
import com.example.noet.presentation.navigation.BottomNavigation
import com.example.noet.presentation.navigation.Screen
import com.example.noet.presentation.ui.components.AppTopBar

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val showBottomBar = currentRoute in listOf(
        Screen.Home.route,
        Screen.Category.route,
        Screen.Paragraph.route
    )

    val showBackButton = currentRoute == "category_detail/{categoryId}/{categoryName}"
    val title = when (currentRoute) {
        Screen.Home.route -> "Focus Now"
        Screen.Category.route -> "Category"
        Screen.Paragraph.route -> "Paragraph"
        "category_detail/{categoryId}/{categoryName}" ->
            backStackEntry?.arguments?.getString("categoryName") ?: "Detail"
        else -> ""
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = title,
                showBackButton = showBackButton,
                onClickBack = {
                    navController.popBackStack()
                }
            )
        },
        bottomBar = {
            if (showBottomBar) {
                BottomNavigation(navController = navController)
            }
        }
    ) { paddingValues ->
        AppNavGraph(
            navController = navController,
            modifier = Modifier.padding(paddingValues),
            onTitleChanged = {}
        )
    }
}