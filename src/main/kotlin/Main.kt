package org.example

import org.example.com.otakuwear.models.Viaje

fun main() {

    var tripValdivia = Viaje("Puerto Montt","Valdivia",12000,20,20)
    var tripSantiago = Viaje("Puerto Montt","Santiago",30000,30,30)
    var viajesDisponibles = mutableListOf<Viaje>()
    viajesDisponibles.add(tripSantiago)
    viajesDisponibles.add(tripValdivia)

    var menuActivo = true

    while (menuActivo){

        println("""
            ---------------------------------
                    Terminal de buses
            ---------------------------------
            Elige una opcion ->
            1.- Registrar viaje
            2.- Ver viajes
            3.- Comprar Pasaje
            4.- Ver Metricas
            5.- Salir
            Ingrese una opcion: 
        """.trimIndent())

        val opcion = readln().toIntOrNull()

        when (opcion){
            1 -> crearViaje(viajesDisponibles)
            2 -> verViajes(viajesDisponibles)
            3 -> comprarPasaje(viajesDisponibles)
            4 -> verMetricas(viajesDisponibles)
            5 -> {
                println("Gracias por usar el sistema! Hasta pronto.")
                menuActivo = false
            }
            else -> println("Por favor, elige una opcion valida")
        }
    }
}
// * 1. Crear un viaje nuevo
fun crearViaje(viajes: MutableList<Viaje>){
    println("Ingresa el origen: ")
    var origen = readLine()?.trim() ?: ""

    if (origen.isBlank()){
        println("\nEl nombre es obligatorio\n")
        return
    }

    println("\nIngresa el destino: ")
    var destino = readln().toString();

    println("\nIngresa el precio: ")
    var precio = readln().toInt();

    println("\nIngresa la cantidad de pasajes iniciales: ")
    var pasajesIni = readln().toInt();

    println("\nIngresa la cantidad de pasajes disponibles: ")
    var pasajesDisp = readln().toInt();

    if (precio <= 0 || pasajesIni <= 0) {
        println("Error! El precio y la cantidad de pasajes deben ser números positivos.")
        return
    }

    println("""
        Se creó el Viaje con los siguentes datos:
            Origen: ${origen}
            Destino: ${destino}
            Precio: ${precio}
            Pasajes Iniciales: ${pasajesIni}
            Pasajes Disponibles: ${pasajesDisp}
    """.trimMargin())
    val nuevoViaje = Viaje(origen,destino,precio,pasajesIni,pasajesDisp)

    viajes.add(nuevoViaje)
}
// * 2. Ver Viajes
fun verViajes(viajes: List<Viaje>) {
    println("\n--- Viajes Disponibles ---")
    if (viajes.isEmpty()) {
        println("No hay viajes registrados en el sistema.")
        return
    }

    viajes.forEachIndexed { index, viaje ->
        println("""
        ${index + 1}. Origen: ${viaje.origen}, Destino: ${viaje.destino}
           Precio: $${viaje.precio}
           Pasajes Disponibles: ${viaje.pasajesDisponibles}
        """.trimIndent())
    }
    println("---------------------------------")
}
// * 3. Comprar Pasaje
fun comprarPasaje(viajes: MutableList<Viaje>) {
    println("\n--- Comprar Pasaje ---")
    if (viajes.isEmpty()) {
        println("No hay viajes disponibles para comprar.")
        return
    }

    verViajes(viajes)
    print("Selecciona el numero del viaje que deseas comprar: ")
    val seleccion = readln().toIntOrNull()

    if (seleccion != null && seleccion > 0 && seleccion <= viajes.size) {
        val viajeSeleccionado = viajes[seleccion - 1]
        if (viajeSeleccionado.pasajesDisponibles > 0) {
            viajeSeleccionado.pasajesDisponibles--
            println("\nCompra exitosa! Ha comprado un pasaje para ${viajeSeleccionado.destino}.")
            println("Pasajes restantes para este destino: ${viajeSeleccionado.pasajesDisponibles}")
        } else {
            println("\nLo sentimos, no quedan pasajes para el viaje a ${viajeSeleccionado.destino}.")
        }
    } else {
        println("\nError. Por favor, elige un número de la lista.")
    }
}
// * 4. Ver metricas/estadisticas
fun verMetricas(viajes: List<Viaje>) {
    println("\n--- Metricas ---")
    if (viajes.isEmpty()) {
        println("No hay datos para calcular métricas.")
        return
    }

    val totalPasajesVendidos = viajes.sumOf { it.pasajesIniciales - it.pasajesDisponibles }
    val recaudacionTotal = viajes.sumOf { (it.pasajesIniciales - it.pasajesDisponibles) * it.precio }
    val viajeMasVendido = viajes.maxByOrNull { it.pasajesIniciales - it.pasajesDisponibles }

    println("Total de pasajes vendidos: $totalPasajesVendidos")
    println("Recaudación total: $${recaudacionTotal}")
    if (viajeMasVendido != null && (viajeMasVendido.pasajesIniciales - viajeMasVendido.pasajesDisponibles > 0)) {
        println("Viaje con más pasajes vendidos: ${viajeMasVendido.destino} (${viajeMasVendido.pasajesIniciales - viajeMasVendido.pasajesDisponibles} vendidos)")
    } else {
        println("Aún no se han vendido pasajes.")
    }
    println("---------------------------------")
}