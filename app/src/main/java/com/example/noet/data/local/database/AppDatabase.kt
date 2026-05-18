package com.example.noet.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noet.data.local.dao.DaoCategory
import com.example.noet.data.local.dao.DaoParagraph
import com.example.noet.data.local.dao.DaoVocabulary
import com.example.noet.data.local.entity.Category
import com.example.noet.data.local.entity.Paragraph
import com.example.noet.data.local.entity.Vocabulary

@Database(
    entities = [
        Category::class,
        Vocabulary::class,
        Paragraph::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun categoryDao(): DaoCategory
    abstract fun vocabularyDao(): DaoVocabulary
    abstract fun paragraphDao(): DaoParagraph

    companion object{
        fun getDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            ).addMigrations().build()
        }
    }
}