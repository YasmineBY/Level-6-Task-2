package com.example.popularmovieskotlin.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "game_table")
data class Movie(
    var movieTitle: String,
    var Rating: String,
    var releaseDate: Date,
    var Overview : String,
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

    )

{
    fun getBackdropImage() = ""
    fun getPosterImage() = ""
}


