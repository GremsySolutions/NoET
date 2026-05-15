package com.example.noet.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable (route = Screen.Home.route){  }

        composable (route = Screen.Category.route){  }

        composable (route = Screen.Paragraph.route){  }

        composable (route = Screen.History.route){  }

    }

}