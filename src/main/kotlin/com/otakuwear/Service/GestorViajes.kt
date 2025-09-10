package org.example.com.otakuwear.Service
import org.example.com.otakuwear.Model.Viaje;
class GestorViajes {
    private val viajes = mutableListOf<Viaje>()

    fun addViaje(viaje: Viaje): Boolean {
        return try {
            println("Agrega destino")
            var Ndestino:String= readln().toString()
            println("Agrega precio")
            var Nprecio:Int=readln().toInt()
            println("Agrega numero de pasajeros")
            var Npasajero:Int=readln().toInt()
            val viaje=Viaje(Ndestino,Nprecio,Npasajero)
            viajes.add(viaje)
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