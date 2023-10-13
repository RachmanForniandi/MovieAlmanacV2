package com.example.moviealmanacv2.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviealmanacv2.models.genres.Genres
import com.example.moviealmanacv2.models.movies.Movies
import com.example.moviealmanacv2.repositories.MoviesRepository
import kotlinx.coroutines.launch
import org.koin.dsl.module
import timber.log.Timber
import java.lang.Exception
import kotlin.math.ceil

val homeViewModel = module {
    factory { MovieViewModel(get()) }
}
class MovieViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModel() {
    val title ="Movie"
    val message by lazy { MutableLiveData<String>() }
    val loading by lazy { MutableLiveData<Boolean>() }
    val loadMore by lazy { MutableLiveData<Boolean>() }
    val genres by lazy { MutableLiveData<String>() }
    val movies by lazy { MutableLiveData<Movies>() }

    init {
        genres.value=""
        message.value =null
    }

    var page=1
    var total =1

    /*fun fetchGenre(){
        loading.value = true
        viewModelScope.launch {
            try {
                val responseGenre = moviesRepository.fetchGenre()
                genres.value = responseGenre

            }catch (e: Exception){
                message.value="Terjadi Error pada genre"
            }
        }
    }*/

    fun fetchMovieData(){
        Timber.e("fetchPage: $page")
        if (page >1)loadMore.value = true else loading.value = true
        viewModelScope.launch {
            try {
                val responseMovie=moviesRepository.fetchDataDiscoverMovie(genres.value!!,page)
                movies.value = responseMovie
                val totalResults:Double = responseMovie.totalResults / 30.0
                total= ceil(totalResults).toInt()
                page++
                loading.value = false
                loadMore.value = false
            }catch (e:Exception){
                message.value="Terjadi Error data Movie"
            }
        }
    }

    val genresOption = listOf(
        Genres("","General"),
        Genres("28", "Action"),
        Genres("12", "Adventure"),
        Genres("16", "Animation"),
        Genres("35", "Comedy"),
        Genres("80", "Crime"),
        Genres("99", "Documentary"),
        Genres("18", "Drama"),
        Genres("10751", "Family"),
        Genres("14", "Fantasy"),
        Genres("27", "Horror"),
        Genres("10402", "Music"),
        Genres("9648", "Mystery"),
        Genres("10749", "Romance"),
        Genres("878", "Science Fiction"),
        Genres("10770", "TV Movie"),
        Genres("53", "Thriller"),
        Genres("10752", "War"),
        Genres("37", "Western"),
    )
}