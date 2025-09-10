package org.example.com.otakuwear.Model

open class Viaje(
    var destino: String,
    var precio: Int,
    var pasajeros: Int)
{
    fun mostrarViaje(){
        println("destino: $destino")
        println("precio: $precio")
        println("pasajero: $pasajeros")
    }
}

