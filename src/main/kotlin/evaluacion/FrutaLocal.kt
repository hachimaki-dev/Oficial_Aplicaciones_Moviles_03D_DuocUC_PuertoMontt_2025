package org.example.evaluacion

class FrutaLocal(nombre: String, precioPorKilo: Double, stockKilos: Double) : Fruta(nombre, precioPorKilo, stockKilos) {
    override fun descripcion(): String {
        return "Fruta local: $nombre - Agregada."
    }
}