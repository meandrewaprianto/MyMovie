package com.andrewaprianto.mymovie.core.data.source.remote.network

import com.andrewaprianto.mymovie.core.data.source.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{
    @GET("3/movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en"
    ): MovieResponse
}