package com.shchurovsi.plainnewsapp.domain.usecases

import com.shchurovsi.plainnewsapp.domain.repository.NewsRepository

class GetSavedArticlesUseCase(
    private val repository: NewsRepository
) {

    operator fun invoke() {
        repository.getSavedNews()
    }
}
