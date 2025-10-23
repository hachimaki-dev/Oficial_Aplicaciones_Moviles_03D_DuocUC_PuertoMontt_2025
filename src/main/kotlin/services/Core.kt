package org.example.services

import org.example.models.Fruta
import org.example.models.FrutaLocal
import org.example.models.FrutaTropical

class Core {
    val inventarioFrutas = mutableListOf<Fruta>()

    fun mostrarMenu() {
        print("\n")
        println("=".repeat(40))
        println(" ".repeat(13) + "Menu principal")
        println("=".repeat(40))
        println("1.- Registrar nueva fruta") // (con validaciones completas)
        println("2.- Mostrar todas las frutas")
        println("3.- Mostrar frutas mas caras") // (> $1000)
        println("4.- Calcular métricas del inventario") // (valor total, promedios, extremos)
        println("5.- Salir")
    }

    fun procesarOpcion(opcion: String) {
        when (opcion.trim()) {
            "1" -> {
                registrarFruta()
            }
            "2" -> {
                mostrarInventario()
            }
            "3" -> {
                filtrarFrutasCaras()
            }
            "4" -> {
                calcularMetricas()
            }
            "5" -> {
                salirPrograma()
            }
            else -> {
                println("[ERROR] Opcion invalida: $opcion")
                println("Por favor, selecciona un número entre el 1 y 5")
            }
        }
    }

    fun registrarFruta() {
        try {
            println("Ingresa el nombre de la Fruta: ")
            val nombre = readln()
            println("Ingresa el precio por kilo de la Fruta:")
            val precioPorKilo = readln().toDoubleOrNull()?: 0.0
            // Null safety que reaccionara si el usuario no ingresa nada, automaticamente el valor sera de 0.0
            println("Ingresa el stock por kilos de la Fruta:")
            // Null safety que reaccionara si el usuario no ingresa nada, automaticamente el valor sera de -1
            val stockKilos = readln().toIntOrNull()?: -1
            // Estos valores automaticos por el Null safety ejecutara una excepcion de inicializacion de la clase
            // entregando un mensaje de error al usuario
            println("Ingresa el tipo de fruta ( 1-2 ): ")
            println("1.- Fruta local")
            println("2.- Fruta tropical")
            val opcion = readln()
            when (opcion.trim()){
                "1" -> {
                    val frutaLocal = FrutaLocal(nombre, precioPorKilo, stockKilos)
                    inventarioFrutas.add(frutaLocal)
                }
                "2" -> {
                    val frutaTropical = FrutaTropical(nombre, precioPorKilo, stockKilos)
                    inventarioFrutas.add(frutaTropical)
                }
                else ->  {
                    println("[ERROR] Opcion de fruta invalida: $opcion")
                }
            }
        } catch (e: Exception) {
            println(e)
        }
    }

    fun mostrarInventario() {
        // Validacion si es que la lista de frutas no esta vacia
        if(inventarioFrutas.isEmpty()) {
            print("El inventario de frutas esta vacio")
            return
        }
        inventarioFrutas.forEach {
            println("=".repeat(40))
            println(it.descripcion())
            println("Precio por kilo: ${it.precioPorKilo}")
            println("Stock por kilos: ${it.stockKilos}")
        }
        println("=".repeat(40))
    }

    fun filtrarFrutasCaras() {
        val inventarioFrutasCaras = inventarioFrutas.filter { it.precioPorKilo > 1000 }
            .sortedBy { it.precioPorKilo }
        inventarioFrutasCaras.forEach {
            println("=".repeat(40))
            println(it.descripcion())
            println("Precio por kilo: ${it.precioPorKilo}")
            println("Stock por kilos: ${it.stockKilos}")
        }
    }

    fun calcularMetricas() {
        val valorTotal = inventarioFrutas.sumOf{ it.precioPorKilo * it.stockKilos }
        // Incluir metricas adicionales como promedio .average
        val valorPromedio = inventarioFrutas.map{ it.precioPorKilo }.average()
        val valorMaximo = inventarioFrutas.maxByOrNull { it.precioPorKilo  }
        val valorMinimo = inventarioFrutas.minByOrNull { it.precioPorKilo  }

        println("Valor total: $valorTotal")
        println("Valor promedio: $valorPromedio")
        println("Valor maximo: $valorMaximo")
        println("Valor minimo: $valorMinimo")
    }

    fun salirPrograma() {
        System.exit(0)
    }
}


