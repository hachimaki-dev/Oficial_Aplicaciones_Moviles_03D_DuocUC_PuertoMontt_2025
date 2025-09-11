import model.Fruta
import model.FrutaLocal
import model.FrutaTropical
import service.GestorFrutas
fun main(){
    val gestor : GestorFrutas = GestorFrutas()
    var menu:Boolean=true
    var op:Int =0
    while(menu) {
        println("===MENU GESTOR FRUTAS===")
        println("1-Registrar fruta local")
        println("2-Registrar fruta tropical")
        println("3-Ver inventario")
        println("4-Calcular valor inventario")
        println("5-Ver fruta mas cara")
        println("6-Insertar valores ejemplo")
        println("7-Salir programa")
        println("INGRESAR OPCION")
        op = readln().toInt()
        when (op) {
            1 -> {
                println("Inserte Nombre")
                var nombre: String = readln() ?: ""
                println("Inserte precio")
                var precio: Double = readln().toDouble() ?: 0.0
                println("Inserte stock")
                var stock: Int = readln().toInt() ?: 0
                gestor.addFruta(FrutaLocal(nombre, precio, stock))
            }

            2 -> {
                println("Inserte Nombre")
                var nombre: String = readln() ?: ""
                println("Inserte precio")
                var precio: Double = readln().toDouble() ?: 0.0
                println("Inserte stock")
                var stock: Int = readln().toInt() ?: 0
                gestor.addFruta(FrutaTropical(nombre, precio, stock))
            }

            3 -> {
                gestor.mostrarFrutas()
            }

            4 -> {
                println("===CALCULO VALOR TOTAL DEL INVENTARIO===")
                println(gestor.calcularValorTotalStock())
            }

            5 -> {
                gestor.mostrarFrutaCaras()
            }

            6 -> {
                print("INSERTANDO VALORES DE EJEMPLO")
                gestor.addFruta(FrutaTropical("Mango", 5000.0, 6))
                gestor.addFruta(FrutaTropical("Durazno", 900.0, 5))
                gestor.addFruta(FrutaLocal("Palta", 10000.0, 3))
                gestor.addFruta(FrutaLocal("Pera", 600.0, 19))
            }

            7 -> {
                println("SALIENDO PROGRAMA")
                menu = false
            }

            else -> {println("OPCION NO VALIDA")}
        }

    }
}

