package org.example.evaluacion1


fun main() {

    var sistemaActivo = true

    // Bucle principal del programa
    while (sistemaActivo) {
        mostrarMenu()

        // Captura de opción con validación
        val opcionTexto = readLine()?.trim() ?: ""

        // Procesamiento de la opción seleccionada
        when (opcionTexto) {
            "1" -> {
                registrarFruta()
            }
            "2" -> {
                mostrarInventarioCompleto()
            }
            "3" -> {
                mostrarFrutasCaras()
            }
            "4" -> {
                calcularValores()
            }
            "5" -> {
                println("\nGracias por usar fruteria del watonsito bby")
                println("¡Hasta la próxima!")
                sistemaActivo = false
            }
            else -> {
                println("\n[ERROR] Opción inválida: '$opcionTexto'")
                println("Por favor, selecciona un número entre 1 y 5")
            }
        }

        // Pausa antes del siguiente ciclo (excepto al salir)
        if (sistemaActivo) {
            println("\nPresiona ENTER para continuar...")
            readLine()
        }
    }
}

data class Fruta (val nombre :String,
             val precioPorKilo : Double,
             val stockKilos : Int) {
    fun validar(): Boolean {
        return nombre.isNotBlank() && precioPorKilo > 0 && stockKilos >= 0

    }

    fun mostrarInfo(): String {
        return "nombre: $nombre, precio: $precioPorKilo, stock: $stockKilos"


    }
}
fun mostrarMenu() {
    println("\n" + "=".repeat(40))
    println("    FRuteria watonsito bby")
    println("=".repeat(40))
    println("1. Agregar Fruta")
    println("2. Ver inventario completo")
    println("3. Filtrar FRutas CAars (> $1000)")
    println("4. Calcular valor total del inventario")
    println("5. Salir del sistema")
    println("-".repeat(40))
    print("Selecciona una opción [1-5]: ")
}
val almacenFruta= mutableListOf<Fruta>()

    fun registrarFruta(){
        print("Nombre de la fruta: ")
        val nombre = readLine()?.trim() ?: ""

        if (nombre.isBlank()) {
            println("[ERROR] El nombre es obligatorio")
            return

        }
        print("Precio en DodgeCoin: $")
        val precioTexto = readLine()?.trim() ?: "0"
        val precioPorKilo = precioTexto.toDoubleOrNull()

        if (precioPorKilo == null || precioPorKilo <= 0) {
            println("[ERROR] El precio de la fruta debe ser un número positivo")
            return
        }
        print("Cantidad en stock: ")
        val stockTexto = readLine()?.trim() ?: "0"
        val stockKilos = stockTexto.toIntOrNull()

        if (stockKilos == null || stockKilos < 0) {
            println("[ERROR] El stockKilos debe ser un número no negativo")
             return
         }
        val nuevaFruta = Fruta(nombre,precioPorKilo,stockKilos)
        if (nuevaFruta.validar()){
            almacenFruta.add(nuevaFruta)
            println("\n[ÉXITO] Frutita registrada:")
            println("-> ${nuevaFruta.mostrarInfo()}")
        } else {
            println("[ERROR] Los datos fruta no son validos ")
        }


        }

fun mostrarInventarioCompleto() {

    if (almacenFruta.isEmpty()) {
        println("No hay frutas en el negocio.")
        return
    }
    println("Total de frutas: ${almacenFruta.size}")
    println("-".repeat(50))

    almacenFruta.forEachIndexed { indice,fruta ->
        val numero = indice + 1
        println("$numero. ${fruta.mostrarInfo()}")
    }

    println("-".repeat(50))
}
fun mostrarFrutasCaras() {
    val frutasCaras = almacenFruta.filter { fruta ->
        fruta.precioPorKilo > 1000.0
    }
    if (frutasCaras.isEmpty()) {
        println("No hay frutas en el negocio.")
        return
    }
    println("Total de frutas: ${frutasCaras.size}")
}
fun calcularValores() {
        if (almacenFruta.isEmpty()){
            println("no hay nadi")
            return
        }
        //valor total de las frutitas
        val valorTotal =almacenFruta.sumOf{
            fruta -> fruta.precioPorKilo * fruta.stockKilos
        }
        // fruta mas cara
        val frutaMasCara = almacenFruta.maxByOrNull { it.precioPorKilo }

        // fruta mas varata
        val frutaMasBarata = almacenFruta.minByOrNull { it.precioPorKilo }

        // Precio promedio
        val precioPromedio = almacenFruta.map { it.precioPorKilo }.average()
        //stock Total
        val stockTotal = almacenFruta.sumOf { it.precioPorKilo }


    // Mostrar resultados
    println("Valor total del inventario: $${String.format("%.2f", valorTotal)}")
    println("Precio promedio: $${String.format("%.2f", precioPromedio)}")
    println("Stock total: $stockTotal unidades")
    println()
    println("Prenda más cara: ${frutaMasCara?.mostrarInfo() ?: "N/A"}")
    println("Prenda más barata: ${frutaMasBarata?.mostrarInfo() ?: "N/A"}")
    }

