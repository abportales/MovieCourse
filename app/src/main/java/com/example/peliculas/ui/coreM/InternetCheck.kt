package com.example.peliculas.ui.coreM

import kotlinx.coroutines.coroutineScope
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketAddress

object InternetCheck {

    /**
     * corutinas para operaciones asincronas (no sabemos cuando terminaran
     * ya que en este caso checara si hay ping a internet)
     */
    suspend fun isNetworkAvailable() = coroutineScope {
        return@coroutineScope try {
            val sock = Socket()
            val socketAddress = InetSocketAddress("8.8.8.8",53)
            sock.connect(socketAddress,2000)
            sock.close()
            true
        }catch (e: IOException){
            false
        }
    }
}