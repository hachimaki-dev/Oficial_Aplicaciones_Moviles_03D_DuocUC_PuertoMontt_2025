

    abstract class Fruta(
        val nombre: String,
        val precioPorKilo: Double,
        val stockKilos: Int
    ) {

        abstract fun descripcion(): String

        // Función común: calcular valor total en stock
        fun valorTotal(): Double = precioPorKilo * stockKilos
    }

    // Subclase: Fruta Local
    class FrutaLocal(nombre: String, precio: Double, stock: Int) :
        Fruta(nombre, precioPorKilo = precio, stockKilos = stock) {
        override fun descripcion() = "Fruta local: $nombre - Producida en la región"
    }

    // Subclase: Fruta Tropical
    class FrutaTropical(nombre: String, precio: Double, stock: Int) :
        Fruta(nombre, precioPorKilo = precio, stockKilos = stock) {
        override fun descripcion() = " Fruta tropical: $nombre - Importada"
    }
    val inventario = mutableListOf<Fruta>()

    fun main() {
        while (true) {
            println("\n=== Menú de Gestión de Inventario ===")
            println("1. Registrar nueva fruta")
            println("2. Mostrar todas las frutas")
            println("3. Mostrar frutas caras (> $1000)")
            println("4. Calcular métricas del inventario")
            println("5. Salir")
            print(" Selecciona una opción: ")

            when (readLine()?.trim()) {
                "1" -> registrarFruta()
                "2" -> mostrarFrutas()
                "3" -> mostrarFrutasCaras()
                "4" -> calcularMetricas()
                "5" -> {
                    println(" xau xau ")
                    return
                }

                else -> println(" Opción no válida, intenta de nuevo")
            }
        }
    }

    fun registrarFruta() {
        try {
            print(" Nombre de la fruta: ")
            val nombre = readLine()?.trim().orEmpty()
            if (nombre.isBlank()) throw IllegalArgumentException(" El nombre no puede estar vacío")

            print(" Precio por kilo: ")
            val precio = readLine()?.toDoubleOrNull()
                ?: throw NumberFormatException(" Precio inválido")
            if (precio <= 0) throw IllegalArgumentException(" El precio debe ser mayor que 0")

            print(" Stock en kilos: ")
            val stock = readLine()?.toIntOrNull()
                ?: throw NumberFormatException(" Stock inválido")
            if (stock < 0) throw IllegalArgumentException(" El stock no puede ser negativo")

            print(" Tipo de fruta (1 = Local, 2 = Tropical): ")
            val tipo = readLine()?.trim()

            val fruta: Fruta = when (tipo) {
                "1" -> FrutaLocal(nombre, precio, stock)
                "2" -> FrutaTropical(nombre, precio, stock)
                else -> throw IllegalArgumentException("Tipo no válido")
            }

            inventario.add(fruta)
            println(" Fruta registrada exitosamente")

        } catch (e: NumberFormatException) {
            println(" Error de formato: ${e.message}")
        } catch (e: IllegalArgumentException) {
            println("️ Error de validación: ${e.message}")
        } catch (e: Exception) {
            println(" Error inesperado: ${e.message}")
        }
    }

    fun mostrarFrutas() {
        if (inventario.isEmpty()) {
            println("No hay frutas registradas en el inventario")
        } else {
            println("\n Inventario de frutas:")
            inventario.forEachIndexed { index, fruta ->
                println("${index + 1}. ${fruta.descripcion()} - Precio: $${fruta.precioPorKilo} - Stock: ${fruta.stockKilos}kg")
            }
        }
    }

    fun mostrarFrutasCaras() {
        val frutasCaras = inventario.filter { it.precioPorKilo > 1000 }
            .sortedByDescending { it.precioPorKilo }

        if (frutasCaras.isEmpty()) {
            println(" No hay frutas con precio mayor a $1000")
        } else {
            println("\n Frutas caras (> $1000):")
            frutasCaras.forEach {
                println("${it.descripcion()} - Precio: $${it.precioPorKilo}")
            }
        }
    }
    fun calcularMetricas() {
        val valorTotal = inventario.sumOf { it.valorTotal() }
        val precioPromedio = inventario.map { it.precioPorKilo }.average()
        val frutaMasCara = inventario.maxByOrNull { it.precioPorKilo }
        val frutaMasBarata = inventario.minByOrNull { it.precioPorKilo }

        println("\n Métricas del Inventario:")
        println("Valor total del stock: $${"%.2f".format(valorTotal)}")
        println("Precio promedio: $${"%.2f".format(precioPromedio)}")
        println(" Fruta más cara: ${frutaMasCara?.nombre} ($${frutaMasCara?.precioPorKilo})")
        println(" Fruta más barata: ${frutaMasBarata?.nombre} ($${frutaMasBarata?.precioPorKilo})")
    }
