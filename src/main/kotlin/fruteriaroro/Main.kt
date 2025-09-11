package org.example.fruteriaroro

fun main() {
    println("*".repeat(50))
    println("    BIENVENIDO A FRUTERIA RORO")
    println("    Sistema Fruteria")
    println("*".repeat(50))

    var sistemaActivo = true

    while (sistemaActivo) {
        mostrarMenu()

        val opcionTexto = readLine()?.trim() ?: ""
        when (opcionTexto) {
            "1" -> {
                registrarFruta()
            }
            "2" -> {
                mostrarFruta()
            }
            "3" -> {
                filtrarFrutaCara()
            }
            "4" -> {
                calcularMetricas()
            }
            "5" -> {
                println("\nGracias por usar el sistema de fruteria")
                sistemaActivo = false
            }
            else -> {
                println("\n[ERROR] Opción inválida: '$opcionTexto'")
                println("Por favor, selecciona un número entre 1 y 5")
            }
        }

        if (sistemaActivo) {
            println("\nPresiona ENTER para continuar...")
            readLine()
        }
    }
}

data class Fruta(
    val nombreFruta : String,
    val precio : Double,
    val stock : Int,

    ){
    fun validar(): Boolean {
        return precio > 0 && stock >= 0 && nombreFruta.isNotBlank()
    }

    fun mostrarInfo(): String {
        return "$nombreFruta - Precio: $${precio} - Stock: ${stock} unidades"
    }
}

val frutas = mutableListOf<Fruta>()

fun mostrarMenu() {
    println("\n" + "=".repeat(40))
    println("Fruteria RORO")
    println("=".repeat(40))
    println("1. Registrar una nueva fruta")
    println("2. Ver Frutas")
    println("3. Mostrar Frutas Caras")
    println("4. Calcular Stock")
    println("5. Salir del sistema")
    println("-".repeat(40))
    print("Selecciona una opción [1-5]: ")
}

fun registrarFruta() {
    println("Registrar una nueva fruta")
    try {
        print("Nombre de la fruta: ")
        val nombreFruta = readLine().toString()

        if (nombreFruta.isBlank()) {
            println("Debe ingresar el nombre de la fruta, intentelo otra vez: ")
            return
        }

        print("Precio por kilo: ")
        val precioTexto = readLine()?.trim() ?: "0"
        val precio = precioTexto.toDoubleOrNull()

        if (precio == null || precio <= 0) {
            println("El precio debe ser un número positivo, intentelo otra vez: ")
            return
        }

        print("Stock por kilo: ")
        val stockTexto = readLine()?.trim() ?: "0"
        val stock = stockTexto.toIntOrNull()

        if (stock == null || stock < 0) {
            println("El stock debe ser un número no negativo, intentelo otra vez: ")
            return
        }

        val nuevaFruta = Fruta(nombreFruta, precio, stock)

        if (nuevaFruta.validar()) {
            frutas.add(nuevaFruta)
            println("\n Fruta registrada exitosamente: ")
            println("-> ${nuevaFruta.mostrarInfo()}")
            println("-".repeat(50))
        } else {
            println("[ERROR] Los datos de la fruta no son válidos, intentelo otra vez: ")
        }
    } catch (excepcion: Exception) {
        println("[ERROR] ${excepcion.message}")
        println("Por favor, intenta nuevamente: ")
    }
}

fun mostrarFruta() {
    println("*".repeat(50))
    println("\n--- Frutas ---")
    println("*".repeat(50))

    if (frutas.isEmpty()) {
        println("No hay frutas registradas en el sistema.")
        println("Usa la opción 1 para agregar una nueva fruta: ")
        return
    }

    println("Total de frutas: ${frutas.size}")
    println("-".repeat(50))

    frutas.forEachIndexed { indice, frutas ->
        val numero = indice + 1
        println("$numero. ${frutas.mostrarInfo()}")
    }

    println("-".repeat(50))
}


fun filtrarFrutaCara() {
    println("*".repeat(50))
    println("\n--- Frutas mas cara (Mayor a 1.000) ---")
    println("*".repeat(50))

    val frutaCara = frutas.filter { fruta ->
        fruta.precio > 1000.0
    }

    if (frutaCara.isEmpty()) {
        println("No hay una fruta cara registrada: ")
        println("Usa la opción 1 para agregar una nueva fruta: ")
        return
    }

    println("Se encontraron ${frutaCara.size} frutas caras: ")
    println("-".repeat(50))

    frutaCara
        .sortedByDescending { it.precio }
        .forEachIndexed { indice, fruta ->
            println("${indice + 1}. ${fruta.mostrarInfo()}")
        }
}


fun calcularMetricas(){
    println("*".repeat(50))
    println("\n--- Metricas del Sistema ---")
    println("*".repeat(50))

    val valorTotal = frutas.sumOf { fruta ->
        fruta.precio * fruta.stock
    }
    val masCara = frutas.maxByOrNull { it.precio }
    val masBarata = frutas.minByOrNull { it.precio }
    val precioPromedio = frutas.map { it.precio }.average()

    if (frutas.isEmpty()) {
        println("No hay una fruta cara registrada: ")
        println("Usa la opción 1 para agregar una nueva fruta: ")
        return
    }else {

    println("Valor Frutas Total: $%.2f".format(valorTotal))
    println("Precio promedio por fruta: $%.2f".format(precioPromedio))
    println("La fruta mas cara es: $${masCara?.precio}")
    println("La fruta mas barata es: $${masBarata?.precio}")
    println("-".repeat(50))
    }
}


