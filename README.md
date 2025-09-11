# Aplicación Puerto Frutal SPA - Gestión de Inventario

## 📋 Descripción del Proyecto

Desarrolla una aplicación de consola en Kotlin para gestionar el inventario de frutas de la empresa Puerto Frutal SPA, ubicada en Puerto Montt. La empresa se dedica a la compra y distribución de frutas a supermercados y ferias de la región.

## 🎯 Objetivos de Aprendizaje

- Implementar clases y objetos en Kotlin
- Aplicar manejo de excepciones con try-catch
- Utilizar funciones de orden superior (filter, sumOf)
- Implementar herencia y polimorfismo
- Trabajar con validaciones y conversiones seguras
- Manejar colecciones mutables

## 📝 Requerimientos a Implementar

### 1. Registro de Frutas (25 puntos)
- Crear una clase `Fruta` con propiedades: `nombre`, `precioPorKilo`, `stockKilos`
- Implementar validaciones:
    - El precio debe ser mayor que 0
    - El stock no puede ser negativo
    - El nombre no puede estar vacío
- Usar bloques `try-catch` para manejar errores de validación
- Usar conversiones seguras como `toDoubleOrNull()` y `toIntOrNull()`
- Mostrar mensajes apropiados al usuario en caso de error

### 2. Mostrar Frutas Registradas (15 puntos)
- Implementar función para mostrar todas las frutas registradas
- Mostrar información completa: nombre, precio por kilo y stock disponible
- Formato claro y legible en consola
- Manejar el caso cuando no hay frutas registradas

### 3. Filtrar Frutas Caras (15 puntos)
- Implementar filtro usando la función `filter`
- Mostrar solo frutas con precio mayor a $1000
- Presentar resultados de manera clara
- Ordenar los resultados por precio (usar `sortedBy` o `sortedByDescending`)

### 4. Calcular Valor Total del Stock (15 puntos)
- Usar la función `sumOf` para calcular el valor total del inventario
- Fórmula: `precioPorKilo * stockKilos` para cada fruta
- Mostrar el resultado total en formato monetario
- Incluir métricas adicionales como precio promedio usando `average()`

### 5. Herencia y Polimorfismo (20 puntos)
- **Clase base:** `Fruta` (clase abstracta o abierta)
- **Subclases:**
    - `FrutaLocal`: para frutas producidas localmente
    - `FrutaTropical`: para frutas importadas/tropicales
- **Método abstracto:** `descripcion()` que debe ser sobrescrito en cada subclase
- Cada subclase debe proporcionar una implementación única del método `descripcion()`
- Crear instancias de ambos tipos y almacenarlas en una lista polimórfica

### 6. Manejo de Colecciones y Funciones Avanzadas (10 puntos)
- Usar `mutableListOf<Fruta>()` para almacenar las frutas
- Implementar funciones como `maxByOrNull`, `minByOrNull` para encontrar extremos
- Usar `forEach` o `forEachIndexed` para iteraciones
- Demostrar el uso de `when` para manejar diferentes opciones del menú


## 🚀 Funcionalidades del Menú Principal

1. **Registrar nueva fruta** (con validaciones completas)
2. **Mostrar todas las frutas**
3. **Mostrar frutas caras (> $1000)**
4. **Calcular métricas del inventario** (valor total, promedios, extremos)
5. **Salir**

## ⚡ Consejos de Implementación

- Usa `data class` si solo necesitas almacenar datos, o clases regulares para más funcionalidad
- Implementa un menú interactivo con `readLine()` y validación de entrada
- Siempre usa conversiones seguras: `toDoubleOrNull()`, `toIntOrNull()`, `trim()`
- Maneja casos edge como listas vacías con `isEmpty()` antes de operaciones
- Usa `?.` (safe call operator) cuando sea apropiado
- Implementa validaciones antes de crear objetos
- Crea múltiples instancias de cada tipo de fruta para probar toda la funcionalidad

## 📤 Entrega

- Subir su codigo al repo del curso. La rama debe llamarse "evaluacion1/nombre_apellido1_apellido2"
## 🎯 Criterios de Evaluación

| Requerimiento | Puntos | Descripción |
|---------------|--------|-------------|
| Registro de Frutas | 25 | Clase, validaciones robustas, manejo de excepciones |
| Mostrar Frutas | 15 | Visualización clara de datos, manejo de casos vacíos |
| Filtrar Frutas Caras | 15 | Uso correcto de `filter` y ordenamiento |
| Valor Total Stock | 15 | Implementación con `sumOf` y métricas adicionales |
| Herencia y Polimorfismo | 20 | Clases base, subclases, método sobrescrito |
| Colecciones y Funciones | 10 | Manejo de listas, funciones avanzadas, `when` |
| **Total** | **100** | **Puntos totales** |

