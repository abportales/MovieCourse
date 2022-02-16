package com.example.peliculas.ui.repository

import com.example.peliculas.ui.Data.Model.MovieList


/**
 * una interfaz, hace los metodos que se usaran despues en la
 * implementacion, las q habiamos declarado en el modelo source
 * si dejamos los metodos como fun, nunca sabremos cuando el servidor
 * nos ha respondido o no, para eso usaremos corutinas, que son nuevas
 * de android, y para implementarlas se usa suspend, esto hace q se queden
 * pendientes hasta q terminen
 */
interface MovieRepository {
    suspend fun getUpcomingMovies(): MovieList
    suspend fun getTopRatedMovies(): MovieList
    suspend fun getPopularMovies(): MovieList
}