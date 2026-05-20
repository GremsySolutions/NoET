package com.example.noet.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.noet.presentation.ui.screens.category.CategoryScreen
import com.example.noet.presentation.ui.screens.category.DetailCategoryScreen
import com.example.noet.presentation.ui.screens.home.HomeScreen
import com.example.noet.presentation.ui.screens.paragraph.CameraXScreen
import com.example.noet.presentation.ui.screens.paragraph.DetailParagraphScreen
import com.example.noet.presentation.ui.screens.paragraph.ParagraphScreen
import com.example.noet.presentation.ui.screens.test.TestPictureScreen
import com.example.noet.presentation.ui.screens.test.TestScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    onTitleChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable (route = Screen.Home.route){
            HomeScreen()
        }

        composable (route = Screen.Category.route){
            CategoryScreen(navController = navController)
        }

        composable(route = "category_detail/{categoryId}/{categoryName}") {
            DetailCategoryScreen()
        }

        composable (route = Screen.Paragraph.route){
            ParagraphScreen(navController = navController)
        }
        composable(route = "camera_x_screen") {
            CameraXScreen(
                onImageCaptured = { uri ->
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("captured_uri", uri.toString())
                    navController.popBackStack()
                },
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }
        composable(route = "paragraph_detail/{paragraphId}/{title}") {
            DetailParagraphScreen()
        }
        composable (route = Screen.Test.route){
            TestScreen(navController = navController)
        }
        composable(route = "test_picture_detail") {
            TestPictureScreen()
        }
    }

}