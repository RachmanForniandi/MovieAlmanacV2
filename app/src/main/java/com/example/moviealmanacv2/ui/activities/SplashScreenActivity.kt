package com.example.moviealmanacv2.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviealmanacv2.R
import java.util.Timer
import kotlin.concurrent.schedule

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Timer("splashGone", true).schedule(3000) {
            val intentHome=Intent(this@SplashScreenActivity, HomeActivity::class.java)
            startActivity(intentHome)
                finish()
            }
        }
    }
