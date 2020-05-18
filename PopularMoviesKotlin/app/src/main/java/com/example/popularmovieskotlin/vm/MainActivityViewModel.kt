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


    private val movieRepositry: MovieRepository = MovieRepository()
    private val movie = MutableLiveData<Movie>()
    private val error = MutableLiveData<String>()


    fun getMovies() {
        movieRepositry.getMovies().enqueue(object : Callback<MovieResults> {

            override fun onResponse(call: Call<MovieResults>, response: Response<MovieResults>) {
                if (response.isSuccessful){
                  var temp= response.body()
                }
                else error.value = "An error occurred: ${response.errorBody().toString()}"
            }
            override fun onFailure(call: Call<MovieResults>, t: Throwable) {
                error.value = t.message
            }
        })

    }

//    var movieApiService: MovieApiService = retrofit.create(MovieApiService::class.java)

//    movieApiService.getMovie().enqueue(object : Callback<MovieResults> {
//        override fun onResponse(call: Call<MovieResults>, response: Response<MovieResults>) {
//            if (response.isSuccessful) {
//                val temp = response.body()
//            } else error.value = "An error occurred: ${response.errorBody().toString()}"
//        }
//
//        override fun onFailure(call: Call<MovieResults>, t: Throwable) {
//            error.value = t.message
//        }
//    })

}