package org.example

// Funcion principal
fun main() {
    println("""
        +----------------------------------+
        |    Puerto Frutal Puerto Montt    |
        +----------------------------------+
    """.trimIndent())

    var sistemaActivo = true

    while (sistemaActivo) {
        mostrarMenu()

        val opcionTexto = readln().trim()

        when (opcionTexto) {
            "1" -> {
                registrarFrutas()
            }
            "2" -> {
                mostrarInventarioCompleto()
            }
            "3" -> {
                mostrarFrutasCaras()
            }
            "4" -> {
                mostrarMetricasInventario()
            }
            "5" -> {
                println("\nSaliendo del sistema..")
                println("¡Nos vemos luego!")
                sistemaActivo = false
            }
            else -> {
                println("[ERROR] Opción inválida.")
                println("Elige una opción entre el 1 y el 5")
            }
        }
    }
}
// FIN funcion principal


// Clase padre
abstract class Fruta(
    val nombre: String,
    val precioPorKilo: Double,
    val stockKilos: Double
){
    abstract fun descripcion(): String

    fun validar(): Boolean{
        return nombre.isNotBlank() && precioPorKilo > 0 && stockKilos >= 0
    }

    fun mostrarInfo(): String {
        return "$nombre - Precio: $precioPorKilo - Stock: $stockKilos"
    }
}

// sub-clases
class FrutaLocal(nombre: String, precioPorKilo: Double, stockKilos: Double) : Fruta(nombre, precioPorKilo, stockKilos) {
    override fun descripcion() = "Fruta local: $nombre"
}

class FrutaTropical(nombre: String, precioPorKilo: Double, stockKilos: Double) : Fruta(nombre, precioPorKilo, stockKilos) {
    override fun descripcion() = "Fruta Tropical: $nombre"
}



val listaFrutas = mutableListOf<Fruta>()

// Funciones del interfaz
fun mostrarMenu() {
    println("""
        
        ===========================================
                   Gestión de Inventario
        ===========================================
        1. Registrar Frutas
        2. Mostrar Frutas Registradas
        3. Mostrar Frutas Caras (> $1000)
        4. Calcular Métricas del inventario
        5. Salir
        >""".trimIndent())
}

fun registrarFrutas() {


    println("\n-- Registrar Frutas Puerto Montt --")

    try {

        print("Nombre de la fruta:")
        val nombre = readln().trim()
        if (nombre.isBlank()) {
            println("[ERROR] Campo necesario")
            return
        }

        print("Precio por Kilo:")
        val precioPorKilo = readln().trim().toDoubleOrNull()
        if (precioPorKilo === null || precioPorKilo <= 0) {
            println("[ERROR] Valor ingresado no válido.")
            return
        }

        print("Cantidad Stock:")
        val stock = readln().trim().toDoubleOrNull()
        if (stock == null || stock < 0) {
            println("[ERROR] Valor ingresado no válido.")
            return
        }

        println("Elige el tipo de Fruta:")
        println("1. Fruta Local")
        println("2. Futa Tropical")
        val tipoFruta = readln().trim().toIntOrNull()
        if (tipoFruta === null || tipoFruta <= 0 || tipoFruta >= 3) {
            println("[ERROR] Tipo de Fruta ínvalido.")
            return
        }
        if (tipoFruta == 1) {
            val nuevaFruta = FrutaLocal(nombre, precioPorKilo, stock)


            if (nuevaFruta.validar()) {
                listaFrutas.add(nuevaFruta)
                println("¡Fruta registrada con éxito!")
                println(nuevaFruta.mostrarInfo())
            } else {
                println("[ERROR] los datos de las prendas son invalidos!")
            }
        } else if (tipoFruta == 2){
            val nuevaFruta = FrutaTropical(nombre, precioPorKilo, stock)

            if (nuevaFruta.validar()) {
                listaFrutas.add(nuevaFruta)
                println("¡Fruta registrada con éxito!")
                println(nuevaFruta.mostrarInfo())
            } else {
                println("[ERROR] los datos de las prendas son invalidos!")
            }
        }

    }catch (exception: Exception){
        println("[ERROR] ${exception.message}")
        println("Vuelva a intentarlo.")
    }
}

fun mostrarInventarioCompleto() {
    println("\n--- INVENTARIO COMPLETO ---")

    if (listaFrutas.isEmpty()) {
        println("No hay frutas registradas en el sistema.")
        println("Usa la opción 1 para agregar frutas.")
        return
    }

    println("Total de frutas: ${listaFrutas.size}")
    println("-".repeat(50))

    listaFrutas.forEachIndexed { indice, fruta ->
        val numero = indice + 1
        println("$numero. ${fruta.mostrarInfo()}")
    }

    println("-".repeat(50))
}

fun mostrarFrutasCaras(){
    println("\n--- PRENDAS PREMIUM (> $1000) ---")

    val frutasCaras = listaFrutas.filter { fruta ->
        fruta.precioPorKilo > 1000.0
    }

    if (frutasCaras.isEmpty()) {
        println("No hay prendas premium registradas.")
        return
    }

    println("Se encontraron ${frutasCaras.size} prendas premium:")
    println("-".repeat(50))

    frutasCaras
        .sortedByDescending { it.precioPorKilo }  // Ordenar por precio descendente
        .forEachIndexed { indice, fruta ->
            println("${indice + 1}. ${fruta.mostrarInfo()}")
        }
}

fun mostrarMetricasInventario(){
    println("\n--- MÉTRICAS DEL INVENTARIO ---")

    if (listaFrutas.isEmpty()) {
        println("No hay datos para calcular métricas.")
        return
    }

    // Valor total del inventario (precio × stock por cada prenda)
    val valorTotal = listaFrutas.sumOf { fruta ->
        fruta.precioPorKilo * fruta.stockKilos
    }

    // Prenda más cara
    val prendaMasCara = listaFrutas.maxByOrNull { it.precioPorKilo }

    // Prenda más barata
    val prendaMasBarata = listaFrutas.minByOrNull { it.precioPorKilo }

    // Precio promedio
    val precioPromedio = listaFrutas.map { it.precioPorKilo }.average()

    // Stock total
    val stockTotal = listaFrutas.sumOf { it.stockKilos }

    // Mostrar resultados
    println("Valor total del inventario: $${String.format("%.2f", valorTotal)}")
    println("Precio promedio: $${String.format("%.2f", precioPromedio)}")
    println("Stock total: $stockTotal unidades")
    println()
    println("Fruta más cara: ${prendaMasCara?.mostrarInfo() ?: "N/A"}")
    println("Fruta más barata: ${prendaMasBarata?.mostrarInfo() ?: "N/A"}")
}