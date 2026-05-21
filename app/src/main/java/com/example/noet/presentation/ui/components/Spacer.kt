package com.example.noet.presentation.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Spacer8V() {
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun Spacer16V() {
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun Space32V() {
    Spacer(modifier = Modifier.height(32.dp))
}

@Composable
fun Spacer8H() {
    Spacer(modifier = Modifier.width(8.dp))
}

@Composable
fun Spacer12H() {
    Spacer(modifier = Modifier.width(12.dp))
}

@Composable
fun Spacer16H() {
    Spacer(modifier = Modifier.width(16.dp))
}

@Composable
fun Space32H() {
    Spacer(modifier = Modifier.width(32.dp))
}
