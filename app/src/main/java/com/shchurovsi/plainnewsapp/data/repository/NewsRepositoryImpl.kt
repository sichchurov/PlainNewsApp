package com.shchurovsi.plainnewsapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.shchurovsi.plainnewsapp.data.datasource.NewsLocalDataSource
import com.shchurovsi.plainnewsapp.data.datasource.NewsRemoteDataSource
import com.shchurovsi.plainnewsapp.data.local.model.ArticleDbModel
import com.shchurovsi.plainnewsapp.data.mapper.Mapper
import com.shchurovsi.plainnewsapp.data.network.model.ArticleDto
import com.shchurovsi.plainnewsapp.data.network.model.NewsResponseDto
import com.shchurovsi.plainnewsapp.domain.repository.NewsRepository
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsLocalDataSource: NewsLocalDataSource,
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val mapper: Mapper
) : NewsRepository {

    override suspend fun getBreakingNews(
        countryCode: String,
        pageNumber: Int
    ): Response<NewsResponseDto> {
        return newsRemoteDataSource.getApi().getBreakingNews(countryCode, pageNumber)
    }

    override suspend fun searchingNews(
        query: String,
        pageNumber: Int,
        pageSize: Int
    ): Response<NewsResponseDto> {
        return newsRemoteDataSource.getApi().searchNews(query, pageNumber, pageSize)
    }

    override fun getSavedNews(): LiveData<List<ArticleDbModel>> {
        return newsLocalDataSource.getDao().getSavedArticles()
    }

    override suspend fun insertNews(article: ArticleDto): Long {
        return newsLocalDataSource.getDao().upsert(mapper.mapArticleDtoToArticleDbModel(article))
    }

    override fun deleteNews() {
        TODO("Not yet implemented")
    }
}
