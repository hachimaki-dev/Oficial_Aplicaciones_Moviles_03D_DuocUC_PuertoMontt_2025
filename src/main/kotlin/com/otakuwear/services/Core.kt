package org.example.com.otakuwear.services

import org.example.com.otakuwear.models.Viaje

class Core {
    var listaViajes = mutableListOf<Viaje>()

    fun registrarNuevoViaje() {
        println("Ingresa el destino del viaje:")
        var destino: String = readLine() ?: ""
        println("Ingresa el precio del viaje:")
        var precio: Int = readln().toInt()
        println("Ingresa la cantidad de pasajes disponibles:")
        var pasajesDisponibles: Int = readln().toInt()
        println("Ingresa la cantidad de pasajes iniciales:")
        var pasajesIniciales: Int = readln().toInt()

        var nuevoViaje: Viaje = Viaje(destino, precio, pasajesDisponibles, pasajesIniciales)

        listaViajes.add(nuevoViaje)
    }

    fun verViajes() {
        var contador: Int = 1
        listaViajes.forEach {
            println("=".repeat(50))
            println("Viaje ${contador}")
            println("Destino: ${it.destino}")
            println("Precio: ${it.precio}")
            println("Pasajes disponibles: ${it.pasajesDisponibles}")
            println("Pasajes iniciales: ${it.pasajesIniciales}")
            println("=".repeat(50))
            contador++
        }
    }

    fun verViajesDisponibles(){
        var contador: Int = 1
        listaViajes.forEach {
            if(it.pasajesDisponibles > 0){
                println("=".repeat(50))
                println("Viaje ${contador}")
                println("Destino: ${it.destino}")
                println("Precio: ${it.precio}")
                println("Pasajes disponibles: ${it.pasajesDisponibles}")
                println("Pasajes iniciales: ${it.pasajesIniciales}")
                println("=".repeat(50))
                contador++
            }
        }
    }

    fun validarViajeDisponible(pasaje: Int) {
        var contador: Int = 0
        if (listaViajes.isEmpty()) {
            println("Lo sentimos, no hay viajes disponibles para comprar.")
            return
        }

        listaViajes.forEach {
            if(it.pasajesDisponibles > 0){
                if(contador == pasaje-1){
                    listaViajes[contador] = Viaje(it.destino, it.precio, it.pasajesDisponibles-1, it.pasajesDisponibles)
                }
                contador++
            }
        }
    }

    fun comprarPasaje() {
        verViajesDisponibles()
        println("Seleccione un pasaje: ")
        var pasaje:  Int = readLine()?.toInt() ?: 0
        validarViajeDisponible(pasaje)
        // No es necesario validar si el viaje esta disponible ya que solo deja escoger viajes disponibles
    }

    fun verMetricas() {
        val pasajesVendidosTotales =   listaViajes.sumOf{it.pasajesIniciales-it.pasajesDisponibles}
        val recaudacionTotal = listaViajes.sumOf{it.precio*pasajesVendidosTotales}
        val precioPromedio = listaViajes.map{it.precio}.average()
        // Mostrar resultados

        println("Los pasajes vendidos totales son: $pasajesVendidosTotales")
        println("La recaudacion total es: $recaudacionTotal")
        println("El precio promedio es: $precioPromedio")
    }

}