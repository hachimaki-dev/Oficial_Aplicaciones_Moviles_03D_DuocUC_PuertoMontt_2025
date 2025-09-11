package org.example.Fruteria

fun main() {
    data class Fruta(
        val nombre: String,
        val precio: Int,
        var stock: Int,
        val stockInicial: Int
    )

    val frutas = mutableListOf<Fruta>()

    fun pausar() {
        println("\nPresiona ENTER para continuar...")
        readLine()
    }

    fun mostrarMenu() {
        println("\nPuerto frutal SPA")
        println("1. Registrar Fruta")
        println("2. Ver inventario")
        println("3. Mostrar frutas más caras")
        println("4. Ver métricas")
        println("5. Salir")
        print("Elige una opción: ")
    }

    fun registrarFruta() {
        try {
            print("Nombre de la fruta: ")
            val nombre = readLine()?.trim().orEmpty()
            if (nombre.isEmpty()) {
                println("Nombre no puede estar vacío.")
                pausar()
                return
            }

            print("Precio de la fruta: ")
            val precio = readLine()?.toIntOrNull()
            if (precio == null || precio <= 0) throw Exception("Precio inválido.")

            print("Cantidad en stock: ")
            val cantidad = readLine()?.toIntOrNull()
            if (cantidad == null || cantidad <= 0) throw Exception("Cantidad inválida.")

            frutas.add(Fruta(nombre, precio, cantidad, cantidad))
            println("Fruta registrada con éxito.")
        } catch (e: Exception) {
            println("[ERROR] ${e.message}")
        }
        pausar()
    }

    fun verInventario() {
        if (frutas.isEmpty()) {
            println("No hay frutas registradas.")
        } else {
            println("\nStock de Frutas")
            frutas.forEachIndexed { index, f ->
                println("${index + 1}. ${f.nombre} - $${f.precio} - Stock: ${f.stock}/${f.stockInicial}")
            }
        }
        pausar()
    }

    fun mostrarFrutasCaras() {
        println("\n--- Frutas superiores 3000 ---")
        val frutasCaras = frutas.filter { it.precio > 1000 }

        if (frutasCaras.isEmpty()) {
            println("No hay frutas caras.")
        } else {
            println("Se encontraron ${frutasCaras.size} frutas caras:")
            println("-".repeat(50))
            frutasCaras
                .sortedByDescending { it.precio }
                .forEachIndexed { index, f ->
                    println("${index + 1}. ${f.nombre} - $${f.precio} - Stock: ${f.stock}/${f.stockInicial}")
                }
        }
        pausar()
    }

    fun verMetricas() {
        if (frutas.isEmpty()) {
            println("No hay frutas registradas.")
            pausar()
            return
        }


        val recaudacionTotal = frutas.sumOf { ( it.stock) * it.precio }
        val stockTotal = frutas.sumOf { ( it.stock)  }

        println("\nMétricas")

        println("total en mercancia: $$recaudacionTotal")
        println("stock total: #$stockTotal")


        pausar()
    }


    while (true) {
        mostrarMenu()
        when (readLine()?.trim()) {
            "1" -> registrarFruta()
            "2" -> verInventario()
            "3" -> mostrarFrutasCaras()
            "4" -> verMetricas()
            "5" -> {
                println("Saliendo del sistema. Gracias por usar la frutería.")
                break
            }
            else -> {
                println("[ERROR] Opción inválida, intente de nuevo.")
                pausar()
            }
        }
    }
}
