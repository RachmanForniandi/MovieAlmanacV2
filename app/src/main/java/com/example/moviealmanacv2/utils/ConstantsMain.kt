package com.example.moviealmanacv2.utils

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.bumptech.glide.Glide
import com.example.moviealmanacv2.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

class ConstantsMain {

    companion object{
        const val BASE_URL ="https://api.themoviedb.org/3/"
        const val API_KEY="04a5984bb20ef987a182de11ee3810b4"
        const val TMDb_BACKDROP_PATH = "https://image.tmdb.org/t/p/w500_and_h282_face"
        const val TMDb_POSTER_PATH   = "https://image.tmdb.org/t/p/w220_and_h330_face"
        var MOVIE_ID =0
        var MOVIE_TITLE =""

        @BindingAdapter("loadImageUrl")
        @JvmStatic
        fun loadImageUrl(imageView: ImageView, imgUrl:String){
            imgUrl.let {
                Glide
                .with(imageView)
                .load(TMDb_POSTER_PATH+ imgUrl)
                .centerCrop()
                .placeholder(R.drawable.place_holder)
                .error(R.drawable.place_holder)
                .into(imageView)
            }
        }
    }
    @SuppressLint("SimpleDateFormat")
    fun getStringDate(date: String): String? {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val outputDate = SimpleDateFormat("EEE, dd MMM yyy")
        var d: Date? = null
        try {
            d = dateFormat.parse(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return outputDate.format(d)
    }
}