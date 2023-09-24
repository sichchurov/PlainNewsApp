package com.shchurovsi.plainnewsapp.domain.entities

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)