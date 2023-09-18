package com.example.moviealmanacv2.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import com.example.moviealmanacv2.adapters.GenreAdapter
import com.example.moviealmanacv2.adapters.MovieAdapter
import com.example.moviealmanacv2.databinding.CustomToolbarBinding
import com.example.moviealmanacv2.databinding.FragmentHomeBinding
import com.example.moviealmanacv2.models.genres.Genres
import com.example.moviealmanacv2.models.movies.MoviesItem
import com.example.moviealmanacv2.ui.activities.DetailActivity
import com.example.moviealmanacv2.viewmodels.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module
import timber.log.Timber

val homeModule = module {
    factory { HomeFragment() }
}
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var bindingToolbar: CustomToolbarBinding
    private val viewModel: MovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        bindingToolbar = binding.toolbar
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        bindingToolbar.title = viewModel.title

        binding.listGenre.adapter= genreAdapter
        viewModel.genres.observe(viewLifecycleOwner) {
            MovieAdapter.VIEW_TYPE = if (it!!.isEmpty()) 1 else 2
            firstLoad()
        }

        binding.listMovies.adapter =moviesAdapter
        viewModel.movies.observe(viewLifecycleOwner) {
            if (viewModel.page == 1) moviesAdapter.clear()
            moviesAdapter.addNews(it.results)
        }

        viewModel.message.observe(viewLifecycleOwner) {
            it?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                viewModel.loading.postValue(false)
            }
        }

        binding.scroll.setOnScrollChangeListener {
                v: NestedScrollView?, _: Int, scrollY: Int, _: Int, _: Int ->
            if (scrollY == v?.getChildAt(0)!!.measuredHeight - v.measuredHeight) {
                if (viewModel.page <= viewModel.total && viewModel.loadMore.value == false) viewModel.fetchMovieData()
            }
        }

    }
    private val genreAdapter by lazy {
        GenreAdapter(viewModel.genresOption,object :GenreAdapter.OnAdapterListener{
            override fun onClick(genre: Genres) {
                Timber.e(genre.id)
                viewModel.genres.postValue(genre.id)
            }
        })
    }

    private val moviesAdapter by lazy {
        MovieAdapter(arrayListOf(),object :MovieAdapter.OnMoviesClickListener{
            override fun onClick(movies: MoviesItem) {
                val bundle =Bundle()
                bundle.putSerializable(DETAIL_MOVIE, movies)
                val intent = Intent(activity, DetailActivity::class.java)

                intent.putExtra(DETAIL_MOVIE, bundle)
                startActivity(intent)
            }
        })
    }

    private fun firstLoad(){
        binding.scroll.scrollTo(0,0)
        viewModel.page=1
        viewModel.total=1
        viewModel.fetchMovieData()
    }

    companion object{
        const val DETAIL_MOVIE="detail_movie"
    }

}