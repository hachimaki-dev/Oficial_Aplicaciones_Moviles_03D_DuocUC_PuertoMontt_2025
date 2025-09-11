package org.example

open  class Fruta(
    val nombre: String,
    val precioPorKilo: Double,
    val stockKilos: Double
) {

    open fun descripcion(): String{
        return "Es fruta"
    }

    fun validar(): Boolean{
        return true
    }

    fun mostrarInfo(): String{
        return "Nombre: $nombre | Precio: $precioPorKilo | Stock: $stockKilos"
    }
}