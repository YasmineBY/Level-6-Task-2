package com.example.popularmovieskotlin.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
@Entity(tableName = "result_table")

data class MovieResults(
    var page: Int,

    @SerializedName("total_results")
    var totalResults: Int,

    @SerializedName("total_pages")
    var totalPages: Int,

    var results: List<Movie>


)