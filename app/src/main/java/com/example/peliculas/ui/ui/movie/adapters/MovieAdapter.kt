package com.example.peliculas.ui.ui.movie.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.peliculas.databinding.MovieItemBinding
import com.example.peliculas.ui.Data.Model.Movie
import com.example.peliculas.ui.coreM.BaseViewHolder

/**
 * el asterisco avisa q reciba cualquier tipo de viewholder
 * se pueden implementar los metodos con alt+enter
 * lo primero es ir a buscar esa intterfaz, que inflara cada uno de los elementos
 * de la lista
 */
class MovieAdapter(
    private val movieList: List<Movie>,
    private val itemClickListener: OnMovieClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    /**
     * reaccion al cliclear la pantalla, evento
     */
    interface OnMovieClickListener {
        fun onMovieClick(movie: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = MoviesViewHolder(itemBinding, parent.context)
//obtiene la posicion
        itemBinding.root.setOnClickListener {
            val position =
                holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                    ?: return@setOnClickListener
            itemClickListener.onMovieClick(movieList[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        //cada elemento de la lista, cargue cada elemento
        when (holder) {
            is MoviesViewHolder -> holder.bind(movieList[position])
        }
    }

    override fun getItemCount(): Int = movieList.size

    /**
     * forma parte de la clase, cuando muera la clase padre, en este caso mavie adapter
     * morira la inner class, si no la clase quedaria viva y ocuparia memoria
     * root hace referencia a toda la layout completa
     */
    private inner class MoviesViewHolder(
        val binding: MovieItemBinding,
        val context: Context
    ) : BaseViewHolder<Movie>(binding.root) {
        override fun bind(item: Movie) {
            Glide.with(context).load("https://image.tmdb.org/t/p/w500/${item.poster_path}")
                .centerCrop().into(binding.imgMovie)
        }

    }

}