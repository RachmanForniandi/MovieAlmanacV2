package com.example.moviealmanacv2.networkUtils


import com.example.moviealmanacv2.models.genres.Genres
import com.example.moviealmanacv2.models.movies.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkClients {


    @GET("discover/movie")
    suspend fun getGenre(
        @Query("api_key")api_key:String,
    ): Genres

    @GET("discover/movie")
    suspend fun getDiscoverMovieByGenre(
        @Query("api_key")api_key:String,
        @Query("genre")genre:String,
        @Query("page")page:Int
    ):Movies
}