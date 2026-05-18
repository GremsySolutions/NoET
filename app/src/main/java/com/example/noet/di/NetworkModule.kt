package com.example.noet.di

import com.google.ai.client.generativeai.GenerativeModel
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideGenerativeModel(): GenerativeModel{
        return GenerativeModel(
            modelName = "gemini-2.5-flash",
            apiKey = "AIzaSyA7pX71s_3_lhk83APgDnY4L7FAc_ZamlY"
        )
    }

    @Provides
    @Singleton
    fun provideGson() : Gson = Gson()
}