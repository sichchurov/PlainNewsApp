package com.shchurovsi.plainnewsapp.domain.usecases

import androidx.lifecycle.LiveData
import com.shchurovsi.plainnewsapp.domain.entities.Article
import com.shchurovsi.plainnewsapp.domain.repository.NewsRepository
import javax.inject.Inject

class GetSavedArticlesUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    operator fun invoke(): LiveData<List<Article>> {
        return repository.getSavedNews()
    }
}