## 🔧 Ejemplo de Implementación

```kotlin
// Definición de la clase base
abstract class Fruta(
    val nombre: String,
    val precioPorKilo: Double,
    val stockKilos: Double
) {
    abstract fun descripcion(): String
    
    fun validar(): Boolean {
        return precio > 0 && stock >= 0 && nombre.isNotBlank()
    }
    
    fun valorTotal(): Double = precioPorKilo * stockKilos
}

// Subclases con polimorfismo
class FrutaLocal(nombre: String, precio: Double, stock: Double) 
    : Fruta(nombre, precio, stock) {
    override fun descripcion() = "Fruta local: $nombre - Producida en la región"
}

class FrutaTropical(nombre: String, precio: Double, stock: Double) 
    : Fruta(nombre, precio, stock) {
    override fun descripcion() = "Fruta tropical: $nombre - Importada"
}

// Uso en el programa principal
val inventario = mutableListOf<Fruta>()

// Creación de instancias
val manzana = FrutaLocal("Manzana", 1200.0, 50.0)
val mango = FrutaTropical("Mango", 2500.0, 20.0)

inventario.add(manzana)
inventario.add(mango)

// Filtrar frutas caras
val frutasCaras = inventario.filter { it.precioPorKilo > 1000 }
    .sortedByDescending { it.precioPorKilo }

// Calcular valor total
val valorTotal = inventario.sumOf { it.valorTotal() }

// Encontrar extremos
val masCara = inventario.maxByOrNull { it.precioPorKilo }
val masBarata = inventario.minByOrNull { it.precioPorKilo }
```

## 🌟 Conceptos Clave a Demostrar

- **Null Safety**: Uso de `?.`, `?:`, y funciones `OrNull`
- **Validaciones Robustas**: Múltiples comprobaciones antes de crear objetos
- **Programación Funcional**: `filter`, `map`, `sumOf`, `maxByOrNull`, etc.
- **Polimorfismo**: Mismo método, comportamientos diferentes según la subclase
- **Manejo de Excepciones**: `try-catch` para entrada de usuario
- **Colecciones**: Operaciones avanzadas sobre listas

---

## 📚 ANEXO: Guía de Referencia Rápida de Kotlin

> **Nota:** Esta sección es material adicional para tu aprendizaje y referencia. Te ayudará no solo en la prueba, sino también para ir más allá y convertirte en un mejor programador Kotlin. ¡Explora, experimenta y diviértete programando!

### 🔢 Tipos de Datos Primitivos y sus Métodos

| Tipo | Métodos Útiles | Descripción | Ejemplo de Uso |
|------|----------------|-------------|----------------|
| **String** | `.isBlank()` | Verifica si está vacío o solo espacios | `if (nombre.isBlank()) { error }` |
| | `.trim()` | Elimina espacios al inicio y final | `val limpio = input.trim()` |
| | `.lowercase()` | Convierte a minúsculas | `if (respuesta.lowercase() == "si")` |
| | `.contains()` | Busca texto dentro del string | `if (fruta.contains("manzana"))` |
| | `.repeat()` | Repite el string n veces | `"*".repeat(50) // Línea decorativa` |
| **Double** | `.toInt()` | Convierte a entero (puede perder precisión) | `val entero = 3.7.toInt() // 3` |
| | `String.format()` | Formatea con decimales | `"%.2f".format(precio) // "12.50"` |
| **Int** | `.toDouble()` | Convierte a decimal | `val decimal = stock.toDouble()` |
| | `..` (rangos) | Crea rangos de números | `for (i in 1..5) { println(i) }` |

```kotlin
// 💡 Tip: Conversiones seguras
val userInput = "123.45abc"
val precio = userInput.toDoubleOrNull() ?: 0.0 // Devuelve 0.0 si falla
val stock = "10x".toIntOrNull() ?: -1 // Devuelve -1 si falla

// 💡 Curiosidad: String templates
val mensaje = "La $fruta cuesta ${precio} por kilo"
val calculo = "Total: ${precio * cantidad}" // Expresiones complejas
```

### 📋 Colecciones: Tu Superpoder en Kotlin

| Tipo de Colección | Cuándo Usar | Métodos Clave | Casos de Uso |
|-------------------|-------------|---------------|--------------|
| **List** (inmutable) | Datos que no cambiarán | `.size`, `.get()`, `.contains()` | Menús fijos, configuraciones |
| **MutableList** | Datos que necesitas modificar | `.add()`, `.remove()`, `.clear()` | Inventarios, carritos de compra |
| **Map** | Asociar clave-valor | `.get()`, `.put()`, `.keys`, `.values` | Precios por producto, usuarios-contraseñas |

