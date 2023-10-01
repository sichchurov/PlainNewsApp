package com.shchurovsi.plainnewsapp.data.network.model


data class NewsResponseDto(
    val articles: List<ArticleDto>,
    val status: String,
    val totalResults: Int
)
