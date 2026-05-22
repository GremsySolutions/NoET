package com.example.noet.presentation.ui.screens.test

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.noet.R
import com.example.noet.domain.model.LoadingType
import com.example.noet.presentation.ui.components.Spacer8V
import com.example.noet.presentation.viewmodel.TestViewModel

@Composable
fun TestScreen(
    navController: NavController,
    viewModel: TestViewModel = hiltViewModel()
) {
    val loadingType by viewModel.loadingType.collectAsState()
    val navigationRoute by viewModel.navigationRoute.collectAsState()

    LaunchedEffect(navigationRoute) {
        navigationRoute?.let { route ->
            navController.navigate(route)
            viewModel.onNavigationHandled()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        CardItemTest(
            painter = painterResource(R.drawable.ic_picture),
            title = stringResource(R.string.hoc_tu_vung_bang_tranh),
            description = stringResource(R.string.hoc_tu_vung_bang_tranh_description),
            onClick = {
                viewModel.generatePicture()
            }
        )
        if (loadingType == LoadingType.PICTURE) {
            LoadingTestItem(
                text = stringResource(R.string.dang_tao_tranh)
            )
        }
        Spacer8V()
        CardItemTest(
            painter = painterResource(R.drawable.ic_music),
            title = stringResource(R.string.am_nhac_cung_AI),
            description = stringResource(R.string.am_nhac_cung_AI_description),
            onClick = {
                viewModel.generateMusic()
            }
        )
        if (loadingType == LoadingType.MUSIC) {
            LoadingTestItem(
                text = stringResource(R.string.dang_sang_tac_bai_hat)
            )
        }
    }
}