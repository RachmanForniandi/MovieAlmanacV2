package com.example.moviealmanacv2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviealmanacv2.databinding.ItemGenreBinding
import com.example.moviealmanacv2.databinding.ItemMovieBinding
import com.example.moviealmanacv2.models.movies.Movies
import com.example.moviealmanacv2.models.movies.MoviesItem
import com.example.moviealmanacv2.utils.ConstantsMain

class MovieAdapter (val movies:ArrayList<MoviesItem>,
                    val listener: OnMoviesClickListener)
    : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    class MovieHolder (val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: MoviesItem){
            binding.movie = data
            binding.format = ConstantsMain()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val moviePosition = movies[position]
        holder.bind(moviePosition)
        holder.itemView.setOnClickListener {
            listener.onClick(moviePosition)
        }
    }

    interface OnMoviesClickListener{
        fun onClick(moviesItem:MoviesItem)
    }

    fun addNews(data:List<MoviesItem>){
        movies.addAll(data)
        notifyItemRangeInserted((movies.size - data.size),data.size)
    }

    fun clear(){
        movies.clear()
        notifyDataSetChanged()
    }
}