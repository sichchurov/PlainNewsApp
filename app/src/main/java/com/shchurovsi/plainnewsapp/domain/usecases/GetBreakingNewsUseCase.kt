package com.shchurovsi.plainnewsapp.domain.usecases

import com.shchurovsi.plainnewsapp.domain.repository.NewsRepository
import javax.inject.Inject

class GetBreakingNewsUseCase @Inject constructor(private val repository: NewsRepository) {

    suspend operator fun invoke(
        countryCode: String,
        pageNumber: Int,
        category: String
    ) =
        repository.getBreakingNews(countryCode, pageNumber, category)
}
