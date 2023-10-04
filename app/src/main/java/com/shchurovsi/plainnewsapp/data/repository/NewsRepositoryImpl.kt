package com.shchurovsi.plainnewsapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.shchurovsi.plainnewsapp.data.datasource.NewsLocalDataSource
import com.shchurovsi.plainnewsapp.data.datasource.NewsRemoteDataSource
import com.shchurovsi.plainnewsapp.data.mapper.Mapper
import com.shchurovsi.plainnewsapp.domain.entities.Article
import com.shchurovsi.plainnewsapp.domain.entities.NewsResponse
import com.shchurovsi.plainnewsapp.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsLocalDataSource: NewsLocalDataSource,
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val mapper: Mapper
) : NewsRepository {

    override suspend fun getBreakingNews(
        countryCode: String,
        pageNumber: Int
    ): NewsResponse {
        return mapper.mapNewsResponseDtoToNewsResponseEntity(
            newsRemoteDataSource.getApi().getBreakingNews(countryCode, pageNumber)
        )
    }

    override suspend fun searchingNews(
        query: String,
        pageNumber: Int,
        pageSize: Int
    ): NewsResponse {
        return mapper.mapNewsResponseDtoToNewsResponseEntity(
            newsRemoteDataSource.getApi().searchNews(query, pageNumber, pageSize)
        )
    }

    override fun getSavedNews(): LiveData<List<Article>> {
        return newsLocalDataSource.getDao().getSavedArticles().map {
            it.map { articleDb ->
                mapper.mapArticleDbModelToArticleEntity(articleDb)
            }
        }
    }

    override suspend fun insertNews(article: Article): Long {
        return newsLocalDataSource.getDao().upsert(
            mapper.mapArticleEntityToArticleDbModel(article)
        )
    }

    override suspend fun deleteNews(article: Article) {
        article.id?.let { newsLocalDataSource.getDao().removeArticle(it) }
    }
}
