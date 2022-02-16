package com.example.peliculas.ui.Data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.peliculas.ui.Data.Model.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    //crea la instancia con un singleton, para evitar que tengamos mas de 1 instancia
    companion object {

        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {

            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "movie_table"
            ).build()

            return INSTANCE!!

            //este codigo es de la documentacion oficial de kotlin
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDataBase::class.java,
//                    "movie_table"
//                ).build()
//                INSTANCE = instance
//                // return instance
//                instance
//            }
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}