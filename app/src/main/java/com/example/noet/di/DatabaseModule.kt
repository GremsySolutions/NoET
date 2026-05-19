package com.example.noet.di

import android.content.Context
import com.example.noet.data.local.dao.DaoCategory
import com.example.noet.data.local.dao.DaoParagraph
import com.example.noet.data.local.dao.DaoVocabulary
import com.example.noet.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    fun provideCategoryDao(database: AppDatabase): DaoCategory {
        return database.categoryDao()
    }

    @Provides
    fun provideVocabularyDao(database: AppDatabase): DaoVocabulary {
        return database.vocabularyDao()
    }

    @Provides
    fun provideParagraphDao(database: AppDatabase): DaoParagraph {
        return database.paragraphDao()
    }
}