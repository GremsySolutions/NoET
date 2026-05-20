package com.example.noet.presentation.ui.screens.category

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.noet.presentation.viewmodel.CategoryViewModel

@Composable
fun DetailCategoryScreen(
    viewModel: CategoryViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val vocabularies by viewModel.categoryDetail.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        vocabularies?.vocabularies?.let {list ->
            items(list) { item ->
                Spacer(modifier = Modifier.height(16.dp))
                DetailItemCategory(
                    word = item.word,
                    meaningVi = item.meaningVi,
                    exampleVi = item.exampleVi,
                    exampleEn = item.exampleEn
                )
            }
        }

    }
}