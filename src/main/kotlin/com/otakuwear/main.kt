package com.otakuwear

import com.otakuwear.models.Prenda


/**
 * Almacén global de todas las prendas del sistema.
 * Usamos MutableList para permitir agregar/quitar prendas dinámicamente.
 */
val almacenPrendas = mutableListOf<Prenda>()

/**
 * Muestra el menú principal de opciones al usuario
 */
fun mostrarMenu() {
    println("\n" + "=".repeat(40))
    println("    OTAKUWEAR - GESTIÓN DE INVENTARIO")
    println("=".repeat(40))
    println("1. Registrar nueva prenda")
    println("2. Ver inventario completo")
    println("3. Filtrar prendas premium (> $1000)")
    println("4. Calcular valor total del inventario")
    println("5. Salir del sistema")
    println("-".repeat(40))
    print("Selecciona una opción [1-5]: ")
}


/**
 * Registra una nueva prenda en el sistema con validación completa
 */
fun registrarPrenda() {
    println("\n--- REGISTRO DE NUEVA PRENDA ---")

    try {
        // Captura del nombre con validación
        print("Nombre de la prenda: ")
        val nombre = readLine()?.trim() ?: ""

        if (nombre.isBlank()) {
            println("[ERROR] El nombre es obligatorio")
            return
        }

        // Captura del precio con validación numérica
        print("Precio en dólares: $")
        val precioTexto = readLine()?.trim() ?: "0"
        val precio = precioTexto.toDoubleOrNull()

        if (precio == null || precio <= 0) {
            println("[ERROR] El precio debe ser un número positivo")
            return
        }

        // Captura del stock con validación numérica
        print("Cantidad en stock: ")
        val stockTexto = readLine()?.trim() ?: "0"
        val stock = stockTexto.toIntOrNull()

        if (stock == null || stock < 0) {
            println("[ERROR] El stock debe ser un número no negativo")
            return
        }

        // Crear y validar la prenda
        val nuevaPrenda = Prenda(nombre, precio, stock)

        if (nuevaPrenda.validar()) {
            almacenPrendas.add(nuevaPrenda)
            println("\n[ÉXITO] Prenda registrada:")
            println("-> ${nuevaPrenda.mostrarInfo()}")
        } else {
            println("[ERROR] Los datos de la prenda no son válidos")
        }

    } catch (excepcion: Exception) {
        println("[ERROR INESPERADO] ${excepcion.message}")
        println("Por favor, intenta nuevamente")
    }
}

/**
 * Muestra todas las prendas registradas en el sistema
 */
fun mostrarInventarioCompleto() {
    println("\n--- INVENTARIO COMPLETO ---")

    if (almacenPrendas.isEmpty()) {
        println("No hay prendas registradas en el sistema.")
        println("Usa la opción 1 para agregar la primera prenda.")
        return
    }

    println("Total de prendas: ${almacenPrendas.size}")
    println("-".repeat(50))

    almacenPrendas.forEachIndexed { indice, prenda ->
        val numero = indice + 1
        println("$numero. ${prenda.mostrarInfo()}")
    }

    println("-".repeat(50))
}

/**
 * Filtra y muestra las prendas premium (precio mayor a $1000)
 * Demuestra el uso de programación funcional
 */
fun mostrarPrendasPremium() {
    println("\n--- PRENDAS PREMIUM (> $1000) ---")

    val prendasPremium = almacenPrendas.filter { prenda ->
        prenda.precio > 1000.0
    }

    if (prendasPremium.isEmpty()) {
        println("No hay prendas premium registradas.")
        return
    }

    println("Se encontraron ${prendasPremium.size} prendas premium:")
    println("-".repeat(50))

    prendasPremium
        .sortedByDescending { it.precio }  // Ordenar por precio descendente
        .forEachIndexed { indice, prenda ->
            println("${indice + 1}. ${prenda.mostrarInfo()}")
        }
}


/**
 * Calcula métricas financieras del inventario
 * Demuestra operaciones agregadas avanzadas
 */
fun calcularMetricasInventario() {
    println("\n--- MÉTRICAS DEL INVENTARIO ---")

    if (almacenPrendas.isEmpty()) {
        println("No hay datos para calcular métricas.")
        return
    }

    // Valor total del inventario (precio × stock por cada prenda)
    val valorTotal = almacenPrendas.sumOf { prenda ->
        prenda.precio * prenda.stock
    }

    // Prenda más cara
    val prendaMasCara = almacenPrendas.maxByOrNull { it.precio }

    // Prenda más barata
    val prendaMasBarata = almacenPrendas.minByOrNull { it.precio }

    // Precio promedio
    val precioPromedio = almacenPrendas.map { it.precio }.average()

    // Stock total
    val stockTotal = almacenPrendas.sumOf { it.stock }

    // Mostrar resultados
    println("Valor total del inventario: $${String.format("%.2f", valorTotal)}")
    println("Precio promedio: $${String.format("%.2f", precioPromedio)}")
    println("Stock total: $stockTotal unidades")
    println()
    println("Prenda más cara: ${prendaMasCara?.mostrarInfo() ?: "N/A"}")
    println("Prenda más barata: ${prendaMasBarata?.mostrarInfo() ?: "N/A"}")
}


/**
 * Función principal que controla el flujo del programa
 * Demuestra estructuras de control avanzadas
 */
fun main() {
    // Mensaje de bienvenida
    println("*".repeat(50))
    println("       BIENVENIDO A OTAKUWEAR")
    println("    Sistema de Gestión de Inventario")
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
                registrarPrenda()
            }
            "2" -> {
                mostrarInventarioCompleto()
            }
            "3" -> {
                mostrarPrendasPremium()
            }
            "4" -> {
                calcularMetricasInventario()
            }
            "5" -> {
                println("\nGracias por usar OtakuWear")
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