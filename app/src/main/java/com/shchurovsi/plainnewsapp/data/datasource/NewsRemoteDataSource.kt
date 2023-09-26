package com.shchurovsi.plainnewsapp.data.datasource

import com.shchurovsi.plainnewsapp.data.network.ApiService

interface NewsRemoteDataSource {

    fun getApi(): ApiService
}
