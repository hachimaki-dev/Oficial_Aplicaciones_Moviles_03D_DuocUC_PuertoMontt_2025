package org.example

import org.example.services.Core

fun main() {
    val core: Core = Core()

    while(true) {
        core.mostrarMenu()
        val opcion = readln()
        core.procesarOpcion(opcion)
    }

}