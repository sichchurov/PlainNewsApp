package com.shchurovsi.plainnewsapp.data.datasource

import com.shchurovsi.plainnewsapp.data.network.ApiFactory
import com.shchurovsi.plainnewsapp.data.network.ApiService
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor() : NewsRemoteDataSource {
    override fun getApi(): ApiService {
        return ApiFactory.api
    }

}
