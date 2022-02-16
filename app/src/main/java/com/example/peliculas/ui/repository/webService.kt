package com.example.peliculas.ui.repository

import com.example.peliculas.ui.Data.Model.MovieList
import com.example.peliculas.ui.application.AppConstants
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * es el encargado de usar retrofit para traer la informacion del servidor
 *
 * tenemos como base la url
 * https://api.themoviedb.org/3/movie/upcoming?api_key=2cecf78b0af3def9525eb00e72fb17bb
 * lo descomponemos, la base url la tenemos con una constante
 * luego seria la url, que seria el GET, que sale en la API y despues viene el
 * signo ? que denota un query, y despues viene un "api_key"
 * que lo ponemos igual que en la url
 * ctrl+alt+L para identar
 */
interface webService {
    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") apiKey: String): MovieList

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String): MovieList

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): MovieList
}

object RetrofitClient{
    /**
     * lazy hace q se inicialice en el momento que se usara, y no antes
     */
    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
                //esto hara q se transforme el json del api al gson de google q usaremos
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(webService::class.java)
    }
}
