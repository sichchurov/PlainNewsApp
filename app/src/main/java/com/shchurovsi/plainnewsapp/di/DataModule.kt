package com.shchurovsi.plainnewsapp.di

import com.shchurovsi.plainnewsapp.data.datasource.NewsLocalDataSource
import com.shchurovsi.plainnewsapp.data.datasource.NewsLocalDataSourceImpl
import com.shchurovsi.plainnewsapp.data.datasource.NewsRemoteDataSource
import com.shchurovsi.plainnewsapp.data.datasource.NewsRemoteDataSourceImpl
import com.shchurovsi.plainnewsapp.data.repository.NewsRepositoryImpl
import com.shchurovsi.plainnewsapp.domain.entities.Categories
import com.shchurovsi.plainnewsapp.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module


@Module
interface DataModule {

    @Suppress("WARNINGS")
    @ApplicationScope
    @Binds
    fun bindNewsRepository(impl: NewsRepositoryImpl): NewsRepository

    @Suppress("WARNINGS")
    @ApplicationScope
    @Binds
    fun bindNewsLocalDataSource(impl: NewsLocalDataSourceImpl): NewsLocalDataSource

    @Suppress("WARNINGS")
    @ApplicationScope
    @Binds
    fun bindNewsRemoteDataSource(impl: NewsRemoteDataSourceImpl): NewsRemoteDataSource

}
