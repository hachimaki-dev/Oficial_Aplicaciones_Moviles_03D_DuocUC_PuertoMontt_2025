// Clase principal para representar un Viaje
class Viaje(
    val destino: String,
    val precio: Int,
    var pasajesDisponibles: Int
) {
    private val pasajesIniciales: Int = pasajesDisponibles

    fun comprarPasaje(): Boolean {
        return if (pasajesDisponibles > 0) {
            pasajesDisponibles--
            true
        } else {
            false
        }
    }

    fun pasajesVendidos(): Int = pasajesIniciales - pasajesDisponibles

    override fun toString(): String {
        return "Destino: $destino | Precio: $$precio | Disponibles: $pasajesDisponibles"
    }
}

// ------------------- Funcionalidades -------------------

val viajes = mutableListOf<Viaje>()

fun registrarViaje() {
    println("Ingrese destino:")
    val destino = readLine()?.trim().orEmpty()
    if (destino.isEmpty()) {
        println("  El destino no puede estar vacío.")
        return
    }

    println("Ingrese precio (CLP):")
    val precio = readLine()?.toIntOrNull()
    if (precio == null || precio <= 0) {
        println("  Precio inválido.")
        return
    }

    println("Ingrese cantidad de pasajes disponibles:")
    val pasajes = readLine()?.toIntOrNull()
    if (pasajes == null || pasajes <= 0) {
        println("  Cantidad de pasajes inválida.")
        return
    }

    viajes.add(Viaje(destino, precio, pasajes))
    println("  Viaje registrado con éxito.")
}

fun verViajes() {
    if (viajes.isEmpty()) {
        println("  No hay viajes registrados.")
        return
    }
    println("  Lista de viajes:")
    viajes.forEachIndexed { index, viaje ->
        println("${index + 1}. $viaje")
    }
}

fun comprarPasaje() {
    if (viajes.isEmpty()) {
        println("⚠️ No hay viajes disponibles.")
        return
    }
    verViajes()
    println("Ingrese el número del viaje que desea comprar:")
    val opcion = readLine()?.toIntOrNull()
    if (opcion == null || opcion !in 1..viajes.size) {
        println("⚠️ Opción inválida.")
        return
    }
    val viajeSeleccionado = viajes[opcion - 1]
    if (viajeSeleccionado.comprarPasaje()) {
        println("Pasaje comprado para ${viajeSeleccionado.destino}.")
    } else {
        println("No quedan pasajes disponibles en este viaje.")
    }
}

fun verMetricas() {
    if (viajes.isEmpty()) {
        println("⚠️ No hay viajes registrados.")
        return
    }
    val vendidosTotales = viajes.sumOf { it.pasajesVendidos() }
    val recaudacion = viajes.sumOf { it.pasajesVendidos() * it.precio }
    val promedioPrecio = if (viajes.isNotEmpty()) viajes.map { it.precio }.average() else 0.0

    println("  Métricas del sistema:")
    println("Pasajes vendidos totales: $vendidosTotales")
    println("Recaudación total: $$recaudacion")
    println("Precio promedio de viajes: $${"%.2f".format(promedioPrecio)}")
}

// ------------------- Menú Principal -------------------

fun main() {
    while (true) {
        println(
            """
            |==============================
            |   Terminal de Buses Puerto Montt
            | 1. Registrar Viaje
            | 2. Ver Viajes
            | 3. Comprar Pasaje
            | 4. Ver Métricas
            | 5. Salir
            |==============================
            |Seleccione una opción:
            """.trimMargin()
        )
        when (readLine()) {
            "1" -> registrarViaje()
            "2" -> verViajes()
            "3" -> comprarPasaje()
            "4" -> verMetricas()
            "5" -> {
                println("👋 Saliendo del sistema...")
                return
            }
            else -> println("⚠️ Opción inválida, intente de nuevo.")
        }
        println()
    }
}
