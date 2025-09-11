package org.example


registroFrutas(){
    Fruta(nombre: String, precioPorKilo: Int, stockKilos: Int){

    }
}















fun mostrarMenu(){
    println("Bienvenido al sistema de gestión de inventario Puerto Frutal SPA")
    println("1. Registrar nueva fruta (con validaciones completas)")
    println("2. Mostrar todas las frutas")
    println("3. Mostrar frutas caras > $1000")
    println("4. Calcular métricas del inventario (valor total, promedios, extremos)")
    println("5. Salir")
}



fun main() {
    mostrarMenu()


}