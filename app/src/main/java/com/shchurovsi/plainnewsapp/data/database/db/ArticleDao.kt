package com.shchurovsi.plainnewsapp.data.database.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shchurovsi.plainnewsapp.data.database.model.ArticleModel

@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles")
    fun getSavedArticles(): LiveData<List<ArticleModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(articleModel: ArticleModel): Long

    @Delete
    suspend fun removeArticle(articleModel: ArticleModel)
}
