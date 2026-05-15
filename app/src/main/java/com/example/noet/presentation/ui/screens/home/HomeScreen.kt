package com.example.noet.presentation.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.noet.presentation.navigation.AppNavGraph
import com.example.noet.presentation.navigation.BottomNavigation
import com.example.noet.presentation.ui.components.AppTopBar
import com.example.noet.presentation.ui.components.Search
import com.example.noet.ui.theme.backgroundPrimary

@Composable
fun HomeScreen(

) {
    val navController = rememberNavController()

    Scaffold (
        topBar = {
            AppTopBar(
                title = "Focus Now",
                showBackButton = false,
                onClickBack = {}
            )
        },
        bottomBar = {
            BottomNavigation(
                navController = navController
            )
        },
        containerColor = backgroundPrimary
    ){paddingValues->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
        ) {
            Search(
                query = "",
                onQueryChange = {},
                onSearch = {},
                active = false,
                onActiveChange = {}
            )

            AppNavGraph(
                navController = navController,
                modifier = Modifier.padding(paddingValues)
            )
        }

    }
}

