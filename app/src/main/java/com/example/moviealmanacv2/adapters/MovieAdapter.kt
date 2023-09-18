package com.example.moviealmanacv2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviealmanacv2.databinding.ItemGenreBinding
import com.example.moviealmanacv2.databinding.ItemMovieBinding
import com.example.moviealmanacv2.databinding.ItemMovieSecondBinding
import com.example.moviealmanacv2.models.movies.Movies
import com.example.moviealmanacv2.models.movies.MoviesItem
import com.example.moviealmanacv2.utils.ConstantsMain

private const val ALL_GENERAL = 1
private const val SPECIFIC_GENRES = 2
class MovieAdapter (val movies:ArrayList<MoviesItem>,
                    val listener: OnMoviesClickListener)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        var VIEW_TYPE= 1
    }
    class MovieHolder (val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: MoviesItem){
            binding.movie = data
            //binding.format = ConstantsMain()
        }
    }
    class MovieHolderSecond (val binding: ItemMovieSecondBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: MoviesItem){
            binding.movie = data
            //binding.format = ConstantsMain()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType== ALL_GENERAL) {
            MovieHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }else{
            MovieHolderSecond(ItemMovieSecondBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }
    override fun getItemViewType(position: Int) = VIEW_TYPE
    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val moviePosition = movies[position]
        if (VIEW_TYPE == ALL_GENERAL){
            (holder as MovieHolder).bind(moviePosition)
        }else{
            (holder as MovieHolderSecond).bind(moviePosition)
        }
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