package com.example.popularmovieskotlin.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.popularmovieskotlin.R
import com.example.popularmovieskotlin.model.Movie
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter(private val movies: List<Movie> , private val onClick: (Movie) -> Unit) :


    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private lateinit var context: Context

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener { onClick(movies[adapterPosition]) }
        }
        fun bind(movie: Movie) {
            itemView.txtMovieName.text = movie.movieTitle
            Glide.with(context).load(movie.getPosterImage()).into(itemView.ivImagePreview)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }
}