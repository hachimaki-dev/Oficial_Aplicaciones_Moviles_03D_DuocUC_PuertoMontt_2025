// CLASE FRUTAS

open class Fruta(
    val nombre: String,
    val precioPorKilo: Double,
    val stockKilos: Int,
) {
    fun validar(): Boolean {
        return precioPorKilo > 0 && stockKilos >= 0 && nombre.isNotBlank()
    }

    open fun mostrarInfo(): String {
        return "$nombre - Precio: $${precioPorKilo} - Stock: ${stockKilos} unidades"
    }
}

// SUBCLASE FRUTA TROPICAL
data class FrutaTropical(val extraDulce : Boolean) : Fruta("Mango", 2000.0, 30){
    override fun mostrarInfo(): String {
        println("Es tropical")
        return super.mostrarInfo()
    }
}

// SUBCLASE FRUTA LOCAL
data class FrutaLocal(val extraAcida : Boolean) : Fruta("Ciruela", 2000.0, 30){
    override fun mostrarInfo(): String {
        println("Es local")
        return super.mostrarInfo()
    }
}

// MENU
fun mostrarMenu() {
    println("\n" + "=".repeat(40))
    println("    FRUTERIA - GESTIÓN DE INVENTARIO")
    println("=".repeat(40))
    println("1. Registrar nueva fruta")
    println("2. Ver inventario completo")
    println("3. Ver frutas premium(> $1000)")
    println("4. Calcular metricas del invenvario") //(valor total, promedios, extremos)
    println("5. Salir del sistema")
    println("-".repeat(40))
    print("Selecciona una opción [1-5]: ")
}
// LISTA DE FRUTAS
val almacenFrutas= mutableListOf<Fruta>()

// OP1 REGISTRAR NUEVA FRUTA
fun registrarFruta() {
    println("\n--- REGISTRO DE NUEVA FRUTA ---")

    try {
        // Captura del nombre con validación
        print("Nombre de la fruta: ")
        val nombre = readLine()?.trim() ?: ""

        if (nombre.isBlank()) {
            println("[ERROR] El nombre es obligatorio")
            return
        }

        // Captura del precio con validación numérica
        print("Precio: $")
        val precioTexto = readLine()?.trim() ?: "0"
        val precio = precioTexto.toDoubleOrNull()

        if (precio == null || precio <= 0) {
            println("[ERROR] El precio debe ser un número positivo")
            return
        }

        // Captura del stock con validación numérica
        print("Cantidad en stock: ")
        val stockTexto = readLine()?.trim() ?: "0"
        val stock = stockTexto.toIntOrNull()

        if (stock == null || stock < 0) {
            println("[ERROR] El stock debe ser un número no negativo")
            return
        }

        // Crear y validar la prenda
        val nuevaFruta = Fruta(nombre, precio, stock)

        if (nuevaFruta.validar()) {
            almacenFrutas.add(nuevaFruta)
            println("\n[ÉXITO] Prenda registrada:")
            println("-> ${nuevaFruta.mostrarInfo()}")
        } else {
            println("[ERROR] Los datos de la fruta no son válidos")
        }

    } catch (excepcion: Exception) {
        println("[ERROR INESPERADO] ${excepcion.message}")
        println("Por favor, intenta nuevamente")
    }
}

// OP2 VER INVENTARIO
fun mostrarInventarioCompleto() {
    println("\n--- INVENTARIO COMPLETO ---")

    if (almacenFrutas.isEmpty()) {
        println("No hay frutas registradas en el sistema.")
        println("Usa la opción 1 para agregar la primera fruta.")
        return
    }

    println("Total de frutas: ${almacenFrutas.size}")
    println("-".repeat(50))

    almacenFrutas.forEachIndexed { indice, fruta ->
        val numero = indice + 1
        println("$numero. ${fruta.mostrarInfo()}")
    }

    println("-".repeat(50))
}

// OP3 VER FRUTAS PREMIUM
fun mostrarFrutasPremium() {
    println("\n--- FRUTAS PREMIUM (> $1000) ---")

    val frutasPremium = almacenFrutas.filter { fruta ->
        fruta.precioPorKilo > 1000.0
    }

    if (frutasPremium.isEmpty()) {
        println("No hay frutas premium registradas.")
        return
    }

    println("Se encontraron ${frutasPremium.size} frutas premium:")
    println("-".repeat(50))

    frutasPremium
        .sortedByDescending { it.precioPorKilo }  // Ordenar por precio descendente
        .forEachIndexed { indice, prenda ->
            println("${indice + 1}. ${prenda.mostrarInfo()}")
        }
}

// OP4 CALCULAR METRICAS DEL INVENTARIO
fun calcularMetricasInventario() {
    println("\n--- MÉTRICAS DEL INVENTARIO ---")

    if (almacenFrutas.isEmpty()) {
        println("No hay datos para calcular métricas.")
        return
    }

    // Valor total del inventario (precio × stock por cada prenda)
    val valorTotal = almacenFrutas.sumOf { fruta ->
        fruta.precioPorKilo * fruta.stockKilos
    }

    // fruta más cara
    val frutaMasCara = almacenFrutas.maxByOrNull { it.precioPorKilo}

    // fruta más barata
    val frutaMasBarata = almacenFrutas.minByOrNull { it.precioPorKilo }

    // fruta promedio
    val precioPromedio = almacenFrutas.map { it.precioPorKilo }.average()

    // Stock total
    val stockTotal = almacenFrutas.sumOf { it.stockKilos }

    // Mostrar resultados
    println("Valor total del inventario: $${String.format("%.2f", valorTotal)}")
    println("Precio promedio: $${String.format("%.2f1", precioPromedio)}")
    println("Stock total: $stockTotal unidades")
    println()
    println("fruta más cara: ${frutaMasCara?.mostrarInfo() ?: "N/A"}")
    println("fruta más barata: ${frutaMasBarata?.mostrarInfo() ?: "N/A"}")
}

// CODIGO MAIN DONDE SE LLAMA TODO

fun main() {
    println("*".repeat(50))
    println("       BIENVENIDO A LA FRUTERIA")
    println("    Sistema de Gestión de Inventario")
    println("*".repeat(50))

    // BUCLE
    var sistemaActivo = true

    while (sistemaActivo) {
        mostrarMenu()

        // INPUT DEL CLIENTE
        val opcionTexto = readLine()?.trim() ?: ""
        // OPCIONES
        // Procesamiento de la opción seleccionada
        when (opcionTexto) {
            "1" -> {
                registrarFruta()
            }
            "2" -> {
                mostrarInventarioCompleto()
            }
            "3" -> {
                mostrarFrutasPremium()
            }
            "4" -> {
                calcularMetricasInventario()
            }
            "5" -> {
                println("\nGracias por usar la fruteria")
                println("¡Hasta la próxima!")
                sistemaActivo = false
            }
            else -> {
                println("\n[ERROR] Opción inválida: '$opcionTexto'")
                println("Por favor, selecciona un número entre 1 y 5")
            }
        }
    }
}
