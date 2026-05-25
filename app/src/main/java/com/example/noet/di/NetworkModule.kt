package com.example.noet.di

import com.example.noet.BuildConfig
import com.example.noet.data.remote.api.ApiKeyManager
import com.example.noet.data.remote.api.OpenRouterApi
import com.google.ai.client.generativeai.GenerativeModel
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideGenerativeModel(
        apiKeyManager: ApiKeyManager
    ): GenerativeModel{
        return GenerativeModel(
            modelName = "gemini-2.5-flash-lite",
//            apiKey = apiKeyManager.getGeminiKey()
            apiKey = BuildConfig.API_KEY1
        )
    }

    @Provides
    fun provideOkHttp(
        apiKeyManager: ApiKeyManager
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader(
                        "Authorization",
                        "Bearer ${
                            apiKeyManager.getOpenRouterKey()
                        }"
                    )
                    .addHeader(
                        "HTTP-Referer",
                        "https://noet.com"
                    )
                    .build()

                chain.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://openrouter.ai/api/v1/")
            .client(okHttpClient)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideOpenRouterApi(
        retrofit: Retrofit
    ): OpenRouterApi {
        return retrofit.create(
            OpenRouterApi::class.java
        )
    }

    @Provides
    @Singleton
    fun provideGson() : Gson = Gson()
}