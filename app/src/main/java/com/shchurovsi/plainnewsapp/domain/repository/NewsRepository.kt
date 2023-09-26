package com.shchurovsi.plainnewsapp.domain.repository

import androidx.lifecycle.LiveData
import com.shchurovsi.plainnewsapp.data.network.model.NewsResponseDto
import com.shchurovsi.plainnewsapp.domain.entities.Article
import retrofit2.Response

interface NewsRepository {

    fun getSavedNews(): LiveData<List<Article>>

    fun insertNews(article: Article): Long

    fun deleteNews()

    suspend fun getBreakingNews(
        countryCode: String,
        pageNumber: Int
    ): Response<NewsResponseDto>
}
