package com.example.noet.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noet.data.remote.api.ApiKeyManager
import com.example.noet.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    val apiKeyManager: ApiKeyManager
): ViewModel(){

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()
    private val _navigationRoute = MutableStateFlow<String?>(null)
    val navigationRoute = _navigationRoute.asStateFlow()
    fun onNavigationHandled(){
        _navigationRoute.value = null
    }

    fun saveKeys(
        geminiKey: String,
        openRouterKey: String
    ) {
        viewModelScope.launch {
            _loading.value = true
            apiKeyManager.saveGeminiKey(
                geminiKey
            )

            apiKeyManager.saveOpenRouterKey(
                openRouterKey
            )
            delay(1000)
            _loading.value = false
            _navigationRoute.value = Screen.Home.route
        }
    }
}