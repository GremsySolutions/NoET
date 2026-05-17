package com.example.noet.presentation.ui.screens.category

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CategoryScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(top = 8.dp)
    ){
        CardListCategory(
            modifier = Modifier.weight(1f),
            onCategoryClick = { categoryId, categoryName ->
                navController.navigate("category_detail/$categoryId/$categoryName")
            }
        )
    }
}