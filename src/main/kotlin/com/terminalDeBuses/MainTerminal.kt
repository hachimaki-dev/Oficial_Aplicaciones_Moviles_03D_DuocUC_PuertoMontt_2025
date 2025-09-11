package com.terminalDeBuses

data class Viaje(
    val destino: String,
    val precio: Int, // CLP
    var pasajesDisponibles: Int,
    val pasajesIniciales: Int
) {
    fun validar(): Boolean {
        return destino.isNotBlank() && precio > 0 && pasajesIniciales > 0 && pasajesDisponibles in 0..pasajesIniciales
    }

    fun vendidos(): Int = pasajesIniciales - pasajesDisponibles

    fun mostrarInfo(): String {
        return "Destino: $destino - Precio: ${'$'}${precio} - Disponibles: ${pasajesDisponibles}/${pasajesIniciales}"
    }
}

val listaViajes = mutableListOf<Viaje>()


fun mostrarMenu() {
    println("\n" + "=".repeat(45))
    println(" TERMINAL PM - SISTEMA DE VENTAS DE PASAJES")
    println("=".repeat(45))
    println("1. Registrar viaje")
    println("2. Ver viajes")
    println("3. Comprar pasaje")
    println("4. Ver métricas")
    println("5. Salir")
    println("-".repeat(45))
    print("Selecciona una opción [1-5]: ")
}

fun registrarViaje() {
    println("\n--- REGISTRO DE VIAJE ---")
    try {
        print("Destino: ")
        val destino = readLine()?.trim() ?: ""
        if (destino.isBlank()) {
            println("[ERROR] El destino es obligatorio")
            return
        }

        print("Precio (CLP): ${'$'}")
        val precioTexto = readLine()?.trim() ?: ""
        val precio = precioTexto.toIntOrNull()
        if (precio == null || precio <= 0) {
            println("[ERROR] El precio debe ser un número entero positivo")
            return
        }

        print("Cantidad de pasajes (stock inicial): ")
        val stockTexto = readLine()?.trim() ?: ""
        val stock = stockTexto.toIntOrNull()
        if (stock == null || stock <= 0) {
            println("[ERROR] La cantidad de pasajes debe ser un entero positivo")
            return
        }

        val nuevoViaje = Viaje(
            destino = destino,
            precio = precio,
            pasajesDisponibles = stock,
            pasajesIniciales = stock
        )

        if (nuevoViaje.validar()) {
            listaViajes.add(nuevoViaje)
            println("\n[ÉXITO] Viaje registrado:")
            println("-> ${nuevoViaje.mostrarInfo()}")
        } else {
            println("[ERROR] Los datos del viaje no son válidos")
        }
    } catch (ex: Exception) {
        println("[ERROR INESPERADO] ${ex.message}")
        println("Por favor, intenta nuevamente")
    }
}

fun verViajes() {
    println("\n--- LISTA DE VIAJES ---")
    if (listaViajes.isEmpty()) {
        println("No hay viajes registrados. Usa la opción 1 para agregar uno.")
        return
    }
    listaViajes.forEachIndexed { index, viaje ->
        println("${index + 1}. ${viaje.mostrarInfo()}")
    }
}

fun comprarPasaje() {
    println("\n--- COMPRA DE PASAJE ---")
    if (listaViajes.isEmpty()) {
        println("No hay viajes disponibles para comprar.")
        return
    }
    verViajes()
    print("\nIngresa el número de viaje a comprar: ")
    val indiceTexto = readLine()?.trim() ?: ""
    val indice = indiceTexto.toIntOrNull()
    if (indice == null || indice !in 1..listaViajes.size) {
        println("[ERROR] Selección inválida")
        return
    }
    val viaje = listaViajes[indice - 1]
    if (viejeSinDisponibles(viaje)) {
        println("[INFO] No quedan pasajes disponibles para este destino")
        return
    }
    viaje.pasajesDisponibles -= 1
    println("\n[ÉXITO] Compra realizada. Quedan ${viaje.pasajesDisponibles} pasajes para ${viaje.destino}.")
}

private fun viejeSinDisponibles(viaje: Viaje): Boolean = viaje.pasajesDisponibles <= 0

fun verMetricas() {
    println("\n--- MÉTRICAS ---")
    if (listaViajes.isEmpty()) {
        println("No hay viajes registrados.")
        return
    }

    val totalVendidos = listaViajes.sumOf { it.vendidos() }
    val recaudacionTotal = listaViajes.sumOf { it.vendidos() * it.precio }
    val promedioPrecio = listaViajes.map { it.precio }.average()

    println("Pasajes vendidos (totales): ${totalVendidos}")
    println("Recaudación total: ${'$'}${recaudacionTotal}")
    println("Precio promedio de viajes: ${'$'}${"%.0f".format(promedioPrecio)}")
}

fun main() {
    while (true) {
        mostrarMenu()
        val opcionTexto = readLine()?.trim() ?: ""
        when (opcionTexto) {
            "1" -> registrarViaje()
            "2" -> verViajes()
            "3" -> comprarPasaje()
            "4" -> verMetricas()
            "5" -> {
                println("\nGracias por usar el sistema. ¡Hasta pronto!")
                return
            }
            else -> println("[ERROR] Opción inválida. Intenta nuevamente.")
        }
    }
}


