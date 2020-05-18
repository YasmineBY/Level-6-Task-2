package com.example.popularmovieskotlin.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.popularmovieskotlin.R
import com.example.popularmovieskotlin.vm.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initViews()
        initViewModel()
    }


    fun initViews() {
        fab.setOnClickListener { view ->
            viewModel.getMovies()
        }


    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)


        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
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