package com.example.peliculas.ui.coreM

import java.lang.Exception

//clases selladas, esta retornora 3 estados, de la presentacion
//del liveData
sealed class Resource <out T> {
    class Loading<out T>: Resource<T>()
    data class Success<out T>(val data: T): Resource<T>()
    data class Failure(val exception: Exception): Resource<Nothing>()
}