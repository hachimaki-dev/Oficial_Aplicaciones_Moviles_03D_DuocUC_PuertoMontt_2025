# Puerto Frutal SPA - Sistema de Gestión de Inventario

## Descripción del Proyecto

Este proyecto implementa un sistema de gestión de inventario para la empresa Puerto Frutal SPA, permitiendo el registro y control de diferentes tipos de frutas (locales y tropicales). La aplicación está desarrollada en Kotlin y utiliza conceptos de programación orientada a objetos como herencia, polimorfismo y manejo de colecciones.

## Estructura del Proyecto

```
src/main/kotlin/
├── Main.kt                           # Punto de entrada principal
├── com/puertoFrutal/
│   ├── MainPuertoFrutal.kt           # Implementación del menú y funcionalidades
│   └── model/
│       ├── Fruta.kt                  # Clase abstracta base
│       ├── FrutaLocal.kt             # Subclase para frutas locales
│       └── FrutaTropical.kt          # Subclase para frutas tropicales
```

## Características Implementadas

1. **Modelo de Datos**:
   - Clase abstracta `Fruta` con propiedades comunes y métodos
   - Subclases `FrutaLocal` y `FrutaTropical` con implementaciones específicas
   - Polimorfismo a través del método `descripcion()`

2. **Funcionalidades**:
   - Registro de nuevas frutas con validaciones
   - Visualización de todas las frutas registradas
   - Filtrado de frutas por precio (> $1000)
   - Cálculo de métricas del inventario

3. **Manejo de Excepciones**:
   - Validación de datos de entrada
   - Captura y manejo de excepciones

4. **Interfaz de Usuario**:
   - Menú interactivo por consola
   - Presentación tabular de datos
   - Mensajes informativos y de error

## Diagrama de Clases

```
+-------------------+
|      Fruta        | (abstracta)
+-------------------+
| - nombre: String  |
| - precioPorKilo: Double |
| - stockKilos: Double |
+-------------------+
| + descripcion(): String | (abstracto)
| + validar(): Boolean |
| + valorTotal(): Double |
+-------------------+
         ^
         |
         |
+--------+--------+
|                 |
|                 |
+----------+      +----------+
| FrutaLocal |      | FrutaTropical |
+----------+      +----------+
| + descripcion(): String | | + descripcion(): String |
| + zonaProduccion(): String | | + paisOrigen(): String |
+----------+      +----------+
```

## Cómo Ejecutar el Proyecto

1. Asegúrate de tener instalado Kotlin y JDK (versión 8 o superior)
2. Clona este repositorio
3. Navega hasta el directorio del proyecto
4. Ejecuta el proyecto con el comando:
   ```
   kotlinc -cp . -include-runtime -d puerto-frutal.jar src/main/kotlin/*.kt src/main/kotlin/com/puertoFrutal/*.kt src/main/kotlin/com/puertoFrutal/model/*.kt
   java -jar puerto-frutal.jar
   ```

## Funcionalidades del Menú

1. **Registrar nueva fruta**:
   - Permite ingresar nombre, precio por kilo y stock
   - Seleccionar tipo (local o tropical)
   - Validación de datos

2. **Mostrar todas las frutas**:
   - Muestra una tabla con todas las frutas registradas
   - Incluye nombre, precio, stock y valor total

3. **Mostrar frutas caras (> $1000)**:
   - Filtra y muestra las frutas con precio mayor a $1000
   - Ordenadas por precio descendente

4. **Calcular métricas del inventario**:
   - Valor total del inventario
   - Precio promedio por kilo
   - Stock total en kilos
   - Fruta más cara y más barata
   - Fruta con mayor y menor stock
   - Distribución por tipo de fruta

5. **Salir**:
   - Finaliza la ejecución del programa

## Implementación de Requerimientos

### Herencia y Polimorfismo

Se implementó una jerarquía de clases con `Fruta` como clase abstracta base y dos subclases concretas: `FrutaLocal` y `FrutaTropical`. El polimorfismo se aplica a través del método abstracto `descripcion()` que cada subclase implementa de manera diferente.

### Manejo de Colecciones

Se utiliza una lista mutable (`MutableList<Fruta>`) para almacenar el inventario de frutas. Se implementan operaciones como:
- Agregar elementos (`.add()`)
- Filtrar elementos (`.filter()`)
- Ordenar elementos (`.sortedByDescending()`)
- Calcular estadísticas (`.sumOf()`, `.average()`, `.maxByOrNull()`, etc.)

### Validaciones y Excepciones

Se implementan validaciones para los datos de entrada:
- Nombre no vacío
- Precio mayor que cero
- Stock no negativo
- Tipo de fruta válido

Se utilizan bloques `try-catch` para manejar excepciones como `NumberFormatException` e `IllegalArgumentException`.

## Conclusión

Este proyecto demuestra la aplicación de conceptos fundamentales de programación orientada a objetos en Kotlin, como herencia, polimorfismo, manejo de colecciones y excepciones, para crear una aplicación funcional de gestión de inventario.
