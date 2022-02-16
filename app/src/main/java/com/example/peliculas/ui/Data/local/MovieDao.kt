package com.example.peliculas.ui.Data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.peliculas.ui.Data.Model.MovieEntity

/**
 * DAO, data access objets
 */

@Dao
interface MovieDao {

    /**
     * no va conforme a la ejecucion del programa,
     * esta es una corutina, y puede tardar tiempo enb ejecutarse
     * @insert puede q venga una pelicula que ya este
     * y solo agregaria copias de la misma, para evitar esto
     * existe un "resultor" de conflictos, y la accion a tomar
     * es que la reemplace
     */
    @Query("SELECT * FROM movieentity")
    suspend fun getAllMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movie: MovieEntity)
}