package com.shchurovsi.plainnewsapp.domain.usecases

import com.shchurovsi.plainnewsapp.domain.entities.ArticleDto
import com.shchurovsi.plainnewsapp.domain.repository.NewsRepository
import javax.inject.Inject

class InsertArticleUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    operator fun invoke(article: ArticleDto) {
        repository.insertNews(article)
    }
}
