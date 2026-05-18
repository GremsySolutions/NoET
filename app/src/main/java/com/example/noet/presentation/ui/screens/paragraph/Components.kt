package com.example.noet.presentation.ui.screens.paragraph

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noet.R
import com.example.noet.presentation.ui.components.Spacer16H
import com.example.noet.ui.theme.deleteColor

@Composable
fun CardListParagraph(modifier: Modifier = Modifier) {
    val paragraphs = listOf(
        Triple("Đoạn văn 1", "20/05/2024 - 10:45 AM", "Cuộc sống là một hành trình đầy những trải nghiệm và bài học..."),
        Triple("Đoạn văn 2", "21/05/2024 - 11:30 AM", "Học tiếng Anh giúp bạn mở mang kiến thức và kết nối thế giới..."),
    )
    LazyColumn {
        items(paragraphs) { items ->
            val (title, dateTime, previewText) = items
            CardItemParagraph(
                title = title,
                dateTime = dateTime,
                previewText = previewText,
                onClick = {  },
                onDelete = {  },
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Composable
fun CardItemParagraph(
    title: String,
    dateTime: String,
    previewText: String,
    onClick: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(12.dp),
                clip = false
            )
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .clickable(onClick = onClick)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_scan),
            contentDescription = "Paragraph Image",
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.LightGray.copy(alpha = 0.3f))
        )
        Spacer16H()
        Column (
            modifier = Modifier.weight(1f)
        ){
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ){
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Icon(
                    painter = painterResource(R.drawable.ic_delete),
                    contentDescription = "Delete",
                    tint = deleteColor,
                    modifier = Modifier
                        .clickable { onDelete() }
                )
            }

            Text(
                text = dateTime,
                fontSize = 14.sp,
                color = Color.Gray
            )
            Text(
                text = previewText,
                fontSize = 14.sp,
                color = Color.DarkGray,
                maxLines = 2,
                lineHeight = 20.sp
            )
        }
    }
}