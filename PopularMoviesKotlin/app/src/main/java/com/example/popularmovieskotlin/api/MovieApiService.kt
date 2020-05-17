package com.example.popularmovieskotlin.api

import com.example.popularmovieskotlin.model.Movie
import retrofit2.Call
import retrofit2.http.GET

interface MovieApiService {

    // The GET method needed to retrieve a random number trivia.
    @GET("")
    fun getMovie(): Call<Movie>

}