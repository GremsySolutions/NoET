package com.example.noet.di

import com.example.noet.data.local.dao.DaoCategory
import com.example.noet.data.local.dao.DaoParagraph
import com.example.noet.data.local.dao.DaoVocabulary
import com.example.noet.data.local.database.AppDatabase
import com.example.noet.data.repository.CategoryRepositoryImpl
import com.example.noet.data.repository.ParagraphRepositoryImpl
import com.example.noet.data.repository.TestPictureRepositoryImpl
import com.example.noet.data.repository.VocabularyRepositoryImpl
import com.example.noet.domain.repository.AiRepository
import com.example.noet.domain.repository.CategoryRepository
import com.example.noet.domain.repository.ParagraphRepository
import com.example.noet.domain.repository.TestPictureRepository
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
        categoryDao: DaoCategory,
        aiRepository: AiRepository
    ): VocabularyRepository {
        return VocabularyRepositoryImpl(database.vocabularyDao(),categoryDao, aiRepository = aiRepository)
    }

    @Provides
    @Singleton
    fun provideCategoryRepository (
        categoryDao: DaoCategory
    ): CategoryRepository{
        return CategoryRepositoryImpl(categoryDao)
    }
    @Provides
    @Singleton
    fun provideParagraphRepository(
        paragraphDao: DaoParagraph,
        categoryDao: DaoCategory,
        aiRepository: AiRepository
    ): ParagraphRepository {
        return ParagraphRepositoryImpl(paragraphDao, categoryDao,aiRepository)
    }

    @Provides
    @Singleton
    fun provideTestRepository(
       vocabularyDao: DaoVocabulary,
       aiRepository: AiRepository
    ): TestPictureRepository {
        return TestPictureRepositoryImpl(vocabularyDao, aiRepository)
    }
}