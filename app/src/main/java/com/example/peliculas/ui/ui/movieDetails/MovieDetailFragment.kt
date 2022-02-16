package com.example.peliculas.ui.ui.movieDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.peliculas.R
import com.example.peliculas.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    /**
     * binding nos servira como si hicieramos el findbyId pero este busca directamente
     * las vistas y podemos acceder a ellas completamente
     */
    private lateinit var binding: FragmentMovieDetailBinding
    private val args by navArgs<MovieDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailBinding.bind(view)
        Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w500/${args.posterImageUrl}").centerCrop().into(binding.imgMovie)
        Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w500/${args.backgrounImageUrl}").centerCrop().into(binding.imgBackground)
        binding.txtTitle.text = args.title
        binding.txtLanguage.text = "Language ${args.language}"
        binding.txtDescription.text = args.overview
        binding.txtRating.text = "${args.voteAverage} (${args.voteCount} Reviews"
        binding.txtReleased.text = "Released ${args.releaseData}"
    }
}