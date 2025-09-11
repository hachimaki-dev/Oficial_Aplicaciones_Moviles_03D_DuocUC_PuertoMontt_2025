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

