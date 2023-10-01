package com.shchurovsi.plainnewsapp.domain.usecases

import com.shchurovsi.plainnewsapp.data.network.model.NewsResponseDto
import com.shchurovsi.plainnewsapp.domain.repository.NewsRepository
import retrofit2.Response
import javax.inject.Inject

class SearchingNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(query: String, pageNumber: Int, pageSize: Int): Response<NewsResponseDto> {
        return repository.searchingNews(query, pageNumber, pageSize)
    }
}