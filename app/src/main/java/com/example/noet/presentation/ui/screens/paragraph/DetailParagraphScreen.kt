package com.example.noet.presentation.ui.screens.paragraph

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.noet.presentation.viewmodel.ParagraphViewModel

@Composable
fun DetailParagraphScreen(
    viewModel: ParagraphViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val paragraph by viewModel.detailParagraph.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        paragraph?.let { item ->
            DetailParagraphItem(
                originalText = item.originText,
                translateText = item.translateText,
                imagePath = item.imagePath
            )
        }
    }
}