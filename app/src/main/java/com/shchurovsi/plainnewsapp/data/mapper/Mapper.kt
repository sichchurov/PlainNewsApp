package com.shchurovsi.plainnewsapp.data.mapper

import com.shchurovsi.plainnewsapp.data.local.model.ArticleDbModel
import com.shchurovsi.plainnewsapp.data.local.model.SourceDbModel
import com.shchurovsi.plainnewsapp.data.network.model.ArticleDto
import com.shchurovsi.plainnewsapp.data.network.model.SourceDto
import com.shchurovsi.plainnewsapp.domain.entities.Article
import javax.inject.Inject

class Mapper @Inject constructor() {


    fun mapArticleDbModelToArticleDto(articleDb: ArticleDbModel) = ArticleDto(
        author = articleDb.author,
        content = articleDb.content,
        description = articleDb.description,
        title = articleDb.title,
        publishedAt = articleDb.publishedAt,
        url = articleDb.url,
        source = mapSourceDbModelToSourceDto(articleDb.source),
        urlToImage = articleDb.urlToImage
    )

    fun mapArticleDtoToArticleDbModel(articleDto: ArticleDto) = ArticleDbModel(
        author = articleDto.author,
        content = articleDto.content,
        description = articleDto.description,
        title = articleDto.title,
        publishedAt = articleDto.publishedAt,
        url = articleDto.url,
        source = mapSourceDtoToSourceDbModel(articleDto.source),
        urlToImage = articleDto.urlToImage
    )

    private fun mapArticleDtoToArticle(articleDto: ArticleDto) = Article(
        author = articleDto.author,
        content = articleDto.content,
        description = articleDto.description,
        title = articleDto.title,
        publishedAt = articleDto.publishedAt,
        url = articleDto.url,
        source = articleDto.source.name,
        urlToImage = articleDto.urlToImage
    )

    private fun mapSourceDtoToSourceDbModel(sourceDto: SourceDto) = SourceDbModel(
        sourceDto.id,
        sourceDto.name
    )

    private fun mapSourceDbModelToSourceDto(sourceDb: SourceDbModel) = SourceDto(
        sourceDb.id,
        sourceDb.name
    )

    // TODO: create mapper from Response<Dto> to Response<Entity>


}
