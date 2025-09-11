package org.example

open class FrutaLocal(
    nombre: String,
    precioPorKilo: Double,
    stockKilos: Double): Fruta(nombre,precioPorKilo,stockKilos) {

    override fun descripcion(): String {
        return "Es local"
    }
}