package com.example.popularmovieskotlin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.popularmovieskotlin.R
import com.example.popularmovieskotlin.api.MovieApi
import com.example.popularmovieskotlin.api.MovieApiService
import com.example.popularmovieskotlin.api.MovieRepository
import com.example.popularmovieskotlin.model.MovieResults

import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            initViews()
        }


    }



    var baseUrl: String = "https://api.themoviedb.org"
    //todo make retrofit call
    fun initViews() {
        val error = MutableLiveData<String>()
        val movieRepository = MovieRepository()
        //Api key: d0bd4d0326159e106f1f1cd105382a5f
        //https://api.themoviedb.org/3/movie/550?api_key=d0bd4d0326159e106f1f1cd105382a5f

        val movieApi: MovieApiService = MovieApi.createApi()

        movieApi.getMovie().equals(object : Callback<MovieResults> {
        override fun onResponse(call: Call<MovieResults>, response: Response<MovieResults>) {
            if (response.isSuccessful){
                val temp =response.body()
            }
            else error.value = "An error occurred: ${response.errorBody().toString()}"
        }
            override fun onFailure(call: Call<MovieResults>, t: Throwable) {
                error.value = t.message
            }
        })

//        numbersRepository.getRandomNumberTrivia().enqueue(object : Callback<Trivia> {
//            override fun onResponse(call: Call<Trivia>, response: Response<Trivia>) {
//                if (response.isSuccessful) trivia.value = response.body()
//                else error.value = "An error occurred: ${response.errorBody().toString()}"
//            }
//
//            override fun onFailure(call: Call<Trivia>, t: Throwable) {
//                error.value = t.message
//            }
//        })
    }

 }







//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        return when (item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
//}
