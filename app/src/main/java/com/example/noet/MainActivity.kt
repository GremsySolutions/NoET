package com.example.noet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import com.example.noet.data.local.database.AppDatabase
import com.example.noet.presentation.ui.screens.home.HomeScreen
import com.example.noet.ui.theme.NoETTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LaunchedEffect(Unit) {
                withContext(Dispatchers.IO) {
                    val db = AppDatabase.getDatabase(applicationContext)
                    db.openHelper.writableDatabase
                }
            }
            NoETTheme {
                MainScreen()
            }
        }
    }
}