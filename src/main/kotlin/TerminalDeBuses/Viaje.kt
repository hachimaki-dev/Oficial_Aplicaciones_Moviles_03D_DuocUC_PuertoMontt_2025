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

    fun compraViaje(cantidad: Int){
        if (cantidad < pasajesDisponibles){
            pasajesDisponibles -= cantidad
            pasajesIniciales += cantidad
        }else{
            println("No hay suficientes pasajes disponibles")
            return
        }
        println("Pasajes comprado")
    }
}