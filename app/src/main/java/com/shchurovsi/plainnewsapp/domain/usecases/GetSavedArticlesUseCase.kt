package com.shchurovsi.plainnewsapp.domain.usecases

import androidx.lifecycle.LiveData
import com.shchurovsi.plainnewsapp.data.local.model.ArticleDbModel
import com.shchurovsi.plainnewsapp.domain.repository.NewsRepository
import javax.inject.Inject

class GetSavedArticlesUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    operator fun invoke(): LiveData<List<ArticleDbModel>> {
        return repository.getSavedNews()
    }
}
