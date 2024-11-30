package com.manop.movieproject

import android.app.Application
import com.manop.movieproject.data.AppContainer
import com.manop.movieproject.data.DefaultAppContainer

class MoviesPhotosApplication: Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}