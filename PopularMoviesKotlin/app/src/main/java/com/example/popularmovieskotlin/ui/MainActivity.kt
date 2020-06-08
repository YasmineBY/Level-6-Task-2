package com.example.popularmovieskotlin.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmovieskotlin.R
import com.example.popularmovieskotlin.adapter.MovieAdapter
import com.example.popularmovieskotlin.model.Movie
import com.example.popularmovieskotlin.vm.MainActivityViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.movie_item.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

const val MOVIE_DETAILS = "movie_details"

class MainActivity : AppCompatActivity() {
    private lateinit var movies: ArrayList<Movie>
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initViews()
        initViewModel()
    }

    fun formatDate(receivedDate: Date): String {
        var formatDate: SimpleDateFormat? = SimpleDateFormat("yyyy-MM-dd")
        var formattedDate = formatDate?.format(receivedDate)
        return "Release: " + formattedDate.toString()
    }

    private fun onMovieClick(movie: Movie) {
        val prefs = this.getSharedPreferences(MOVIE_DETAILS, 0)
        val editor = prefs.edit()
        editor.putString("NAME", movie.movieTitle)
        editor.putString("RATING", movie.voteAverage)
        editor.putString("DATE", formatDate(movie.releaseDate))
        editor.putString("OVERVIEW", movie.Overview)
        editor.putString("POSTER_PATH", movie.posterPath)
        editor.putString("BACKDROP_PATH", movie.backdropPath)
        editor.commit()

        val intent = Intent(this, ViewMovieActivity::class.java)
        startActivity(intent)
    }

    fun initViews() {
        initalizeRecyclerView()
        btnSubmit.setOnClickListener { view ->
            var year: Int = etYear.text.toString().toInt()
            viewModel.getMovies(year)
        }

    }

    private fun observeViewModel() {
        viewModel.listOfMovies.observe(this, Observer { movies ->
            this@MainActivity.movies.clear()
            this@MainActivity.movies.addAll(movies)
            movieAdapter.notifyDataSetChanged()
        })
    }

    private fun initViewModel() {
//        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.movie.observe(this, Observer {
            txtMovieName.text = it.movieTitle
        })

        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }


    private fun initalizeRecyclerView() {

        recyclerView = findViewById(R.id.rvMovies)
        movies = arrayListOf()
        movieAdapter = MovieAdapter(
            movies,
            { movies -> onMovieClick(movies) })
        viewManager = GridLayoutManager(this, 2)

        observeViewModel()

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = movieAdapter
        }
    }
}

