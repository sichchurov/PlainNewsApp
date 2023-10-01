package com.shchurovsi.plainnewsapp.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shchurovsi.plainnewsapp.data.local.model.ArticleDbModel

@Database(
    entities = [ArticleDbModel::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class ArticleDb : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao

    companion object {

        @Volatile
        private var instance: ArticleDb? = null
        private val LOCK = Any()
        private const val DB_NAME = "article_db.db"

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context): ArticleDb {
            return Room.databaseBuilder(
                context.applicationContext,
                ArticleDb::class.java,
                DB_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
