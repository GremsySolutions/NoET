package com.example.noet.presentation.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.example.noet.R
import com.example.noet.presentation.ui.components.Spacer16H
import com.example.noet.presentation.ui.components.Spacer16V
import com.example.noet.presentation.ui.components.Spacer8H
import com.example.noet.presentation.ui.components.Spacer8V
import com.example.noet.ui.theme.primaryColor
import com.example.noet.ui.theme.textColor

@Composable
fun CardListHome(modifier: Modifier = Modifier) {

    LazyColumn {

    }
}

@Composable
fun CardItemHome(
    word: String,
    category: String,
    meaningVi: String,
    meaningEn: String,
    exampleVi: String,
    exampleEn: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable(
                onClick = onClick
            )
    ) {
        Image(
            painter = painterResource(R.drawable.ic_paragraph),
            contentDescription = null
        )
        Spacer16H()
        Column() {
            Text(
                text = word,
                color = textColor,
                fontSize = 18.sp,
            )
            Spacer16V()
            Row() {
                Text(
                    text = category,
                    color = primaryColor,
                    fontSize = 16.sp,
                )
                Spacer8H()
                Text(
                    text = meaningVi,
                    color = textColor,
                    fontSize = 16.sp,
                )
            }
            Spacer8V()
            Text(
                text = exampleVi
            )
            Spacer8V()
            Text(
                text = " "
            )
        }
        Spacer16H()
    }
}