package com.shchurovsi.plainnewsapp.domain.usecases

import com.shchurovsi.plainnewsapp.domain.entities.Article
import com.shchurovsi.plainnewsapp.domain.repository.NewsRepository
import javax.inject.Inject

class InsertArticleUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    operator fun invoke(article: Article) {
        repository.insertNews(article)
    }
}
