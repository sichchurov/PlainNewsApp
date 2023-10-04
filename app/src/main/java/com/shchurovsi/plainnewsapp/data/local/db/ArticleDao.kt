package com.shchurovsi.plainnewsapp.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shchurovsi.plainnewsapp.data.local.model.ArticleDbModel

@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles")
    fun getSavedArticles(): LiveData<List<ArticleDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(articleDbModel: ArticleDbModel): Long

    @Query("DELETE FROM articles WHERE id = :articleId")
    suspend fun removeArticle(articleId: Int)
}
