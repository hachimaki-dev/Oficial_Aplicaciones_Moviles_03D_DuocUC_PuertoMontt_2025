package org.example

import org.example.com.otakuwear.services.Core

fun main() {
    println("".repeat(50))
    println("\nBienvenido a OtakuWear\n")
    println("".repeat(50))

    val core: Core = Core()
    var sistemaActivo: Boolean = true

    fun mostrarMenu(){
        println("Menu principal")
        println("1.- Registrar prenda")
        println("2.- Mostrar inventario")
        println("3.- Filtrar por premium")
        println("4.- Mostrar metricas financieras")
        println("5.- Salir")
    }

    while(sistemaActivo) {
        mostrarMenu()

        // Captura la opcion con validacion
        val opcionTexto = readln()

        when(opcionTexto){
            "1" -> {
                core.registrarPrenda()
            }
            "2" -> {
                core.mostrarInventario()
            }
            "3" -> {
                core.mostrarPrendasPremium()
            }
            "4" -> {
                core.calcularMetricasInventario()
            }
            "5" -> {
                sistemaActivo = false
            }
            else -> {
                println("\n[ERROR] Opción inválida: '$opcionTexto'")
                println("Por favor, selecciona un número entre 1 y 5")
            }
            //Tiene que haber una pausa, el cual se pueda salir con Enter
        }
    }
}