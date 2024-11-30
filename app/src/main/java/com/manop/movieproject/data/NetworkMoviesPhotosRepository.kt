package com.manop.movieproject.data

import com.manop.movieproject.network.MoviePhoto
import com.manop.movieproject.network.MoviesApiService

class NetworkMoviesPhotosRepository(
    private val moviesApiService: MoviesApiService
) : MoviesPhotosRepository {

    override suspend fun getMoviesPhotos(): List<MoviePhoto> {
        val apiKey = "a3c9f0c12e66eb4797aef8d6051f32d0"
        val photos =  moviesApiService.getPhotos(apiKey)
        return photos
    }
}