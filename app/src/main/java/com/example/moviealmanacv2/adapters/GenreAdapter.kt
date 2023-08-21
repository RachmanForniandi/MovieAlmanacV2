package com.example.moviealmanacv2.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviealmanacv2.R
import com.example.moviealmanacv2.databinding.ItemGenreBinding
import com.example.moviealmanacv2.models.genres.Genres

class GenreAdapter(val genres:List<Genres>,
                   val onClickListener: OnAdapterListener): RecyclerView.Adapter<GenreAdapter.GenreHolder>() {

    private val itemCategories = arrayListOf<TextView>()

    interface OnAdapterListener {
        fun onClick(genre: Genres)
    }

    class GenreHolder (val binding: ItemGenreBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreAdapter.GenreHolder {
        return GenreHolder(ItemGenreBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: GenreHolder, position: Int) {
        val genre = genres[position]
        holder.binding.txtGenre.text = genre.name
        itemCategories.add(holder.binding.txtGenre)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(genre)
            setColor(holder.binding.txtGenre)
        }
        setColor(itemCategories[0])
    }

    override fun getItemCount(): Int {
        return genres.size
    }

    @SuppressLint("ResourceAsColor")
    private fun setColor(textView: TextView){
        itemCategories.forEach{
            it.setBackgroundColor(R.color.white)
        }
        textView.setBackgroundColor(android.R.color.darker_gray)
    }
}