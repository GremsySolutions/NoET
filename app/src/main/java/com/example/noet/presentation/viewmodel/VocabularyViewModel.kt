package com.example.noet.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noet.data.local.entity.VocabularyWithCategory
import com.example.noet.domain.repository.VocabularyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.text.contains

@HiltViewModel
class VocabularyViewModel @Inject constructor(
    private val vocabularyRepository: VocabularyRepository
): ViewModel() {

    private val _vocabularies = MutableStateFlow<List<VocabularyWithCategory>>(emptyList())
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    val filteredVocabularies: StateFlow<List<VocabularyWithCategory>> = _searchQuery
        .combine(_vocabularies) { query, list ->
            if (query.isBlank()) {
                list
            } else {
                list.filter {
                    it.vocabulary.word.contains(query, ignoreCase = true) ||
                            it.vocabulary.meaningVi.contains(query, ignoreCase = true)
                }
            }
        }
        .stateIn(viewModelScope,
            SharingStarted.WhileSubscribed(5000), emptyList())

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

    fun searchQuery(newQuery: String) {
        _searchQuery.value = newQuery
    }
}