package org.example.terminalbuses

fun main() {
    data class Viaje(
        val destino: String,
        val precio: Int,
        var pasajesDisponibles: Int,
        val pasajesIniciales: Int
    )

    val viajes = mutableListOf<Viaje>()

    fun mostrarMenu() {
        println("\n=== Terminal de Buses Puerto Montt ===")
        println("1. Registrar Viaje")
        println("2. Ver Viajes")
        println("3. Comprar Pasaje")
        println("4. Ver Métricas")
        println("5. Salir")
        print("Elige una opción: ")
    }

    fun registrarViaje() {
        print("Ingrese destino: ")
        val destino = readLine()?.trim().orEmpty()
        if (destino.isEmpty()) {
            println("El destino no puede estar vacío.")
            return
        }

        print("Ingrese precio del pasaje: ")
        val precio = readLine()?.toIntOrNull()
        if (precio == null || precio <= 0) {
            println("Precio inválido.")
            return
        }

        print("Ingrese cantidad de pasajes: ")
        val cantidad = readLine()?.toIntOrNull()
        if (cantidad == null || cantidad <= 0) {
            println("Cantidad inválida.")
            return
        }

        viajes.add(Viaje(destino, precio, cantidad, cantidad))
        println("Viaje registrado con éxito.")
    }


    fun verViajes() {
        if (viajes.isEmpty()) {
            println("No hay viajes registrados.")
        } else {
            println("\n=== Lista de Viajes ===")
            viajes.forEachIndexed { index, v ->
                println("${index + 1}. ${v.destino} - $${v.precio} - " +
                        "Disponibles: ${v.pasajesDisponibles}/${v.pasajesIniciales}")
            }
        }
    }

    fun comprarPasaje() {
        if (viajes.isEmpty()) {
            println("No hay viajes disponibles para comprar.")
            return
        }

        verViajes()
        print("Seleccione el número de viaje: ")
        val opcion = readLine()?.toIntOrNull()
        if (opcion == null || opcion !in 1..viajes.size) {
            println("Opción inválida.")
            return
        }

        val viaje = viajes[opcion - 1]
        if (viaje.pasajesDisponibles > 0) {
            viaje.pasajesDisponibles--
            println("Pasaje comprado a ${viaje.destino} por $${viaje.precio}.")
        } else {
            println("No quedan pasajes disponibles para ${viaje.destino}.")
        }
    }

    fun verMetricas() {
        if (viajes.isEmpty()) {
            println("No hay viajes registrados.")
            return
        }

        val vendidosTotales = viajes.sumOf { it.pasajesIniciales - it.pasajesDisponibles }
        val recaudacionTotal = viajes.sumOf { (it.pasajesIniciales - it.pasajesDisponibles) * it.precio }
        val precioPromedio = if (vendidosTotales > 0) recaudacionTotal / vendidosTotales else 0

        println("\n=== Métricas ===")
        println("Pasajes vendidos: $vendidosTotales")
        println("Recaudación total: $$recaudacionTotal")
        println("Precio promedio: $$precioPromedio")
    }


    while (true) {
        mostrarMenu()
        when (readLine()) {
            "1" -> registrarViaje()
            "2" -> verViajes()
            "3" -> comprarPasaje()
            "4" -> verMetricas()
            "5" -> {
                println("Saliendo del sistema. Gracias por usar el programa.")
                break
            }
            else -> println("Opción inválida, intente de nuevo.")
        }
    }
}
