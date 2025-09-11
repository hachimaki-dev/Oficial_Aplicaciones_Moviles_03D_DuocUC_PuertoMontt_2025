package org.example.com.otakuwear

import org.example.com.otakuwear.services.Core


fun main() {
    var sistemaActivo: Boolean = true

    var core: Core = Core()

    fun mostrarMenu(){
        println("Menu principal")
        println("1.- Registrar viaje")
        println("2.- Ver viajes")
        println("3.- Comprar pasaje")
        println("4.- Ver metricas")
        println("5.- Salir")
    }

    while (sistemaActivo) {
        mostrarMenu()

        // Captura de opción con validación
        val opcionTexto = readln()

        // Procesamiento de la opción seleccionada
        when (opcionTexto) {
            "1" -> {
                core.registrarNuevoViaje()
            }
            "2" -> {
                core.verViajes()
            }
            "3" -> {
                core.comprarPasaje()
            }
            "4" -> {
                core.verMetricas()
            }
            "5" -> {
                sistemaActivo = false
            }
            else -> {
                println("\n[ERROR] Opción inválida: '$opcionTexto'")
                println("Por favor, selecciona un número entre 1 y 5")
            }
        }
    }
}