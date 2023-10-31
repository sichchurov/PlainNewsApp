package com.shchurovsi.plainnewsapp.domain.usecases

import com.shchurovsi.plainnewsapp.domain.repository.NewsRepository
import javax.inject.Inject

class GetCategoryNewsUseCase @Inject constructor(private val repository: NewsRepository) {

    suspend operator fun invoke(
        countryCode: String,
        pageNumber: Int,
        category: String
    ) =
        repository.getCategoryNews(countryCode, pageNumber, category)
}
