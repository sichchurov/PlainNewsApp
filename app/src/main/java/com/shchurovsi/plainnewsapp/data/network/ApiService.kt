package com.shchurovsi.plainnewsapp.data.network

import com.shchurovsi.plainnewsapp.BuildConfig
import com.shchurovsi.plainnewsapp.data.network.model.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country") countryCode: String = "us",
        @Query("page") pageNumbers: Int = 1,
        @Query("category") category: String = "general",
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponseDto

    @GET("/v2/everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("page") pageNumber: Int = 1,
        @Query("pageSize") pageSize: Int = 50,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponseDto


    companion object {

        private const val API_KEY = BuildConfig.API_KEY
    }
}
