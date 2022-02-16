package com.example.peliculas.ui.Data.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * este es el corazon de la app, la info que se mostrara
 * al usuario y se obtiene desde la base o servidor
 * en este caso la api de movie, nos da una objeto, ahora tenemos
 * que crear aqui un modelo que lo entienda y podamos
 * mostrar esos datos
 *
 * usaremos las variables como la key del modelo de la api
 * tenemos que revisar q sean los mismos, ya que sera el modelo
 * que usaremos para q soporte la info de la web api
 *
 * @SerializedName("adult") //esto hara q podamos cambiar el nombre en nuestro modelo
 * y al serializar lo buscara en la api como adult,
 */

data class Movie(
    val id: Int = -1,
    val adult: Boolean = false,
    //val genre_ids: List<Int> = listOf(), //se inicia una lista vacia
    val backdrop_path: String = "",
    val original_title: String = "",
    val original_language: String = "",
    val overview: String = "",
    val popularity: Double = -1.0,
    val poster_path: String = "",
    val release_date: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Double = -1.0,
    val vote_count: Int = -1,
    var movie_type: String = ""
)

data class MovieList(val results: List<Movie> = listOf())

/**
 * para base de datos room, se necesitan 2 modelos, uno vive en la base de datos
 * y el otro es para el parseo de informacion del servidor
 * y aqui generamos nuestra tabla de sql
 *
 * este no es soportado como campo de sql, asi esque lo quitamos
 * val genre_ids: List<Int> = listOf(), //se inicia una lista vacia
 */

@Entity
data class MovieEntity(
    @PrimaryKey
    val id: Int = -1,
    @ColumnInfo(name = "adult")
    val adult: Boolean = false,
    @ColumnInfo(name = "backdrop_path")
    val backdrop_path: String = "",
    @ColumnInfo(name = "original_title")
    val original_title: String = "",
    @ColumnInfo(name = "original_language")
    val original_language: String = "",
    @ColumnInfo(name = "overview")
    val overview: String = "",
    @ColumnInfo(name = "popularity")
    val popularity: Double = -1.0,
    @ColumnInfo(name = "poster_path")
    val poster_path: String = "",
    @ColumnInfo(name = "release_date")
    val release_date: String = "",
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "video")
    val video: Boolean = false,
    @ColumnInfo(name = "vote_average")
    val vote_average: Double = -1.0,
    @ColumnInfo(name = "vote_count")
    val vote_count: Int = -1,
    @ColumnInfo(name = "movie_type")
    val movie_type: String = ""
)

//de aqui cualquier objeto movieentity puede "convertirse a movielist"
fun List<MovieEntity>.toMovieList(): MovieList {
    val resultList = mutableListOf<Movie>()
    this.forEach { movieEntity->
        resultList.add(movieEntity.toMovie())
    }
    return MovieList(resultList)
}

//para que se convierta a movie list, primero tenemos que regresar un movie
fun MovieEntity.toMovie(): Movie = Movie(
    this.id,
    this.adult,
    this.backdrop_path,
    this.original_title,
    this.original_language,
    this.overview,
    this.popularity,
    this.poster_path,
    this.release_date,
    this.title,
    this.video,
    this.vote_average,
    this.vote_count,
    this.movie_type
)

fun Movie.toMovieEntity(movieType: String): MovieEntity = MovieEntity(
    this.id,
    this.adult,
    this.backdrop_path,
    this.original_title,
    this.original_language,
    this.overview,
    this.popularity,
    this.poster_path,
    this.release_date,
    this.title,
    this.video,
    this.vote_average,
    this.vote_count,
    movie_type = movieType
)