package com.shchurovsi.plainnewsapp.data.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shchurovsi.plainnewsapp.data.database.mapper.Converter
import com.shchurovsi.plainnewsapp.data.database.model.ArticleModel

@Database(
    entities = [ArticleModel::class],
    version = 1
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
            ).build()
        }
    }
}
