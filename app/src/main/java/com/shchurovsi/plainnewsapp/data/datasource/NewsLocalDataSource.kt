package com.shchurovsi.plainnewsapp.data.datasource

import com.shchurovsi.plainnewsapp.data.local.db.ArticleDao

interface NewsLocalDataSource {

    fun getDao(): ArticleDao

}
