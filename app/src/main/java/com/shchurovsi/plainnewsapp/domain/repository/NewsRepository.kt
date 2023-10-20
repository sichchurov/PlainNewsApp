package com.shchurovsi.plainnewsapp.domain.repository

import androidx.lifecycle.LiveData
import com.shchurovsi.plainnewsapp.data.repository.DataStoreRepository
import com.shchurovsi.plainnewsapp.domain.entities.Article
import com.shchurovsi.plainnewsapp.domain.entities.NewsResponse

interface NewsRepository {

    fun getSavedNews(): LiveData<List<Article>>

    suspend fun insertNews(article: Article): Long

    suspend fun deleteNews(article: Article)

    suspend fun getBreakingNews(
        countryCode: String,
        pageNumber: Int
    ): NewsResponse

    suspend fun searchingNews(
        query: String,
        pageNumber: Int,
        pageSize: Int
    ): NewsResponse
}
