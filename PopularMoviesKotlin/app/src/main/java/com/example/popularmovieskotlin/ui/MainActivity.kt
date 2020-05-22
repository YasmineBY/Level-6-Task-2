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

const val MOVIE_DETAILS = "movie_details"

class MainActivity : AppCompatActivity() {
    private lateinit var movies: ArrayList<Movie>
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val viewModel: MainActivityViewModel by viewModels()
//    private  var movieAdapter: MovieAdapter(movies,{ movies -> onMovieClick(movies) } )
    private lateinit var movieAdapter: MovieAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initViews()
        initViewModel()
    }

    private fun onMovieClick(movie: Movie) {
        Snackbar.make(rvMovies, "This color is:", Snackbar.LENGTH_LONG).show()
        val prefs = this.getSharedPreferences(MOVIE_DETAILS, 0)
        val editor = prefs.edit()
        editor.putString("NAME", movie.movieTitle)
        editor.putString("RATING", movie.voteAverage)
        //TODO LOOK AT DATE (THIS IS NOT FORMAT THAT IS NEEDED)
        editor.putString("DATE", movie.releaseDate.toString())
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
            viewModel.getMovies()
        }

    }

    private fun observeViewModel() {
        viewModel.listOfMovies.observe(this, Observer {
                movies ->
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
//fun initViews() {
//    val error = MutableLiveData<String>()
//    val movieRepository = MovieRepository()
//    //Api key: d0bd4d0326159e106f1f1cd105382a5f
//    //https://api.themoviedb.org/3/movie/550?api_key=d0bd4d0326159e106f1f1cd105382a5f
//
//    val okHttpClient = OkHttpClient.Builder()
//        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//        .build()
//    val retrofit = Retrofit.Builder()
//        .baseUrl(baseUrl)
//        .client(okHttpClient)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//    var movieApiService: MovieApiService = retrofit.create(MovieApiService::class.java)
//
//
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
//
//
//}