package com.puertoFrutal.model

abstract class Fruta(val nombre: String, val precioPorKilo: Double, val stockKilos: Double) {

    abstract fun descripcion(): String

    fun validar(): Boolean {
        return precioPorKilo > 0 && stockKilos >= 0 && nombre.isNotBlank()
    }

    fun valorTotal(): Double = precioPorKilo * stockKilos
}