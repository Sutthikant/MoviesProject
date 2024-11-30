package com.manop.movieproject.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviePhoto(
    @SerialName("poster_path")
    val imageUrl: String
)

