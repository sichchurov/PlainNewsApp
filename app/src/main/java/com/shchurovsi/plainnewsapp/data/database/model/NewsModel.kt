package com.shchurovsi.plainnewsapp.data.database.model

data class NewsModel(
    val articles: List<ArticleModel>,
    val status: String,
    val totalResults: Int
)