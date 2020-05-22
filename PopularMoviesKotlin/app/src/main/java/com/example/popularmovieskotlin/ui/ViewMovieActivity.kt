package com.example.popularmovieskotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.popularmovieskotlin.R
import com.example.popularmovieskotlin.model.Movie
import kotlinx.android.synthetic.main.activity_view_movie.*
import kotlinx.android.synthetic.main.movie_item.*

class ViewMovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_movie)
        initViews()
    }

    private fun initViews() {
        displayMovies()
    }

    private fun displayMovies()  {
        val prefs = this.getSharedPreferences(MOVIE_DETAILS, 0)
        var moviename = prefs.getString("NAME", "")
        println(moviename)
        txtMovieNameDetails.setText(moviename)
//        editor.putString("NAME", movie.movieTitle)
//        editor.putString("RATING", movie.voteAverage)
//        editor.putString("OVERVIEW", movie.Overview)
//        editor.putString("POSTER_PATH", movie.posterPath)
//        editor.putString("BACKDROP_PATH", movie.backdropPath)
    }
}
