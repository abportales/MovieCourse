package com.example.peliculas.ui.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.example.peliculas.R
import com.example.peliculas.databinding.FragmentMovieBinding
import com.example.peliculas.ui.Data.Model.Movie
import com.example.peliculas.ui.Data.Remote.RemoteMovieDataSource
import com.example.peliculas.ui.Data.local.AppDataBase
import com.example.peliculas.ui.Data.local.LocalMovieDataSource
import com.example.peliculas.ui.coreM.Resource
import com.example.peliculas.ui.presentation.MovieViewModel
import com.example.peliculas.ui.presentation.MovieViewModelFactory
import com.example.peliculas.ui.repository.MovieRepositoryImplement
import com.example.peliculas.ui.repository.RetrofitClient
import com.example.peliculas.ui.ui.movie.adapters.MovieAdapter
import com.example.peliculas.ui.ui.movie.adapters.concat.PopularContactAdapter
import com.example.peliculas.ui.ui.movie.adapters.concat.TopRatedConcatAdapter
import com.example.peliculas.ui.ui.movie.adapters.concat.UpcomingconcatAdapter


class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.OnMovieClickListener {

    private lateinit var binding: FragmentMovieBinding
    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(
            MovieRepositoryImplement(
                RemoteMovieDataSource(RetrofitClient.webservice),
                LocalMovieDataSource(AppDataBase.getDatabase(requireContext()).movieDao())
            )
        )

    }//by es para delegar

    private lateinit var concatAdapter: ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)
        concatAdapter = ConcatAdapter()

        viewModel.fetchMainScreenMovies()
            .observe(viewLifecycleOwner, androidx.lifecycle.Observer { result ->
                when (result) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        concatAdapter.apply {
                            addAdapter(
                                0,
                                UpcomingconcatAdapter(
                                    MovieAdapter(
                                        result.data.first.results,
                                        this@MovieFragment
                                    )
                                )
                            )
                            addAdapter(
                                1,
                                TopRatedConcatAdapter(
                                    MovieAdapter(
                                        result.data.second.results,
                                        this@MovieFragment
                                    )
                                )
                            )
                            addAdapter(
                                2,
                                PopularContactAdapter(
                                    MovieAdapter(
                                        result.data.third.results,
                                        this@MovieFragment
                                    )
                                )
                            )
                        }
                        binding.rvMovies.adapter = concatAdapter
                    }
                    is Resource.Failure -> {
                        binding.progressBar.visibility = View.GONE
                        Log.d("LiveData_Error", "${result.exception}")
                    }
                }
            })
    }

    override fun onMovieClick(movie: Movie) {
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
            movie.poster_path,
            movie.backdrop_path,
            movie.vote_average.toFloat(),
            movie.vote_count,
            movie.overview,
            movie.title,
            movie.original_language,
            movie.release_date
        )
        findNavController().navigate(action)
    }
}
/**
 * podriamos usar esto, pero al querer sincronizar las llamadas o partes de carga de vista del usuario
 * tendriamos que anidar llamadas, y caemos en mala practica ya que al ser 10 o 15 llamadas, seria un
 * anidado ilegible, se deja esto comentado para guia y lo activo como la mejora
 *
//si estamos en un fragmento no usamos ondestroy u otra cosa
viewModel.fetchUpcomingMovies().observe(viewLifecycleOwner, androidx.lifecycle.Observer { result ->
when(result){
is Resource.Loading -> {
Log.d("LiveData","Loading...")
}
is Resource.Success -> {
Log.d("LiveData","${result.data}")
}
is Resource.Failure -> {
Log.d("LiveData","${result.exception}")
}
}
})

viewModel.fetchTopRatedMovies().observe(viewLifecycleOwner, androidx.lifecycle.Observer { result ->
when(result){
is Resource.Loading -> {
Log.d("LiveDataTR","Loading...")
}
is Resource.Success -> {
Log.d("LiveDataTR_S","${result.data}")
}
is Resource.Failure -> {
Log.d("LiveDataTR_E","${result.exception}")
}
}
})

viewModel.fetchPopularMovies().observe(viewLifecycleOwner, androidx.lifecycle.Observer { result ->
when(result){
is Resource.Loading -> {
Log.d("LiveDataP","Loading...")
}
is Resource.Success -> {
Log.d("LiveDataP_S","${result.data}")
}
is Resource.Failure -> {
Log.d("LiveDataP_E","${result.exception}")
}
}
})
}*/