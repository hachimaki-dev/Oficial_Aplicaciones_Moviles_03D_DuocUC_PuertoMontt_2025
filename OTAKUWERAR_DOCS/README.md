# Tutorial Completo: De Cero a Experto con Kotlin y Maven

## Tabla de Contenidos
1. [¿Qué vas a aprender?](#qué-vas-a-aprender)
2. [Preparación del entorno](#preparación-del-entorno)
3. [Fundamentos de Kotlin](#fundamentos-de-kotlin)
4. [Construyendo OtakuWear paso a paso](#construyendo-otakuwear-paso-a-paso)
5. [Conceptos avanzados explicados](#conceptos-avanzados-explicados)
6. [Ejercicios de práctica](#ejercicios-de-práctica)
7. [Próximos pasos](#próximos-pasos)

---

## ¿Qué vas a aprender?

Al finalizar este tutorial serás capaz de:
- Instalar y configurar IntelliJ IDEA con Maven desde cero
- Entender la sintaxis básica y avanzada de Kotlin
- Aplicar los 4 pilares de la Programación Orientada a Objetos
- Implementar programación funcional con colecciones
- Manejar errores de forma profesional
- Crear aplicaciones robustas y escalables

**Tiempo estimado**: 4-5 horas
**Nivel**: Principiante absoluto → Intermedio avanzado

---

## Preparación del entorno

### Paso 1: Descargar e instalar IntelliJ IDEA

1. **Ir al sitio oficial**:
    - Visita: https://www.jetbrains.com/idea/
    - Descarga la versión **Community Edition** (gratuita)

2. **Instalación por sistema operativo**:

   **Windows**:
    - Ejecuta el archivo `.exe` descargado
    - En el instalador, marca estas opciones importantes:
        - "Create Desktop Shortcut"
        - "Add launchers dir to the PATH"
        - "Add 'Open Folder as Project'"

   **macOS**:
    - Abre el archivo `.dmg`
    - Arrastra IntelliJ IDEA a la carpeta Applications
    - Al abrir por primera vez, autoriza en Preferencias del Sistema

   **Linux**:
    - Extrae el archivo `.tar.gz`: `tar -xzf ideaIC-*.tar.gz`
    - Navega a la carpeta `bin/` y ejecuta `./idea.sh`

3. **Primera configuración**:
    - Abre IntelliJ IDEA
    - Acepta el Privacy Policy
    - Configura el tema (recomendado: Darcula para reducir fatiga visual)
    - NO instales plugins adicionales por ahora

### Paso 2: Verificar Java y configurar Maven

**¿Por qué Maven?**
Maven es un sistema de gestión de proyectos que:
- Maneja dependencias automáticamente
- Estandariza la estructura del proyecto
- Facilita la compilación y empaquetado
- Es el estándar de la industria

**Verificación de Java**:
```bash
# En terminal/cmd ejecuta:
java -version
# Deberías ver algo como: openjdk version "11.0.x" o superior
```

Si no tienes Java instalado:
- **Windows/macOS**: Descarga OpenJDK desde https://adoptium.net/
- **Linux**: `sudo apt install openjdk-11-jdk` (Ubuntu/Debian)

---

## Fundamentos de Kotlin

### ¿Qué es Kotlin y por qué usarlo?

Kotlin es un lenguaje de programación moderno desarrollado por JetBrains que:
- **Interoperabilidad**: 100% compatible con Java
- **Concisión**: Reduce el código boilerplate significativamente
- **Seguridad**: Previene errores comunes como NullPointerException
- **Expresividad**: Sintaxis clara y legible
- **Multiplataforma**: Compila a JVM, Android, JavaScript, Native

### Conceptos fundamentales que DEBES dominar

#### 1. Sistema de tipos y variables

```kotlin
// Variables mutables (pueden cambiar)
var contador = 0
contador = 5  // Válido

// Variables inmutables (no pueden cambiar después de inicializarse)
val nombre = "Juan"
// nombre = "Pedro"  // Error de compilación

// Especificación explícita de tipos
var edad: Int = 25
val precio: Double = 29.99
val esActivo: Boolean = true
val mensaje: String = "Hola mundo"
```

**Pilar de POO: Encapsulación**
> Nota: En Kotlin, `val` y `var` son fundamentales para la encapsulación. `val` garantiza inmutabilidad, lo que hace el código más seguro y predecible.

#### 2. Null Safety - La revolución de Kotlin

```kotlin
// Variable que NO puede ser nula
var nombre: String = "Juan"
// nombre = null  // Error de compilación

// Variable que SÍ puede ser nula
var apellido: String? = null
apellido = "Pérez"  // Válido
apellido = null     // Válido

// Acceso seguro a propiedades
val longitud = apellido?.length  // Retorna Int? (puede ser null)

// Operador Elvis - proporciona valor por defecto
val longitudSegura = apellido?.length ?: 0

// Llamada forzada (usar con precaución)
val longitudForzada = apellido!!.length  // Lanza excepción si es null
```

**Curiosidad técnica**:
El Null Safety de Kotlin elimina el "billion-dollar mistake" (error de mil millones de dólares) mencionado por Tony Hoare, inventor de las referencias null. En Java, los NullPointerException son una de las causas más comunes de crashes.

#### 3. Funciones - Bloques de construcción

```kotlin
// Función básica con cuerpo
fun saludar(nombre: String): String {
    return "Hola, $nombre"
}

// Función de expresión simple
fun multiplicar(a: Int, b: Int): Int = a * b

// Función con parámetros por defecto
fun crearUsuario(nombre: String, edad: Int = 18, activo: Boolean = true): String {
    return "Usuario: $nombre, $edad años, activo: $activo"
}

// Funciones de orden superior (reciben otras funciones)
fun operar(a: Int, b: Int, operacion: (Int, Int) -> Int): Int {
    return operacion(a, b)
}

// Uso:
val resultado = operar(5, 3) { x, y -> x + y }  // Lambda
```

#### 4. Colecciones - El poder de los datos estructurados

**Tabla de métodos esenciales para listas:**

| Método | Descripción | Ejemplo | Resultado |
|--------|-------------|---------|-----------|
| `add(elemento)` | Agrega un elemento | `lista.add("item")` | `true` |
| `remove(elemento)` | Remueve un elemento | `lista.remove("item")` | `true/false` |
| `size` | Tamaño de la lista | `lista.size` | `Int` |
| `isEmpty()` | ¿Está vacía? | `lista.isEmpty()` | `Boolean` |
| `contains(elemento)` | ¿Contiene elemento? | `lista.contains("item")` | `Boolean` |
| `get(indice)` o `[indice]` | Obtiene por índice | `lista[0]` | Elemento |
| `indexOf(elemento)` | Índice del elemento | `lista.indexOf("item")` | `Int` (-1 si no existe) |
| `clear()` | Vacía la lista | `lista.clear()` | `Unit` |

```kotlin
// Lista mutable (se puede modificar)
val prendas = mutableListOf<String>()
prendas.add("Camiseta")
prendas.add("Pantalón")

// Lista inmutable (solo lectura)
val colores = listOf("Rojo", "Azul", "Verde")

// Lista con tipo específico y inicialización
val precios = mutableListOf<Double>(19.99, 29.99, 39.99)
```

**Programación Funcional - Métodos de transformación:**

| Método | Propósito | Entrada | Salida |
|--------|-----------|---------|--------|
| `filter { condición }` | Filtrar elementos | `List<T>` | `List<T>` |
| `map { transformación }` | Transformar elementos | `List<T>` | `List<R>` |
| `forEach { acción }` | Ejecutar acción en cada elemento | `List<T>` | `Unit` |
| `sumOf { expresión }` | Sumar valores calculados | `List<T>` | Número |
| `groupBy { selector }` | Agrupar por criterio | `List<T>` | `Map<K, List<T>>` |
| `sortedBy { selector }` | Ordenar por criterio | `List<T>` | `List<T>` |

```kotlin
val numeros = listOf(1, 2, 3, 4, 5, 6)

// Filtrar números pares
val pares = numeros.filter { it % 2 == 0 }  // [2, 4, 6]

// Transformar cada número multiplicándolo por 2
val dobles = numeros.map { it * 2 }  // [2, 4, 6, 8, 10, 12]

// Sumar todos los números
val suma = numeros.sumOf { it }  // 21

// Encadenar operaciones
val resultado = numeros
    .filter { it > 3 }      // [4, 5, 6]
    .map { it * 2 }         // [8, 10, 12]
    .sumOf { it }           // 30
```

---

## Construyendo OtakuWear paso a paso

### Paso 1: Crear proyecto Maven con Kotlin

1. **Abrir IntelliJ IDEA**
    - En la pantalla de bienvenida, clic en **"New Project"**

2. **Configurar proyecto Maven**
    - **Generators**: Selecciona **"Maven Archetype"**
    - **Name**: `OtakuWear`
    - **Location**: Elige una ubicación (ej: `C:\Proyectos\OtakuWear`)
    - **Language**: **Kotlin**
    - **Build system**: **Maven**
    - **JDK**: Selecciona Java 11 o superior
    - **Add sample code**: Desmarca esta opción
    - Clic **"Create"**

3. **Estructura del proyecto Maven**
   ```
   OtakuWear/
   ├── pom.xml                    (configuración de Maven)
   ├── src/
   │   ├── main/
   │   │   ├── kotlin/            (código fuente)
   │   │   └── resources/         (archivos de recursos)
   │   └── test/
   │       └── kotlin/            (pruebas unitarias)
   └── target/                    (archivos compilados)
   ```

### Paso 2: Configurar el archivo pom.xml

Maven necesita saber cómo compilar Kotlin. Reemplaza el contenido de `pom.xml`:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.otakuwear</groupId>
    <artifactId>otaku-wear</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <kotlin.version>1.9.10</kotlin.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.jetbrains.kotlin</groupId>
            <artifactId>kotlin-stdlib</artifactId>
            <version>${kotlin.version}</version>
        </dependency>
    </dependencies>

    <build>
        <sourceDirectory>../src/main/kotlin</sourceDirectory>
        <testSourceDirectory>src/test/kotlin</testSourceDirectory>
        <plugins>
            <plugin>
                <groupId>org.jetbrains.kotlin</groupId>
                <artifactId>kotlin-maven-plugin</artifactId>
                <version>${kotlin.version}</version>
                <executions>
                    <execution>
                        <id>compile</id>
                        <phase>compile</phase>
                        <goals>
                            <goal>compile</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>
```

**¿Qué hace cada sección?**
- **dependencies**: Librerías que usa nuestro proyecto
- **build**: Configuración de compilación
- **properties**: Variables de configuración

Después de modificar `pom.xml`, IntelliJ mostrará una notificación "Maven projects need to be imported". Haz clic en **"Import Changes"**.

### Paso 3: Crear el archivo principal

1. **Crear la estructura de paquetes**:
    - Clic derecho en `src/main/kotlin`
    - **New** → **Package**
    - Nombre: `com.otakuwear`

2. **Crear el archivo Main**:
    - Clic derecho en `com.otakuwear`
    - **New** → **Kotlin Class/File**
    - Nombre: `Main`
    - Tipo: **File**

### Paso 4: Modelar nuestro dominio - La clase Prenda

**Pilar de POO: Abstracción**
> La abstracción consiste en identificar las características esenciales de un objeto del mundo real y representarlas en código. Una prenda tiene nombre, precio y stock, pero ignoramos detalles como material, color, etc.

Escribe en `Main.kt`:

```kotlin
package com.otakuwear

/**
 * Clase que representa una prenda de vestir en nuestro sistema.
 * 
 * @param nombre Nombre de la prenda (ej: "Camiseta de Naruto")
 * @param precio Precio en dólares (debe ser positivo)
 * @param stock Cantidad disponible (no puede ser negativo)
 */
data class Prenda(
    val nombre: String,
    val precio: Double,
    val stock: Int
) {
    
    /**
     * Valida que los datos de la prenda sean correctos según reglas de negocio
     * @return true si todos los datos son válidos, false en caso contrario
     */
    fun validar(): Boolean {
        return precio > 0 && stock >= 0 && nombre.isNotBlank()
    }

    /**
     * Formatea la información de la prenda para mostrar al usuario
     * @return String con formato "Nombre - Precio: $XX.XX - Stock: XX unidades"
     */
    fun mostrarInfo(): String {
        return "$nombre - Precio: $${precio} - Stock: ${stock} unidades"
    }
}
```

**¿Qué es una Data Class?**

Una `data class` es una característica especial de Kotlin que automáticamente genera:
- `toString()`: Representación en texto del objeto
- `equals()` y `hashCode()`: Para comparar objetos
- `copy()`: Para crear copias modificadas
- `componentN()`: Para destructuring

Ejemplo:
```kotlin
val prenda1 = Prenda("Camiseta", 25.0, 10)
val prenda2 = Prenda("Camiseta", 25.0, 10)

println(prenda1 == prenda2)  // true (equals automático)
println(prenda1)             // Prenda(nombre=Camiseta, precio=25.0, stock=10)

val prendaModificada = prenda1.copy(stock = 5)  // Crea nueva instancia
```

### Paso 5: Gestión de datos - Colecciones mutables

```kotlin
/**
 * Almacén global de todas las prendas del sistema.
 * Usamos MutableList para permitir agregar/quitar prendas dinámicamente.
 */
val almacenPrendas = mutableListOf<Prenda>()
```

**Sabías que...**
> En programación funcional pura, se evitan las variables globales mutables. Sin embargo, para aplicaciones simples como esta, es una solución pragmática. En aplicaciones más complejas, usaríamos patrones como Repository o DAO.

**Comparación de tipos de listas:**

| Tipo | Mutabilidad | Cuándo usar | Métodos disponibles |
|------|-------------|-------------|-------------------|
| `List<T>` | Inmutable | Datos que no cambian | `get`, `size`, `contains`, `filter`, `map` |
| `MutableList<T>` | Mutable | Datos que se modifican | Todo lo anterior + `add`, `remove`, `clear` |
| `ArrayList<T>` | Mutable | Acceso rápido por índice | Implementación específica de MutableList |
| `LinkedList<T>` | Mutable | Inserciones/eliminaciones frecuentes | Mejor para modificaciones en el medio |

### Paso 6: Interfaz de usuario - Sistema de menús

```kotlin
/**
 * Muestra el menú principal de opciones al usuario
 */
fun mostrarMenu() {
    println("\n" + "=".repeat(40))
    println("    OTAKUWEAR - GESTIÓN DE INVENTARIO")
    println("=".repeat(40))
    println("1. Registrar nueva prenda")
    println("2. Ver inventario completo")
    println("3. Filtrar prendas premium (> $1000)")
    println("4. Calcular valor total del inventario")
    println("5. Salir del sistema")
    println("-".repeat(40))
    print("Selecciona una opción [1-5]: ")
}
```

**Principio de diseño aplicado:**
> **Single Responsibility Principle (SRP)**: Cada función tiene una única responsabilidad. `mostrarMenu()` solo se encarga de mostrar opciones, no de procesarlas.

### Paso 7: Captura y validación de datos

```kotlin
/**
 * Registra una nueva prenda en el sistema con validación completa
 */
fun registrarPrenda() {
    println("\n--- REGISTRO DE NUEVA PRENDA ---")

    try {
        // Captura del nombre con validación
        print("Nombre de la prenda: ")
        val nombre = readLine()?.trim() ?: ""
        
        if (nombre.isBlank()) {
            println("[ERROR] El nombre es obligatorio")
            return
        }

        // Captura del precio con validación numérica
        print("Precio en dólares: $")
        val precioTexto = readLine()?.trim() ?: "0"
        val precio = precioTexto.toDoubleOrNull()
        
        if (precio == null || precio <= 0) {
            println("[ERROR] El precio debe ser un número positivo")
            return
        }

        // Captura del stock con validación numérica
        print("Cantidad en stock: ")
        val stockTexto = readLine()?.trim() ?: "0"
        val stock = stockTexto.toIntOrNull()
        
        if (stock == null || stock < 0) {
            println("[ERROR] El stock debe ser un número no negativo")
            return
        }

        // Crear y validar la prenda
        val nuevaPrenda = Prenda(nombre, precio, stock)
        
        if (nuevaPrenda.validar()) {
            almacenPrendas.add(nuevaPrenda)
            println("\n[ÉXITO] Prenda registrada:")
            println("-> ${nuevaPrenda.mostrarInfo()}")
        } else {
            println("[ERROR] Los datos de la prenda no son válidos")
        }

    } catch (excepcion: Exception) {
        println("[ERROR INESPERADO] ${excepcion.message}")
        println("Por favor, intenta nuevamente")
    }
}
```

**Manejo de excepciones en profundidad:**

```kotlin
// Jerarquía de excepciones en Kotlin
try {
    val numero = "abc".toInt()  // NumberFormatException
} catch (e: NumberFormatException) {
    println("No es un número válido")
} catch (e: Exception) {
    println("Error general: ${e.message}")
} finally {
    println("Este bloque siempre se ejecuta")
}
```

**Métodos útiles para validación de String:**

| Método | Descripción | Ejemplo |
|--------|-------------|---------|
| `isBlank()` | ¿Está vacío o solo espacios? | `"   ".isBlank()` → `true` |
| `isEmpty()` | ¿Está completamente vacío? | `"".isEmpty()` → `true` |
| `trim()` | Elimina espacios al inicio/final | `" hola ".trim()` → `"hola"` |
| `isNotBlank()` | Contrario de `isBlank()` | `"hola".isNotBlank()` → `true` |
| `toDoubleOrNull()` | Convierte a Double o null | `"12.5".toDoubleOrNull()` → `12.5` |

### Paso 8: Visualización de datos

```kotlin
/**
 * Muestra todas las prendas registradas en el sistema
 */
fun mostrarInventarioCompleto() {
    println("\n--- INVENTARIO COMPLETO ---")
    
    if (almacenPrendas.isEmpty()) {
        println("No hay prendas registradas en el sistema.")
        println("Usa la opción 1 para agregar la primera prenda.")
        return
    }

    println("Total de prendas: ${almacenPrendas.size}")
    println("-".repeat(50))
    
    almacenPrendas.forEachIndexed { indice, prenda ->
        val numero = indice + 1
        println("$numero. ${prenda.mostrarInfo()}")
    }
    
    println("-".repeat(50))
}
```

**Funciones de iteración con índice:**

| Función | Propósito | Parámetros lambda |
|---------|-----------|------------------|
| `forEach { }` | Ejecuta acción en cada elemento | `elemento` |
| `forEachIndexed { }` | Ejecuta acción con índice | `índice, elemento` |
| `mapIndexed { }` | Transforma con índice | `índice, elemento` → nuevo valor |
| `filterIndexed { }` | Filtra con índice | `índice, elemento` → Boolean |

### Paso 9: Programación funcional aplicada

```kotlin
/**
 * Filtra y muestra las prendas premium (precio mayor a $1000)
 * Demuestra el uso de programación funcional
 */
fun mostrarPrendasPremium() {
    println("\n--- PRENDAS PREMIUM (> $1000) ---")
    
    val prendasPremium = almacenPrendas.filter { prenda -> 
        prenda.precio > 1000.0 
    }
    
    if (prendasPremium.isEmpty()) {
        println("No hay prendas premium registradas.")
        return
    }

    println("Se encontraron ${prendasPremium.size} prendas premium:")
    println("-".repeat(50))
    
    prendasPremium
        .sortedByDescending { it.precio }  // Ordenar por precio descendente
        .forEachIndexed { indice, prenda ->
            println("${indice + 1}. ${prenda.mostrarInfo()}")
        }
}
```

**Programación Funcional vs Imperativa:**

```kotlin
// ESTILO IMPERATIVO (tradicional)
val prendasCaras = mutableListOf<Prenda>()
for (prenda in almacenPrendas) {
    if (prenda.precio > 1000) {
        prendasCaras.add(prenda)
    }
}

// ESTILO FUNCIONAL (más expresivo)
val prendasCaras = almacenPrendas.filter { it.precio > 1000 }
```

**Ventajas del estilo funcional:**
- Más conciso y legible
- Menos propenso a errores
- Facilita el paralelismo
- Expresivo: describe QUÉ queremos, no CÓMO

### Paso 10: Operaciones agregadas complejas

```kotlin
/**
 * Calcula métricas financieras del inventario
 * Demuestra operaciones agregadas avanzadas
 */
fun calcularMetricasInventario() {
    println("\n--- MÉTRICAS DEL INVENTARIO ---")
    
    if (almacenPrendas.isEmpty()) {
        println("No hay datos para calcular métricas.")
        return
    }

    // Valor total del inventario (precio × stock por cada prenda)
    val valorTotal = almacenPrendas.sumOf { prenda -> 
        prenda.precio * prenda.stock 
    }

    // Prenda más cara
    val prendaMasCara = almacenPrendas.maxByOrNull { it.precio }

    // Prenda más barata
    val prendaMasBarata = almacenPrendas.minByOrNull { it.precio }

    // Precio promedio
    val precioPromedio = almacenPrendas.map { it.precio }.average()

    // Stock total
    val stockTotal = almacenPrendas.sumOf { it.stock }

    // Mostrar resultados
    println("Valor total del inventario: $${String.format("%.2f", valorTotal)}")
    println("Precio promedio: $${String.format("%.2f", precioPromedio)}")
    println("Stock total: $stockTotal unidades")
    println()
    println("Prenda más cara: ${prendaMasCara?.mostrarInfo() ?: "N/A"}")
    println("Prenda más barata: ${prendaMasBarata?.mostrarInfo() ?: "N/A"}")
}
```

**Funciones agregadas importantes:**

| Función | Descripción | Tipo retorno | Ejemplo |
|---------|-------------|--------------|---------|
| `sumOf { }` | Suma valores calculados | Número | `sumOf { it.precio }` |
| `maxByOrNull { }` | Elemento con valor máximo | `T?` | `maxByOrNull { it.precio }` |
| `minByOrNull { }` | Elemento con valor mínimo | `T?` | `minByOrNull { it.stock }` |
| `average()` | Promedio aritmético | Double | `map { it.precio }.average()` |
| `count { }` | Cuenta elementos que cumplen condición | Int | `count { it.precio > 100 }` |

**Sabías que...**
> Las funciones que terminan en `OrNull` devuelven `null` si la lista está vacía, evitando excepciones. Esto es parte del sistema de null safety de Kotlin.

### Paso 11: Control de flujo y bucles

```kotlin
/**
 * Función principal que controla el flujo del programa
 * Demuestra estructuras de control avanzadas
 */
fun main() {
    // Mensaje de bienvenida
    println("*".repeat(50))
    println("       BIENVENIDO A OTAKUWEAR")
    println("    Sistema de Gestión de Inventario")
    println("*".repeat(50))

    var sistemaActivo = true

    // Bucle principal del programa
    while (sistemaActivo) {
        mostrarMenu()
        
        // Captura de opción con validación
        val opcionTexto = readLine()?.trim() ?: ""
        
        // Procesamiento de la opción seleccionada
        when (opcionTexto) {
            "1" -> {
                registrarPrenda()
            }
            "2" -> {
                mostrarInventarioCompleto()
            }
            "3" -> {
                mostrarPrendasPremium()
            }
            "4" -> {
                calcularMetricasInventario()
            }
            "5" -> {
                println("\nGracias por usar OtakuWear")
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
```

**Estructuras de control en Kotlin:**

| Estructura | Sintaxis | Cuándo usar |
|-----------|----------|-------------|
| `if-else` | `if (condición) { } else { }` | Decisiones simples |
| `when` | `when (valor) { caso -> acción }` | Múltiples opciones (switch mejorado) |
| `while` | `while (condición) { }` | Repetir mientras sea verdadero |
| `for` | `for (item in colección) { }` | Iterar sobre colecciones |
| `repeat` | `repeat(n) { }` | Repetir n veces |

**When avanzado:**
```kotlin
// Con rangos
when (edad) {
    in 0..17 -> "Menor de edad"
    in 18..64 -> "Adulto"
    in 65..100 -> "Adulto mayor"
    else -> "Edad inválida"
}

// Con tipos
when (objeto) {
    is String -> "Es texto"
    is Int -> "Es número"
    else -> "Tipo desconocido"
}

// Sin parámetro (como if-else múltiple)
when {
    precio < 50 -> "Económico"
    precio < 200 -> "Moderado"
    else -> "Premium"
}
```

### Paso 12: Compilar y ejecutar

1. **Compilar el proyecto**:
    - Terminal en IntelliJ: `View` → `Tool Windows` → `Terminal`
    - Ejecuta: `mvn compile`
    - Deberías ver: `BUILD SUCCESS`

2. **Ejecutar el programa**:
    - Clic en el ícono de play verde junto a `fun main()`
    - O usa `Ctrl+Shift+F10` (Windows) / `Cmd+Shift+R` (Mac)
    - O desde terminal: `mvn exec:java -Dexec.mainClass="com.otakuwear.MainKt"`

3. **Verificar que funciona correctamente**:
    - Deberías ver el menú principal
    - Prueba registrar al menos 3 prendas:
        - Una económica (< $100)
        - Una moderada ($100-$1000)
        - Una premium (> $1000)

---

## Conceptos avanzados explicados

### 1. Principios SOLID aplicados en nuestro código

**Single Responsibility Principle (SRP)**
Cada función tiene una única responsabilidad:
- `mostrarMenu()` - Solo mostrar opciones
- `registrarPrenda()` - Solo registrar prendas
- `validar()` - Solo validar datos

**Open/Closed Principle aplicado:**
```kotlin
// Extensible sin modificar código existente
fun Prenda.esLimitada(): Boolean = stock < 5
fun Prenda.aplicarDescuento(porcentaje: Double): Prenda = 
    copy(precio = precio * (1 - porcentaje / 100))

// Uso:
val prenda = Prenda("Camiseta", 100.0, 3)
if (prenda.esLimitada()) {
    val prendaConDescuento = prenda.aplicarDescuento(10.0)
}
```

### 2. Patrones de diseño implementados

**Data Transfer Object (DTO)**
La clase `Prenda` actúa como DTO:
```kotlin
// Transferencia de datos entre capas
data class Prenda(
    val nombre: String,
    val precio: Double,
    val stock: Int
)
```

**Repository Pattern (simulado)**
Nuestro `almacenPrendas` simula un repositorio:
```kotlin
// En una aplicación real, sería algo así:
interface PrendaRepository {
    fun guardar(prenda: Prenda): Boolean
    fun obtenerTodas(): List<Prenda>
    fun filtrarPorPrecio(precioMinimo: Double): List<Prenda>
}

class PrendaRepositoryImpl : PrendaRepository {
    private val almacen = mutableListOf<Prenda>()
    
    override fun guardar(prenda: Prenda): Boolean {
        return almacen.add(prenda)
    }
    
    override fun obtenerTodas(): List<Prenda> = almacen.toList()
    
    override fun filtrarPorPrecio(precioMinimo: Double): List<Prenda> =
        almacen.filter { it.precio >= precioMinimo }
}
```

### 3. Programación funcional vs imperativa en detalle

**Transformaciones de datos:**

```kotlin
// IMPERATIVO: Modificamos estado paso a paso
fun calcularEstadisticasImperativo(prendas: List<Prenda>): Map<String, Any> {
    var totalValor = 0.0
    var totalStock = 0
    var prendasCaras = 0
    
    for (prenda in prendas) {
        totalValor += prenda.precio * prenda.stock
        totalStock += prenda.stock
        if (prenda.precio > 1000) {
            prendasCaras++
        }
    }
    
    return mapOf(
        "valorTotal" to totalValor,
        "stockTotal" to totalStock,
        "prendasCaras" to prendasCaras
    )
}

// FUNCIONAL: Expresamos transformaciones declarativamente
fun calcularEstadisticasFuncional(prendas: List<Prenda>): Map<String, Any> {
    return mapOf(
        "valorTotal" to prendas.sumOf { it.precio * it.stock },
        "stockTotal" to prendas.sumOf { it.stock },
        "prendasCaras" to prendas.count { it.precio > 1000 }
    )
}
```

**Ventajas del enfoque funcional:**
- Menos líneas de código
- Menos posibilidad de errores
- Más legible y expresivo
- Facilita testing y debugging

### 4. Sistema de tipos avanzado de Kotlin

**Type Inference (inferencia de tipos):**
```kotlin
// Kotlin infiere los tipos automáticamente
val nombre = "Juan"        // String inferido
val precio = 29.99         // Double inferido
val activo = true          // Boolean inferido

// Equivale a escribir:
val nombre: String = "Juan"
val precio: Double = 29.99
val activo: Boolean = true
```

**Smart Casts:**
```kotlin
fun procesarEntrada(entrada: Any) {
    if (entrada is String) {
        // Kotlin sabe que aquí entrada es String
        println("Longitud: ${entrada.length}")
        println("Mayúsculas: ${entrada.uppercase()}")
    } else if (entrada is Int) {
        // Aquí entrada es Int automáticamente
        println("Doble: ${entrada * 2}")
    }
}
```

**Generics (Genéricos):**
```kotlin
// Clase genérica para almacenar cualquier tipo
class Almacen<T>(private val items: MutableList<T> = mutableListOf()) {
    
    fun agregar(item: T) = items.add(item)
    
    fun obtenerTodos(): List<T> = items.toList()
    
    fun filtrar(predicado: (T) -> Boolean): List<T> = items.filter(predicado)
    
    fun <R> mapear(transformacion: (T) -> R): List<R> = items.map(transformacion)
}

// Uso específico
val almacenPrendas = Almacen<Prenda>()
almacenPrendas.agregar(Prenda("Camiseta", 25.0, 10))

val almacenTextos = Almacen<String>()
almacenTextos.agregar("Hola mundo")
```

### 5. Manejo de errores profesional

**Jerarquía de excepciones personalizada:**
```kotlin
// Excepciones específicas del dominio
sealed class PrendaException(message: String) : Exception(message)

class PrecioInvalidoException(precio: Double) : 
    PrendaException("Precio inválido: $precio. Debe ser mayor a 0")

class StockNegativoException(stock: Int) : 
    PrendaException("Stock inválido: $stock. No puede ser negativo")

class NombreVacioException : 
    PrendaException("El nombre de la prenda no puede estar vacío")

// Función de validación mejorada
fun Prenda.validarConExcepciones() {
    when {
        nombre.isBlank() -> throw NombreVacioException()
        precio <= 0 -> throw PrecioInvalidoException(precio)
        stock < 0 -> throw StockNegativoException(stock)
    }
}

// Uso con manejo específico
fun registrarPrendaSegura() {
    try {
        val prenda = Prenda("", -10.0, -5)
        prenda.validarConExcepciones()
        almacenPrendas.add(prenda)
        
    } catch (e: NombreVacioException) {
        println("Error de nombre: ${e.message}")
    } catch (e: PrecioInvalidoException) {
        println("Error de precio: ${e.message}")
    } catch (e: StockNegativoException) {
        println("Error de stock: ${e.message}")
    }
}
```

**Result Type (manejo sin excepciones):**
```kotlin
// Enfoque funcional para manejo de errores
sealed class ResultadoValidacion<out T> {
    data class Exito<T>(val valor: T) : ResultadoValidacion<T>()
    data class Error(val mensaje: String) : ResultadoValidacion<Nothing>()
}

fun validarPrendaFuncional(nombre: String, precio: Double, stock: Int): ResultadoValidacion<Prenda> {
    return when {
        nombre.isBlank() -> ResultadoValidacion.Error("Nombre vacío")
        precio <= 0 -> ResultadoValidacion.Error("Precio inválido: $precio")
        stock < 0 -> ResultadoValidacion.Error("Stock inválido: $stock")
        else -> ResultadoValidacion.Exito(Prenda(nombre, precio, stock))
    }
}

// Uso funcional
fun procesarPrendaSegura() {
    val resultado = validarPrendaFuncional("Camiseta", 25.0, 10)
    
    when (resultado) {
        is ResultadoValidacion.Exito -> {
            almacenPrendas.add(resultado.valor)
            println("Prenda agregada exitosamente")
        }
        is ResultadoValidacion.Error -> {
            println("Error: ${resultado.mensaje}")
        }
    }
}
```

### 6. Extensiones y DSLs

**Funciones de extensión:**
```kotlin
// Extender tipos existentes
fun String.esPrecioValido(): Boolean {
    val numero = this.toDoubleOrNull()
    return numero != null && numero > 0
}

fun List<Prenda>.valorTotalInventario(): Double =
    sumOf { it.precio * it.stock }

fun List<Prenda>.groupByPriceRange(): Map<String, List<Prenda>> =
    groupBy { prenda ->
        when {
            prenda.precio < 100 -> "Económico"
            prenda.precio < 500 -> "Moderado"
            else -> "Premium"
        }
    }

// Uso natural
val precio = "29.99"
if (precio.esPrecioValido()) {
    println("Precio válido")
}

val total = almacenPrendas.valorTotalInventario()
val agrupadas = almacenPrendas.groupByPriceRange()
```

**Domain Specific Language (DSL) básico:**
```kotlin
// DSL para crear prendas de forma más expresiva
class PrendaBuilder {
    var nombre: String = ""
    var precio: Double = 0.0
    var stock: Int = 0
    
    fun build(): Prenda = Prenda(nombre, precio, stock)
}

fun prenda(init: PrendaBuilder.() -> Unit): Prenda {
    val builder = PrendaBuilder()
    builder.init()
    return builder.build()
}

// Uso del DSL
val camiseta = prenda {
    nombre = "Camiseta de Naruto"
    precio = 29.99
    stock = 15
}

val pantalon = prenda {
    nombre = "Pantalón cargo"
    precio = 89.99
    stock = 8
}
```

---

## Ejercicios de práctica

### Nivel Principiante

**Ejercicio 1: Categorías de productos**
Modifica la clase `Prenda` para incluir una categoría (enum):

```kotlin
enum class CategoriaPrenda(val descripcion: String) {
    CAMISETA("Camisetas y tops"),
    PANTALON("Pantalones y shorts"),
    CALZADO("Zapatos y zapatillas"),
    ACCESORIO("Accesorios diversos"),
    CHAQUETA("Chaquetas y abrigos")
}

data class Prenda(
    val nombre: String,
    val precio: Double,
    val stock: Int,
    val categoria: CategoriaPrenda
) {
    // Resto del código...
}
```

Implementa:
- Función para filtrar por categoría
- Estadísticas por categoría
- Validación de categoría

**Ejercicio 2: Sistema de descuentos**
Implementa diferentes tipos de descuentos:

```kotlin
sealed class TipoDescuento(val porcentaje: Double) {
    object SinDescuento : TipoDescuento(0.0)
    object DescuentoEstudiantil : TipoDescuento(15.0)
    object DescuentoTercerEdad : TipoDescuento(20.0)
    class DescuentoPersonalizado(porcentaje: Double) : TipoDescuento(porcentaje)
}

fun Prenda.aplicarDescuento(tipo: TipoDescuento): Double {
    return precio * (1 - tipo.porcentaje / 100)
}
```

**Ejercicio 3: Búsqueda y ordenamiento**
Implementa funciones de búsqueda:

```kotlin
fun buscarPorNombre(termino: String): List<Prenda> {
    return almacenPrendas.filter { 
        it.nombre.contains(termino, ignoreCase = true) 
    }
}

fun ordenarPor(criterio: String): List<Prenda> {
    return when (criterio.lowercase()) {
        "nombre" -> almacenPrendas.sortedBy { it.nombre }
        "precio" -> almacenPrendas.sortedBy { it.precio }
        "stock" -> almacenPrendas.sortedBy { it.stock }
        else -> almacenPrendas
    }
}
```

### Nivel Intermedio

**Ejercicio 4: Sistema de tallas**
Crea un sistema complejo de tallas:

```kotlin
enum class Talla(val codigo: String, val orden: Int) {
    XS("XS", 1),
    S("S", 2),
    M("M", 3),
    L("L", 4),
    XL("XL", 5),
    XXL("XXL", 6)
}

data class StockPorTalla(val talla: Talla, val cantidad: Int)

data class PrendaConTallas(
    val nombre: String,
    val precio: Double,
    val stocks: Map<Talla, Int>
) {
    fun stockTotal(): Int = stocks.values.sum()
    
    fun tallasDisponibles(): List<Talla> = 
        stocks.filter { it.value > 0 }.keys.sorted()
    
    fun tallaConMayorStock(): Talla? = 
        stocks.maxByOrNull { it.value }?.key
}
```

**Ejercicio 5: Historial de operaciones**
Implementa un sistema de auditoría:

```kotlin
sealed class TipoOperacion {
    object Registro : TipoOperacion()
    object Eliminacion : TipoOperacion()
    data class ModificacionStock(val stockAnterior: Int, val stockNuevo: Int) : TipoOperacion()
    data class ModificacionPrecio(val precioAnterior: Double, val precioNuevo: Double) : TipoOperacion()
}

data class RegistroOperacion(
    val timestamp: Long,
    val operacion: TipoOperacion,
    val prenda: String,
    val usuario: String = "Sistema"
)

object AuditoriaService {
    private val historial = mutableListOf<RegistroOperacion>()
    
    fun registrarOperacion(operacion: TipoOperacion, nombrePrenda: String) {
        historial.add(
            RegistroOperacion(
                timestamp = System.currentTimeMillis(),
                operacion = operacion,
                prenda = nombrePrenda
            )
        )
    }
    
    fun obtenerHistorial(): List<RegistroOperacion> = historial.toList()
    
    fun filtrarPorTipo(tipo: TipoOperacion::class): List<RegistroOperacion> =
        historial.filter { it.operacion::class == tipo }
}
```

**Ejercicio 6: Validaciones avanzadas**
Sistema de validación por capas:

```kotlin
interface ValidadorPrenda {
    fun validar(prenda: Prenda): List<String>
}

class ValidadorNombre : ValidadorPrenda {
    override fun validar(prenda: Prenda): List<String> {
        val errores = mutableListOf<String>()
        
        if (prenda.nombre.isBlank()) {
            errores.add("El nombre es obligatorio")
        }
        
        if (prenda.nombre.length < 3) {
            errores.add("El nombre debe tener al menos 3 caracteres")
        }
        
        if (prenda.nombre.length > 100) {
            errores.add("El nombre no puede exceder 100 caracteres")
        }
        
        return errores
    }
}

class ValidadorPrecio : ValidadorPrenda {
    override fun validar(prenda: Prenda): List<String> {
        val errores = mutableListOf<String>()
        
        if (prenda.precio <= 0) {
            errores.add("El precio debe ser positivo")
        }
        
        if (prenda.precio > 10000) {
            errores.add("El precio parece excesivamente alto")
        }
        
        return errores
    }
}

class ValidadorComposite(private val validadores: List<ValidadorPrenda>) : ValidadorPrenda {
    override fun validar(prenda: Prenda): List<String> {
        return validadores.flatMap { it.validar(prenda) }
    }
}

// Uso
val validadorCompleto = ValidadorComposite(
    listOf(ValidadorNombre(), ValidadorPrecio())
)

fun validarPrendaCompleta(prenda: Prenda): Boolean {
    val errores = validadorCompleto.validar(prenda)
    
    if (errores.isNotEmpty()) {
        println("Errores de validación:")
        errores.forEach { println("- $it") }
        return false
    }
    
    return true
}
```

### Nivel Avanzado

**Ejercicio 7: Persistencia en archivos**
Sistema de persistencia completo:

```kotlin
import java.io.File
import java.io.IOException

object PersistenciaService {
    private const val ARCHIVO_DATOS = "prendas.txt"
    private const val SEPARADOR = "|"
    
    fun guardarPrendas(prendas: List<Prenda>): Boolean {
        return try {
            val contenido = prendas.joinToString("\n") { prenda ->
                "${prenda.nombre}$SEPARADOR${prenda.precio}$SEPARADOR${prenda.stock}"
            }
            
            File(ARCHIVO_DATOS).writeText(contenido)
            println("Datos guardados exitosamente en $ARCHIVO_DATOS")
            true
            
        } catch (e: IOException) {
            println("Error al guardar: ${e.message}")
            false
        }
    }
    
    fun cargarPrendas(): List<Prenda> {
        return try {
            if (!File(ARCHIVO_DATOS).exists()) {
                println("No existe archivo de datos previo")
                return emptyList()
            }
            
            File(ARCHIVO_DATOS)
                .readLines()
                .filter { it.isNotBlank() }
                .mapNotNull { linea ->
                    val partes = linea.split(SEPARADOR)
                    if (partes.size == 3) {
                        val nombre = partes[0]
                        val precio = partes[1].toDoubleOrNull() ?: return@mapNotNull null
                        val stock = partes[2].toIntOrNull() ?: return@mapNotNull null
                        Prenda(nombre, precio, stock)
                    } else null
                }
                .also { println("Cargadas ${it.size} prendas desde archivo") }
                
        } catch (e: IOException) {
            println("Error al cargar: ${e.message}")
            emptyList()
        }
    }
}

// Modificar main() para usar persistencia
fun main() {
    // Cargar datos al inicio
    almacenPrendas.addAll(PersistenciaService.cargarPrendas())
    
    // ... resto del programa ...
    
    // Guardar al salir
    println("\nGuardando datos...")
    PersistenciaService.guardarPrendas(almacenPrendas)
}
```

**Ejercicio 8: Sistema de reportes**
Generador de reportes complejo:

```kotlin
data class ReporteVentas(
    val fechaGeneracion: String,
    val totalPrendas: Int,
    val valorTotalInventario: Double,
    val prendaPorCategoria: Map<CategoriaPrenda, Int>,
    val top5PrendasCaras: List<Prenda>,
    val prendasBajoStock: List<Prenda>
)

object GeneradorReportes {
    fun generarReporteCompleto(prendas: List<Prenda>): ReporteVentas {
        return ReporteVentas(
            fechaGeneracion = java.time.LocalDateTime.now().toString(),
            totalPrendas = prendas.size,
            valorTotalInventario = prendas.sumOf { it.precio * it.stock },
            prendaPorCategoria = prendas.groupingBy { it.categoria }.eachCount(),
            top5PrendasCaras = prendas.sortedByDescending { it.precio }.take(5),
            prendasBajoStock = prendas.filter { it.stock < 10 }
        )
    }
    
    fun exportarReporteTexto(reporte: ReporteVentas): String {
        return buildString {
            appendLine("=== REPORTE DE INVENTARIO ===")
            appendLine("Generado: ${reporte.fechaGeneracion}")
            appendLine()
            
            appendLine("RESUMEN GENERAL:")
            appendLine("- Total prendas: ${reporte.totalPrendas}")
            appendLine("- Valor inventario: ${String.format("%.2f", reporte.valorTotalInventario)}")
            appendLine()
            
            appendLine("PRENDAS POR CATEGORÍA:")
            reporte.prendaPorCategoria.forEach { (categoria, cantidad) ->
                appendLine("- ${categoria.descripcion}: $cantidad prendas")
            }
            appendLine()
            
            if (reporte.top5PrendasCaras.isNotEmpty()) {
                appendLine("TOP 5 PRENDAS MÁS CARAS:")
                reporte.top5PrendasCaras.forEachIndexed { index, prenda ->
                    appendLine("${index + 1}. ${prenda.mostrarInfo()}")
                }
                appendLine()
            }
            
            if (reporte.prendasBajoStock.isNotEmpty()) {
                appendLine("PRENDAS CON BAJO STOCK (<10):")
                reporte.prendasBajoStock.forEach { prenda ->
                    appendLine("- ${prenda.mostrarInfo()}")
                }
            }
        }
    }
}
```

---

## Próximos pasos

### Tecnologías para aprender después

**1. Frameworks Web con Kotlin:**
- **Ktor**: Framework asíncrono para APIs y web apps
- **Spring Boot**: Framework empresarial con soporte Kotlin
- **http4k**: Framework funcional para HTTP

**2. Desarrollo Android:**
- **Jetpack Compose**: UI moderna declarativa
- **Room**: ORM para SQLite
- **Retrofit**: Cliente HTTP para APIs

**3. Programación multiplataforma:**
- **Kotlin Multiplatform**: Código compartido entre plataformas
- **Compose Multiplatform**: UI multiplataforma

**4. Programación asíncrona:**
- **Coroutines**: Programación asíncrona nativa
- **Flow**: Streams reactivos de datos

### Proyectos prácticos sugeridos

**Nivel Intermedio:**
1. **Sistema de biblioteca**: Préstamos, usuarios, multas
2. **Calculadora científica**: Operaciones complejas, historial
3. **Juego de trivia**: Preguntas, puntuación, rankings

**Nivel Avanzado:**
1. **API REST completa**: CRUD con base de datos
2. **Chat en tiempo real**: WebSockets, múltiples usuarios
3. **E-commerce básico**: Carrito, pagos, inventario

### Recursos de aprendizaje recomendados

**Documentación oficial:**
- Kotlin Language Reference: https://kotlinlang.org/docs/
- Kotlin Koans (ejercicios): https://play.kotlinlang.org/koans/

**Libros recomendados:**
- "Kotlin in Action" by Dmitry Jemerov
- "Programming Kotlin" by Venkat Subramaniam
- "Head First Kotlin" by Dawn Griffiths

**Comunidades y recursos:**
- r/Kotlin (Reddit)
- Kotlin Slack community
- KotlinConf talks (YouTube)

### Certificaciones y cursos

1. **JetBrains Academy**: Cursos interactivos de Kotlin
2. **Coursera**: "Kotlin for Java Developers"
3. **Udacity**: "Developing Android Apps with Kotlin"

---

## Felicitaciones

Has completado un tutorial exhaustivo de Kotlin que te ha llevado desde los conceptos más básicos hasta técnicas avanzadas de programación. Ahora dominas:

**Conceptos fundamentales:**
- Sintaxis de Kotlin y sistema de tipos
- Null safety y manejo seguro de datos
- Funciones y programación funcional
- Clases y programación orientada a objetos

**Conceptos avanzados:**
- Patrones de diseño y principios SOLID
- Manejo profesional de errores
- Programación funcional avanzada
- Extensiones y DSLs básicos

**Herramientas y buenas prácticas:**
- Maven como sistema de build
- IntelliJ IDEA como IDE profesional
- Estructuras de datos y algoritmos
- Testing y debugging
**: La programación es una habilidad que se perfecciona con la práctica constante. Construye proyectos que te apasionen, contribuye a proyectos open source, y mantente actualizado con las nuevas características del lenguaje.