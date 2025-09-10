package org.example.com.terminalbusesexample

/**
 * Sistema de Venta de Pasajes - Terminal de Buses de Puerto Montt
 *
 * Este sistema permite:
 * 1. Registrar viajes disponibles
 * 2. Ver todos los viajes registrados
 * 3. Comprar pasajes
 * 4. Ver métricas de ventas
 * 5. Salir del sistema
 */

// Clase que representa un viaje de bus
data class Viaje(
    val destino: String,
    val precio: Int,
    var pasajesDisponibles: Int,
    val pasajesIniciales: Int = pasajesDisponibles
) {
    // Propiedad calculada para obtener pasajes vendidos
    val pasajesVendidos: Int
        get() = pasajesIniciales - pasajesDisponibles

    // Función para mostrar información del viaje de forma organizada
    fun mostrarInfo(indice: Int): String {
        return """
        ${indice}. Destino: $destino
           Precio: $${"%,d".format(precio)} CLP
           Disponibles: $pasajesDisponibles pasajes
           Vendidos: $pasajesVendidos pasajes
        """.trimIndent()
    }
}

// Lista mutable para almacenar todos los viajes
val viajesDisponibles = mutableListOf<Viaje>()

fun main() {
    println("🚌 ¡Bienvenido al Terminal de Buses de Puerto Montt! 🚌")
    println("=" * 50)

    // Bucle principal del programa
    while (true) {
        mostrarMenu()

        print("\n👉 Selecciona una opción (1-5): ")
        val opcion = readLine()

        println() // Línea en blanco para mejor legibilidad

        when (opcion) {
            "1" -> registrarViaje()
            "2" -> verViajes()
            "3" -> comprarPasaje()
            "4" -> verMetricas()
            "5" -> {
                println("👋 ¡Gracias por usar nuestro sistema!")
                println("¡Que tengas un buen viaje! 🚌💨")
                break
            }
            else -> {
                println("❌ Opción inválida. Por favor selecciona un número del 1 al 5.")
            }
        }

        // Pausa para que el usuario pueda leer la información
        println("\n" + "-" * 50)
        print("Presiona ENTER para continuar...")
        readLine()
        println()
    }
}

/**
 * Muestra el menú principal del sistema
 */
fun mostrarMenu() {
    println("""
    ┌─────────────────────────────────────────────────┐
    │            MENÚ PRINCIPAL                       │
    ├─────────────────────────────────────────────────┤
    │  1. 📝 Registrar nuevo viaje                    │
    │  2. 👀 Ver viajes disponibles                   │
    │  3. 🎫 Comprar pasaje                           │
    │  4. 📊 Ver métricas de ventas                   │
    │  5. 🚪 Salir                                    │
    └─────────────────────────────────────────────────┘
    """.trimIndent())
}

/**
 * Registra un nuevo viaje en el sistema
 * Incluye validaciones para asegurar datos correctos
 */
fun registrarViaje() {
    println("📝 REGISTRAR NUEVO VIAJE")
    println("=" * 25)

    // Solicitar y validar destino
    var destino = ""
    while (destino.isBlank()) {
        print("🏙️  Ingresa el destino: ")
        destino = readLine()?.trim() ?: ""
        if (destino.isBlank()) {
            println("❌ El destino no puede estar vacío. Intenta nuevamente.")
        }
    }

    // Solicitar y validar precio
    var precio = 0
    while (precio <= 0) {
        print("💰 Ingresa el precio del pasaje (CLP): $")
        try {
            precio = readLine()?.toIntOrNull() ?: 0
            if (precio <= 0) {
                println("❌ El precio debe ser un número positivo mayor a 0.")
            }
        } catch (e: Exception) {
            println("❌ Por favor ingresa un número válido.")
        }
    }

    // Solicitar y validar cantidad de pasajes
    var pasajes = 0
    while (pasajes <= 0) {
        print("🎫 Ingresa la cantidad de pasajes disponibles: ")
        try {
            pasajes = readLine()?.toIntOrNull() ?: 0
            if (pasajes <= 0) {
                println("❌ La cantidad de pasajes debe ser un número positivo mayor a 0.")
            }
        } catch (e: Exception) {
            println("❌ Por favor ingresa un número válido.")
        }
    }

    // Crear y agregar el viaje
    val nuevoViaje = Viaje(destino, precio, pasajes)
    viajesDisponibles.add(nuevoViaje)

    println("\n✅ ¡Viaje registrado exitosamente!")
    println("📋 Resumen del viaje:")
    println(nuevoViaje.mostrarInfo(viajesDisponibles.size))
}

/**
 * Muestra todos los viajes registrados en el sistema
 */
fun verViajes() {
    println("👀 VIAJES DISPONIBLES")
    println("=" * 22)

    if (viajesDisponibles.isEmpty()) {
        println("📭 No hay viajes registrados en el sistema.")
        println("💡 Usa la opción 1 para registrar un nuevo viaje.")
        return
    }

    println("📋 Lista de viajes registrados:\n")
    viajesDisponibles.forEachIndexed { index, viaje ->
        println(viaje.mostrarInfo(index + 1))
        println() // Línea en blanco entre viajes
    }

    println("📊 Total de viajes registrados: ${viajesDisponibles.size}")
}

/**
 * Procesa la compra de un pasaje
 * Incluye validaciones y actualiza el inventario
 */
