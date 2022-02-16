package com.example.peliculas.ui.ui.movie.adapters.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.peliculas.databinding.TopRatedMoviesRowBinding
import com.example.peliculas.databinding.UpcomingMovieRowBinding
import com.example.peliculas.ui.coreM.BaseConcatHolder
import com.example.peliculas.ui.ui.movie.adapters.MovieAdapter

class TopRatedConcatAdapter(private val moviesAdapter: MovieAdapter) : RecyclerView.Adapter<BaseConcatHolder<*>>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding = TopRatedMoviesRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when(holder){
            is ConcatViewHolder -> holder.bind(moviesAdapter)
        }
    }

    //porque solo estamos pasando 1 adapter y no una lista
    override fun getItemCount(): Int = 1

    private inner class ConcatViewHolder(val binding: TopRatedMoviesRowBinding): BaseConcatHolder<MovieAdapter>(binding.root){
        override fun bind(adapter: MovieAdapter) {
            binding.rvTopRatedMovies.adapter = adapter
        }
    }

}