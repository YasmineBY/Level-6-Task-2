package com.example.popularmovieskotlin.api

class MovieRepository {
    private val movieApiService: MovieApiService = MovieApi.createApi()

    fun getMovies(year: Int) = movieApiService.getMovies(year)
}