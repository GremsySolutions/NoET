package com.example.noet.presentation.ui.screens.test

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noet.R
import com.example.noet.presentation.ui.components.Spacer16V
import com.example.noet.presentation.ui.components.Spacer8H
import com.example.noet.presentation.ui.components.Spacer8V
import com.example.noet.presentation.viewmodel.TestViewModel
import java.util.Locale

@Composable
fun TestSongScreen(
    viewModel: TestViewModel,
    modifier: Modifier = Modifier
) {
    val musicResult by viewModel.musicResult.collectAsState()
    val context = LocalContext.current

    val tts = remember {
        var textToSpeech: TextToSpeech? = null
        textToSpeech = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech?.language = Locale.US
            }
        }
        textToSpeech
    }

    DisposableEffect(Unit) {
        onDispose {
            tts?.stop()
            tts?.shutdown()
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        item {
            musicResult?.let { result ->
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = result.title,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 20.sp
                        )
                        Spacer8H()
                        IconButton(
                            onClick = {
                                tts?.let {
                                    it.language = Locale.US
                                    it.setPitch(1.2f)
                                    it.setSpeechRate(0.8f)
                                    it.speak(result.lyrics, TextToSpeech.QUEUE_FLUSH, null, null)
                                }
                            }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_volume_up),
                                contentDescription = "Play lyrics",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                    Text(
                        text = "Giai điệu: ${result.melody}",
                        fontStyle = FontStyle.Italic,
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                    Spacer16V()
                    Text(
                        text = result.lyrics,
                        color = Color.DarkGray,
                        fontSize = 16.sp,
                        lineHeight = 24.sp
                    )
                    Spacer16V()
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.ban_dich_tieng_viet),
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Spacer8H()
                        IconButton(
                            onClick = {
                                tts?.let {
                                    it.language = Locale.forLanguageTag("vi-VN")
                                    it.setPitch(1.2f)
                                    it.setSpeechRate(0.8f)
                                    it.speak(result.translateVi, TextToSpeech.QUEUE_FLUSH, null, null)
                                }
                            },

                            ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_volume_up),
                                contentDescription = "Play translation",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                    Spacer8V()
                    Text(
                        text = result.translateVi,
                        color = Color.Gray,
                        fontStyle = FontStyle.Italic,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}