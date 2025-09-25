package org.example

import org.example.com.otakuwear.models.Fruta
import org.example.com.otakuwear.models.FrutaLocal
import org.example.com.otakuwear.models.FrutaTropical
import kotlin.math.round

val inventario = mutableListOf<Fruta>()

fun main() {
    val Manzana = FrutaLocal("Manzana", 2000.0, 30.0)
    val Pera = FrutaLocal("Pera",1400.0,23.0)
    val Mango = FrutaTropical("Mango", 2800.0, 40.0)
    inventario.add(Manzana)
    inventario.add(Pera)
    inventario.add(Mango)

    var menuActivo = true

    while (menuActivo) {
        println(
            """
                
                ---------------------------------
                        Puerto Frutal SPA
                ---------------------------------
                
                Elige una opcion ->
                1.- Registrar fruta
                2.- Mostrar inventario
                3.- Filtrar Frutas Caras
                4.- Calcular valor total del stock
                5.- Salir
                Ingrese una opcion: 
            """.trimIndent()
        )

        val opcion = readln().toInt()

        when(opcion){
            1 -> regFruta()
            2 -> verInv()
            3 -> filtrarFrutaCara()
            4 -> sumValStock()
            5 -> {
                println("\nCerrando el programa.\n")
                menuActivo = false
            }
            else -> println("\nIngrese una opcion valida\n")
        }
    }
}

fun regFruta() {
    try {
        println("Ingresa el nombre: ")
        val nombre = readLine()?.trim() ?: ""

        println("\nIngresa el precio x KG: ")

        val precio = readln().toDoubleOrNull() ?: throw NumberFormatException("El precio ingresado no es un número válido.")

        println("\nIngresa el stock: ")
        val stock = readln().toDoubleOrNull()?: throw NumberFormatException("El stock ingresado no es un número válido.")

        println("\n¿Qué tipo de fruta es? (1: Local, 2: Tropical): ")
        val tipo = readln().toIntOrNull()

        val nuevaFruta: Fruta = when (tipo) {
            1 -> FrutaLocal(nombre, precio, stock)
            2 -> FrutaTropical(nombre, precio, stock)
            else -> throw IllegalArgumentException("Tipo de fruta no válido. Elige 1 o 2.")
        }

        inventario.add(nuevaFruta)
        println("\n Fruta Agregada: ${nuevaFruta.descripcion()}\n")

    } catch (e: NumberFormatException) {
        println("❌ Error de formato: ${e.message}")
    } catch (e: IllegalArgumentException) {
        println("❌ Error de validación: ${e.message}")
    } catch (e: Exception) {
        println("❌ Ocurrió un error inesperado: ${e.message}")
    }
}

fun verInv(){
    println("\n--- Inventario de la tienda ---")
    if (inventario.isEmpty()) {
        println("No hay frutas registradas en el sistema.")
        return
    }

    inventario.forEachIndexed { i, Fruta ->
        println("""
        ${i + 1}. Nombre: ${Fruta.nombre}, Precio x Kg: $${Fruta.precioPorKilo}
           Stock: ${Fruta.stockKilos}
        """.trimIndent())
    }
}

fun filtrarFrutaCara(){
    println("\n--- Inventario de la tienda ---\n --- Fruta Cara ---")
    if (inventario.isEmpty()) {
        println("No hay frutas registradas en el sistema.")
        return
    }
    val frutasCaras = inventario.filter{ it.precioPorKilo > 1000.0 }.sortedByDescending{ it.precioPorKilo }
    if (frutasCaras.isEmpty()) {
        println("No hay frutas caras en el inventario.")
    } else {
        frutasCaras.forEachIndexed { i, fruta ->
            println("${i + 1}. Nombre: ${fruta.descripcion()}, Precio x Kg: $${fruta.precioPorKilo}, Stock: ${fruta.stockKilos}")
        }
    }
}

fun sumValStock(){
    println("\n--- Inventario de la tienda ---\n --- Valor total del stock ---")
    if (inventario.isEmpty()) {
        println("No hay frutas registradas en el sistema.")
        return
    }else{
        val valorTotalStock = inventario.sumOf { it.precioPorKilo * it.stockKilos }
        println("\nValor total del Stock: $valorTotalStock")
        val precioPromedio = round(inventario.map { it.precioPorKilo }.average())
        println("\nPrecio Promedio: $precioPromedio")
        println("\nProducto mas caro / Producto mas barato\n")
        val frutaMasCara = inventario.maxByOrNull { it.precioPorKilo }
        val frutaMasBarata = inventario.minByOrNull { it.precioPorKilo }

        println("Fruta más cara: ${frutaMasCara?.nombre} ($${frutaMasCara?.precioPorKilo})")
        println("Fruta más barata: ${frutaMasBarata?.nombre} ($${frutaMasBarata?.precioPorKilo})")
    }
}