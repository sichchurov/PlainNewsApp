package com.shchurovsi.plainnewsapp.data.mapper

import com.shchurovsi.plainnewsapp.data.local.model.ArticleDbModel
import com.shchurovsi.plainnewsapp.data.local.model.SourceDbModel
import com.shchurovsi.plainnewsapp.data.network.model.ArticleDto
import com.shchurovsi.plainnewsapp.data.network.model.NewsResponseDto
import com.shchurovsi.plainnewsapp.data.network.model.SourceDto
import com.shchurovsi.plainnewsapp.domain.entities.Article
import com.shchurovsi.plainnewsapp.domain.entities.NewsResponse
import com.shchurovsi.plainnewsapp.domain.entities.Source
import javax.inject.Inject

class Mapper @Inject constructor() {


    fun mapArticleDbModelToArticleEntity(articleDb: ArticleDbModel) = Article(
        author = articleDb.author,
        content = articleDb.content,
        description = articleDb.description,
        title = articleDb.title,
        publishedAt = articleDb.publishedAt,
        url = articleDb.url,
        source = mapSourceDbModelToSource(articleDb.source),
        urlToImage = articleDb.urlToImage
    )

    fun mapArticleEntityToArticleDbModel(article: Article) = ArticleDbModel(
        author = article.author,
        content = article.content,
        description = article.description,
        title = article.title,
        publishedAt = article.publishedAt,
        url = article.url,
        source = mapSourceEntityToSourceDbModel(article.source),
        urlToImage = article.urlToImage
    )

    private fun mapArticleDtoToArticle(articleDto: ArticleDto) = Article(
        author = articleDto.author,
        content = articleDto.content,
        description = articleDto.description,
        title = articleDto.title,
        publishedAt = articleDto.publishedAt,
        url = articleDto.url,
        source = mapSourceDtoToSourceEntity(articleDto.source),
        urlToImage = articleDto.urlToImage
    )

    private fun mapSourceEntityToSourceDbModel(source: Source) = SourceDbModel(
        source.id,
        source.name
    )

    private fun mapSourceDtoToSourceEntity(sourceDto: SourceDto) = Source(
        sourceDto.id,
        sourceDto.name
    )

    private fun mapSourceDbModelToSource(sourceDb: SourceDbModel) = Source(
        sourceDb.id,
        sourceDb.name
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
