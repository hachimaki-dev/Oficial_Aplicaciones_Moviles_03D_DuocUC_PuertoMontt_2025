package com.otakuwear

abstract class Fruta(
    val nombre: String,
    val precioPorKilo: Double,
    val stockKilos: Int
) {
    open fun mostrarInfo() = "$nombre - Precio: $${precioPorKilo} - Stock: ${stockKilos} unidades"
    abstract fun descripcion(): String
    open fun tiempoVida(): String = "Variable según condiciones"
}

class FrutaLocal(nombre: String, precio: Double, stock: Int) : Fruta(nombre, precio, stock) {
    override fun descripcion() = "🍎 Fruta local: $nombre - Fresca de la región"
    override fun tiempoVida() = "3-7 días refrigerada"
    fun zonaProduccion() = "Región de Los Lagos"
}

class FrutaTropical(nombre: String, precio: Double, stock: Int) : Fruta(nombre, precio, stock) {
    override fun descripcion() = "🥭 Fruta tropical: $nombre - Importada"
    override fun tiempoVida() = "5-14 días según madurez"
    fun paisOrigen() = "Países tropicales"
}

val almacenElGordo = mutableListOf<Fruta>()

fun mostrarMenu() {
    println("\n---    El Gordo Almacen - GESTIÓN DEL ALMACEN     ---")
    println("1. Registrar nueva fruta")
    println("2. Ver inventario completo")
    println("3. Frutas mayor a: (> $1000)")
    println("4. Calcular valor total de las frutas")
    println("5. Ver descripciones")
    println("6. Salir del sistema")
    print("Selecciona una opción [1-6]: ")
}

fun registrarFruta() {
    println("\n--- REGISTRO DE NUEVA FRUTA ---")

    try {
        print("Nombre de la fruta: ")
        val nombre = readLine()?.trim() ?: ""

        if (nombre.isBlank()) {
            println("[ERROR] El nombre es obligatorio")
            return
        }

        print("Precio en CLP: $")
        val precioTexto = readLine()?.trim() ?: "0"
        val precio = precioTexto.toDoubleOrNull()

        if (precio == null || precio <= 0) {
            println("[ERROR] El precio debe ser un número positivo")
            return
        }

        print("Cantidad en stock: ")
        val stockTexto = readLine()?.trim() ?: "0"
        val stock = stockTexto.toIntOrNull()

        if (stock == null || stock < 0) {
            println("[ERROR] El stock debe ser un número no negativo")
            return
        }

        print("Tipo de fruta (local/tropical): ")
        val tipo = readLine()?.trim()?.lowercase()

        val nuevaFruta = when (tipo) {
            "local" -> FrutaLocal(nombre, precio, stock)
            "tropical" -> FrutaTropical(nombre, precio, stock)
            else -> {
                println("[ERROR] Tipo inválido. Usa 'local' o 'tropical'")
                return
            }
        }

        almacenElGordo.add(nuevaFruta)
        println("\n[ÉXITO] Fruta registrada:")
        println("-> ${nuevaFruta.mostrarInfo()}")

    } catch (excepcion: Exception) {
        println("[ERROR INESPERADO] ${excepcion.message}")
        println("Por favor, intenta nuevamente")
    }
}

fun mostrarInventarioCompleto() {
    println("\n--- INVENTARIO COMPLETO ---")

    if (almacenElGordo.isEmpty()) {
        println("No hay frutas registradas en el sistema.")
        println("Usa la opción 1 para agregar la primera fruta.")
        return
    }

    println("Total de frutas: ${almacenElGordo.size}")

    almacenElGordo.forEachIndexed { indice, fruta ->
        val numero = indice + 1
        println("$numero. ${fruta.mostrarInfo()}")
    }
}

fun mostrarFrutasCaras() {
    println("\n--- FRUTAS CARAS (> $1000) ---")

    val frutaCara = almacenElGordo.filter { fruta ->
        fruta.precioPorKilo > 1000.0
    }

    if (frutaCara.isEmpty()) {
        println("No hay frutas caras registradas.")
        return
    }

    println("Se encontraron ${frutaCara.size} frutas cara:")

    frutaCara
        .sortedByDescending { it.precioPorKilo }
        .forEachIndexed { indice, fruta ->
            println("${indice + 1}. ${fruta.mostrarInfo()}")
        }
}

fun calcularMetricasInventario() {
    println("\n--- MÉTRICAS DEL INVENTARIO ---")

    if (almacenElGordo.isEmpty()) {
        println("No hay datos para calcular métricas.")
        return
    }

    val valorTotal = almacenElGordo.sumOf { fruta ->
        fruta.precioPorKilo * fruta.stockKilos
    }

    val frutaMasCara = almacenElGordo.maxByOrNull { it.precioPorKilo }
    val frutaMasBarata = almacenElGordo.minByOrNull { it.precioPorKilo }
    val precioPromedio = almacenElGordo.map { it.precioPorKilo }.average()
    val stockTotal = almacenElGordo.sumOf { it.stockKilos }

    println("Valor total del inventario: $${String.format("%.2f", valorTotal)}")
    println("Precio promedio: $${String.format("%.2f", precioPromedio)}")
    println("Stock total: $stockTotal unidades")
    println()
    println("Fruta más cara: ${frutaMasCara?.mostrarInfo() ?: "N/A"}")
    println("Fruta más barata: ${frutaMasBarata?.mostrarInfo() ?: "N/A"}")
}

fun mostrarDescripciones() {
    println("\n--- DESCRIPCIONES DE LAS FRUTAS ---")

    if (almacenElGordo.isEmpty()) {
        println("No hay frutas registradas para mostrar descripciones.")
        return
    }

    almacenElGordo.forEach { fruta ->
        println(fruta.descripcion())
        println("Duración: ${fruta.tiempoVida()}")
        println()
    }
}

fun main() {
    println("*".repeat(50))
    println("       BIENVENIDO A ALMACENES EL GORDO")
    println("    Sistema de Gestión de Fruteria")
    println("*".repeat(50))

    var sistemaActivo = true

    while (sistemaActivo) {
        mostrarMenu()

        val opcionTexto = readLine()?.trim() ?: ""

        when (opcionTexto) {
            "1" -> registrarFruta()
            "2" -> mostrarInventarioCompleto()
            "3" -> mostrarFrutasCaras()
            "4" -> calcularMetricasInventario()
            "5" -> mostrarDescripciones()
            "6" -> {
                println("\nGracias por venir a Almacenes El Gordo")
                println("¡Hasta la próxima!")
                sistemaActivo = false
            }
            else -> {
                println("\n[ERROR] Opción inválida: '$opcionTexto'")
                println("Por favor, selecciona un número entre 1 y 6")
            }
        }

        if (sistemaActivo) {
            println("\nPresiona ENTER para continuar...")
            readLine()
        }
    }
}
