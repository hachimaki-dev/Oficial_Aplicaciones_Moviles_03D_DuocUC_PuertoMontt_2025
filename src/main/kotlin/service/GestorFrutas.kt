package service
import model.Fruta
class GestorFrutas {
    private val frutas = mutableListOf<Fruta>()

    fun addFruta(fruta: Fruta): Boolean {
        return try {
            frutas.add(fruta)
            println("Fruta agregada a inventario")
            true
        }catch(e: Exception){
            println("Fallo al registrar fruta al inventario")
            false
        }
        }
    fun mostrarFrutas(){
        if (frutas.isEmpty()){
            println("No se encuentran frutas en el inventario")
            return
        }
        println("/n ===INVENTARIO DE FRUTAS===")
        frutas.forEach{it.mostrarFruta()}
    }
    fun mostrarFrutaCaras() {
        val frutaCaras = frutas.filter { it.precioPorKilo > 1000 }
        if (frutaCaras.isEmpty()) {
            println("No ay frutas con valor mas grande que 1000")
            return
        }
        println("\n===FRUTAS CARAS===")
        frutaCaras.forEach { it.mostrarFruta() }
    }

    fun calcularValorTotalStock(): Double {
        return frutas.sumOf{it.calculoFruta()}
    }

}
