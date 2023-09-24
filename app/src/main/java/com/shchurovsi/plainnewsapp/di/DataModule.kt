package com.shchurovsi.plainnewsapp.di

import android.app.Application
import com.shchurovsi.plainnewsapp.data.database.db.ArticleDao
import com.shchurovsi.plainnewsapp.data.database.db.ArticleDb
import com.shchurovsi.plainnewsapp.data.network.ApiFactory
import com.shchurovsi.plainnewsapp.data.network.ApiService
import com.shchurovsi.plainnewsapp.data.repository.NewsRepositoryImpl
import com.shchurovsi.plainnewsapp.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
interface DataModule {

    @Suppress("WARNINGS")
    @ApplicationScope
    @Binds
    fun bindNewsRepository(impl: NewsRepositoryImpl): NewsRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideDao(application: Application): ArticleDao {
            return ArticleDb(application).getArticleDao()
        }

        @ApplicationScope
        @Provides
        fun providesService(): ApiService {
            return ApiFactory.api
        }
    }
}
