package com.example.peliculas.ui.Data.Remote

import com.example.peliculas.ui.Data.Model.MovieList
import com.example.peliculas.ui.application.AppConstants
import com.example.peliculas.ui.repository.webService

/**
 * este es llamado de movieRepositoryImpl
 * esta capa de data es encargada de ir a buscar la informacion
 * con retrofit, la va a traer y se la va a dar al repositorio,
 * una vez que la tiene el repositorio, de ahi va al viewmodel
 *
 * ya tenemos el modelo que recibira la info de la api, ahora necesitamos
 * 3 metodos para manipular las 3 listas, upcoming, rated y popular
 *
 * tenemos que crear esa consulta al servidor, ya creamos un webservice
 * entonces dentro del datasource usaremos el webservice
 */
class RemoteMovieDataSource(private val webService: webService) {

    suspend fun getUpcomingMovies(): MovieList = webService.getUpcomingMovies(AppConstants.API_KEY)

    suspend fun getTopRatedMovies(): MovieList = webService.getTopRatedMovies(AppConstants.API_KEY)

    suspend fun getPopularMovies(): MovieList = webService.getPopularMovies(AppConstants.API_KEY)
}