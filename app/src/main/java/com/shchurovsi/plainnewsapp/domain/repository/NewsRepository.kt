package com.shchurovsi.plainnewsapp.domain.repository

import androidx.lifecycle.LiveData
import com.shchurovsi.plainnewsapp.domain.entities.Article

interface NewsRepository {

    fun getSavedNews(): LiveData<List<Article>>

    fun insertNews(article: Article): Long

    fun deleteNews()
}
