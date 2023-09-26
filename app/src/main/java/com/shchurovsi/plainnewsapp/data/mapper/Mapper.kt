package com.shchurovsi.plainnewsapp.data.mapper

import com.shchurovsi.plainnewsapp.data.local.model.ArticleDbModel
import com.shchurovsi.plainnewsapp.domain.entities.ArticleDto
import javax.inject.Inject

class Mapper @Inject constructor() {


    fun mapArticleDbModelToArticle(articleDb: ArticleDbModel) = ArticleDto(
        author = articleDb.author,
        content = articleDb.content,
        description = articleDb.description,
        title = articleDb.title,
        publishedAt = articleDb.publishedAt,
        url = articleDb.url,
        source = articleDb.source.name,
        urlToImage = articleDb.urlToImage
    )

}
