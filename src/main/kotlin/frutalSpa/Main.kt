package frutalSpa
import java.lang.Exception

// AGRADESCO A SYSTEM OF A DOWN, SLIPKNOT, THREE DAYS GRACE, SLAYER, LIMP BIZKIT, por que sin ellos no lo hubiera logrado
val inventario = mutableListOf<Fruta>()


abstract class Fruta(
    val nombre: String,
    val precioPorKilo: Double, // Cambiado a Double según la pauta
    val stockKilos: Double
) {

    abstract fun descripcion(): String
}


class FrutaLocal(nombre: String, precio: Double, stock: Double) : Fruta(nombre, precio, stock) {
    override fun descripcion() = "Fruta local: $nombre - Producida en la región"
}


class FrutaTropical(nombre: String, precio: Double, stock: Double) : Fruta(nombre, precio, stock) {
    override fun descripcion() = "Fruta tropical: $nombre - Importada"
}


fun main() {

    while (true) {
        println("\n********* Bienvenido a Frutal Spa *********")
        println(
            """
        1.-Registrar nueva fruta 
        2.-Mostrar todas las frutas
        3.-Mostrar frutas caras 
        4.-Calcular métricas de inventario 
        5.-Salir
        """.trimIndent()
        )

        print("\n**** Ingrese una opción: ****\n")
        val opcion = readLine()?.toIntOrNull()


        when (opcion) {
            1 -> registrar()
            2 -> mostrarInventario()
            3 -> filtrarFrutasCaras()
            4 -> calcularMetricas()
            5 -> {
                println("\n*** Gracias por Preferirnos ***")
                return
            }
            else -> {
                println("\n Opción no válida, ingrese un número del 1 al 5.")
            }
        }

        println("\nPresione Enter para continuar...")
        readLine()
    }
}


fun registrar() {
    println("\n*** Registrar Nueva Fruta ***")

    try {
        var nombre: String
        while (true) {
            print("Ingrese el nombre de la fruta:\n")
            nombre = readLine() ?: ""
            if (nombre.isNotBlank()) {
                break
            }

            println("ERROR, Inténtelo de nuevo.")
        }

        print("Ingrese el precio por Kg:\n")
        val precio = readLine()?.toDoubleOrNull()
        if (precio == null || precio <= 0) {

            throw Exception("Precio inválido. Debe ingresar un número positivo.")
        }

        print("Ingrese el stock inicial en Kilogramos:\n")
        val stock = readLine()?.toDoubleOrNull()
        if (stock == null || stock < 0.0) {
            throw Exception("Stock inválido. Debe ingresar un número no negativo.")
        }

        print("Ingrese el tipo de fruta (1: Local, 2: Tropical):\n")
        val tipo = readLine()?.toIntOrNull()


        val nuevaFruta: Fruta = when (tipo) {
            1 -> FrutaLocal(nombre, precio, stock)
            2 -> FrutaTropical(nombre, precio, stock)
            else -> throw Exception("Tipo de fruta no válido.")
        }

        inventario.add(nuevaFruta)

        println("\n ¡ Su Nueva Fruta '${nuevaFruta.nombre}' fue registrada con éxito!")

    } catch (e: Exception) {

        println("ERROR: ${e.message}")
    }
}

fun mostrarInventario() {
    println("\n *** Inventario Actual ***")
    if (inventario.isEmpty()) {
        println("Aún no hay frutas registradas.")
    } else {
        inventario.forEachIndexed { index, fruta ->

            println("${index + 1}. Nombre: ${fruta.nombre}, Precio/Kg: $${fruta.precioPorKilo}, Stock: ${fruta.stockKilos} Kg")
            println("   -> ${fruta.descripcion()}")
        }
    }
}

fun filtrarFrutasCaras() {
    println("\n*** Frutas Caras (Precio > $1000) ***")

    val frutasCaras = inventario.filter { it.precioPorKilo > 1000 }.sortedByDescending { it.precioPorKilo }
    if (frutasCaras.isEmpty()) {
        println("No hay frutas con precio superior a $1000.")
    } else {
        frutasCaras.forEach { fruta ->
            println("- Nombre: ${fruta.nombre}, Precio/Kg: $${fruta.precioPorKilo}")
        }
    }
}

fun calcularMetricas() {
    println("\n*** Métricas del Inventario ***")
    if(inventario.isNotEmpty()){

        val valorTotal = inventario.sumOf { it.precioPorKilo * it.stockKilos }
        println("Valor total del inventario: $$valorTotal")


        val precioPromedio = inventario.map { it.precioPorKilo }.average()
        val frutaMasCara = inventario.maxByOrNull { it.precioPorKilo }
        val frutaMasBarata = inventario.minByOrNull { it.precioPorKilo }

        println("Precio promedio por kilo: $${String.format("%.2f", precioPromedio)}")
        if (frutaMasCara != null) {
            println("Fruta más cara: ${frutaMasCara.nombre} ($${frutaMasCara.precioPorKilo})")
        }
        if (frutaMasBarata != null) {
            println("Fruta más barata: ${frutaMasBarata.nombre} ($${frutaMasBarata.precioPorKilo})")
        }

    } else {
        println("El inventario está vacío.")
    }
}