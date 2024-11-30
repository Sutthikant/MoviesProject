package com.manop.movieproject.data

import com.manop.movieproject.network.MoviesApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

class DefaultAppContainer: AppContainer {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            Json {
                ignoreUnknownKeys = true
            }.asConverterFactory(
                "application/json".toMediaType()
            )
        )
        .build()

    private val retrofitService: MoviesApiService by lazy {
        retrofit.create(MoviesApiService::class.java)
    }

    override val moviesPhotosRepository: MoviesPhotosRepository by lazy {
        NetworkMoviesPhotosRepository(retrofitService)
    }

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
    }
}