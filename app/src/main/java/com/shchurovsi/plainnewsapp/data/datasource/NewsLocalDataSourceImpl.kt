package com.shchurovsi.plainnewsapp.data.datasource

import android.app.Application
import com.shchurovsi.plainnewsapp.data.local.db.ArticleDao
import com.shchurovsi.plainnewsapp.data.local.db.ArticleDb
import javax.inject.Inject

class NewsLocalDataSourceImpl @Inject constructor(
    private val application: Application
) : NewsLocalDataSource {
    override fun getDao(): ArticleDao {
        return ArticleDb(application).getArticleDao()
    }

}
