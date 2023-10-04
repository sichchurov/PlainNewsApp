package com.shchurovsi.plainnewsapp.domain.usecases

import com.shchurovsi.plainnewsapp.domain.entities.Article
import com.shchurovsi.plainnewsapp.domain.repository.NewsRepository
import javax.inject.Inject

class DeleteArticleUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    suspend operator fun invoke(article: Article) {
        repository.deleteNews(article)
    }
}
