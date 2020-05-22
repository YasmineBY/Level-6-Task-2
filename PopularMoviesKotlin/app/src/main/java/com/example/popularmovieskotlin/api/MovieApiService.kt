package com.example.popularmovieskotlin.api

import com.example.popularmovieskotlin.model.MovieResults
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    //https://api.themoviedb.org/3/movie/550?api_key=d0bd4d0326159e106f1f1cd105382a5f
//    ?api_key=d0bd4d0326159e106f1f1cd105382a5f&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1
    // The GET method needed to retrieve a random number trivia.

    @GET("/3/discover/movie?api_key=d0bd4d0326159e106f1f1cd105382a5f&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&")
    fun getMovies(@Query("primary_release_year") year: Int): Call<MovieResults>
//
//    @GET("/3/discover/movie")
//    fun getMovie(
//        @Query("api_key") apiKey: String,
//        @Query("language") language: String,
//        @Query("page") page: Int
//
//    ): Call<MovieResults>

}