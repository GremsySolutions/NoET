package com.example.noet.presentation.ui.screens.test

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noet.R
import com.example.noet.presentation.ui.components.Spacer16H
import com.example.noet.presentation.ui.components.Spacer8V
import com.example.noet.ui.theme.primaryColor

@Composable
fun CardItemTest(
    painter: Painter,
    title: String,
    description: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
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
    ) {
        Box(
            modifier = modifier
                .size(60.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(primaryColor.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ){
            Image(
                painter = painter,
                contentDescription = "",
            )
        }
        Spacer16H()
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                color = primaryColor,
                fontWeight = FontWeight.Bold
            )
            Spacer8V()
            Text(
                text = description,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun VocabularyPaintingItem(
    english: String,
    vietnamese: String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = english,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = vietnamese,
                color = Color.Gray
            )
        }

        IconButton(
            onClick = {

            }
        ) {

            Icon(
                painter = painterResource(R.drawable.ic_volume_up),
                contentDescription = null,
                tint = primaryColor
            )
        }
    }
}