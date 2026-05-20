package com.example.noet.presentation.ui.screens.test

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.noet.R
import com.example.noet.presentation.ui.components.Spacer16V

@Composable
fun TestScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        CardItemTest(
            painter = painterResource(R.drawable.ic_picture),
            title = "Học từ vựng bằng tranh",
            description = "Khám phá từ vựng tiếng Anh qua hình ảnh tương tác được hỗ trợ bởi AI",
            onClick = {}

        )
        Spacer16V()
        CardItemTest(
            painter = painterResource(R.drawable.ic_music),
            title = "Âm nhạc cùng AI",
            description = "Cá nhân hoá bài hát theo trình độ để học từ vựng và phát âm dễ nhớ hơn",
            onClick = {}

        )
    }
}