package org.example.TerminalDeBuses

import com.otakuwear.almacenPrendas
import com.otakuwear.registrarPrenda

fun main(){
    /* Variables */
    val Viajes = mutableListOf<Viaje>()
    var finPrograma = false


    fun registrarViaje(){
        println("-----------------")
        println("REGISTRAR NUEVO VIAJE")

        print("Nombre destino: ")
        val destino = readLine()?.toString()?.trim()

        print("Precio viaje en CLP: $")
        val precioTexto = readLine()?.trim() ?:"0"
        val precio = precioTexto.toIntOrNull()

        if (precio == null || precio <= 0) {
            println("[ERROR] El precio debe ser un número positivo")
            return
        }

        print("Pasajes disponibles: ")
        var pasajesDisponiblesTexto = readLine()?.trim() ?: "0"
        var pasajesDispo= pasajesDisponiblesTexto.toIntOrNull()

        if (pasajesDispo == null || pasajesDispo < 0) {
            println("[ERROR] La cantidad de pasajes disponibles debe ser un número no negativo")
            return
        }

         var pasajesIniciales = 0

        var nuevoViaje = Viaje(destino,precio,pasajesDispo,pasajesIniciales)
        Viajes.add(nuevoViaje)
        println("VIAJE REGISTRADO\n")
    }

    fun mostrarViajes() {
        println("\n--- VIAJES REGISTRADOS ---")

        if (Viajes.isEmpty()) {
            println("No hay viajes registrados en el sistema.")
            println("Usa la opción 1 para agregar el primer viaje.")
            return
        }

        println("Total de Viajes: ${Viajes.size}")
        println("-".repeat(50))

        Viajes.forEachIndexed { indice, viaje ->
            val numero = indice + 1
            println("$numero. ${viaje.mostrarInfo()}")
        }

        println("-".repeat(50))
    }

    fun comprarViaje(){
        println("\n--- COMPRAR VIAJE ---")

        if (Viajes.isEmpty()) {
            println("No hay viajes registrados en el sistema.")
            return
        }

        Viajes.forEachIndexed { indice, viaje ->
            val numero = indice + 1
            println("$numero. ${viaje.mostrarInfo()}")
        }

        println("-".repeat(50))
        println("Seleccione una opción")
        var opcionCompra = readLine()?.toIntOrNull()
        if (opcionCompra == null || opcionCompra <= 0 || opcionCompra > Viajes.size) {
            println("Tiene que ser una opción válida")
            return
        }

        var viajeSeleccionado = Viajes[opcionCompra-1]
        println("Viaje a ${viajeSeleccionado.destino} seleccionado\nIngrese cantidad de pasajes")
        var cantPasajes = readLine()?.toIntOrNull() ?: 0

        viajeSeleccionado.compraViaje(cantPasajes)

    }

    fun calcularMetricas(){
        var cantVendidos = 0
        var sumaValores = 0
        var promedioValores = 0.0
        var pasajesRestantes = 0
        println("---- MÉTRICAS ----")
        Viajes.forEachIndexed { indice, viaje ->
            cantVendidos += viaje.pasajesIniciales
            sumaValores += viaje.pasajesIniciales * viaje.precio
            promedioValores += viaje.precio / Viajes.size
        }

        println("Pasajes vendidos totales: $cantVendidos")
        if(sumaValores == 0){
            println("Total dinero recaudado: No se han vendido pasajes aún")
        }else{
            println("Total dinero recaudado: $${sumaValores}")
        }
        println("Promedio de precios: ${promedioValores}")

        Viajes.forEachIndexed { indice, viaje ->
            val numero = indice + 1
            println("$numero. ${viaje.mostrarInfo()}")
            pasajesRestantes = viaje.pasajesDisponibles - viaje.pasajesIniciales
            println("Pasajes restantes: ${pasajesRestantes}")
        }
    }


    while (finPrograma == false){

        println(
            """
            ------------------------
            TERMINAL DE BUSES
            
            1.- Registrar nuevo viaje
            2.- Mostrar todos los viajes
            3.- Comprar viaje
            4.- Mostrar métricas
            5.- Salir
            """

        )
        print("Selecciona una opción -> ")
        var opcion = readLine()?.toInt()
        when (opcion) {
            1 -> {
                registrarViaje()
            }
            2 -> {
                mostrarViajes()
            }
            3 -> {
                comprarViaje()
            }
            4 ->{
                calcularMetricas()
            }
            5 ->{
                return
            }
        }

    }





}

