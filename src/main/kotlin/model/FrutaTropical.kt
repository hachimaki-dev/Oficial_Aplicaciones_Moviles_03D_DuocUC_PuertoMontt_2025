package model

class FrutaTropical(nombre: String, precioPorKilo: Double,stockKilos: Int):
    Fruta(nombre, precioPorKilo, stockKilos) {
    override fun descripcion(): String {
        return "FRUTA IMPORTADA"
    }
}