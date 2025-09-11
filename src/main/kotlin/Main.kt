package org.example

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    var almacenFrutas = mutableListOf<Fruta>()
    var salir = false

    fun registroFruta(){



        println("---- REGISTRAR NUEVA FRUTA ----")
        try {
            print("Ingrese nombre fruta: ")
            var nombreFruta = readLine()?.trim() ?: ""

            if (nombreFruta.isBlank()){
                println("ERROR: El nombre fruta es obligatorio")
                return
            }

            print("Ingrese el precio por kilo: $")
            var precioFrutaTexto = readLine()?.trim() ?: "0"
            var precioFruta = precioFrutaTexto.toDoubleOrNull()

            if (precioFruta == null || precioFruta <= 0.0) {
                println("ERROR: El precio de la fruta debe ser válido")
                return
            }

            print("Ingrese stock en kilos: ")
            var stockFrutaTexto = readLine()?.trim() ?: "0"
            var stockFruta = stockFrutaTexto.toDoubleOrNull()

            if (stockFruta == null || stockFruta < 0.0){
                println("ERROR: El stock de fruta es obligatorio")
                return
            }

            println("Ingese tipo de fruta")
            println("1-> Local")
            println("2-> Tropical")
            print("Seleccione una opción: ")
            var opcionFrutaTexto = readLine()?.trim() ?: "0"
            var opcionFruta = opcionFrutaTexto.toInt()
            if (opcionFruta == 0 || opcionFruta == null){
                println("ERROR: El opcion de fruta es obligatorio")
                return
            }
            when (opcionFruta) {
                1 -> {
                    val frutaNueva = FrutaLocal(nombreFruta, precioFruta, stockFruta)
                    if (frutaNueva.validar()){
                        almacenFrutas.add(frutaNueva)
                        println("-- FRUTA ${frutaNueva.nombre.uppercase()} REGISTRADA")
                        println("-> ${frutaNueva.mostrarInfo()}")
                    }
                }
                2 -> {
                    val frutaNueva = FrutaTropical(nombreFruta, precioFruta, stockFruta)
                    if (frutaNueva.validar()){
                        almacenFrutas.add(frutaNueva)
                        println("-- FRUTA ${frutaNueva.nombre.uppercase()} REGISTRADA")
                        println("-> ${frutaNueva.mostrarInfo()}")
                    }
                }
            }




        }catch (excepcion: Exception){
            println("ERROR INESPERADO: Ha ocurrido un error")
            println(excepcion.message)
            println("Intente nuevamente")
        }

    }

    fun mostrarFrutas(){
        println("---- FRUTAS REGISTRADAS ---")

        if (almacenFrutas.isEmpty()){
            println("Todavía no hay ninguna fruta en el almacén")
            println("Registre una nueva fruta para mostrar")
            return
        }

        println("------------------------------------")
        println("FRUTAS ALMACENADAS ${almacenFrutas.size}")
        almacenFrutas.forEachIndexed { index, fruta ->
            var indice = index + 1
            println("${indice} -> ${fruta.mostrarInfo()} -> ${fruta.descripcion()}")
        }

        println("------------------------------------")
    }

    fun mostrarFrutasCaras(){
        println("---- FRUTAS PREMIUN (ARRIBA DE $1000) ----")

        if (almacenFrutas.isEmpty()){
            println("Todavía no hay ninguna fruta en el almacén")
            println("Registre una nueva fruta para mostrar")
            return
        }

        var almacenOrdenado = almacenFrutas.sortedByDescending { it.precioPorKilo }
        var almacenPremiun = almacenFrutas.filter { it.precioPorKilo > 1000 }

        almacenPremiun.forEachIndexed { index, fruta ->
            var indice = index + 1
            println("${indice} -> ${fruta.mostrarInfo()} -> ${fruta.descripcion()}")
        }

        println("------------------------------------")

    }

    fun calcularMetricas(){
        println("---- CÁLCULO DE MÉTRICAS ---")

        if (almacenFrutas.isEmpty()){
            println("Todavía no hay ninguna fruta en el almacén")
            println("Registre una nueva fruta para calcular métricas")
            return
        }

        val valorTotalStock = almacenFrutas.sumOf { it.precioPorKilo * it.stockKilos }

        val precioPromedio = almacenFrutas.map { it.precioPorKilo }.average()

        val stockPromedio = almacenFrutas.map { it.stockKilos }.average()

        println("Valor total del stock: $${"%,.2f".format(valorTotalStock)}")
        println("Precio promedio por kilo: $${"%,.2f".format(precioPromedio)}")
        println("Stock promedio en kilos: ${"%,.2f".format(stockPromedio)}")
        println("------------------------------------")
    }

    while (!salir) {
        println("\n====== MENÚ PRINCIPAL ======")
        println("1. Registrar nueva fruta")
        println("2. Mostrar todas las frutas")
        println("3. Mostrar frutas caras (> $1000)")
        println("4. Calcular métricas del inventario")
        println("5. Salir")
        print("Seleccione una opción: ")

        val opcion = readLine()?.trim()?.toIntOrNull()

        when (opcion) {
            1 -> registroFruta()
            2 -> mostrarFrutas()
            3 -> mostrarFrutasCaras()
            4 -> calcularMetricas()
            5 -> {
                println("Saliendo del programa... ¡Hasta luego!")
                salir = true
            }
            else -> println("Opción inválida, intente nuevamente")
        }
    }


}