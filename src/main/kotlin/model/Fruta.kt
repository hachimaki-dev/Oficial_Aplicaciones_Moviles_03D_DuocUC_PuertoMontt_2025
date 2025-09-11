package model

open class Fruta (
    val nombre : String,
    val precioPorKilo : Double,
    val stockKilos : Int
) {
    init {
        require(precioPorKilo > 0) { "precioPorKilo debe ser mayor a 0" }
        require(stockKilos > 0) { "stockKilos debe ser mayor a 0" }
        require(nombre.isNotBlank()) { "nombre no puede estar vacio" }
    }
    fun mostrarFruta(){
        println("${descripcion()}")
        println("Nombre: $nombre")
        println("Precio por kilo: $precioPorKilo")
        println("Stock en kilos: $stockKilos")
    }
    fun calculoFruta(): Double {
        return precioPorKilo*stockKilos
    }
    open fun descripcion(): String {
        return "Fruta generica: $nombre"
    }
}