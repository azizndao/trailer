package com.example.moviescompose.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Image(
    @SerialName("aspect_ratio")
    val aspectRatio: Float,

    val height: Long,

    @SerialName("iso_639_1")
    val iso639_1: String? = null,

    @SerialName("file_path")
    val filePath: String,

    @SerialName("vote_average")
    val voteAverage: Float,

    @SerialName("vote_count")
    val voteCount: Long,

    val width: Long
)
