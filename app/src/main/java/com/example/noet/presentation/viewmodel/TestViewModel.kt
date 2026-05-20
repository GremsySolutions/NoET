package com.example.noet.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noet.data.local.entity.Vocabulary
import com.example.noet.domain.repository.TestPictureRepository
import com.example.noet.domain.use_case.AiPictureResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val testPictureRepository: TestPictureRepository
) : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()
    private val _paintingResult = MutableStateFlow<AiPictureResponse?>(null)
    val paintingResult = _paintingResult.asStateFlow()

    fun generatePicture() {
        viewModelScope.launch {
            _isLoading.value = true
            val result = testPictureRepository.generatePicture()
            _paintingResult.value = result
            _isLoading.value = false
        }
    }
    fun clearPaintingResult() {
        _paintingResult.value = null
    }
}