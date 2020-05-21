package com.example.popularmovieskotlin.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "movie_table")
data class Movie (
    @SerializedName("title")
    var movieTitle: String,
    @SerializedName("poster_path")
    var posterPath: String,
    @SerializedName("backdrop_path")
    var backdropPath: String,
    @SerializedName("overview")
    var Overview : String,
    @SerializedName("vote_average")
    var voteAverage: String,
    @SerializedName("release_date")
    var releaseDate: Date
//    @PrimaryKey(autoGenerate = true)
//    var id: Long? = null
    )

{
    fun getPosterImage() = "https://image.tmdb.org/t/p/w600/${posterPath}"
    fun getBackdropImage() = "https://image.tmdb.org/t/p/w500/${backdropPath}"

}


