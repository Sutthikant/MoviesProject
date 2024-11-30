package com.manop.movieproject.network

import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {
    @GET("movie/popular")
    suspend fun getPhotos(
        @Query("api_key") apiKey: String,
    ): List<MoviePhoto>
}
