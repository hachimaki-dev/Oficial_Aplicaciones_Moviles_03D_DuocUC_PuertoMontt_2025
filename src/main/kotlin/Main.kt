package org.example

val frutas = mutableListOf<Fruta>()

abstract class Fruta(val nombre: String, val precioPorKilo: Double, val stockKilos: Int){
    open fun validacion() : Boolean {
        return precioPorKilo > 0 && stockKilos >= 0 && nombre.isNotBlank()
    }
    abstract fun infoFruta(): String
}

class FrutaLocal(nombre: String, precio: Double, cantidad: Int)
    : Fruta(nombre, precio, cantidad) {
    override fun infoFruta() = "Fruta local: ${nombre} Vale ${precioPorKilo} y hay ${stockKilos}"
    fun zonaProduccion() = "Región de Los Lagos"
}

class FrutaTropical(nombre: String, precio: Double, cantidad: Int)
    : Fruta(nombre, precio, cantidad) {
    override fun infoFruta() = "Fruta tropical: ${nombre} Vale ${precioPorKilo}, hay ${stockKilos}"
    fun paisOrigen() = "tropical"
}





fun registrarFruta() {
    println("Registro de frutas")

    try {
        print("Nombre de la fruta: ")
        val nombre = readLine() ?: ""

        if (nombre.isBlank()) {
            println("ERROR El nombre de la fruta es obligatorio")
            return
        }


        print("Precio")
        val precioTexto = readLine() ?: "0"
        val precio = precioTexto.toDoubleOrNull()

        if (precio == null || precio <= 0) {
            println("error: El precio debe no debe ser menor a 0")
            return
        }


        print("Cantidad en stock: ")
        val stockTexto = readLine() ?: "0"
        val stock = stockTexto.toIntOrNull()

        if (stock == null || stock < 0) {
            println("error El stock no debe se menor a 0")
            return
        }

        val frutaNueva = FrutaLocal(nombre, precio, stock)

        frutas.add(FrutaLocal("Manzana", 1200.0, 50))
        frutas.add(FrutaTropical("Mango", 2500.0, 20))

        if (frutaNueva.validacion()) {
            frutas.add(frutaNueva)
            println("La fruta ha sido registrada")
            println("${frutaNueva.infoFruta()}")
        } else {
            println("[ERROR] Los datos de la prenda no son válidos")
        }

    } catch (excepcion: Exception) {
        println("ups ha habido un error inesperado ${excepcion.message}")
        println("intentelo de nuevo")
    }
}


fun mostrarFrutas() {
    println("Almacen de frutas")

    if (frutas.isEmpty()) {
        println("No hay frutas registradas en el sistem de almacenamiento.")
        return
    }

    println("Total de frutas: ${frutas.size}")

    frutas.forEachIndexed { i, fruta ->
        val numero = i + 1
        println("$numero. ${fruta.infoFruta()}")
    }

}



fun mostrarMenu(){
    println("Bienvenido al sistema de gestión de inventario Puerto Frutal SPA")
    println("1. Registrar nueva fruta (con validaciones completas)")
    println("2. Mostrar todas las frutas")
    println("3. Mostrar frutas caras > $1000")
    println("4. Calcular métricas del inventario (valor total, promedios, extremos)")
    println("5. Salir")
}

fun frutasCaras() {
    println("Las frutas más caras del almacen")

    val frutasCaras = frutas.filter { fruta ->
        fruta.precioPorKilo > 1000.0
    }

    if (frutasCaras.isEmpty()) {
        println("No hay frutas caras en el almacen.")
        return
    }

    println("En el almacen hay ${frutasCaras.size} frutas caras:")

    frutasCaras
        .sortedByDescending { it.precioPorKilo }
        .forEachIndexed { i, fruta ->
            println("${i + 1}. ${fruta.infoFruta()}")
        }
}

fun totalFrutas() {
    println("Total almacen frutas")

    if (frutas.isEmpty()) {
        println("No hay datos para calcular el total del almacen.")
        return
    }


    val valorTotal = frutas.sumOf { fruta ->
        fruta.precioPorKilo * fruta.stockKilos
    }


    val frutaCara = frutas.maxByOrNull { it.precioPorKilo }


    val frutaBarata = frutas.minByOrNull { it.precioPorKilo }


    val precioPromedio = frutas.map { it.precioPorKilo }.average()


    val stockTotal = frutas.sumOf { it.stockKilos }


    println("Valor total del inventario: $${String.format("%.2f", valorTotal)}")
    println("Precio promedio: $${String.format("%.2f", precioPromedio)}")
    println("Stock total: $stockTotal unidades")
    println()
    println("La fruta más cara: ${frutaCara?.infoFruta() ?: "ninguna"}")
    println("La fruta más barata: ${frutaBarata?.infoFruta() ?: "ninguna"}")
}

fun main() {
    var sistema: Boolean = true
    while(sistema){
        mostrarMenu()
        val opcion = readLine() ?: ""
        when (opcion) {
            "1", "registro" ->
                registrarFruta()


            "2", "ver" -> {
                mostrarFrutas()
            }

            "3", "filtrar" -> {
                frutasCaras()
            }

            "4", "calculoTotal" -> {
                totalFrutas()
            }

            "5", "salir" -> {
                println("\nChao ")
                sistema = false
            }

            else -> {
                println("Opción inválida: '${opcion}'")
                println("Selecciona un número valido entre el 1 y el 5")
            }
        }
        if (sistema) {
            println("resiona un boton para continuar")
            readLine()
        }
    }
}