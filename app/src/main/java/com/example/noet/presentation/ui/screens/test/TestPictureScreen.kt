package com.example.noet.presentation.ui.screens.test

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.noet.presentation.ui.components.Spacer16V
import com.example.noet.presentation.ui.components.Spacer8V
import com.example.noet.presentation.viewmodel.TestViewModel

@Composable
fun TestPictureScreen(
    viewModel: TestViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val paintingResult by viewModel.paintingResult.collectAsState()
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        item {
            paintingResult?.let { result ->

                Text(
                    text = result.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                Spacer16V()

                AsyncImage(
                    model = result.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp)
                )

                Spacer16V()

                result.words.forEach { item ->
                    VocabularyPaintingItem(item.english, item.vietnamese)
                    Spacer8V()
                }
            }
        }
    }

}