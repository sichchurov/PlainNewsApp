package com.shchurovsi.plainnewsapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val description: String?,
    val publishedAt: String,
    val source: String,
    val title: String,
    val url: String,
    val urlToImage: String?
)
