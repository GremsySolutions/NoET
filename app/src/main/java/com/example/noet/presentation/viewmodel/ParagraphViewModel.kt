package com.example.noet.presentation.viewmodel

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noet.domain.repository.ParagraphRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ParagraphViewModel  @Inject constructor(
    private val paragraphRepository: ParagraphRepository
): ViewModel(){

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun scanAndSave(bitmap: Bitmap, context: Context){
        viewModelScope.launch {
            _isLoading.value = true
            paragraphRepository.scanAndSave(bitmap, context)
            _isLoading.value = false
        }
    }
}