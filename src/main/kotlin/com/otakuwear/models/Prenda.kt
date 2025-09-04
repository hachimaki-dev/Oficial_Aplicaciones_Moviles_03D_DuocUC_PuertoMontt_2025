package com.otakuwear.models

data class Prenda(val nombre: String, val precio: Double, val stock: Int) {
    fun validar(): Boolean {
        return precio > 0 && stock >= 0 && nombre.isNotBlank()
    }


    fun mostrarInfo(): String {
        return "$nombre - Precio: $${precio} - Stock: ${stock} unidades"
    }
}