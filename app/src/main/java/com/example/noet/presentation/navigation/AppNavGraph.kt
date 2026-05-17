package com.example.noet.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.noet.presentation.ui.screens.category.CategoryScreen
import com.example.noet.presentation.ui.screens.category.DetailCategoryScreen
import com.example.noet.presentation.ui.screens.home.HomeScreen
import com.example.noet.presentation.ui.screens.paragraph.ParagraphScreen

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

        composable(route = "category_detail/{categoryId}/{categoryName}") { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("categoryName") ?: "Detail"

            DetailCategoryScreen()
        }

        composable (route = Screen.Paragraph.route){
            ParagraphScreen()
        }

//        composable (route = Screen.History.route){  }



    }

}