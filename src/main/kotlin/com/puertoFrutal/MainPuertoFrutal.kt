package com.puertoFrutal

import com.puertoFrutal.model.Fruta
import com.puertoFrutal.model.FrutaLocal
import com.puertoFrutal.model.FrutaTropical

/**
 * Aplicación principal para la gestión de inventario de Puerto Frutal SPA
 */
fun main() {
    println("=== Bienvenido al Sistema de Gestión de Inventario de Puerto Frutal SPA ===")
    println("*".repeat(70))
    
    // Lista mutable para almacenar las frutas
    val inventario = mutableListOf<Fruta>()
    
    // Agregar algunas frutas de ejemplo para probar
    try {
        inventario.add(FrutaLocal("Manzana", 1200.0, 50.0))
        inventario.add(FrutaLocal("Pera", 1500.0, 30.0))
        inventario.add(FrutaTropical("Mango", 2500.0, 20.0))
        inventario.add(FrutaTropical("Piña", 1800.0, 15.0))
        inventario.add(FrutaLocal("Naranja", 900.0, 60.0))
    } catch (e: Exception) {
        println("Error al cargar frutas de ejemplo: ${e.message}")
    }
    
    var opcion: Int
    do {
        mostrarMenu()
        opcion = leerOpcion()
        
        when (opcion) {
            1 -> registrarFruta(inventario)
            2 -> mostrarFrutas(inventario)
            3 -> mostrarFrutasCaras(inventario)
            4 -> calcularMetricas(inventario)
            5 -> println("\n¡Gracias por usar el sistema de Puerto Frutal SPA!")
            else -> println("\n❌ Opción inválida. Por favor, intente nuevamente.")
        }
        
        if (opcion != 5) {
            println("\nPresione Enter para continuar...")
            readLine()
        }
        
    } while (opcion != 5)
}

/**
 * Muestra el menú principal de la aplicación
 */
fun mostrarMenu() {
    println("\n=== MENÚ PRINCIPAL ===")
    println("1. Registrar nueva fruta")
    println("2. Mostrar todas las frutas")
    println("3. Mostrar frutas caras (> $1000)")
    println("4. Calcular métricas del inventario")
    println("5. Salir")
    print("Ingrese su opción: ")
}

/**
 * Lee y valida la opción ingresada por el usuario
 * @return la opción seleccionada como entero
 */
fun leerOpcion(): Int {
    return try {
        val input = readLine()?.trim() ?: ""
        input.toIntOrNull() ?: 0
    } catch (e: Exception) {
        0
    }
}

/**
 * Registra una nueva fruta en el inventario con validaciones
 * @param inventario la lista mutable de frutas
 */
fun registrarFruta(inventario: MutableList<Fruta>) {
    println("\n=== REGISTRO DE NUEVA FRUTA ===")
    
    try {
        // Solicitar y validar nombre
        print("Nombre: ")
        val nombre = readLine()?.trim()
        
        if (nombre.isNullOrBlank()) {
            throw IllegalArgumentException("El nombre no puede estar vacío")
        }
        
        // Solicitar y validar precio
        print("Precio por kilo: ")
        val precioTexto = readLine()?.trim() ?: ""
        val precio = precioTexto.toDoubleOrNull() 
            ?: throw NumberFormatException("Precio inválido: '$precioTexto'")
        
        if (precio <= 0) {
            throw IllegalArgumentException("El precio debe ser mayor que 0")
        }
        
        // Solicitar y validar stock
        print("Stock en kilos: ")
        val stockTexto = readLine()?.trim() ?: ""
        val stock = stockTexto.toDoubleOrNull()
            ?: throw NumberFormatException("Stock inválido: '$stockTexto'")
        
        if (stock < 0) {
            throw IllegalArgumentException("El stock no puede ser negativo")
        }
        
        // Solicitar tipo de fruta
        print("Tipo de fruta (1: Local, 2: Tropical): ")
        val tipoTexto = readLine()?.trim() ?: ""
        val tipo = tipoTexto.toIntOrNull() ?: 0
        
        val fruta = when (tipo) {
            1 -> FrutaLocal(nombre, precio, stock)
            2 -> FrutaTropical(nombre, precio, stock)
            else -> throw IllegalArgumentException("Tipo de fruta inválido. Debe ser 1 (Local) o 2 (Tropical)")
        }
        
        inventario.add(fruta)
        println("\n✅ Fruta registrada exitosamente")
        println(fruta.descripcion())
        
    } catch (e: NumberFormatException) {
        println("\n❌ Error de formato: ${e.message}")
    } catch (e: IllegalArgumentException) {
        println("\n❌ Error de validación: ${e.message}")
    } catch (e: Exception) {
        println("\n❌ Error inesperado: ${e.message}")
    }
}

