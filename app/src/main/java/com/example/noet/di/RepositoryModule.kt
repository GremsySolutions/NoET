package com.example.noet.di

import com.example.noet.data.local.database.AppDatabase
import com.example.noet.data.repository.VocabularyRepositoryImpl
import com.example.noet.domain.repository.AiRepository
import com.example.noet.domain.repository.VocabularyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideVocabularyRepository(
        database: AppDatabase,
        aiRepository: AiRepository
    ): VocabularyRepository {
        return VocabularyRepositoryImpl(database.vocabularyDao(), aiRepository = aiRepository)
    }
}