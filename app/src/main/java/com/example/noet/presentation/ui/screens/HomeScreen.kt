package com.example.noet.presentation.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.noet.presentation.navigation.AppNavGraph
import com.example.noet.presentation.navigation.BottomNavigation

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    Scaffold (
        bottomBar = {
            BottomNavigation(
                navController = navController
            )
        }
    ){paddingValues->
        AppNavGraph(
            navController = navController,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

