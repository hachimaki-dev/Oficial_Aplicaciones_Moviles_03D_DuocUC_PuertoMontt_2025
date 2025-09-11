package com.puertoFrutal.model


class FrutaTropical(nombre: String, precioPorKilo: Double, stockKilos: Double) 
    : Fruta(nombre, precioPorKilo, stockKilos) {

    override fun descripcion() = "Fruta tropical: $nombre - Importada"

    fun paisOrigen() = "Países tropicales"
}