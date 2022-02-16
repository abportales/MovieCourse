package com.example.peliculas.ui.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.peliculas.ui.coreM.Resource
import com.example.peliculas.ui.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

/**
 * Si llegamos aqui es porque la activity nos ha solicitado informacion
 * este a su vez, solicita informacion al repositorio,
 * llama al repositorio y le dice "cual informacion" necesita
 * el view model no necesita saber de donde viene o quien la
 * tiene
 *
 *
 * despues del flujo de info y que ya vamos a mostrar la info
 * se usa con un liveData, para que la vista lo pueda recibir
 * y se va a ver reflejado en pantalla, ese valor
 *
 * esta capa(presentacion) vive entre medio del repositorio y la vista
 *
 * si vemos una pantalla y giramos el cel se recrea esa actividad
 * o fragmento
 *
 * para usar viemodel solo tenemos que extenderlo
 *
 * para buscar info, el viewmodel tiene q ir al repositorio
 */
class MovieViewModel(private val repo: MovieRepository): ViewModel() {

    fun fetchMainScreenMovies() = liveData(Dispatchers.IO){
        //estado de: carga || exito || fallo
        emit(Resource.Loading())
        try {
            emit(Resource.Success(Triple(repo.getUpcomingMovies(),repo.getTopRatedMovies(),repo.getPopularMovies())))
            //y surge la dua, como hacer 4, 5 o 20, solucion: data class
            //data class NTuple4<T1, T2,T3,T4>(val t1: T1, val t2: T2, val t3: T3, val t4: T4)
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }

    }


/**
 * podriamos usar esto, pero al querer sincronizar las llamadas o partes de carga de vista del usuario
 * tendriamos que anidar llamadas, y caemos en mala practica ya que al ser 10 o 15 llamadas, seria un
 * anidado ilegible, se deja esto comentado para guia y lo activo como la mejora
 *
    fun fetchUpcomingMovies() = liveData(Dispatchers.IO){
        //estado de: carga || exito || fallo
        emit(Resource.Loading())

        try {
            emit(Resource.Success(repo.getUpcomingMovies()))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }

    }

    fun fetchTopRatedMovies() = liveData(Dispatchers.IO){
        //estado de: carga || exito || fallo
        emit(Resource.Loading())

        try {
            emit(Resource.Success(repo.getTopRatedMovies()))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }

    }

    fun fetchPopularMovies() = liveData(Dispatchers.IO){
        //estado de: carga || exito || fallo
        emit(Resource.Loading())

        try {
            emit(Resource.Success(repo.getPopularMovies()))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }

    }*/
}
/**
 * no deberiamos pasar datos en la vista como constructores
 * pero podemos hacerlo con el uso de factory
 * ctrl + i para q se cree la implementacion
 */

class MovieViewModelFactory(private val repo: MovieRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }
}

