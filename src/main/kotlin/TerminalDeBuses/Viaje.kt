package org.example.TerminalDeBuses

data class Viaje(
    var destino: String?,
    var precio: Int,
    var pasajesDisponibles: Int,
    var pasajesIniciales: Int
) {
    fun mostrarInfo(): String {
        return "$destino - Precio: $${precio} - Pasajes Disponibles: ${pasajesDisponibles} - Pasajes vendidos: ${pasajesIniciales}"
    }
}