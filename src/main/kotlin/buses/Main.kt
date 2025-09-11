package buses


data class Viaje(
    val destino: String,
    val precio: Int,
    var pasajesDisponibles: Int,
    val pasajesIniciales: Int
)


val viajes = mutableListOf<Viaje>()

fun main() {
    println("--- BIENVENIDO AL SISTEMA DE VENTAS DE PASAJES ---")
    println("---    Terminal de Buses de Puerto Montt    ---")


    cargarDatosDePrueba()


    while (true) {
        mostrarMenu()
        print("➡️  Ingrese una opción: ")


        val opcion = readLine()?.toIntOrNull()

        when (opcion) {
            1 -> registrarViaje()
            2 -> verViajes()
            3 -> comprarPasaje()
            4 -> verMetricas()
            5 -> {
                println("\n Gracias por usar el sistema. ¡Hasta luego! ")
                return
            }
            else -> {
                println("\n Opción no válida. Por favor, ingrese un número del 1 al 5.")
            }
        }


        pausar()
    }
}


fun mostrarMenu() {
    println("\n==================== MENÚ PRINCIPAL ====================")
    println("1. Registrar un nuevo Viaje")
    println("2. Ver todos los Viajes disponibles")
    println("3. Comprar un Pasaje")
    println("4. Ver Métricas (estadísticas de ventas)")
    println("5. Salir del sistema")
    println("========================================================")
}


fun pausar() {
    print("\nPresione Enter para continuar...")
    readLine()
}


fun cargarDatosDePrueba() {
    viajes.add(Viaje("Santiago", 15000, 50, 50))
    viajes.add(Viaje("Osorno", 3000, 20, 20))
    viajes.add(Viaje("Valdivia", 5000, 30, 30))
    viajes.add(Viaje("Temuco", 8000, 25, 25))
}





fun registrarViaje() {
    println("\n--- 📝 Registrar Nuevo Viaje ---")


    var destino: String
    while (true) {
        print("Ingrese el destino del viaje: ")
        destino = readLine() ?: ""
        if (destino.isNotBlank()) {
            break
        }
        println("El destino no puede estar vacío. Inténtelo de nuevo.")
    }


    var precio: Int
    while (true) {
        print("Ingrese el precio del pasaje (CLP): ")
        val precioInput = readLine()?.toIntOrNull()
        if (precioInput != null && precioInput > 0) {
            precio = precioInput
            break
        }
        println("Precio inválido. Debe ingresar un número positivo.")
    }


    var cantidadPasajes: Int
    while (true) {
        print("Ingrese la cantidad de pasajes disponibles: ")
        val pasajesInput = readLine()?.toIntOrNull()
        if (pasajesInput != null && pasajesInput > 0) {
            cantidadPasajes = pasajesInput
            break
        }
        println("Cantidad inválida. Debe ingresar un número positivo.")
    }


    val nuevoViaje = Viaje(
        destino = destino,
        precio = precio,
        pasajesDisponibles = cantidadPasajes,
        pasajesIniciales = cantidadPasajes
    )
    viajes.add(nuevoViaje)

    println("\n¡Viaje a ${nuevoViaje.destino} registrado con éxito!")
}


fun verViajes() {
    println("\n--- Viajes Disponibles ---")


    if (viajes.isEmpty()) {
        println("No hay viajes registrados en el sistema en este momento.")
        return
    }


    println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::")
    println("N°   DESTINO     PRECIO      PASAJES DISPONIBLES")
    println("---------------------------------------------------------")
    viajes.forEachIndexed { index, viaje ->
        val numeroViaje = index + 1

        val linea = String.format(
            "%-3d | %-10s | CLP $%-6d | %d",
            numeroViaje,
            viaje.destino,
            viaje.precio,
            viaje.pasajesDisponibles
        )
        println(linea)
    }
    println("---------------------------------------------------------")
}


fun comprarPasaje() {
    println("\n--- Comprar Pasaje ---")

    if (viajes.isEmpty()) {
        println("No hay viajes disponibles para comprar.")
        return
    }


    verViajes()

    var viajeSeleccionado: Viaje? = null
    while (true) {
        print("\nSeleccione el número (N°) del viaje que desea comprar: ")
        val seleccionInput = readLine()?.toIntOrNull()
        val indiceSeleccionado = if (seleccionInput != null) seleccionInput - 1 else -1

        // Validamos que el índice esté dentro del rango de la lista
        if (seleccionInput != null && indiceSeleccionado in viajes.indices) {
            viajeSeleccionado = viajes[indiceSeleccionado]
            break
        }
        println("Selección inválida. Por favor, ingrese un número de la lista.")
    }


    if (viajeSeleccionado!!.pasajesDisponibles > 0) {

        viajeSeleccionado.pasajesDisponibles--
        println("\n ¡Compra exitosa! Ha comprado un pasaje para ${viajeSeleccionado.destino}.")
        println("Quedan ${viajeSeleccionado.pasajesDisponibles} pasajes disponibles para este destino.")
    } else {
        println("\n Lo sentimos, no quedan pasajes disponibles para ${viajeSeleccionado.destino}.")
    }
}


fun verMetricas() {
    println("\n--- Métricas del Sistema ---")

    if (viajes.isEmpty()) {
        println("ℹ️  No hay datos para calcular métricas, ya que no hay viajes registrados.")
        return
    }


    val totalPasajesVendidos = viajes.sumOf { it.pasajesIniciales - it.pasajesDisponibles }


    val recaudacionTotal = viajes.sumOf { (it.pasajesIniciales - it.pasajesDisponibles) * it.precio }


    val precioPromedio = if (viajes.isNotEmpty()) viajes.sumOf { it.precio }.toDouble() / viajes.size else 0.0

    println("---------------------------------------------")
    println("Pasajes vendidos totales: $totalPasajesVendidos")
    println("Recaudación total: CLP $$recaudacionTotal")

    println("Precio promedio de los viajes: CLP $${String.format("%.2f", precioPromedio)}")
    println("---------------------------------------------")
}