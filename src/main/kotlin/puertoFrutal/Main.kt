abstract class Fruta(
    val nombre: String,
    val precioPorKilo: Double,
    val stockKilos: Double

) { abstract fun descripcion(): String
    fun valorTotal(): Double = precioPorKilo * stockKilos

}



class FrutaLocal(nombre: String, precioPorKilo: Double, stockKilos: Double)
    : Fruta(nombre, precioPorKilo, stockKilos) {
    override fun descripcion(): String = "fruta local: $nombre - producida en la region"

}



class FrutaTropical(nombre: String, precioPorKilo: Double, stockKilos: Double)
    : Fruta(nombre, precioPorKilo, stockKilos) {
    override fun descripcion(): String = "fruta tropical: $nombre - importada"

}



fun main() {
    val inventario = mutableListOf<Fruta>()
    val input = System.`in`.bufferedReader()
    fun leerTexto(p: String): String {
        print(p)
        return input.readLine()?.trim() ?: ""
    }
    fun leerDoubleSeguro(p: String): Double? {
        print(p)
        val crudo = input.readLine()?.trim().orEmpty()
        return crudo.replace(" ", "").replace(",", ".").toDoubleOrNull()
    }
    fun pausar() {
        println()
        print("presiona enter para continuar ")
        input.readLine()
        println()
    }



    fun menu() {
        println("====================================")
        println(" Puerto Frutal SPA ")
        println("====================================")
        println("1) Registrar nueva fruta")
        println("2) Mostrar todas las frutas")
        println("3) Mostrar frutas caras")
        println("4) Calcular metricas del inventario")
        println("5) Salir")
    }
    fun registrar() {
        println("\nregistro de fruta")
        val tipo = leerTexto("tipo (local/tropical): ").lowercase()
            try {
                val nombre = leerTexto("nombre: ")
                    if (nombre.isBlank()) throw IllegalArgumentException("el nombre no puede estar vacio")
                val precio = leerDoubleSeguro("precio por kilo: ")
                ?: throw NumberFormatException("precio invalido")
                    if (precio <= 0.0) throw IllegalArgumentException("el precio debe ser mayor que 0")
                val stock = leerDoubleSeguro("stock en kilos: ")
                ?: throw NumberFormatException("stock invalido")
                    if (stock < 0.0) throw IllegalArgumentException("el stock no puede ser negativo")
                val fruta: Fruta = when (tipo) {
                "local", "l" -> FrutaLocal(nombre, precio, stock)
                "tropical", "t" -> FrutaTropical(nombre, precio, stock)
                    else -> FrutaLocal(nombre, precio, stock)
            }
            inventario.add(fruta)
            println("ok: ${fruta.descripcion()}")
        } catch (e: NumberFormatException) {
            println("error: numero no valido")
        } catch (e: IllegalArgumentException) {
            println("error: ${e.message}")
        } catch (e: Exception) {
            println("error: ${e.message}")
        }
    }
    fun mostrarTodas() {
        println("\nlistado de frutas")
            if (inventario.isEmpty()) {
            println("no hay frutas registradas")
                return

        }
        inventario.forEachIndexed { i, f ->
            val precioFmt = "%.2f".format(f.precioPorKilo)
            val stockFmt = "%.2f".format(f.stockKilos)
            val valorFmt = "%.2f".format(f.valorTotal())
            val tipo = if (f is FrutaLocal) "local" else "tropical"
            println("${i + 1}. ${f.nombre} , tipo: $tipo , precio/kg: $precioFmt , stock kg: $stockFmt , valor: $valorFmt")
        }
    }
    fun mostrarCaras() {
        println("\nfrutas con precio > 1000")
            if (inventario.isEmpty()) {
            println("no hay frutas registradas")
            return

        }
        val lista = inventario
            .filter { it.precioPorKilo > 1000.0 }
            .sortedByDescending { it.precioPorKilo }
             if (lista.isEmpty()) {
            println("no hay frutas que superen 1000 por kilo")
            return

        }
        lista.forEachIndexed { i, f ->
            val precioFmt = "%.2f".format(f.precioPorKilo)
            println("${i + 1}. ${f.nombre} | $precioFmt por kilo")

        }
    }
    fun metricas() {
        println("\nmetricas del inventario")
            if (inventario.isEmpty()) {
            println("no hay datos aun")
            return

        }
        val valorTotal = inventario.sumOf { it.valorTotal() }
        val precioProm = inventario.map { it.precioPorKilo }.average()
        val stockTotal = inventario.sumOf { it.stockKilos }
        val masCara = inventario.maxByOrNull { it.precioPorKilo }
        val masBarata = inventario.minByOrNull { it.precioPorKilo }
        println("valor total del stock: ${"%.2f".format(valorTotal)}")
        println("precio promedio por kilo: ${"%.2f".format(precioProm)}")
        println("stock total en kilos: ${"%.2f".format(stockTotal)}")
            if (masCara != null) println("fruta mas cara: ${masCara.nombre} (${ "%.2f".format(masCara.precioPorKilo) })")
            if (masBarata != null) println("fruta mas barata: ${masBarata.nombre} (${ "%.2f".format(masBarata.precioPorKilo) })")
    }

    while (true) {
        menu()
        print("elige una opcion: ")
        when (input.readLine()?.trim().orEmpty()) {
            "1" -> { registrar(); pausar() }
            "2" -> { mostrarTodas(); pausar() }
            "3" -> { mostrarCaras(); pausar() }
            "4" -> { metricas(); pausar() }
            "5" -> { println("fin del programa"); return }
            else -> { println("opcion invalida"); pausar() }
        }
    }
}






