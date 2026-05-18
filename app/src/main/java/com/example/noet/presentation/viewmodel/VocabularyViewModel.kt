package com.example.noet.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noet.data.local.entity.Vocabulary
import com.example.noet.domain.repository.AiRepository
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

    private val _vocabularies = MutableStateFlow<List<Vocabulary>>(emptyList())
    val vocabularies: StateFlow<List<Vocabulary>> = _vocabularies.asStateFlow()
    fun translateAndSave(word: String, categoryId: Int, onComplete: () -> Unit) {
        viewModelScope.launch {
            val success = vocabularyRepository.translateAndSave(word, categoryId)
            if (success) {
                Log.d("AI_DEBUG", "Luu thanh cong")
            }else{
                Log.d("AI_DEBUG", "Loi dich")
            }
            onComplete()
        }
    }


}