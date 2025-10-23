package org.example.models

abstract class Fruta (
    val nombre: String,
    val precioPorKilo: Double,
    val stockKilos: Int
){
    init{
        require(precioPorKilo > 0 ) { "El precio debe ser mayor que 0" }
        require( stockKilos >= 0 ) { "El stock no puede ser negativo" }
        require( nombre.isNotBlank()) { "El nombre no puede estar vacío" }
    }
    // Es open ya que esta funcion sera sobreescrita a causa del polimorfismo de las demas clases
    abstract fun descripcion(): String
}
class FrutaLocal(nombre: String, precio: Double, stock: Int)
    : Fruta(nombre, precio, stock) {
    override fun descripcion() = "Fruta local: $nombre - Producida en la región"
}

class FrutaTropical(nombre: String, precio: Double, stock: Int)
    : Fruta(nombre, precio, stock) {
    override fun descripcion() = "Fruta tropical: $nombre - Importada"
}
