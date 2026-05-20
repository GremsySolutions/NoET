package com.example.noet.presentation.ui.screens.paragraph

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.noet.R
import androidx.compose.material3.Text
import com.example.noet.presentation.ui.components.Spacer16H
import com.example.noet.presentation.ui.components.Spacer16V
import com.example.noet.presentation.ui.components.Spacer8V
import com.example.noet.presentation.viewmodel.ParagraphViewModel
import com.example.noet.ui.theme.backgroundColor2
import com.example.noet.ui.theme.deleteColor
import com.example.noet.ui.theme.primaryColor
import java.io.File

@Composable
fun CardListParagraph(
    onParagraphClick: (Int, String)-> Unit,
    viewModel: ParagraphViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val paragraphList by viewModel.paragraph.collectAsState()
    val dateFormatter = remember { java.text.SimpleDateFormat("dd/MM/yyyy HH:mm", java.util.Locale.getDefault()) }

    var showDialog by remember { mutableStateOf(false) }
    var idToDelete by remember { mutableStateOf(-1) }

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
                    text = "Bạn có chắc chắn muốn xoá đoạn văn này không?",
                    fontSize = 16.sp
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                        viewModel.deleteParagraph(idToDelete)
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

    LazyColumn {
        items(paragraphList) { item ->
            val dateString = dateFormatter.format(java.util.Date(item.created_at))

            CardItemParagraph(
                title = item.title,
                imagePath = item.imagePath,
                dateTime = dateString,
                originalText = item.originText,
                translateText = item.translateText,
                onClick = {
                    onParagraphClick(item.id, item.title)
                },
                onDelete = {
                    showDialog = true
                    idToDelete = item.id
                },
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Composable
fun CardItemParagraph(
    title: String,
    imagePath: String,
    dateTime: String,
    originalText: String,
    translateText: String,
    onClick: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val imageFile = remember(imagePath) { File(context.filesDir, imagePath) }

    Row (
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
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.LightGray.copy(alpha = 0.3f))
        ) {
            AsyncImage(
                model = imageFile,
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.ic_scan),
                error = painterResource(R.drawable.ic_scan)
            )
        }
        Spacer16H()
        Column (
            modifier = Modifier.weight(1f)
        ){
            Text(
                text = title,
                fontSize = 16.sp,
                color = primaryColor,
                lineHeight = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer8V()
            Text(
                text = "Dịch Anh: $originalText",
                fontSize = 14.sp,
                color = Color.DarkGray,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                lineHeight = 20.sp
            )
            Spacer8V()
            Text(
                text = "Dịch Việt: $translateText",
                fontSize = 14.sp,
                color = Color.DarkGray,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                lineHeight = 20.sp
            )
            Spacer16V()
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ){
                Text(
                    text = dateTime,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Icon(
                    painter = painterResource(R.drawable.ic_delete),
                    contentDescription = "Delete",
                    tint = deleteColor,
                    modifier = Modifier
                        .clickable { onDelete() }
                )
            }
        }
    }
}


@Composable
fun DetailParagraphItem(
    originalText: String,
    translateText: String,
    imagePath: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val imageFile = remember(imagePath) { File(context.filesDir, imagePath) }

    Column(

    ) {

        Box(

        ) {
            AsyncImage(
                model = imageFile,
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.ic_scan),
                error = painterResource(R.drawable.ic_scan)
            )
        }

        Spacer16V()
        Text(
            text = "Đoạn văn bằng Tiếng Anh được tóm tắt",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = primaryColor
        )
        Spacer8V()
        Text(
            text = originalText,
            fontSize = 16.sp
        )
        Spacer16V()
        Text(
            text = "Đoạn dịch Tiếng Việt",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = primaryColor
        )
        Spacer8V()
        Text(
            text = translateText,
            fontSize = 16.sp
        )
    }
}