package com.example.moviealmanacv2.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.moviealmanacv2.R
import com.example.moviealmanacv2.databinding.ActivityDetailBinding
import com.example.moviealmanacv2.models.movies.MoviesItem
import com.example.moviealmanacv2.ui.fragments.HomeFragment
import com.example.moviealmanacv2.ui.fragments.HomeFragment.Companion.DETAIL_MOVIE
import com.example.moviealmanacv2.utils.ConstantsMain.Companion.TMDb_BACKDROP_PATH
import com.example.moviealmanacv2.utils.ConstantsMain.Companion.TMDb_POSTER_PATH
import org.koin.dsl.module

val detailMovieModule = module {
    factory { DetailActivity() }
}
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()


        val bundleDetail = intent.getBundleExtra(DETAIL_MOVIE)
        val dataDetail = bundleDetail?.getSerializable(DETAIL_MOVIE) as MoviesItem
        parsingDetailMovie(dataDetail)
        //setupToDetailTrailer()
    }



    private fun setupView() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title=""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun parsingDetailMovie(dataDetail: MoviesItem) {
        Glide
            .with(this@DetailActivity)
            .load(TMDb_BACKDROP_PATH+dataDetail.backdropPath)
            .centerCrop()
            .placeholder(R.drawable.place_holder)
            .error(R.drawable.error_placeholder)
            .into(binding.imgBackDrop)

        Glide
            .with(this@DetailActivity)
            .load(TMDb_POSTER_PATH+dataDetail.posterPath)
            .centerCrop()
            .placeholder(R.drawable.place_holder)
            .error(R.drawable.error_placeholder)
            .into(binding.detailMovieMain.imageViewPoster)

        val convertVoteAvg =dataDetail.voteAverage.toString()
        val convertPopularity =dataDetail.popularity.toString()
        val valRating = dataDetail.voteAverage.div(2.0)
        val valForRateBar= valRating.toFloat()

        binding.detailMovieMain.txtTitleMovieDetail.text= dataDetail.title
        binding.detailMovieMain.txtContentOverview.text= dataDetail.overview
        binding.detailMovieMain.txtVoteAverageDetail.text = convertVoteAvg
        binding.detailMovieMain.txtPopularity.text = convertPopularity

        binding.detailMovieMain.ratingBar.rating = valForRateBar

        if (dataDetail.originalLanguage.equals("en")){
            binding.detailMovieMain.txtOriginalLanguage.text="English"
        }else if (dataDetail.originalLanguage.equals("ja")){
            binding.detailMovieMain.txtOriginalLanguage.text="Japanese"
        }else if (dataDetail.originalLanguage.equals("it")){
            binding.detailMovieMain.txtOriginalLanguage.text="Italian"
        }else if (dataDetail.originalLanguage.equals("pl")){
            binding.detailMovieMain.txtOriginalLanguage.text="Poland"
        }else if (dataDetail.originalLanguage.equals("ar")){
            binding.detailMovieMain.txtOriginalLanguage.text="Arab"
        }else if (dataDetail.originalLanguage.equals("fr")){
            binding.detailMovieMain.txtOriginalLanguage.text="France"
        }else if (dataDetail.originalLanguage.equals("uk")){
            binding.detailMovieMain.txtOriginalLanguage.text="Ukraine"
        }else if (dataDetail.originalLanguage.equals("es")){
            binding.detailMovieMain.txtOriginalLanguage.text="Spanish"
        }else if (dataDetail.originalLanguage.equals("de")){
            binding.detailMovieMain.txtOriginalLanguage.text="German"
        }else if (dataDetail.originalLanguage.equals("ko")){
            binding.detailMovieMain.txtOriginalLanguage.text="Korea"
        }else if (dataDetail.originalLanguage.equals("id")){
            binding.detailMovieMain.txtOriginalLanguage.text="Indonesian"
        }else if (dataDetail.originalLanguage.equals("id")){
            binding.detailMovieMain.txtOriginalLanguage.text="Indonesian"
        }else if (dataDetail.originalLanguage.equals("zh")){
            binding.detailMovieMain.txtOriginalLanguage.text="Chinese"
        } else{
            binding.detailMovieMain.txtOriginalLanguage.text="Unknown Language"
        }

    }
    /*private fun setupToDetailTrailer() {
        binding.detailMovieMain.fabPlay.setOnClickListener {
            val intentTrailer= Intent(this@DetailActivity,TrailerActivity::class.java)

        }
    }*/
}