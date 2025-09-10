package terminalrodrigo


fun main() {
    // Mensaje de bienvenida
    println("*".repeat(50))
    println("     BIENVENIDO A OTAKUWEAR")
    println("    Sistema Terminal De Buses")
    println("*".repeat(50))

    var sistemaActivo = true

    // Bucle principal del programa
    while (sistemaActivo) {
        mostrarMenu()

        // Captura de opción con validación
        val opcionTexto = readLine()?.trim() ?: ""

        // Procesamiento de la opción seleccionada
        when (opcionTexto) {
            "1" -> {
                registrarViaje()
            }
            "2" -> {
                mostrarViajes()
            }
            "3" -> {
                comprarPasaje()
            }
            "4" -> {
                verMetricas()
            }
            "5" -> {
                println("\nGracias por usar el sistema de terminal de buses")
                println("¡Hasta la próxima!")
                sistemaActivo = false
            }
            else -> {
                println("\n[ERROR] Opción inválida: '$opcionTexto'")
                println("Por favor, selecciona un número entre 1 y 5")
            }
        }

        // Pausa antes del siguiente ciclo (excepto al salir)
        if (sistemaActivo) {
            println("\nPresiona ENTER para continuar...")
            readLine()
        }
    }
}

data class Viaje(
    val destino : String,
    val precio : Double,
    val stock : Int,

){
    fun validar(): Boolean {
        return precio > 0 && stock >= 0 && destino.isNotBlank()
    }

    fun mostrarInfo(): String {
        return "$destino - Precio: $${precio} - Stock: ${stock} unidades"
    }
}

val viajes = mutableListOf<Viaje>()
var totalPasajesVendidos = 0
var recaudacionTotal = 0.0

fun mostrarMenu() {
    println("\n" + "=".repeat(40))
    println("Terminal De Buses")
    println("=".repeat(40))
    println("1. Registrar un nuevo viaje")
    println("2. Ver Viajes")
    println("3. Comprar Pasaje")
    println("4. Ver Metrica")
    println("5. Salir del sistema")
    println("-".repeat(40))
    print("Selecciona una opción [1-5]: ")
}

fun registrarViaje() {
    println("Registrar un nuevo viaje")

    try {
        print("Destino: ")
        val destino = readLine().toString()

        if (destino.isBlank()) {
            println("Ingrese el nombre del destino: ")
            return
        }

        print("Precio: ")
        val precioTexto = readLine()?.trim() ?: "0"
        val precio = precioTexto.toDoubleOrNull()

        if (precio == null || precio <= 0) {
            println("El precio debe ser un número positivo")
            return
        }

        print("Stock: ")
        val stockTexto = readLine()?.trim() ?: "0"
        val stock = stockTexto.toIntOrNull()

        if (stock == null || stock < 0) {
            println("El stock debe ser un número no negativo")
            return
        }

        val nuevoViaje = Viaje(destino, precio, stock)

        if (nuevoViaje.validar()) {
            viajes.add(nuevoViaje)
            println("\n[ÉXITO] Viaje registrado:")
            println("-> ${nuevoViaje.mostrarInfo()}")
        } else {
            println("[ERROR] Los datos del viaje no son válidos")
        }
    } catch (excepcion: Exception) {
        println("[ERROR INESPERADO] ${excepcion.message}")
        println("Por favor, intenta nuevamente")
    }
}

fun mostrarViajes() {
    println("\n--- Viajes ---")

    if (viajes.isEmpty()) {
        println("No hay viajes registradas en el sistema.")
        println("Usa la opción 1 para agregar un viaje nuevo.")
        return
    }

    println("Total de viajes: ${viajes.size}")
    println("-".repeat(50))

    viajes.forEachIndexed { indice, viaje ->
        val numero = indice + 1
        println("$numero. ${viaje.mostrarInfo()}")
    }

    println("-".repeat(50))
}

fun comprarPasaje() {
    if (viajes.isEmpty()) {
        println("No hay viajes registradas en el sistema.")
        println("Usa la opción 1 para agregar un viaje nuevo.")
        return
    }

    println(mostrarViajes())
    println("Total de viajes: ${viajes.size}")
    println("-".repeat(50))

    try {
        print("Selecciona el viaje para comprar un pasaje: ")
        val select = readLine()?.toIntOrNull()


        if (select == null) {
            println("Seleccion Invalida, selecciona un viaje")
            return
        }

        val viajeSeleccionado = viajes[select - 1]

        print("¿Cuantos pasajes quieres comprar?: ")
        val cantidadTexto = readLine()?.trim() ?: "0"
        val cantidad = cantidadTexto.toIntOrNull()

        if (cantidad == null) {
            print("La cantidad de pasajes a comprar debe ser mayor 1")
            return
        }

        val nuevoStock=viajeSeleccionado.stock-cantidad
        viajes[select-1]=viajeSeleccionado.copy(stock=nuevoStock)

        val costoTotal = viajeSeleccionado.precio * cantidad

        totalPasajesVendidos += cantidad
        recaudacionTotal += costoTotal

        println("\n[ÉXITO] ¡Compra realizada!")

    } catch (excepcion: Exception) {
        println("[ERROR] ${excepcion.message}")
        println("Por favor, intenta nuevamente.")
    }
}


fun verMetricas(){
    println("\n--- MÉTRICAS DEL SISTEMA ---")

    if (totalPasajesVendidos == 0) {
        println("Aún no se ha vendido ningún pasaje.")
        println("Registra y vende un pasaje para ver las métricas aquí.")
        return
    }

    val precioPromedio = recaudacionTotal / totalPasajesVendidos

    println("Pasajes vendidos totales: $totalPasajesVendidos")
    println("Recaudación total: $${"%.2f".format(recaudacionTotal)}")
    println("Precio promedio por pasaje: $%.2f".format(precioPromedio))
    println("-".repeat(50))
}