fun comprarPasaje() {
    println("🎫 COMPRAR PASAJE")
    println("=" * 16)

    if (viajesDisponibles.isEmpty()) {
        println("📭 No hay viajes disponibles.")
        println("💡 Primero debes registrar viajes usando la opción 1.")
        return
    }

    // Mostrar viajes disponibles para compra
    println("🚌 Viajes disponibles para comprar:\n")
    val viajesConPasajes = viajesDisponibles.filter { it.pasajesDisponibles > 0 }

    if (viajesConPasajes.isEmpty()) {
        println("😞 Lo sentimos, todos los viajes están agotados.")
        return
    }

    viajesConPasajes.forEachIndexed { index, viaje ->
        println("${index + 1}. ${viaje.destino} - $${"%,d".format(viaje.precio)} CLP (${viaje.pasajesDisponibles} disponibles)")
    }

    // Solicitar selección del viaje
    print("\n👉 Selecciona el número del viaje que deseas: ")
    val seleccion = readLine()?.toIntOrNull()

    if (seleccion == null || seleccion < 1 || seleccion > viajesConPasajes.size) {
        println("❌ Selección inválida. Debes elegir un número entre 1 y ${viajesConPasajes.size}.")
        return
    }

    val viajeSeleccionado = viajesConPasajes[seleccion - 1]

    // Confirmar compra
    println("\n🎫 Has seleccionado:")
    println("   Destino: ${viajeSeleccionado.destino}")
    println("   Precio: $${"%,d".format(viajeSeleccionado.precio)} CLP")

    print("\n❓ ¿Confirmas la compra? (s/n): ")
    val confirmacion = readLine()?.lowercase()

    if (confirmacion == "s" || confirmacion == "si" || confirmacion == "sí") {
        // Procesar la venta
        viajeSeleccionado.pasajesDisponibles--

        println("\n✅ ¡Compra realizada exitosamente!")
        println("🎫 Tu pasaje a ${viajeSeleccionado.destino} ha sido reservado.")
        println("💰 Total pagado: $${"%,d".format(viajeSeleccionado.precio)} CLP")
        println("🚌 ¡Buen viaje!")

        if (viajeSeleccionado.pasajesDisponibles == 0) {
            println("\n⚠️  Este viaje está ahora completamente agotado.")
        }
    } else {
        println("❌ Compra cancelada.")
    }
}

/**
 * Calcula y muestra las métricas del sistema
 */
fun verMetricas() {
    println("📊 MÉTRICAS DE VENTAS")
    println("=" * 21)

    if (viajesDisponibles.isEmpty()) {
        println("📭 No hay datos para mostrar métricas.")
        println("💡 Registra algunos viajes y realiza ventas primero.")
        return
    }

    // Calcular métricas
    val totalPasajesVendidos = viajesDisponibles.sumOf { it.pasajesVendidos }
    val totalPasajesDisponibles = viajesDisponibles.sumOf { it.pasajesDisponibles }
    val totalPasajesIniciales = viajesDisponibles.sumOf { it.pasajesIniciales }
    val recaudacionTotal = viajesDisponibles.sumOf { it.precio * it.pasajesVendidos }
    val precioPromedio = if (viajesDisponibles.isNotEmpty()) {
        viajesDisponibles.map { it.precio }.average()
    } else 0.0

    // Mostrar métricas generales
    println("📈 RESUMEN GENERAL:")
    println("   • Total de rutas registradas: ${viajesDisponibles.size}")
    println("   • Total de pasajes iniciales: $totalPasajesIniciales")
    println("   • Total de pasajes vendidos: $totalPasajesVendidos")
    println("   • Total de pasajes disponibles: $totalPasajesDisponibles")
    println("   • Recaudación total: $${"%,d".format(recaudacionTotal)} CLP")
    println("   • Precio promedio por pasaje: $${"%,.0f".format(precioPromedio)} CLP")

    if (totalPasajesIniciales > 0) {
        val porcentajeVendido = (totalPasajesVendidos.toDouble() / totalPasajesIniciales) * 100
        println("   • Porcentaje de ocupación: ${"%.1f".format(porcentajeVendido)}%")
    }

    // Mostrar detalles por viaje
    println("\n📋 DETALLE POR VIAJE:")
    viajesDisponibles.forEachIndexed { index, viaje ->
        val recaudacionViaje = viaje.precio * viaje.pasajesVendidos
        val ocupacion = if (viaje.pasajesIniciales > 0) {
            (viaje.pasajesVendidos.toDouble() / viaje.pasajesIniciales) * 100
        } else 0.0

        println("\n   ${index + 1}. ${viaje.destino}:")
        println("      • Precio: $${"%,d".format(viaje.precio)} CLP")
        println("      • Pasajes vendidos: ${viaje.pasajesVendidos}")
        println("      • Pasajes disponibles: ${viaje.pasajesDisponibles}")
        println("      • Recaudación: $${"%,d".format(recaudacionViaje)} CLP")
        println("      • Ocupación: ${"%.1f".format(ocupacion)}%")
    }

    // Encontrar el viaje más popular
    val viajePopular = viajesDisponibles.maxByOrNull { it.pasajesVendidos }
    if (viajePopular != null && viajePopular.pasajesVendidos > 0) {
        println("\n🏆 VIAJE MÁS POPULAR:")
        println("   ${viajePopular.destino} con ${viajePopular.pasajesVendidos} pasajes vendidos")
    }
}

// Función de extensión para repetir strings (similar a Python)
operator fun String.times(n: Int): String = this.repeat(n)