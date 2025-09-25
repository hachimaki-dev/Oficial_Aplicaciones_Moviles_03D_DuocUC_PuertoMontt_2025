package org.example.com.otakuwear.models

class FrutaTropical (nombre: String, precioPorKilo: Double, stockKilos: Double) : Fruta(nombre, precioPorKilo, stockKilos) {
    override fun descripcion(): String {
        return "Fruta tropical: $nombre - Importada."
    }
}