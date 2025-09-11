package org.example.com.otakuwear.models

class Camiseta (
    nombre: String,
    precio: Double,
    stock: Int,
    val talla: Double,
    val material: String
) : Prenda(nombre, precio, stock) {
    override fun validar(): Boolean {
        if(nombre.isBlank()){
            println("Nombre no puede estar vacio")
            return false
        }else if(precio <0){
            println("Precio no puede ser negativo")
            return false
        }
        else if(stock < 0){
            println("Stock no puede ser negativo")
            return false
        }else if(talla < 0){
            println("Talla no puede ser negativo")
            return false
        }else if(material.isBlank()){
            println("Material no puede estar vacio")
            return false
        } else{
            return true
        }
    }

    override fun mostrarInfo() {
        println("=".repeat(50))
        println("Nombre: $nombre")
        println("Precio: $precio")
        println("Stock: $stock")
        println("Talla: $talla")
        println("Material: $material")
        println("=".repeat(50))
    }
}