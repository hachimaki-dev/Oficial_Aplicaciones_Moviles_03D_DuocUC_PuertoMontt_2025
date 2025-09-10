package org.example.com.otakuwear.Service
import org.example.com.otakuwear.Model.Viaje;
class GestorViajes {
    private val viajes = mutableListOf<Viaje>()

    fun addViaje(nuevoviaje: Viaje): Boolean {
        return try {
            println("Agrega destino")
            val Ndestino:String= readln().toString()
            println("Agrega precio")
            val Nprecio:Int=readln().toInt()
            println("Agrega numero de pasajeros")
            val Npasajero:Int=readln().toInt()
            val nuevoviaje=Viaje(Ndestino,Nprecio,Npasajero)
            viajes.add(nuevoviaje)
            println("Viaje agregado")
            true
        }catch (e:Exception){
            println("Error al registrar viaje")
            false
        }
    }
    fun mostrarViajes(viaje: Viaje){
        if (viajes.isEmpty()){
            println("No se detectan viajes")
            return
        }
        println("\n===TODOS LOS VIAJES===")
        viajes.forEach {it.mostrarViaje()}
    }
}
