package com.feriavirtual

abstract class Fruta(
    val nombre: String,
    val precioPorKilo: Double,
    val stockKilos: Double
) {
    abstract fun descripcion(): String

    fun validar(): Boolean {
        return precioPorKilo > 0 && stockKilos >= 0 && nombre.isNotBlank()
    }

    fun mostrarInfo(): String {
        return "'$nombre' - Precio: $${String.format("%.0f", precioPorKilo)} p/kg - Stock: ${stockKilos} kg"
    }
}

class FrutaLocal(
    nombre: String,
    precioPorKilo: Double,
    stockKilos: Double,
    val region: String
) : Fruta(nombre, precioPorKilo, stockKilos) {

    override fun descripcion(): String {
        return "Es una fruta local de la región de $region."
    }
}

class FrutaTropical(
    nombre: String,
    precioPorKilo: Double,
    stockKilos: Double,
    val paisImportacion: String
) : Fruta(nombre, precioPorKilo, stockKilos) {

    override fun descripcion(): String {
        return "Es una fruta tropical importada de $paisImportacion."
    }
}

val almacenFrutas = mutableListOf<Fruta>()

fun mostrarMenu() {
    println("\n" + "=".repeat(45))
    println("|°---FERIA VIRTUAL DE BMART - GESTIÓN DE STOCK---°|")
    println("=".repeat(45))
    println("1. Registrar nueva fruta")
    println("2. Ver inventario o mostrar frutas")
    println("3. Filtrar frutas caras (> $1000 p/kg)")
    println("4. Calcular valor total del inventario")
    println("5. Salir del sistema")
    println("-".repeat(45))
    print("Selecciona una opción [1-5]: ")
}

fun registrarFruta() {
    println("\n--- REGISTRO DE NUEVA FRUTA ---")
    try {
        print("Tipo de fruta (1: Local, 2: Tropical): ")
        val tipo = readLine()?.trim()

        val nombre: String
        val precio: Double
        val stock: Double
        val nuevaFruta: Fruta

        when (tipo) {
            "1", "2" -> {
                print("Nombre de la fruta: ")
                nombre = readLine()?.trim() ?: ""
                if (nombre.isBlank()) {
                    println("[ERROR] El nombre no puede estar vacío.")
                    return
                }

                print("Precio por Kilo: $")
                precio = readLine()?.toDoubleOrNull() ?: 0.0
                if (precio <= 0) {
                    println("[ERROR] El precio debe ser un número mayor que 0.")
                    return
                }

                print("Stock en Kilos: ")
                stock = readLine()?.toDoubleOrNull() ?: -1.0
                if (stock < 0) {
                    println("[ERROR] El stock no puede ser un número negativo.")
                    return
                }

                if (tipo == "1") {
                    print("Región de origen: ")
                    val region = readLine()?.trim() ?: ""
                    nuevaFruta = FrutaLocal(nombre, precio, stock, region)
                } else {
                    print("País de importación: ")
                    val pais = readLine()?.trim() ?: ""
                    nuevaFruta = FrutaTropical(nombre, precio, stock, pais)
                }

                if (nuevaFruta.validar()) {
                    almacenFrutas.add(nuevaFruta)
                    println("\n[ÉXITO] Fruta registrada:")
                    println("FRUTA -> ${nuevaFruta.mostrarInfo()}")
                } else {
                    println("[ERROR] Los datos de la fruta no son válidos.")
                }
            }
            else -> {
                println("[ERROR] Tipo de fruta no válido. Por favor, elige 1 o 2.")
            }
        }
    } catch (e: Exception) {
        println("[ERROR INESPERADO] Ocurrió un problema: ${e.message}")
    }
}

fun mostrarInventarioCompleto() {
    println("\n--- INVENTARIO COMPLETO ---")

    if (almacenFrutas.isEmpty()) {
        println("No hay frutas registradas en el sistema.")
        return
    }

    println("Total de tipos de fruta: ${almacenFrutas.size}")
    println("-".repeat(60))

    almacenFrutas.forEachIndexed { indice, fruta ->
        println("${indice + 1}. ${fruta.mostrarInfo()}")
        println("   └ Descripción: ${fruta.descripcion()}")
    }

    println("-".repeat(60))
}

fun mostrarFrutasCaras() {
    println("\n--- FRUTAS CARAS (> $1000 p/kg) ---")

    val frutasCaras = almacenFrutas
        .filter { it.precioPorKilo > 1000.0 }
        .sortedByDescending { it.precioPorKilo }

    if (frutasCaras.isEmpty()) {
        println("No se encontraron frutas con precio superior a $1000 p/kg.")
        return
    }

    println("Se encontraron ${frutasCaras.size} frutas caras (de mayor a menor precio):")
    println("-".repeat(60))

    frutasCaras.forEach { fruta ->
        println("- ${fruta.mostrarInfo()}")
    }
    println("-".repeat(60))
}

fun calcularMetricasInventario() {
    println("\n--- MÉTRICAS DEL INVENTARIO ---")

    if (almacenFrutas.isEmpty()) {
        println("No hay datos para calcular métricas.")
        return
    }

    val valorTotal = almacenFrutas.sumOf { it.precioPorKilo * it.stockKilos }
    val precioPromedio = almacenFrutas.map { it.precioPorKilo }.average()
    val stockTotal = almacenFrutas.sumOf { it.stockKilos }
    val frutaMasCara = almacenFrutas.maxByOrNull { it.precioPorKilo }
    val frutaMasBarata = almacenFrutas.minByOrNull { it.precioPorKilo }

    println("Valor total del inventario: $${String.format("%.0f", valorTotal)}")
    println("Precio promedio por kilo: $${String.format("%.0f", precioPromedio)}")
    println("Stock total de frutas: $stockTotal kg")
    println()
    println("Fruta más cara: ${frutaMasCara?.mostrarInfo() ?: "N/A"}")
    println("Fruta más barata: ${frutaMasBarata?.mostrarInfo() ?: "N/A"}")
}

fun main() {
    almacenFrutas.add(FrutaLocal("Manzana Fuji", 1200.0, 150.5, "Los Lagos"))
    almacenFrutas.add(FrutaTropical("Plátano", 990.0, 200.0, "Ecuador"))
    almacenFrutas.add(FrutaLocal("Cereza", 5500.0, 45.0, "Chile "))
    almacenFrutas.add(FrutaTropical("Mango", 2100.0, 70.0, "Perú PE"))

    println("*".repeat(50))
    println("BIENVENIDO A LA FERIA VIRTUAL DE BENJAMIN MARTINEZ")
    println("Sistema de Gestión de Inventario")
    println("*".repeat(50))

    var sistemaActivo = true

    while (sistemaActivo) {
        mostrarMenu()

        when (readLine()?.trim()) {
            "1" -> registrarFruta()
            "2" -> mostrarInventarioCompleto()
            "3" -> mostrarFrutasCaras()
            "4" -> calcularMetricasInventario()
            "5" -> {
                println("\nGracias por usar la mejor Feria Virtual.")
                println("¡Vuelve pronto a la mejor Fruteria Virtual.!")
                sistemaActivo = false
            }
            else -> {
                println("\n[ERROR] Opción inválida: '$'readLine()' no es una opcion valida'")
                println("Por favor, selecciona un número entre 1 y 5.")
            }
        }

        if (sistemaActivo) {
            println("\nPresiona ENTER para continuar...")
            readLine()
        }
    }
}