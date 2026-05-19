package com.example.noet.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noet.data.local.entity.CategoryWithVocabularies
import com.example.noet.domain.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val _categories = MutableStateFlow<List<CategoryWithVocabularies>>(emptyList())
    val categories: StateFlow<List<CategoryWithVocabularies>> = _categories.asStateFlow()

    private val _categoryDetail = MutableStateFlow<CategoryWithVocabularies?>(null)
    val categoryDetail: StateFlow<CategoryWithVocabularies?> = _categoryDetail.asStateFlow()

    private val categoryId: String? = savedStateHandle["categoryId"]

    init {
        getAllCategories()
        categoryId?.let { id ->
            id.toIntOrNull()?.let { id ->
                getCategoryWithVocabularies(id)
            }
        }
    }

    fun getAllCategories() {
        viewModelScope.launch {
            categoryRepository.getCategoryWithVocabularies().collect {list ->
                _categories.value = list
            }
        }
    }

    fun getCategoryWithVocabularies(id: Int) {
        viewModelScope.launch {
            categoryRepository.getCategoryIdWithVocabularies(id).collect {
                _categoryDetail.value = it
            }
        }
    }
}