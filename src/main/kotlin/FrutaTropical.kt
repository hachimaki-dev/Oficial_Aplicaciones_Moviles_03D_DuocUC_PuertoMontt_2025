package org.example

open class FrutaTropical(
    nombre: String,
    precioPorKilo: Double,
    stockKilos: Double,): Fruta(nombre,precioPorKilo,stockKilos) {

    override fun descripcion(): String {
       return "Es Tropical"

    }
}