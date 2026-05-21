package com.example.noet.data.remote.api

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ApiKeyManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val prefs =
        context.getSharedPreferences(
            "api_key",
            Context.MODE_PRIVATE
        )

    fun saveGeminiKey(key: String) {
        prefs.edit()
            .putString("gemini_key", key)
            .apply()
    }

    fun getGeminiKey(): String {
        return prefs.getString(
            "gemini_key",
            ""
        ) ?: ""
    }

    fun saveOpenRouterKey(key: String) {
        prefs.edit()
            .putString("openrouter_key", key)
            .apply()
    }

    fun getOpenRouterKey(): String {
        return prefs.getString(
            "openrouter_key",
            ""
        ) ?: ""
    }
}