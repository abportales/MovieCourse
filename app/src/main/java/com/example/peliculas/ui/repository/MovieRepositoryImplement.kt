package com.example.peliculas.ui.repository

import com.example.peliculas.ui.Data.Model.MovieList
import com.example.peliculas.ui.Data.Model.toMovieEntity
import com.example.peliculas.ui.Data.Remote.RemoteMovieDataSource
import com.example.peliculas.ui.Data.local.LocalMovieDataSource
import com.example.peliculas.ui.coreM.InternetCheck

/**
 * aqui se llega desde la peticion del viewmodel, se llega aqui primero
 * al implement, aqui, este repositorio es el que tiene que saber a donde
 * ira a buscar la informacion, si va a una base de datos
 * local, o va a un servidor
 *
 * cada paquete debe ser responsable de hacer solo 1 cosa y no mas de una
 *
 * este hara una llamada a data, que es donde esta la informacion
 *
 * nos saldra el foquito, de que los metodos no han sido implementado
 * le damos alt enter seleccionamos todos y nos los crear automaticamente
 *
 * (private val dataSource: MovieDataSource)
 * se agrego esto para la comunicacion en cadena de metodos
 */
class MovieRepositoryImplement(
    private val dataSourceRemote: RemoteMovieDataSource,
    private val dataSourceLocal: LocalMovieDataSource
) : MovieRepository {

    override suspend fun getUpcomingMovies(): MovieList {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getUpcomingMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("upcoming"))
            }
            dataSourceLocal.getUpcomingMovies()
        } else {
            dataSourceLocal.getUpcomingMovies()
        }
    }

    override suspend fun getTopRatedMovies(): MovieList {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getTopRatedMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("toprated"))
            }
            dataSourceLocal.getTopRatedMovies()
        } else {
            dataSourceLocal.getTopRatedMovies()
        }
    }

    override suspend fun getPopularMovies(): MovieList {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getPopularMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("popular"))
            }
            dataSourceLocal.getPopularMovies()
        } else {
            dataSourceLocal.getPopularMovies()
        }
    }

}