package com.manop.movieproject.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.manop.movieproject.MoviesPhotosApplication
import com.manop.movieproject.data.MoviesPhotosRepository
import com.manop.movieproject.network.MoviePhoto
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MoviesUiState {
    data class Success(val photos: List<MoviePhoto>) : MoviesUiState
    object Error : MoviesUiState
    object Loading : MoviesUiState
}

class MoviesViewModel( private val moviesPhotosRepository: MoviesPhotosRepository) : ViewModel() {

    var moviesUiState: MoviesUiState by mutableStateOf(MoviesUiState.Loading)
        private set

    init {
        getMoviesPhotos()
    }

    fun getMoviesPhotos() {
        viewModelScope.launch {
            moviesUiState = try {
                MoviesUiState.Success(moviesPhotosRepository.getMoviesPhotos())
            } catch (e: IOException) {
                Log.e("my_tag", e.stackTraceToString())
                MoviesUiState.Error
            }

        }
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MoviesPhotosApplication)
                val moviesPhotosRepository = application.container.moviesPhotosRepository
                MoviesViewModel(moviesPhotosRepository = moviesPhotosRepository)
            }
        }
    }
}