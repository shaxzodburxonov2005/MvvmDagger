package com.example.mymvvmnuntium.network


import com.example.mymvvmnuntium.modul.newss.Articles
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/everything")
    suspend fun getAllNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String? = "d99147d0dfb944e784955ff95d913e3a"
    ): Response<Articles>
}