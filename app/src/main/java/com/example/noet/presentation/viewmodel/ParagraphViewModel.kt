package com.example.noet.presentation.viewmodel

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noet.data.local.entity.Paragraph
import com.example.noet.domain.repository.ParagraphRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ParagraphViewModel  @Inject constructor(
    private val paragraphRepository: ParagraphRepository,
    savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _paragraph = MutableStateFlow<List<Paragraph>>(emptyList())
    val paragraph: StateFlow<List<Paragraph>> = _paragraph.asStateFlow()
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _detailParagraph = MutableStateFlow<Paragraph?>(null)
    val detailParagraph = _detailParagraph.asStateFlow()


    private val paragraphId: String? = savedStateHandle["paragraphId"]

    init {
        getAllParagraph()
        paragraphId?.let { id ->
            id.toIntOrNull()?.let { id ->
                getParagrapById(id)
            }
        }
    }
    fun scanAndSave(bitmap: Bitmap, context: Context){
        viewModelScope.launch {
            _isLoading.value = true
            paragraphRepository.scanAndSave(bitmap, context)
            _isLoading.value = false
        }
    }

    fun getAllParagraph() {
        viewModelScope.launch {
            paragraphRepository.getAllParagraph().collect { list ->
                _paragraph.value = list
            }
        }
    }
    fun deleteParagraph(id: Int) {
        viewModelScope.launch {
            paragraphRepository.deleteParagraph(id)
        }
    }

    fun getParagrapById(id: Int) {
        viewModelScope.launch {
            _detailParagraph.value = paragraphRepository.getParagraphById(id)
        }
    }
}