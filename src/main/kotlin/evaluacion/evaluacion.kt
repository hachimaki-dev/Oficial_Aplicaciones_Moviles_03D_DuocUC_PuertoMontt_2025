package org.example.evaluacion

class Fruta(
    val nombre: String,
    val precioPorKilo: Int,
    var stockKilos: Int,

) {

    override fun toString(): String {
        return "Nombre Fruta: $nombre | Precio: $$precioPorKilo CLP | Disponibles: $stockKilos}"
    }
}

val frutas = mutableListOf<Fruta>()

fun main() {
    println("=== SISTEMA DE VENTAS DE FRUTAS ===")
    println("Puerto Frutal SPA\n")

    var continuar = true
    while (continuar) {
        mostrarMenu()
        val opcion = leerOpcion()
        continuar = procesarOpcion(opcion)
    }

    println("\nGracias por usar el sistema...")
}

fun mostrarMenu() {
    println("\n=== MENÚ PRINCIPAL ===")
    println("1. Registrar nueva fruta")
    println("2. Mostrar todas las frutas")
    println("3. Mostrar frutas caras")
    println("4. Calcular métricas del inventario")
    println("5. Salir")
    print("Seleccione una opción (1-5): ")
}

fun leerOpcion(): Int {
    return readLine()?.toIntOrNull() ?: 0
}

fun procesarOpcion(opcion: Int): Boolean {
    return when (opcion) {
        1 -> { registrarFruta(); true }
        2 -> { verFrutas(); true }
        3 -> { filtrarFrutasCaras(); true }
        4 -> { verStock(); true }
        5 -> false
        else -> { println("Opción inválida selecciona una opción del 1 al 5."); true }
    }
}

fun registrarFruta() {
    println("\n=== REGISTRAR NUEVA FRUTA ===")
    val nombre = leerCadena("Ingrese la Fruta: ")
    val precioPorKilo = leerEntero("Ingrese el precio de la Fruta: ")
    val stockKilos = leerEntero("Ingrese cantidad de kilos disponibles: ")
    val fruta = Fruta(nombre, precioPorKilo, stockKilos)
    frutas.add(fruta)
    println("Fruta registrada exitosamente: $fruta")
}

fun verFrutas() {
    println("\n=== FRUTAS DISPONIBLES ===")
    if (frutas.isEmpty()) {
        println("No hay frutas registrados en el sistema.")
    } else {
        for (i in frutas.indices) {
            println("${i + 1}. ${frutas[i]}")
        }
    }
}

fun filtrarFrutasCaras() {
    println("\n--- Frutas PREMIUM mayores a $1000 ---")

    val frutaCara = frutas.filter { fruta ->
        fruta.precioPorKilo > 1000.0
    }

    if (frutaCara.isEmpty()) {
        println("No hay frutas premium registradas.")
        return
    }

    println("Se encontraron ${frutaCara.size} frutas premium: $frutaCara")
    println("-".repeat(50))



}

fun verStock() {
    println("\n=== STOCK DE FRUTAS ===")
    if (frutas.isEmpty()) {
        println("No hay frutas registradas para mostrar métricas.")
        return
    }


    val precioPromedio = frutas.map { it.precioPorKilo }.average()

    println("Precio promedio de frutas: $${String.format("%.0f", precioPromedio)} CLP")
}



fun leerEntero(mensaje: String): Int {
    while (true) {
        print(mensaje)
        val entrada = readLine()?.toIntOrNull()
        if (entrada != null && entrada > 0) {
            return entrada
        } else {
            println("Por favor ingrese un número válido mayor que 0.")
        }
    }
}

fun leerCadena(mensaje: String): String {
    while (true) {
        print(mensaje)
        val entrada = readLine()?.trim()
        if (!entrada.isNullOrEmpty()) {
            return entrada
        } else {
            println("Por favor ingrese un texto válido (no vacío).")
        }
    }
}
