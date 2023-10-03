package com.shchurovsi.plainnewsapp.domain.usecases

import com.shchurovsi.plainnewsapp.domain.entities.NewsResponse
import com.shchurovsi.plainnewsapp.domain.repository.NewsRepository
import javax.inject.Inject

class SearchingNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(
        query: String,
        pageNumber: Int,
        pageSize: Int
    ): NewsResponse {
        return repository.searchingNews(query, pageNumber, pageSize)
    }
}