package com.shchurovsi.plainnewsapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleDbModel(

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: SourceDbModel,
    val title: String,
    val url: String,
    val urlToImage: String
)
