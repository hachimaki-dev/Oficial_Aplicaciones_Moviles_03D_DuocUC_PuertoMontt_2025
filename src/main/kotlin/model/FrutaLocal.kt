package model

class FrutaLocal(nombre: String, precioPorKilo: Double,stockKilos: Int):
    Fruta(nombre, precioPorKilo, stockKilos) {
    override fun descripcion(): String {
        return "FRUTA LOCAL"
    }
}