```kotlin
// 🚀 Funciones de Orden Superior: ¡El poder real de Kotlin!

val frutas = listOf(
    Fruta("Manzana", 1200.0, 50),
    Fruta("Banana", 800.0, 30),
    Fruta("Mango", 2500.0, 20)
)

// FILTER: Filtrar elementos que cumplan una condición
val frutasCaras = frutas.filter { it.precio > 1000 }
val frutasConStock = frutas.filter { it.stock > 0 }
val frutasNombreCorto = frutas.filter { it.nombre.length <= 6 }

// MAP: Transformar cada elemento
val soloNombres = frutas.map { it.nombre }
val preciosConDescuento = frutas.map { it.precio * 0.9 }
val infoCompleta = frutas.map { "${it.nombre}: ${it.precio}" }

// SUMAR Y AGREGACIONES: Calcular valores
val valorTotal = frutas.sumOf { it.precio * it.stock }
val stockTotal = frutas.sumOf { it.stock }
val precioPromedio = frutas.map { it.precio }.average()

// ENCONTRAR EXTREMOS: Máximos y mínimos
val masCara = frutas.maxByOrNull { it.precio }
val masBarata = frutas.minByOrNull { it.precio }
val mayorStock = frutas.maxByOrNull { it.stock }

// ORDENAMIENTO: Organizar datos
val porPrecio = frutas.sortedBy { it.precio }          // Ascendente
val porPrecioDesc = frutas.sortedByDescending { it.precio } // Descendente
val alfabetico = frutas.sortedBy { it.nombre }

// 💎 Encadenamiento: ¡Combina operaciones!
val resultado = frutas
    .filter { it.stock > 10 }           // Solo con stock suficiente
    .sortedByDescending { it.precio }   // Ordenar por precio
    .take(3)                            // Solo los 3 primeros
    .map { "${it.nombre}: ${it.precio}" } // Formatear

// ITERACIÓN: Recorrer elementos
frutas.forEach { println(it.nombre) }
frutas.forEachIndexed { index, fruta -> 
    println("${index + 1}. ${fruta.nombre}")
}
```

### 🛡️ Null Safety: Tu Escudo Protector

| Operador | Significado | Cuándo Usar | Ejemplo |
|----------|-------------|-------------|---------|
| `?` | Tipo nullable | Variable puede ser null | `var nombre: String? = null` |
| `?.` | Safe call | Evita NullPointerException | `nombre?.uppercase()` |
| `?:` | Elvis operator | Valor por defecto si es null | `nombre ?: "Sin nombre"` |
| `!!` | Not-null assertion | Estás 100% seguro que no es null | `nombre!!.uppercase()` ⚠️ |

```kotlin
// 🔒 Null Safety en acción
fun procesarFruta(nombre: String?) {
    // ❌ Peligroso: puede explotar si nombre es null
    // println(nombre.uppercase())
    
    // ✅ Seguro: solo se ejecuta si no es null
    println(nombre?.uppercase())
    
    // ✅ Con valor por defecto
    val nombreSeguro = nombre ?: "Fruta desconocida"
    
    // ✅ Verificación manual
    if (nombre != null) {
        println(nombre.uppercase()) // Aquí Kotlin sabe que no es null
    }
}
```

### 🎯 Manejo de Excepciones: Preparado para Todo

```kotlin
// 📊 Patrón de validación robusto
fun registrarFruta() {
    try {
        print("Nombre: ")
        val nombre = readLine()?.trim()
        
        // Validación en cascada
        if (nombre.isNullOrBlank()) {
            throw IllegalArgumentException("Nombre no puede estar vacío")
        }
        
        print("Precio: ")
        val precioTexto = readLine()?.trim() ?: ""
        val precio = precioTexto.toDoubleOrNull() 
            ?: throw NumberFormatException("Precio inválido: '$precioTexto'")
        
        if (precio <= 0) {
            throw IllegalArgumentException("Precio debe ser positivo")
        }
        
        print("Stock: ")
        val stockTexto = readLine()?.trim() ?: ""
        val stock = stockTexto.toIntOrNull()
            ?: throw NumberFormatException("Stock inválido: '$stockTexto'")
        
        if (stock < 0) {
            throw IllegalArgumentException("Stock no puede ser negativo")
        }
        
        // Si llegamos aquí, todos los datos son válidos
        val fruta = Fruta(nombre, precio, stock)
        println("✅ Fruta registrada exitosamente")
        
    } catch (e: NumberFormatException) {
        println("❌ Error de formato: ${e.message}")
    } catch (e: IllegalArgumentException) {
        println("❌ Error de validación: ${e.message}")
    } catch (e: Exception) {
        println("❌ Error inesperado: ${e.message}")
    }
}
```

