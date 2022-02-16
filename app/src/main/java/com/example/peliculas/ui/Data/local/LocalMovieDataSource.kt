package com.example.peliculas.ui.Data.local

import com.example.peliculas.ui.Data.Model.MovieEntity
import com.example.peliculas.ui.Data.Model.MovieList
import com.example.peliculas.ui.Data.Model.toMovieList

class LocalMovieDataSource(private val movieDao: MovieDao) {

    /**
     * debemos filtar q peliculas son y para eso debemos ir a la creacion de la tabla
     * y agregar movieType
     *
     * si agarramos lo que retorna la DB es una llista de entidades, pero nosotros
     * necesitamos una lista de peliculas
     * para esto haremos una extension fun en movie
     *
     * para separar las peliculas de las 3 secciones, lo haremos con el type
     * que agregamos y con el metodo filter
     */
    suspend fun getUpcomingMovies(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == "upcoming" }.toMovieList()
    }

    suspend fun getTopRatedMovies(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == "toprated" }.toMovieList()
    }

    suspend fun getPopularMovies(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == "popular" }.toMovieList()
    }

    suspend fun saveMovie(movie: MovieEntity){
        movieDao.saveMovie(movie)
    }
}