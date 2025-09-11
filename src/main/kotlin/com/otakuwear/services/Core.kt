package org.example.com.otakuwear.services

import org.example.com.otakuwear.models.Camiseta
import org.example.com.otakuwear.models.Prenda


class Core {
    val almacenPrendas = mutableListOf<Prenda>()
    val almacenCamisetas = mutableListOf<Camiseta>()

    fun registrarPrenda() {
        println("Escoja una opcion")
        println("1.- Registrar prenda")
        println("2.- Registrar camiseta")
        // Captura la opcion con validacion
        val opcionTexto = readln()
        when(opcionTexto) {
            "1" -> {
                println("Ingrese el nombre de la prenda:")
                val nombre: String = readLine() ?: ""
                println("Ingrese el precio de la prenda:")
                val precio: Double = readln().toDouble()
                println("Ingrese el stock de la prenda:")
                val stock: Int = readln().toInt()


                val nuevaPrenda = Prenda(nombre, precio, stock)
                nuevaPrenda.validar()
                almacenPrendas.add(nuevaPrenda)
            }

            "2" -> {
                println("Ingrese el nombre de la prenda:")
                val nombre: String = readLine() ?: ""
                println("Ingrese el precio de la prenda:")
                val precio: Double = readln().toDouble()
                println("Ingrese el stock de la prenda:")
                val stock: Int = readln().toInt()
                println("Ingrese la talla de la prenda:")
                val talla: Double = readln().toDouble()
                println("Ingrese el material de la prenda:")
                val material: String = readLine() ?: ""
                // No pude terminar :((
                val nuevaCamiseta = Camiseta(nombre, precio, stock, talla, material)
                nuevaCamiseta.validar()
                almacenCamisetas.add(nuevaCamiseta)
            }

            else -> {
                println("\n[ERROR] Opción inválida: '$opcionTexto'")
                println("Por favor, selecciona un número entre 1 y 5")
            }
        }
    }

    fun mostrarInventario() {
        var contador: Int = 1
        // Validacion de que el inventario tenga aunque sea una prenda
        if (almacenPrendas.size == 0) {
            println("El inventario esta vacio")
            return
        }
        almacenPrendas.forEach {
            println("=".repeat(50))
            println("Prenda: $contador")
            it.mostrarInfo()
            contador++
        }
    }

    fun mostrarPrendasPremium() {
        var contador: Int = 1
        val prendasCaras = almacenPrendas.filter { it.precio > 1000 }.sortedBy { it.nombre }.sortedByDescending { it.precio }

        if (prendasCaras.size == 0) {
            println("El inventario de prendas caras esta vacio")
            return
        }

        prendasCaras.forEach {
            println("=".repeat(50))
            println("Prenda: $contador")
            it.mostrarInfo()
            contador++
        }
    }

    fun calcularMetricasInventario(){
        val sumaTotal = almacenPrendas.sumOf{ it.precio }
        val prendaMasCara = almacenPrendas.maxByOrNull { it.precio }
        val precioPromedio = almacenPrendas.map { it.precio }.average()
        val prendaMasBarata = almacenPrendas.minByOrNull { it.precio }

        println("Suma total de las prendas: $sumaTotal")
        println("Prenda mas cara: ${prendaMasCara?.nombre}($${String.format("%.0f", prendaMasCara?.precio)})")
        println("Precio promedio de las prendas: $precioPromedio")
        println("Prenda mas barata: ${prendaMasBarata?.nombre}($${String.format("%.0f", prendaMasBarata?.precio)})")
    }
}