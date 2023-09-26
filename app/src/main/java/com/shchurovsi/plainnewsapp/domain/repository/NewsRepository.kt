package com.shchurovsi.plainnewsapp.domain.repository

import androidx.lifecycle.LiveData
import com.shchurovsi.plainnewsapp.data.network.model.NewsResponseDto
import com.shchurovsi.plainnewsapp.domain.entities.ArticleDto
import retrofit2.Response

interface NewsRepository {

    fun getSavedNews(): LiveData<List<ArticleDto>>

    fun insertNews(article: ArticleDto): Long

    fun deleteNews()

    suspend fun getBreakingNews(
        countryCode: String,
        pageNumber: Int
    ): Response<NewsResponseDto>
}
