package com.shchurovsi.plainnewsapp.data.network

import com.shchurovsi.plainnewsapp.data.network.model.NewsResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country") countryCode: String = "ru",
        @Query("page") pageNumbers: Int = 1,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<NewsResponseDto>

    @GET("/v2/everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("page") pageNumber: Int = 1,
        @Query("pageSize") pageSize: Int = 50,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<NewsResponseDto>


    companion object {

        private const val API_KEY = "b1602a4c6fc641d2b9093f72cb626829"
    }
}
