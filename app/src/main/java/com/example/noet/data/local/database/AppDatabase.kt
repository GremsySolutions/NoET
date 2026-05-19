package com.example.noet.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
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
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val now = System.currentTimeMillis()
                        db.execSQL("""
                            INSERT INTO category (name, created_at, updated_at) VALUES 
                            ('General Topics', $now, $now),
                            ('Food & Drinks', $now, $now),
                            ('Travel & Tourism', $now, $now),
                            ('Family & Relationships', $now, $now),
                            ('Animals & Pets', $now, $now),
                            ('Health & Fitness', $now, $now),
                            ('Sports & Games', $now, $now),
                            ('Music & Instruments', $now, $now),
                            ('Movies & Cinema', $now, $now),
                            ('Hobbies & Interests', $now, $now),
                            ('Weather & Seasons', $now, $now),
                            ('Clothing & Fashion', $now, $now),
                            ('Human Body', $now, $now),
                            ('Jobs & Occupations', $now, $now),
                            ('Science & Research', $now, $now),
                            ('Environment & Ecology', $now, $now),
                            ('Economy & Business', $now, $now),
                            ('Politics & Government', $now, $now),
                            ('Law & Justice', $now, $now),
                            ('History & Culture', $now, $now),
                            ('Geography & Landmarks', $now, $now),
                            ('Art & Design', $now, $now),
                            ('Literature & Books', $now, $now),
                            ('Philosophy & Logic', $now, $now),
                            ('Religion & Spirituality', $now, $now),
                            ('Emotions & Feelings', $now, $now),
                            ('Personality Traits', $now, $now),
                            ('Communication & Language', $now, $now),
                            ('Transportation & Vehicles', $now, $now),
                            ('City & Urban Life', $now, $now),
                            ('Countryside & Rural Life', $now, $now),
                            ('Shopping & Consumerism', $now, $now),
                            ('Money & Finance', $now, $now),
                            ('Time & Calendars', $now, $now),
                            ('Colors & Shapes', $now, $now),
                            ('Numbers & Mathematics', $now, $now),
                            ('Tools & Equipment', $now, $now),
                            ('Materials & Textures', $now, $now),
                            ('Space & Astronomy', $now, $now),
                            ('Media & News', $now, $now),
                            ('Internet & Social Media', $now, $now),
                            ('Medicine & Pharmacy', $now, $now),
                            ('Architecture & Building', $now, $now),
                            ('Daily Routines', $now, $now),
                            ('Appliances', $now, $now),
                            ('Senses', $now, $now),
                            ('Holidays & Celebrations', $now, $now),
                            ('Workplace & Office', $now, $now),
                            ('Education & Learning', $now, $now),
                            ('Emergency & Safety', $now, $now),
                            ('Weather Phenomena', $now, $now)
                        """.trimIndent())
                    }
                })
                .addMigrations()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}