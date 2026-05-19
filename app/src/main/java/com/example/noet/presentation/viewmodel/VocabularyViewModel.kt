package com.example.noet.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noet.data.local.entity.Vocabulary
import com.example.noet.data.local.entity.VocabularyWithCategory
import com.example.noet.domain.repository.CategoryRepository
import com.example.noet.domain.repository.VocabularyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VocabularyViewModel @Inject constructor(
    private val vocabularyRepository: VocabularyRepository
): ViewModel() {

    private val _vocabularies = MutableStateFlow<List<VocabularyWithCategory>>(emptyList())
    val vocabularies: StateFlow<List<VocabularyWithCategory>> = _vocabularies.asStateFlow()
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()
    init {
        getAllVocabularies()
    }
    fun getAllVocabularies() {
        viewModelScope.launch {
            vocabularyRepository.getAllVocabularyWithCategory().collect { list ->
                _vocabularies.value = list
            }
        }
    }

    fun getVocabulariesByCategory(categoryId: Int) {
        viewModelScope.launch {
            vocabularyRepository.getVocabularyByCategoryId(categoryId).collect { list ->

            }
        }
    }

    fun translateAndSave(word: String, onComplete: () -> Unit) {
        viewModelScope.launch {
            val success = vocabularyRepository.translateAndSave(word)
            if (success) {
                Log.d("AI_DEBUG", "Luu thanh cong")
            }else{
                _error.value = "Hệ thống đang bận hoặc hết lượt dùng thử. Vui lòng đợi 1 phút và thử lại!"
            }
            onComplete()
        }
    }

    fun deleteVocabulary(id: Int) {
        viewModelScope.launch {
            vocabularyRepository.deleteVocabulary(id)
        }
    }

    fun clear() {
        _error.value = null
    }
}