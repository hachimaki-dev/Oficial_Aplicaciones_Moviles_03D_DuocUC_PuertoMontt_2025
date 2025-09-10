// Sistema de Ventas de Pasajes - Terminal de Buses Puerto Montt
// Plantilla Base para Estudiantes

// TODO: Completar la clase Viaje con las propiedades necesarias
class Viaje(
    // TODO: Agregar propiedades del viaje
    // Sugerencia: destino, precio, pasajesDisponibles, pasajesIniciales
) {

    // TODO: Agregar método para calcular pasajes vendidos
    fun pasajesVendidos(): Int {
        // TODO: Implementar cálculo de pasajes vendidos
        return 0
    }

    // TODO: Agregar método para vender un pasaje
    fun venderPasaje(): Boolean {
        // TODO: Verificar si hay pasajes disponibles y vender uno
        // Retornar true si se pudo vender, false si no
        return false
    }

    // TODO: Agregar método toString para mostrar información del viaje
    override fun toString(): String {
        // TODO: Formatear la información del viaje para mostrar
        return ""
    }
}

// Lista global para almacenar todos los viajes
val viajes = mutableListOf<Viaje>()

// TODO: Función principal que inicia el programa
fun main() {
    println("=== SISTEMA DE VENTAS DE PASAJES ===")
    println("Terminal de Buses Puerto Montt")
    println()

    // TODO: Implementar bucle principal del menú
    var continuar = true
    while (continuar) {
        // TODO: Mostrar menú y procesar opción elegida
        mostrarMenu()

        val opcion = leerOpcion()
        continuar = procesarOpcion(opcion)
    }

    println("¡Gracias por usar nuestro sistema!")
}

// TODO: Función para mostrar el menú principal
fun mostrarMenu() {
    println("\n=== MENÚ PRINCIPAL ===")
    // TODO: Mostrar las 5 opciones del menú
    println("1. Registrar nuevo viaje")
    println("2. Ver todos los viajes")
    println("3. Comprar pasaje")
    println("4. Ver métricas del sistema")
    println("5. Salir")
    print("Seleccione una opción (1-5): ")
}

// TODO: Función para leer la opción del usuario
fun leerOpcion(): Int {
    return try {
        // TODO: Leer y validar entrada del usuario
        // Sugerencia: usar readLine() y toIntOrNull()
        readLine()?.toIntOrNull() ?: 0
    } catch (e: Exception) {
        0 // Opción inválida
    }
}

// TODO: Función para procesar la opción elegida
fun procesarOpcion(opcion: Int): Boolean {
    return when (opcion) {
        1 -> {
            // TODO: Llamar función para registrar viaje
            registrarViaje()
            true // Continuar en el menú
        }
        2 -> {
            // TODO: Llamar función para ver viajes
            verViajes()
            true
        }
        3 -> {
            // TODO: Llamar función para comprar pasaje
            comprarPasaje()
            true
        }
        4 -> {
            // TODO: Llamar función para ver métricas
            verMetricas()
            true
        }
        5 -> {
            // TODO: Salir del programa
            false // Terminar el bucle
        }
        else -> {
            // TODO: Manejar opción inválida
            println("❌ Opción inválida. Por favor seleccione una opción del 1 al 5.")
            true
        }
    }
}

// TODO: Función para registrar un nuevo viaje
fun registrarViaje() {
    println("\n=== REGISTRAR NUEVO VIAJE ===")

    // TODO: Solicitar información del viaje al usuario
    print("Ingrese el destino: ")
    val destino = readLine() ?: ""

    // TODO: Validar que el destino no esté vacío

    print("Ingrese el precio del pasaje (CLP): ")
    // TODO: Leer y validar el precio

    print("Ingrese cantidad de pasajes disponibles: ")
    // TODO: Leer y validar la cantidad de pasajes

    // TODO: Crear el viaje y agregarlo a la lista

    // TODO: Mostrar mensaje de confirmación
}

