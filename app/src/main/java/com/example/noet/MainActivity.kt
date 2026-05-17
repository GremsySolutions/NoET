package com.example.noet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.noet.presentation.ui.screens.home.HomeScreen
import com.example.noet.ui.theme.NoETTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoETTheme {
                MainScreen()
            }
        }
    }
}