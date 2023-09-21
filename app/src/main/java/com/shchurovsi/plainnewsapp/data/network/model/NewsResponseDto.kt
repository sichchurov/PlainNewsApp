package com.shchurovsi.plainnewsapp.data.network.model


import com.google.gson.annotations.SerializedName

data class NewsResponseDto(

    @SerializedName("articles")
    val articles: List<ArticleDto>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)
