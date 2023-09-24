package com.shchurovsi.plainnewsapp.data.database.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shchurovsi.plainnewsapp.data.database.model.ArticleDbModel

@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles")
    fun getSavedArticles(): LiveData<List<ArticleDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(articleDbModel: ArticleDbModel): Long

    @Delete
    suspend fun removeArticle(articleDbModel: ArticleDbModel)
}
