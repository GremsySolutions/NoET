package com.example.noet.presentation.ui.screens.test

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.noet.R
import com.example.noet.domain.model.LoadingType
import com.example.noet.presentation.ui.components.Spacer8V
import com.example.noet.presentation.viewmodel.TestViewModel

@Composable
fun TestScreen(
    navController: NavController,
    viewModel: TestViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val loadingType by viewModel.loadingType.collectAsState()
    val navigationRoute by viewModel.navigationRoute.collectAsState()

    LaunchedEffect(navigationRoute) {
        navigationRoute?.let { route ->
            navController.navigate(route)
            viewModel.onNavigationHandled()
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

        if (loadingType == LoadingType.PICTURE) {
            LoadingTestItem(
                text = "Đang dùng từ vựng để tạo ảnh"
            )
        }

        Spacer8V()

        CardItemTest(
            painter = painterResource(R.drawable.ic_music),
            title = "Âm nhạc cùng AI",
            description = "Cá nhân hoá bài hát theo trình độ để học từ vựng và phát âm dễ nhớ hơn",
            onClick = {
                viewModel.generateMusic()
            }
        )

        if (loadingType == LoadingType.MUSIC) {
            LoadingTestItem(
                text = "Đang sáng tác bài hát bằng AI"
            )
        }
    }
}