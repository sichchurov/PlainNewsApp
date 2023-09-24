package com.shchurovsi.plainnewsapp.data.database.mapper

import com.shchurovsi.plainnewsapp.data.database.model.ArticleDbModel
import com.shchurovsi.plainnewsapp.domain.entities.Article
import javax.inject.Inject

class Mapper @Inject constructor() {

    fun mapDbModelToEntity(articleDb: ArticleDbModel) = Article(
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
