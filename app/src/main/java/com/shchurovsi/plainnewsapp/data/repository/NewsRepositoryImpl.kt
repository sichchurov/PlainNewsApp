package com.shchurovsi.plainnewsapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.shchurovsi.plainnewsapp.data.database.db.ArticleDb
import com.shchurovsi.plainnewsapp.data.database.mapper.Mapper
import com.shchurovsi.plainnewsapp.domain.entities.Article
import com.shchurovsi.plainnewsapp.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    application: Application,
    private val mapper: Mapper
) : NewsRepository {

    private val articleDao = ArticleDb(application).getArticleDao()

    override fun getSavedNews(): LiveData<List<Article>> {
        return articleDao.getSavedArticles().map {
            it.map { dbArticleList ->
                mapper.mapDbModelToEntity(dbArticleList)
            }
        }
    }

    override fun insertNews(article: Article): Long {
        TODO("Not yet implemented")
    }

    override fun deleteNews() {
        TODO("Not yet implemented")
    }
}
