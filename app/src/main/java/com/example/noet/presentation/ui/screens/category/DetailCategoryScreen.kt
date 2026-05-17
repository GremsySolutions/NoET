package com.example.noet.presentation.ui.screens.category

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetailCategoryScreen(
    modifier: Modifier = Modifier
) {
    val vocabFakeList = listOf(
        listOf("employee", "nhân viên", "Anh ấy là một nhân viên giỏi.", "He is a good employee."),
        listOf("project", "dự án", "Dự án này rất quan trọng.", "This project is very important."),
        listOf("deadline", "thời hạn", "Tôi phải xong trước thời hạn.", "I must finish before the deadline.")
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(vocabFakeList) { item ->
            Spacer(modifier = Modifier.height(16.dp))
            DetailItemCategory(
                word = item[0],
                meaningVi = item[1],
                exampleVi = item[2],
                exampleEn = item[3],
                onClickMore = {

                }
            )

        }
    }
}