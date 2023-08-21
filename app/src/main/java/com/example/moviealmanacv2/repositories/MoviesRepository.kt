package com.example.moviealmanacv2.repositories

import com.example.moviealmanacv2.models.genres.Genres
import com.example.moviealmanacv2.models.movies.Movies
import com.example.moviealmanacv2.networkUtils.NetworkClients
import com.example.moviealmanacv2.utils.ConstantsMain
import org.koin.dsl.module


val repositoryModule = module {
    factory { MoviesRepository(get()) }
}
class MoviesRepository (private val networkClients: NetworkClients){



    suspend fun fetchDataDiscoverMovie(
        genre: String? = "",
        page:Int
    ):Movies{
        return networkClients.getDiscoverMovieByGenre(
            ConstantsMain.API_KEY,
            genre!!,
            page
        )
    }


}