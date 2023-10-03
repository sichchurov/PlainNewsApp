package com.shchurovsi.plainnewsapp.domain.repository

import androidx.lifecycle.LiveData
import com.shchurovsi.plainnewsapp.data.local.model.ArticleDbModel
import com.shchurovsi.plainnewsapp.data.network.model.ArticleDto
import com.shchurovsi.plainnewsapp.data.network.model.NewsResponseDto
import retrofit2.Response

interface NewsRepository {

    fun getSavedNews(): LiveData<List<ArticleDbModel>>

    suspend fun insertNews(article: ArticleDto): Long

    fun deleteNews()

    suspend fun getBreakingNews(
        countryCode: String,
        pageNumber: Int
    ): Response<NewsResponseDto>

    suspend fun searchingNews(
        query: String,
        pageNumber: Int,
        pageSize: Int
    ): Response<NewsResponseDto>
}
