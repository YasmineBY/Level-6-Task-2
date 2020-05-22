package com.example.popularmovieskotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.popularmovieskotlin.R
import com.example.popularmovieskotlin.model.Movie
import kotlinx.android.synthetic.main.activity_view_movie.*
import kotlinx.android.synthetic.main.movie_item.*
import kotlinx.android.synthetic.main.movie_item.view.*

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
//

        val prefs = this.getSharedPreferences(MOVIE_DETAILS, 0)

        var posterPath = prefs.getString("POSTER_PATH", "")
        var backdropPath = prefs.getString("BACKDROP_PATH", "")


        txtMovieNameDetails.setText(prefs.getString("NAME", ""))
        //TODO add date to the shared preferences manager
        txtDate.setText(prefs.getString("NAME", ""))
        txtOverview.setText(prefs.getString("OVERVIEW", ""))
        txtRating.setText(prefs.getString("RATING", ""))
        txtDate.setText(prefs.getString("DATE", ""))

        Glide.with(this).load("https://image.tmdb.org/t/p/w500/${posterPath}").into(ivMoviePoster)
        Glide.with(this).load("https://image.tmdb.org/t/p/original/${backdropPath}").into(ivBackdrop)

    }
}
