package org.example

import org.example.com.otakuwear.models.Fruta
import kotlin.math.round

val inventario = mutableListOf<Fruta>()

fun main() {
    val Manzana = Fruta("Manzana",2000.0,30.0)
    val Pera = Fruta("Pera",1400.0,23.0)
    inventario.add(Manzana)
    inventario.add(Pera)

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

fun regFruta(){

    println("Ingresa el nombre: ")
    var nombre = readLine()?.trim() ?: ""

    if (nombre.isBlank()){
        println("\nEl nombre es obligatorio\n")
        return
    }

    println("\nIngresa el precio x KG: ")
    var precio = readln().toDoubleOrNull();

    println("\nIngresa el stock: ")
    var stock = readln().toDoubleOrNull();

    if (precio == null || stock == null){
        println("Por favor, ingrese valores validos para precio o stock\n")
        return
    }else{
        stock.toDouble()
        precio.toDouble()
        if (precio <= 0.0 || stock < 0.0) {
            println("Error! El precio y el stock deben ser números positivos.")
            return
        }else{
            var nuevaFruta = Fruta(nombre,precio,stock)
            println("\nFruta Agregada\n")
            inventario.add(nuevaFruta)
        }
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
    val frutasCaras = inventario.filter { it.precioPorKilo > 1000.0 }
    if (frutasCaras.isEmpty()) {
        println("No hay frutas caras en el inventario.")
    } else {
        frutasCaras.forEachIndexed { i, fruta ->
            println("${i + 1}. Nombre: ${fruta.nombre}, Precio x Kg: $${fruta.precioPorKilo}, Stock: ${fruta.stockKilos}")
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
    }
}