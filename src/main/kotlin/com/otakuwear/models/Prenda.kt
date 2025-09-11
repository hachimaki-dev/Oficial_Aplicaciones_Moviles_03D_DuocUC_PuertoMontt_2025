package org.example.com.otakuwear.models

open class Prenda(
    val nombre: String,
    val precio: Double,
    val stock: Int
){
    init{
        require(nombre.isNotBlank()) { "Nombre no puede estar vacio" }
    }

    open fun validar(): Boolean {
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
        }else{
            return true
        }
    }

    open fun mostrarInfo(){
        println("=".repeat(50))
        println("Nombre: $nombre")
        println("Precio: $precio")
        println("Stock: $stock")
        println("=".repeat(50))
    }
}