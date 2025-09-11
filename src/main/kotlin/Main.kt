import java.text.NumberFormat
import java.util.*

abstract class Fruta(
    val nombre: String,
    val precioPorKilo: Double,
    val stockKilos: Double
) {
    init {
        require(nombre.isNotBlank() && precioPorKilo > 0 && stockKilos >= 0) { "Validación fallida." }
    }
    abstract fun descripcion(): String
    override fun toString(): String =
        "Nombre: $nombre, Precio/Kg: ${NumberFormat.getCurrencyInstance(Locale("es", "CL")).format(precioPorKilo)}, Stock: ${String.format("%.2f", stockKilos)} Kg"
}

class FrutaLocal(nombre: String, precioPorKilo: Double, stockKilos: Double, val zonaProduccion: String) : Fruta(nombre, precioPorKilo, stockKilos) {
    override fun descripcion(): String = "Producida localmente en $zonaProduccion."
}

class FrutaTropical(nombre: String, precioPorKilo: Double, stockKilos: Double, val paisOrigen: String) : Fruta(nombre, precioPorKilo, stockKilos) {
    override fun descripcion(): String = "Importada de $paisOrigen."
}

val inventario = mutableListOf<Fruta>()

fun main() {
    inventario.addAll(listOf(
        FrutaLocal("Frutilla ", 1250.0, 500.0, "chile "),
        FrutaTropical("Melon", 800.0, 850.0, "ecuador"),
        FrutaTropical("Sandia", 2500.0, 150.0, "argentina")
    ))

    var opcion: String?
    do {
        println("=== Puerto Frutal SPA ===")
        println("1. Registrar, 2. Mostrar, 3. Filtrar, 4. Métricas, 5. Salir")
        print("Opción: ")
        opcion = readLine()
        when (opcion?.trim()) {
            "1" -> registrarFruta()
            "2" -> mostrarFrutas()
            "3" -> mostrarFrutasCaras()
            "4" -> calcularMetricas()
            "5" -> println("Adiós.")
            else -> println("Opción inválida.")
        }
    } while (opcion != "5")
}

fun registrarFruta() {
    print("Tipo (local/tropical): ")
    val tipo = readLine()?.trim()?.lowercase() ?: ""
    if (tipo !in listOf("local", "tropical")) { println("Tipo inválido."); return }

    try {
        print("Nombre: "); val nombre = readLine()?.trim () ?: ""
        print("Precio/Kg: "); val precio = readLine()?.trim()?.toDoubleOrNull() ?: throw Exception()
        print("Stock (Kg): "); val stock = readLine()?.trim()?.toDoubleOrNull() ?: throw Exception()

        val nuevaFruta = if (tipo == "local") {
            print("Zona de producción: "); val zona = readLine()?.trim() ?: ""
            FrutaLocal(nombre, precio, stock, zona)
        } else {
            print("País de origen: "); val pais = readLine()?.trim() ?: ""
            FrutaTropical(nombre, precio, stock, pais)
        }
        inventario.add(nuevaFruta)
        println("Registrado: ${nuevaFruta.nombre}")
    } catch (e: Exception) {
        println("Error en la entrada de datos.")
    }
}

fun mostrarFrutas() {
    if (inventario.isEmpty()) { println("Inventario vacío."); return }
    inventario.forEachIndexed { i, f -> println("${i + 1}. $f\n   Descripción: ${f.descripcion()}") }
}

fun mostrarFrutasCaras() {
    if (inventario.isEmpty()) { println("Inventario vacío."); return }
    val caras = inventario.filter { it.precioPorKilo > 1000 }.sortedByDescending { it.precioPorKilo }
    if (caras.isEmpty()) { println("No hay frutas caras.") } else { caras.forEach { println(it) } }
}

fun calcularMetricas() {
    if (inventario.isEmpty()) { println("Inventario vacío."); return }
    val formato = NumberFormat.getCurrencyInstance(Locale("es", "CL"))
    val valorTotal = inventario.sumOf { it.precioPorKilo * it.stockKilos }
    val precioPromedio = inventario.map { it.precioPorKilo }.average()
    val masCara = inventario.maxByOrNull { it.precioPorKilo }
    val masBarata = inventario.minByOrNull { it.precioPorKilo }

    println("Valor Total: ${formato.format(valorTotal)}")
    println("Precio Promedio: ${formato.format(precioPromedio)}")
    println("Más cara: ${masCara?.nombre} (${formato.format(masCara?.precioPorKilo)})")
    println("Más barata: ${masBarata?.nombre} (${formato.format(masBarata?.precioPorKilo)})")
}