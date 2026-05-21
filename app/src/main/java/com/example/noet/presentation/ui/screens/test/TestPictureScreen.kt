package com.example.noet.presentation.ui.screens.test

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import com.example.noet.ui.theme.primaryColor
import org.w3c.dom.Text

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

                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = result.title,
                        fontWeight = FontWeight.Bold,
                        color = primaryColor,
                        fontSize = 18.sp
                    )
                }

                Spacer16V()

                AsyncImage(
                    model = result.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp)
                )

                Spacer16V()
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "* Danh sách từ vựng",
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${result.words.size} từ được tìm thấy",
                        color = primaryColor
                    )
                }

                Spacer8V()

                result.words.forEach { item ->
                    VocabularyPaintingItem(item.phonetic,item.english, item.vietnamese)
                    Spacer8V()
                }
            }
        }
    }

}