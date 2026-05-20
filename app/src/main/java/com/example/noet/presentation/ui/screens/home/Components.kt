package com.example.noet.presentation.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.noet.R
import com.example.noet.presentation.ui.components.Spacer16H
import com.example.noet.presentation.ui.components.Spacer16V
import com.example.noet.presentation.ui.components.Spacer8H
import com.example.noet.presentation.ui.components.Spacer8V
import com.example.noet.presentation.viewmodel.VocabularyViewModel
import com.example.noet.ui.theme.backgroundColor
import com.example.noet.ui.theme.backgroundColor2
import com.example.noet.ui.theme.deleteColor
import com.example.noet.ui.theme.primaryColor
import com.example.noet.ui.theme.textColor

@Composable
fun CardListHome(
    viewModel: VocabularyViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    var showDialog by remember { mutableStateOf(false) }
    var itemToDelete by remember { mutableStateOf("") }
    var idToDelete by remember { mutableStateOf(-1) }
    val vocabularies by viewModel.vocabularies.collectAsState()

    if (showDialog) {
        AlertDialog(
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            ),
            onDismissRequest = {
                showDialog = false
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp),
            containerColor = backgroundColor2,
            title = {
                Text(
                    text = "Xác nhận xoá",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = "Bạn có chắc chắn muốn xoá \"$itemToDelete\" không?",
                    fontSize = 16.sp
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                        viewModel.deleteVocabulary(idToDelete)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = primaryColor
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Xoá ngay", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        )
    }
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
    ){
        items(vocabularies) { word ->
            CardItemHome(
                word = word.vocabulary.word,
                category = word.category.name,
                meaningVi = word.vocabulary.meaningVi,
                exampleVi = word.vocabulary.exampleVi,
                exampleEn = word.vocabulary.exampleEn,
                onClickDelete = {
                    itemToDelete = word.vocabulary.word
                    showDialog = true
                    idToDelete = word.vocabulary.id
                },
                onClickMore = {},
                onClickFavorite = {}
            )
            Spacer16V()
        }
    }
}

@Composable
fun CardItemHome(
    word: String,
    category: String,
    meaningVi: String,
    exampleVi: String,
    exampleEn: String,
    onClickDelete: () -> Unit,
    onClickMore: () -> Unit,
    onClickFavorite: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(IntrinsicSize.Min)
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(12.dp),
                clip = false
            )
            .clip(RoundedCornerShape(12.dp))
            .background(color = Color.White)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = word,
                color = textColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer16V()
            Row() {
                Text(
                    text = category,
                    color = primaryColor,
                    fontSize = 16.sp,
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "|",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = meaningVi,
                    color = textColor,
                    fontSize = 16.sp,
                )
            }
            Spacer8V()
            Text(
                text = "Ví dụ: $exampleVi"
            )
            Spacer8V()
            Text(
                text ="Ex: $exampleEn"
            )
        }
        Spacer16H()
        Column (
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.End
        ){
            Row {
                Icon(
                    painter = painterResource(R.drawable.ic_heart_empty),
                    contentDescription = "Favorite",
                    tint = primaryColor,
                    modifier = Modifier
                        .clickable{
                            onClickFavorite()
                        }
                )
                Spacer8H()
                Icon(
                    painter = painterResource(R.drawable.ic_more_vert),
                    contentDescription = "More",
                    tint = primaryColor,
                    modifier = Modifier
                        .clickable{
                            onClickMore()
                        }
                )
            }
            Icon(
                painter = painterResource(R.drawable.ic_delete),
                contentDescription = "Delete",
                tint = deleteColor,
                modifier = Modifier.clickable {
                    onClickDelete()
                }
            )
        }
    }
}

@Composable
fun AddVocabularyDialog(
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit,
    viewModel: VocabularyViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }
    val errorMessage by viewModel.error.collectAsState()

    val inputError = remember(text) {
        when {
            text.isEmpty() -> null
            text.any { it.isDigit() } -> "Từ vựng không được chứa chữ số"
            text.length < 2 -> "Từ vựng quá ngắn"
            !text.all { it.isLetter() || it == '-' || it.isWhitespace() } -> "Từ vựng không được chứa ký tự đặc biệt"
            else -> null
        }
    }

    val displayError = inputError ?: errorMessage
    AlertDialog(
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
        onDismissRequest = {
            if (!loading){
                viewModel.clear()
                onDismiss()
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        containerColor = backgroundColor2,
        title = {
            Text(
                text = "Thêm từ vựng mới",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = primaryColor
            )
        },
        text = {
            Column {
                OutlinedTextField(
                    value = text,
                    onValueChange = {
                        text = it
                        if (errorMessage != null) {
                            viewModel.clear()
                        }
                    },
                    placeholder = {
                        Text(
                            text = "Nhập từ vựng mới"
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    enabled = !loading,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = backgroundColor,
                        unfocusedBorderColor = backgroundColor2,
                        errorBorderColor = Color.Red,
                    )
                )

                if (displayError != null) {
                    Text(
                        text = displayError!!,
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                if(loading) {
                    Spacer8V()
                    LinearProgressIndicator(
                        modifier = Modifier.fillMaxWidth(),
                        color = primaryColor
                    )
                    Text(
                        text = "Gemini đang dịch và tạo ví dụ...",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    loading = true
                    viewModel.translateAndSave(text) {
                        loading = false
                        if (viewModel.error.value == null ) onDismiss()
                    }
                },
                enabled = text.isNotBlank() && !loading,
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryColor
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    if (loading) "Đang xử lý... " else "Dịch từ", color = Color.White
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    viewModel.clear()
                    onDismiss()
                },
                enabled = !loading
            ) {
                Text("Huỷ", color = Color.Gray)
            }
        }
    )
}
