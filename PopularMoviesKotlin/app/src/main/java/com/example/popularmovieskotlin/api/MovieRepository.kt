package com.example.popularmovieskotlin.api

class MovieRepository {
    private val movieApi: MovieApiService = MovieApi.createApi()

    fun getMovie() = movieApi.getMovie()
}