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
        Screen.Paragraph.route,
        Screen.Test.route
    )

    val showBackButton = currentRoute == "category_detail/{categoryId}/{categoryName}" ||
            currentRoute == "camera_x_screen" ||
            currentRoute == "paragraph_detail/{paragraphId}/{title}" ||
            currentRoute == "test_picture_detail"
    val title = when (currentRoute) {
        Screen.Home.route -> "Học cùng NoET"
        Screen.Category.route -> "Danh sách danh mục"
        Screen.Paragraph.route -> "Đoạn văn"
        Screen.Test.route -> "Các hoạt động"
        "category_detail/{categoryId}/{categoryName}" ->
            backStackEntry?.arguments?.getString("categoryName") ?: "Detail"
        "camera_x_screen" -> "Quét văn bản"
        "paragraph_detail/{paragraphId}/{title}" ->
            backStackEntry?.arguments?.getString("title") ?: "Detail"
        "test_picture_detail" -> "Bức tranh mới"
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