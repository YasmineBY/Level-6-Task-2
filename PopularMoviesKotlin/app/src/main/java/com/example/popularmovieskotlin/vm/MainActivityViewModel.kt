package com.example.popularmovieskotlin.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.popularmovieskotlin.api.MovieApiService
import com.example.popularmovieskotlin.api.MovieRepository
import com.example.popularmovieskotlin.model.Movie
import com.example.popularmovieskotlin.model.MovieResults
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {


    val listOfMovies = MutableLiveData<List<Movie>>()

    val movieRepositry: MovieRepository = MovieRepository()
    val movie = MutableLiveData<Movie>()
    val error = MutableLiveData<String>()


    fun getMovies(year: Int) {
        movieRepositry.getMovies(year).enqueue(object : Callback<MovieResults> {
            override fun onResponse(call: Call<MovieResults>, response: Response<MovieResults>) {
                if (response.isSuccessful) {
                    var movieResults = response.body()
                    listOfMovies.value = movieResults?.results
                } else error.value = "An error occurred: ${response.errorBody().toString()}"
            }

            override fun onFailure(call: Call<MovieResults>, t: Throwable) {
                error.value = t.message
            }
        })
    }


}