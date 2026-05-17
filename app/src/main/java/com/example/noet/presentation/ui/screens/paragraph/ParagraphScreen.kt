package com.example.noet.presentation.ui.screens.paragraph

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noet.R
import com.example.noet.presentation.ui.components.Spacer16V
import com.example.noet.presentation.ui.components.Spacer8H
import com.example.noet.ui.theme.primaryColor

@Composable
fun ParagraphScreen(modifier: Modifier = Modifier) {
    Column (
        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Icon(
            painter = painterResource(R.drawable.ic_scan),
            contentDescription = "Scan",
            tint = primaryColor,
            modifier = Modifier
                .size(60.dp)
                .clickable {

                }
        )
        Spacer8H()
        Text(
            text = "Chọn ảnh để scan",
            fontSize = 16.sp,
            fontStyle = FontStyle.Italic,
            color = primaryColor
        )
        Spacer16V()
        CardListParagraph(
            modifier = Modifier.weight(1f)
        )
    }
}