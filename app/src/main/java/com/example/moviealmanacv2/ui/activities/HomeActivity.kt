package com.example.moviealmanacv2.ui.activities

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.moviealmanacv2.R
import com.example.moviealmanacv2.databinding.ActivityHomeBinding
import com.example.moviealmanacv2.ui.fragments.HomeFragment
import org.koin.dsl.module

val baseModule = module {
    factory { HomeActivity() }
}
class HomeActivity : AppCompatActivity() {

    private lateinit var binding :ActivityHomeBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setSupportActionBar(binding.toolbarMain)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_movie) as NavHostFragment
        navController = navHostFragment.navController
        //setupActionBarWithNavController(navController)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)


    }
    override fun onSupportNavigateUp(): Boolean {
        //val  navController = findNavController(R.id.nav_host_fragment_container)
        return super.onSupportNavigateUp()|| super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.exit))
            .setMessage(getString(R.string.are_you_sure_do_you_want_to_exit))
            .setNegativeButton(getString(R.string.no), null)
            .setPositiveButton(getString(R.string.yes)
            ) { arg0, arg1 -> super@HomeActivity.onBackPressed() }.create().show()
    }


}