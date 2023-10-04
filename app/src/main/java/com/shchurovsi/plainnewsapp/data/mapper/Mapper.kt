package com.shchurovsi.plainnewsapp.data.mapper

import com.shchurovsi.plainnewsapp.data.local.model.ArticleDbModel
import com.shchurovsi.plainnewsapp.data.network.model.ArticleDto
import com.shchurovsi.plainnewsapp.data.network.model.NewsResponseDto
import com.shchurovsi.plainnewsapp.domain.entities.Article
import com.shchurovsi.plainnewsapp.domain.entities.NewsResponse
import javax.inject.Inject

class Mapper @Inject constructor() {

    fun mapArticleDbModelToArticleEntity(articleDb: ArticleDbModel) = Article(
        description = articleDb.description,
        title = articleDb.title,
        publishedAt = articleDb.publishedAt,
        url = articleDb.url,
        source = articleDb.source,
        urlToImage = articleDb.urlToImage
    )

    fun mapArticleEntityToArticleDbModel(article: Article) = ArticleDbModel(
        description = article.description,
        title = article.title,
        publishedAt = article.publishedAt,
        url = article.url,
        source = article.source,
        urlToImage = article.urlToImage
    )

    private fun mapArticleDtoToArticle(articleDto: ArticleDto) = Article(
        description = articleDto.description,
        title = articleDto.title,
        publishedAt = articleDto.publishedAt,
        url = articleDto.url,
        source = articleDto.source.name,
        urlToImage = articleDto.urlToImage
    )

    fun mapNewsResponseDtoToNewsResponseEntity(newsResponseDto: NewsResponseDto): NewsResponse {
        return NewsResponse(
            mapListArticleDtoToListArticleEntity(newsResponseDto.articles),
            newsResponseDto.status,
            newsResponseDto.totalResults
        )
    }

    private fun mapListArticleDtoToListArticleEntity(listArticleDto: List<ArticleDto>): List<Article> {
        return listArticleDto.map {
            mapArticleDtoToArticle(it)
        }
    }
}
