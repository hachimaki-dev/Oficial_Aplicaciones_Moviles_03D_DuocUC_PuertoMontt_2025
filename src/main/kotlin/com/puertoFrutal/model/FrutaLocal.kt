package com.puertoFrutal.model


class FrutaLocal(nombre: String, precioPorKilo: Double, stockKilos: Double) 
    : Fruta(nombre, precioPorKilo, stockKilos) {
    

    override fun descripcion() = "Fruta local: $nombre - Producida en la región"
    

    fun zonaProduccion() = "Región de Los Lagos"
}