/**
 * Muestra todas las frutas registradas en el inventario
 * @param inventario la lista de frutas
 */
fun mostrarFrutas(inventario: List<Fruta>) {
    println("\n=== FRUTAS REGISTRADAS ===")
    
    if (inventario.isEmpty()) {
        println("No hay frutas registradas en el inventario.")
        return
    }
    
    println("| ${'#'} | Nombre            | Precio/Kilo | Stock (kg) | Valor Total |")
    println("|---|-------------------|-------------|------------|------------|")
    
    inventario.forEachIndexed { index, fruta ->
        val valorTotal = fruta.valorTotal()
        println("| ${index + 1} | ${fruta.nombre.padEnd(17)} | $${fruta.precioPorKilo.toString().padEnd(9)} | ${fruta.stockKilos.toString().padEnd(8)} | $${"%.2f".format(valorTotal).padEnd(8)} |")
    }
    
    println("\nTotal de frutas: ${inventario.size}")
}

/**
 * Muestra las frutas con precio mayor a $1000, ordenadas por precio descendente
 * @param inventario la lista de frutas
 */
fun mostrarFrutasCaras(inventario: List<Fruta>) {
    println("\n=== FRUTAS CARAS (> $1000) ===")
    
    if (inventario.isEmpty()) {
        println("No hay frutas registradas en el inventario.")
        return
    }
    
    // Filtrar frutas caras
    val frutasFiltradasCaras = mutableListOf<Fruta>()
    for (fruta in inventario) {
        if (fruta.precioPorKilo > 1000) {
            frutasFiltradasCaras.add(fruta)
        }
    }
    
    // Ordenar por precio descendente
    val frutasCaras = frutasFiltradasCaras.sortedWith(compareByDescending { it.precioPorKilo })
    
    if (frutasCaras.isEmpty()) {
        println("No hay frutas con precio mayor a $1000.")
        return
    }
    
    println("| ${'#'} | Nombre            | Precio/Kilo | Tipo            |")
    println("|---|-------------------|-------------|-----------------|")
    
    frutasCaras.forEachIndexed { index, fruta ->
        val tipo = when (fruta) {
            is FrutaLocal -> "Local"
            is FrutaTropical -> "Tropical"
            else -> "Desconocido"
        }
        println("| ${index + 1} | ${fruta.nombre.padEnd(17)} | $${fruta.precioPorKilo.toString().padEnd(9)} | ${tipo.padEnd(15)} |")
    }
    
    println("\nTotal de frutas caras: ${frutasCaras.size}")
}

/**
 * Calcula y muestra métricas del inventario
 * @param inventario la lista de frutas
 */
fun calcularMetricas(inventario: List<Fruta>) {
    println("\n=== MÉTRICAS DEL INVENTARIO ===")
    
    if (inventario.isEmpty()) {
        println("No hay frutas registradas en el inventario.")
        return
    }
    
    // Valor total del inventario
    val valorTotal = inventario.sumOf { it.valorTotal() }
    
    // Precio promedio por kilo
    val precioPromedio = inventario.map { it.precioPorKilo }.average()
    
    // Stock total en kilos
    val stockTotal = inventario.sumOf { it.stockKilos }
    
    // Fruta más cara y más barata
    val frutaMasCara = inventario.maxByOrNull { it.precioPorKilo }
    val frutaMasBarata = inventario.minByOrNull { it.precioPorKilo }
    
    // Fruta con mayor y menor stock
    val frutaMayorStock = inventario.maxByOrNull { it.stockKilos }
    val frutaMenorStock = inventario.minByOrNull { it.stockKilos }
    
    println("Valor total del inventario: $${"%.2f".format(valorTotal)}")
    println("Precio promedio por kilo: $${"%.2f".format(precioPromedio)}")
    println("Stock total en kilos: ${"%.2f".format(stockTotal)}")
    println("\nFruta más cara: ${frutaMasCara?.nombre} ($${frutaMasCara?.precioPorKilo})")
    println("Fruta más barata: ${frutaMasBarata?.nombre} ($${frutaMasBarata?.precioPorKilo})")
    println("\nFruta con mayor stock: ${frutaMayorStock?.nombre} (${frutaMayorStock?.stockKilos} kg)")
    println("Fruta con menor stock: ${frutaMenorStock?.nombre} (${frutaMenorStock?.stockKilos} kg)")
    
    // Distribución por tipo
    val cantidadLocales = inventario.count { it is FrutaLocal }
    val cantidadTropicales = inventario.count { it is FrutaTropical }
    
    println("\nDistribución por tipo:")
    println("- Frutas locales: $cantidadLocales (${(cantidadLocales.toDouble() / inventario.size * 100).toInt()}%)")
    println("- Frutas tropicales: $cantidadTropicales (${(cantidadTropicales.toDouble() / inventario.size * 100).toInt()}%)")
}