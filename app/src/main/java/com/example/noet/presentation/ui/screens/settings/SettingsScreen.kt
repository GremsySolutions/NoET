package com.example.noet.presentation.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.noet.R
import com.example.noet.presentation.navigation.Screen
import com.example.noet.presentation.ui.components.Spacer16V
import com.example.noet.presentation.ui.components.Spacer8H
import com.example.noet.presentation.ui.components.Spacer8V
import com.example.noet.presentation.viewmodel.SettingViewModel

@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingViewModel = hiltViewModel()
) {
    val loading by viewModel.loading.collectAsState()
    val navigationHome by viewModel.navigationRoute.collectAsState()

    var geminiKey by remember {
        mutableStateOf("")
    }

    var openAiKey by remember {
        mutableStateOf("")
    }

    LaunchedEffect(navigationHome) {
        navigationHome?.let {
            navController.navigate(Screen.Home.route)
            viewModel.onNavigationHandled()
        }
    }
    LazyColumn (
        modifier = Modifier.padding(top = 16.dp)
    ) {
        item {

            CardItemSettings(
                icon = painterResource(R.drawable.gemini),
                title = stringResource(R.string.title_gemini_key),
                description = stringResource(R.string.gemini_key_description),
                modelName = "GEMINI",
                textValue = geminiKey,
                onValueChange = {
                    geminiKey = it
                },
                note = stringResource(R.string.gemini_key_note),
                url = "https://aistudio.google.com/app/apikey"
            )

            Spacer16V()

            CardItemSettings(
                icon = painterResource(R.drawable.openai),
                title = stringResource(R.string.title_openai_key),
                description = stringResource(R.string.openai_key_description),
                modelName = "OPEN AI",
                textValue = openAiKey,
                onValueChange = {
                    openAiKey = it
                },
                note = stringResource(R.string.openai_key_note),
                url = "https://platform.openai.com/api-keys"
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        viewModel.saveKeys(
                            geminiKey,
                            openAiKey
                        )
                    },
                    modifier = Modifier
                        .padding(16.dp),
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    contentPadding = PaddingValues(
                        vertical = 14.dp,
                        horizontal = 32.dp
                    )
                ) {
                    Text(
                        text = stringResource(R.string.cap_nhat_key),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }
    }
    if (loading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.4f))
                .clickable(enabled = false) {},
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    strokeWidth = 4.dp
                )
                Spacer16V()
                Text(
                    text = stringResource(R.string.dang_luu_lai),
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun CardItemSettings(
    icon: Painter,
    title: String,
    description: String,
    modelName: String,
    textValue: String,
    onValueChange: (String) -> Unit,
    note: String,
    url: String
) {
    val uriHandler = LocalUriHandler.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Row(
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = icon,
                        contentDescription = null
                    )
                }
                Spacer8H()
                Column {

                    Text(
                        text = title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer8V()

                    Text(
                        text = description,
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
            }
            IconButton(
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_more_vert),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }

        Spacer16V()
        Text(
            text = "NHẬP ${modelName.uppercase()} API KEY",
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 14.sp,
            letterSpacing = 1.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer8V()

        OutlinedTextField(
            value = textValue,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = stringResource(R.string.nhap_key_cua_ban)
                )
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                errorBorderColor = MaterialTheme.colorScheme.error,
            )
        )

        Spacer16V()

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(18.dp)
            )

            Spacer8H()

            Text(
                text = note,
                color = Color.Gray,
                fontSize = 13.sp
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = stringResource(R.string.truy_cap_tai_day),
                color = MaterialTheme.colorScheme.secondary,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .clickable {
                        uriHandler.openUri(url)
                    }
            )
        }
    }
}