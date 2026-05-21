package com.example.noet.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noet.domain.model.LoadingType
import com.example.noet.domain.repository.TestPictureRepository
import com.example.noet.domain.use_case.AiMusicResponse
import com.example.noet.domain.use_case.AiPictureResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val testPictureRepository: TestPictureRepository
) : ViewModel() {
    private val _loadingType = MutableStateFlow(LoadingType.NONE)

    val loadingType = _loadingType.asStateFlow()
    private val _paintingResult = MutableStateFlow<AiPictureResponse?>(null)
    val paintingResult = _paintingResult.asStateFlow()
    private val _musicResult = MutableStateFlow<AiMusicResponse?>(null)
    val musicResult = _musicResult.asStateFlow()
    private val _navigationRoute = MutableStateFlow<String?>(null)
    val navigationRoute = _navigationRoute.asStateFlow()

    fun onNavigationHandled(){
        _navigationRoute.value = null
    }
    fun generatePicture() {
        viewModelScope.launch {
            _loadingType.value = LoadingType.PICTURE
            val result = testPictureRepository.generatePicture()
            if (result != null) {
                _paintingResult.value = result
                _navigationRoute.value = "test_picture_detail"
            }
            _loadingType.value = LoadingType.NONE
        }
    }

    fun generateMusic() {
        viewModelScope.launch {
            _loadingType.value = LoadingType.MUSIC
            val result = testPictureRepository.generateMusicSong()
            if (result != null) {
                _musicResult.value = result
                _navigationRoute.value = "test_music_detail"
            }
            _loadingType.value = LoadingType.NONE
        }
    }


}