package com.example.noet.presentation.ui.screens.test

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.noet.R
import com.example.noet.presentation.ui.components.Spacer16V
import com.example.noet.presentation.ui.components.Spacer8V
import com.example.noet.presentation.viewmodel.TestViewModel
import com.example.noet.ui.theme.primaryColor

@Composable
fun TestScreen(
    navController: NavController,
    viewModel: TestViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val loading by viewModel.isLoading.collectAsState()
    val paintingResult by viewModel.paintingResult.collectAsState()

    LaunchedEffect(paintingResult) {
        if (paintingResult != null) {
            navController.navigate("test_picture_detail")
            viewModel.clearPaintingResult()
        }
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        CardItemTest(
            painter = painterResource(R.drawable.ic_picture),
            title = "Học từ vựng bằng tranh",
            description = "Khám phá từ vựng tiếng Anh qua hình ảnh tương tác được hỗ trợ bởi AI",
            onClick = {
                viewModel.generatePicture()
            }
        )

        Spacer16V()

        if (loading) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                CircularProgressIndicator()
                Text(
                    text = "Đang tạo ảnh",
                    color = primaryColor,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        CardItemTest(
            painter = painterResource(R.drawable.ic_music),
            title = "Âm nhạc cùng AI",
            description = "Cá nhân hoá bài hát theo trình độ để học từ vựng và phát âm dễ nhớ hơn",
            onClick = {}
        )
    }
}