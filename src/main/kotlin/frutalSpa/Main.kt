package frutalSpa
// AGRADESCO A SYSTEM OF A DOWN, SLIPKNOT, THREE DAYS GRACE, SLAYER, LIMP BIZKIT, por que sin ellos no lo hubiera logrado
val inventario = mutableListOf<Fruta>()


data class Fruta(
    val nombre: String,
    val precioKilo: Int,
    val stockKilos: Double
)

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
                println("\n*** Gracias por usar el sistema. ¡Buenas ventas! ***")
                return
            }
            else -> {
                println("\n Opción no válida. Por favor, ingrese un número del 1 al 5.")
            }
        }

        println("\nPresione Enter para continuar...")
        readLine()
    }
}


fun registrar() {
    println("\n*** Registrar Nueva Fruta ***")

    var nombre: String
    while (true) {
        print("Ingrese el nombre de la fruta:\n")
        nombre = readLine() ?: ""
        if (nombre.isNotBlank()) {
            break
        }
        println("ERROR, El nombre не puede estar vacío. Inténtelo de nuevo.")
    }

    var precio: Int
    while (true) {
        print("Ingrese el precio por Kg:\n")
        val precioInput = readLine()?.toIntOrNull()
        if (precioInput != null && precioInput > 0) {
            precio = precioInput
            break
        }
        println("ERROR, Precio inválido. Debe ingresar un número positivo.")
    }

    var stock: Double
    while (true) {
        print("Ingrese el stock inicial en Kilogramos:\n")
        val stockInput = readLine()?.toDoubleOrNull()
        if (stockInput != null && stockInput >= 0.0) { // El stock puede ser 0
            stock = stockInput
            break
        }
        println("ERROR, Stock inválido. Debe ingresar un número no negativo.")
    }

    val nuevaFruta = Fruta(
        nombre = nombre,
        precioKilo = precio,
        stockKilos = stock,
    )

    // Agrega la fruta a la lista compartida.
    inventario.add(nuevaFruta)

    println("\n ¡ Su Nueva Fruta '${nuevaFruta.nombre}' fue registrada con éxito!")
}

fun mostrarInventario() {
    println("\n--- Inventario Actual ---")
    if (inventario.isEmpty()) {
        println("Aún no hay frutas registradas.")
    } else {
        inventario.forEachIndexed { index, fruta ->
            println("${index + 1}. Nombre: ${fruta.nombre}, Precio/Kg: $${fruta.precioKilo}, Stock: ${fruta.stockKilos} Kg")
        }
    }
}

fun filtrarFrutasCaras() {
    println("\n--- Frutas Caras (Precio > $1000) ---")
    val frutasCaras = inventario.filter { it.precioKilo > 1000 }
    if (frutasCaras.isEmpty()) {
        println("No hay frutas con precio superior a $1000.")
    } else {
        frutasCaras.forEach { fruta ->
            println("- Nombre: ${fruta.nombre}, Precio/Kg: $${fruta.precioKilo}")
        }
    }
}

fun calcularMetricas() {
    println("\n--- Métricas del Inventario ---")
    // Aquí iría la lógica para calcular el valor total, promedios, etc.
    if(inventario.isNotEmpty()){
        val valorTotal = inventario.sumOf { it.precioKilo * it.stockKilos }
        println("Valor total del inventario: $$valorTotal")
    } else {
        println("El inventario está vacío.")
    }
}