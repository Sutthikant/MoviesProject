package com.manop.movieproject.data

import com.manop.movieproject.network.MoviePhoto

interface MoviesPhotosRepository {
    suspend fun getMoviesPhotos(): List<MoviePhoto>
}