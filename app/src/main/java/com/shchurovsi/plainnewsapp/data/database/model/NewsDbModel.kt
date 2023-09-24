package com.shchurovsi.plainnewsapp.data.database.model

data class NewsDbModel(
    val articles: List<ArticleDbModel>,
    val status: String,
    val totalResults: Int
)