### 🏗️ Herencia y Polimorfismo: Código Inteligente

```kotlin
// 🎭 Polimorfismo en acción
abstract class Fruta(
    val nombre: String,
    val precio: Double,
    val stock: Int
) {
    // Método común para todas las frutas
    open fun mostrarInfo() = "$nombre - ${precio} (${stock} unidades)"
    
    // Método que cada subclase debe implementar
    abstract fun descripcion(): String
    
    // Método que puede ser sobrescrito opcionalmente
    open fun tiempoVida(): String = "Variable según condiciones"
}

class FrutaLocal(nombre: String, precio: Double, stock: Int) 
    : Fruta(nombre, precio, stock) {
    
    override fun descripcion() = "🍎 Fruta local: $nombre - Fresca de la región"
    override fun tiempoVida() = "3-7 días refrigerada"
    
    // Método específico de frutas locales
    fun zonaProduccion() = "Región de Los Lagos"
}

class FrutaTropical(nombre: String, precio: Double, stock: Int) 
    : Fruta(nombre, precio, stock) {
    
    override fun descripcion() = "🥭 Fruta tropical: $nombre - Importada"
    override fun tiempoVida() = "5-14 días según madurez"
    
    // Método específico de frutas tropicales
    fun paisOrigen() = "Países tropicales"
}

// 🎪 Usando polimorfismo
val inventario = mutableListOf<Fruta>()
inventario.add(FrutaLocal("Manzana", 1200.0, 50))
inventario.add(FrutaTropical("Mango", 2500.0, 20))

// ¡Cada fruta responde diferente al mismo método!
inventario.forEach { fruta ->
    println(fruta.descripcion()) // Comportamiento polimórfico
    println("Duración: ${fruta.tiempoVida()}")
}
```

### 🎛️ Estructuras de Control Avanzadas

```kotlin
// 🔄 WHEN: El switch de Kotlin con superpoderes
fun procesarOpcion(opcion: String) {
    when (opcion.trim()) {
        "1", "registrar" -> registrarFruta()
        "2", "mostrar", "ver" -> mostrarInventario()
        "3", "filtrar" -> filtrarFrutas()
        "4", "calcular" -> calcularMetricas()
        "5", "salir", "exit", "q" -> salirPrograma()
        else -> {
            println("❌ Opción desconocida: '$opcion'")
            println("💡 Opciones válidas: 1-5, o palabras como 'mostrar', 'salir'")
        }
    }
}

// 🔄 When con condiciones
fun categorizarPrecio(precio: Double) = when {
    precio < 500 -> "💰 Económica"
    precio < 1500 -> "💵 Normal"
    precio < 3000 -> "💸 Premium"
    else -> "💎 Lujo"
}

// 🔄 Loops con estilo
fun mostrarMenuAnimado() {
    val opciones = listOf(
        "Registrar fruta",
        "Ver inventario", 
        "Filtrar frutas caras",
        "Calcular métricas",
        "Salir"
    )
    
    opciones.forEachIndexed { index, opcion ->
        println("${index + 1}. $opcion")
        Thread.sleep(100) // Animación simple 😎
    }
}
```

### 💡 Tips Pro y Curiosidades

#### 🚀 Funciones de Extensión: Superpoderes Personalizados
```kotlin
// Extiende tipos existentes con nuevas funcionalidades
fun Double.formatearMoneda(): String = "${String.format("%.2f", this)}"
fun String.esPrecioValido(): Boolean = this.toDoubleOrNull()?.let { it > 0 } ?: false

// Uso
val precio = 1234.5
println(precio.formatearMoneda()) // "$1234.50"
println("abc".esPrecioValido())   // false
println("123.45".esPrecioValido()) // true
```

#### 🎨 Data Classes: Clases con Superpoderes
```kotlin
data class Fruta(val nombre: String, val precio: Double, val stock: Int) {
    // ¡Kotlin genera automáticamente!
    // - toString() elegante
    // - equals() y hashCode()
    // - copy() para duplicar con cambios
}

val manzana = Fruta("Manzana", 1200.0, 50)
val manzanaDescuento = manzana.copy(precio = 1000.0) // Nuevo objeto con precio diferente
println(manzana) // Fruta(nombre=Manzana, precio=1200.0, stock=50)
```

#### 🔧 Scope Functions: Let, Apply, Run, With
```kotlin
// LET: Útil para null safety y transformaciones
val resultado = nombre?.let { nombreValido ->
    if (nombreValido.length > 3) "Nombre válido: $nombreValido"
    else "Nombre muy corto"
}

// APPLY: Configura objetos
val fruta = Fruta("", 0.0, 0).apply {
    // En versiones mutables, podrías configurar aquí
    println("Configurando fruta...")
}
```
