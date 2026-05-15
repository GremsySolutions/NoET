package com.example.noet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.noet.presentation.navigation.AppNavGraph
import com.example.noet.presentation.navigation.BottomNavigation
import com.example.noet.presentation.navigation.Screen
import com.example.noet.presentation.ui.screens.HomeScreen
import com.example.noet.ui.theme.NoETTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoETTheme {
                HomeScreen()
            }
        }
    }
}