// TODO: Función para mostrar todos los viajes registrados
fun verViajes() {
    println("\n=== VIAJES DISPONIBLES ===")

    // TODO: Verificar si hay viajes registrados
    if (viajes.isEmpty()) {
        println("No hay viajes registrados en el sistema.")
        return
    }

    // TODO: Mostrar todos los viajes con su información
    // Sugerencia: usar un bucle for con índices
    for (i in viajes.indices) {
        // TODO: Mostrar información del viaje en posición i
        println("${i + 1}. ${viajes[i]}")
    }
}

// TODO: Función para comprar un pasaje
fun comprarPasaje() {
    println("\n=== COMPRAR PASAJE ===")

    // TODO: Verificar si hay viajes disponibles
    if (viajes.isEmpty()) {
        println("No hay viajes registrados para comprar.")
        return
    }

    // TODO: Mostrar viajes disponibles
    verViajes()

    // TODO: Solicitar al usuario que seleccione un viaje
    print("\nSeleccione el número del viaje: ")
    val numeroViaje = leerOpcion()

    // TODO: Validar que el número de viaje sea válido

    // TODO: Intentar vender el pasaje

    // TODO: Mostrar mensaje de éxito o error
}

// TODO: Función para mostrar métricas del sistema
fun verMetricas() {
    println("\n=== MÉTRICAS DEL SISTEMA ===")

    if (viajes.isEmpty()) {
        println("No hay viajes registrados para mostrar métricas.")
        return
    }

    // TODO: Calcular total de pasajes vendidos
    val totalPasajesVendidos = 0 // TODO: Implementar cálculo

    // TODO: Calcular recaudación total
    val recaudacionTotal = 0 // TODO: Implementar cálculo

    // TODO: Calcular precio promedio de viajes
    val precioPromedio = 0.0 // TODO: Implementar cálculo

    // TODO: Mostrar las métricas calculadas
    println("Total de pasajes vendidos: $totalPasajesVendidos")
    println("Recaudación total: $${recaudacionTotal} CLP")
    println("Precio promedio de viajes: $${String.format("%.0f", precioPromedio)} CLP")
}

// TODO: Funciones auxiliares adicionales (si las necesitas)

// Función para leer un número entero con validación
fun leerEntero(mensaje: String): Int {
    while (true) {
        print(mensaje)
        val entrada = readLine()?.toIntOrNull()
        if (entrada != null && entrada > 0) {
            return entrada
        } else {
            println("❌ Por favor ingrese un número válido mayor que 0.")
        }
    }
}

// Función para leer una cadena no vacía
fun leerCadena(mensaje: String): String {
    while (true) {
        print(mensaje)
        val entrada = readLine()?.trim()
        if (!entrada.isNullOrEmpty()) {
            return entrada
        } else {
            println("❌ Por favor ingrese un texto válido (no vacío).")
        }
    }
}

/*
NOTAS PARA EL ESTUDIANTE:

1. ORDEN DE IMPLEMENTACIÓN SUGERIDO:
   - Completar la clase Viaje primero
   - Implementar registrarViaje()
   - Implementar verViajes()
   - Implementar comprarPasaje()
   - Implementar verMetricas()
   - Agregar validaciones adicionales

2. CONCEPTOS CLAVE A PRACTICAR:
   - Propiedades de clase y métodos
   - Listas mutables y operaciones
   - Bucles while y for
   - Condicionales when e if
   - Manejo de excepciones (try/catch)
   - Validación de entrada de usuario

3. FUNCIONES AUXILIARES:
   - Ya se incluyen leerEntero() y leerCadena()
   - Úsalas para validar entrada del usuario
   - Evitarán muchos errores comunes

4. TESTING:
   - Prueba cada función por separado
   - Usa datos de ejemplo para verificar cálculos
   - Prueba casos extremos (lista vacía, números negativos)

¡Buena suerte con tu desarrollo! 🚌
*/