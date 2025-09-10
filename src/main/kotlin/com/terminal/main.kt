package org.example.com.terminal


data class Viaje (val destino : String, val precio : Int, val stock : Int) {
    fun mostrarInfo(){
        println("${destino.lowercase()} - ${precio} - ${stock}")
    }
}

val lista : MutableList<Viaje> = mutableListOf()

fun ingresarViaje(){
    val destino: String = readLine()?.toString() ?: ""
    val precio: Int = readLine()?.toIntOrNull() ?: 0
    val stock: Int = readLine()?.toIntOrNull() ?: 0

    val viaje = Viaje(destino,precio ,stock)
    viaje.mostrarInfo()


    lista.add(viaje)
}


fun main(){
    ingresarViaje()
}

fun mostrarLista() {
    lista.forEach { it.mostrarInfo() }
}