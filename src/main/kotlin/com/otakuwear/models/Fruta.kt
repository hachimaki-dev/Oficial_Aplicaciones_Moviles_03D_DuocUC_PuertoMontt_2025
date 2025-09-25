package org.example.com.otakuwear.models

abstract class Fruta(val nombre: String, var precioPorKilo: Double, var stockKilos: Double) {

    init {
        if (nombre.isBlank()) {
            throw IllegalArgumentException("El nombre no puede estar vacío.")
        }
        if (precioPorKilo <= 0.0) {
            throw IllegalArgumentException("El precio debe ser un número positivo.")
        }
        if (stockKilos < 0.0) {
            throw IllegalArgumentException("El stock no puede ser negativo.")
        }
    }

    abstract fun descripcion(): String

}