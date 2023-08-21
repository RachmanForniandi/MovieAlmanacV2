package com.example.moviealmanacv2

import android.annotation.SuppressLint
import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.moviealmanacv2.networkUtils.networkModule
import com.example.moviealmanacv2.repositories.repositoryModule
import com.example.moviealmanacv2.ui.activities.baseModule
import com.example.moviealmanacv2.ui.activities.detailMovieModule
import com.example.moviealmanacv2.ui.fragments.homeModule
import com.example.moviealmanacv2.viewmodels.homeViewModel
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class MovieApp: Application()  {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        //Timber.e("run base application")
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        startKoin {
            if (BuildConfig.DEBUG)
                androidLogger()
            androidContext(this@MovieApp)
            modules(
                networkModule,
                repositoryModule,
                homeViewModel,
                baseModule,
                homeModule,
                detailMovieModule
            )
        }
    }
}