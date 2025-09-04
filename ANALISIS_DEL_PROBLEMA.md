# ANÁLISIS DEL PROBLEMA Y SOLUCIÓN LÓGICA DE OTAKUWEAR

## 1. ANÁLISIS DEL PROBLEMA Y SOLUCIÓN LÓGICA

### 1.1 Identificación del Problema Central

**Problema Empresarial/Técnico**: El sistema resuelve la gestión completa de inventario para una tienda de ropa temática (OtakuWear), permitiendo el registro, consulta, filtrado y análisis de prendas de vestir.

**Dominio de Aplicación**: E-commerce especializado en productos temáticos con enfoque en gestión de inventario y análisis de ventas.

**Necesidades Específicas**:
- Control de stock en tiempo real
- Validación de datos de entrada robusta
- Análisis de productos premium para estrategias de marketing
- Métricas financieras para toma de decisiones
- Interfaz de usuario intuitiva tipo consola

### 1.2 Descomposición Lógica en Bloques

| Funcionalidad | Bloque de Entrada | Bloque de Procesamiento | Bloque de Salida | Bloque de Validación | Bloque de Manejo de Errores |
|---------------|-------------------|------------------------|------------------|---------------------|---------------------------|
| **Registro de Prenda** | Nombre, Precio, Stock (String input) | Conversión de tipos, creación de objeto Prenda | Confirmación de registro | Validación de formato, rangos y nulidad | Try-catch con mensajes específicos |
| **Mostrar Inventario** | Sin entrada directa | Iteración sobre lista, formateo | Lista formateada con índices | Verificación de lista vacía | Mensaje informativo si no hay datos |
| **Filtrado Premium** | Criterio fijo (>$1000) | Filter + sortedByDescending | Lista ordenada de prendas caras | Verificación de resultados | Mensaje si no hay prendas premium |
| **Métricas Financieras** | Datos del almacén | Operaciones agregadas (sum, average, min, max) | Dashboard de métricas | Verificación de datos suficientes | Control de división por cero implícito |
| **Control de Flujo** | Opción del menú | Switch-case (when) | Ejecución de función correspondiente | Validación de opción en rango | Mensaje de opción inválida |

### 1.3 Flujo de Ejecución Paso a Paso

```
1. INICIO → Mostrar bienvenida
2. BUCLE PRINCIPAL → 
   2.1 MOSTRAR MENÚ → Opciones 1-5
   2.2 CAPTURAR ENTRADA → readLine() + validación
   2.3 PROCESAR OPCIÓN → when(opcion)
   2.4 EJECUTAR FUNCIÓN → Según selección
   2.5 VALIDAR RESULTADO → Feedback al usuario
   2.6 PAUSA → Esperar ENTER
3. CONDICIÓN DE SALIDA → opcion == "5"
4. FIN → Mensaje de despedida
```

## 2. ANÁLISIS DE PILARES DE POO

| Pilar POO | Ubicación en Código | Implementación Específica | Beneficios Obtenidos | Alternativas Posibles |
|-----------|-------------------|---------------------------|---------------------|---------------------|
| **Encapsulación** | Data class `Prenda` con propiedades val | Datos inmutables (nombre, precio, stock) + métodos específicos (validar, mostrarInfo) | Integridad de datos, interfaz controlada | Propiedades var con setters customizados |
| **Abstracción** | Métodos `validar()` y `mostrarInfo()` | Oculta lógica de validación y formateo detrás de interfaces simples | Simplifica uso, reduce duplicación de código | Funciones de extensión, interfaces |
| **Herencia** | No implementada explícitamente | Herencia implícita de Any (clase base de Kotlin) | Comportamientos básicos (toString, equals, hashCode) | Jerarquía de productos (Camiseta extends Prenda) |
| **Polimorfismo** | No implementado en el diseño actual | Potencial con interfaces o clases abstractas | N/A en implementación actual | Interface TipoProducto con diferentes implementaciones |

## 3. DICCIONARIO DE FUNCIONES Y MÉTODOS

### Prenda.validar()

| Aspecto | Descripción |
|---------|-------------|
| **Propósito Principal** | Verificar que todos los atributos de la prenda cumplan con las reglas de negocio |
| **Parámetros de Entrada** | Ninguno (accede a propiedades de instancia) |
| **Valor de Retorno** | Boolean: true si válida, false si algún campo es inválido |
| **Efectos Secundarios** | Ninguno (función pura) |
| **Dependencias** | Propiedades precio, stock, nombre de la instancia |
| **Complejidad Algorítmica** | O(1) - operaciones constantes |
| **Casos de Uso** | Validación antes de persistir, verificación de integridad |

**Variaciones Posibles:**
1. Validación con excepciones personalizadas en lugar de booleano
2. Validación por etapas con mensajes específicos por campo
3. Validación con contexto (diferentes reglas según tipo de tienda)
4. Validación asíncrona para verificar duplicados en BD
5. Validación con builder pattern para construcción segura

**Extensiones Futuras:**
- Validación de categorías permitidas
- Verificación de códigos de barras
- Validación de precios según competencia
- Reglas de negocio dinámicas

### Prenda.mostrarInfo()

| Aspecto | Descripción |
|---------|-------------|
| **Propósito Principal** | Formatear datos de la prenda en string legible para usuario final |
| **Parámetros de Entrada** | Ninguno |
| **Valor de Retorno** | String formateado con template específico |
| **Efectos Secundarios** | Ninguno |
| **Dependencias** | Todas las propiedades de la instancia |
| **Complejidad Algorítmica** | O(1) - concatenación de strings |
| **Casos de Uso** | Mostrar en listas, reportes, interfaces de usuario |

**Patrones de Diseño Aplicables:**
- **Template Method**: Para diferentes formatos de visualización
- **Strategy**: Para múltiples estrategias de formateo
- **Visitor**: Para separar lógica de presentación

### registrarPrenda()

| Aspecto | Descripción |
|---------|-------------|
| **Propósito Principal** | Capturar datos de usuario, validarlos y crear nueva instancia Prenda en el almacén |
| **Parámetros de Entrada** | Ninguno (input por consola) |
| **Valor de Retorno** | Unit (procedimiento) |
| **Efectos Secundarios** | Modifica almacenPrendas global, imprime mensajes |
| **Dependencias** | readLine(), almacenPrendas, constructor Prenda |
| **Complejidad Algorítmica** | O(1) para operación básica, O(n) si se valida duplicados |
| **Casos de Uso** | Ingreso de nuevo inventario, restock de productos |

**Optimizaciones Potenciales:**
- Separar captura de datos de validación y persistencia
- Implementar undo/redo para operaciones de registro
- Batch processing para múltiples prendas
- Validación incremental en tiempo real

### mostrarInventarioCompleto()

| Aspecto | Descripción |
|---------|-------------|
| **Propósito Principal** | Presentar listado formateado de todas las prendas con numeración |
| **Parámetros de Entrada** | Ninguno |
| **Valor de Retorno** | Unit |
| **Efectos Secundarios** | Output a consola únicamente |
| **Dependencias** | almacenPrendas, Prenda.mostrarInfo() |
| **Complejidad Algorítmica** | O(n) donde n = número de prendas |
| **Casos de Uso** | Auditoría de inventario, revisión general |

### mostrarPrendasPremium()

| Aspecto | Descripción |
|---------|-------------|
| **Propósito Principal** | Filtrar y mostrar prendas con precio > $1000 ordenadas por precio descendente |
| **Parámetros de Entrada** | Ninguno (criterio hardcodeado) |
| **Valor de Retorno** | Unit |
| **Efectos Secundarios** | Output a consola |
| **Dependencias** | almacenPrendas, filter, sortedByDescending |
| **Complejidad Algorítmica** | O(n log n) debido al sorting |
| **Casos de Uso** | Análisis de productos premium, estrategias de marketing |

**Variaciones Posibles:**
1. Criterio configurable por parámetro
2. Múltiples criterios de filtrado simultáneos
3. Paginación para listas grandes
4. Exportación a diferentes formatos (CSV, JSON)
5. Filtros dinámicos con DSL

### calcularMetricasInventario()

| Aspecto | Descripción |
|---------|-------------|
| **Propósito Principal** | Calcular y mostrar métricas agregadas del inventario completo |
| **Parámetros de Entrada** | Ninguno |
| **Valor de Retorno** | Unit |
| **Efectos Secundarios** | Cálculos intensivos, output formateado |
| **Dependencias** | almacenPrendas, operaciones de colección (sumOf, average, maxByOrNull, minByOrNull) |
| **Complejidad Algorítmica** | O(n) - múltiples pasadas sobre la colección |
| **Casos de Uso** | Reportes ejecutivos, análisis financiero, planificación |

**Extensiones Futuras:**
- Métricas por categoría de producto
- Tendencias históricas
- Análisis de rotación de inventario
- Proyecciones de demanda
- Comparación con períodos anteriores

## 4. ANÁLISIS DE ESTRUCTURAS DE DATOS

| Estructura | Propósito | Atributos | Métodos | Invariantes | Casos de Uso Alternativos |
|------------|-----------|-----------|---------|-------------|---------------------------|
| **Prenda (data class)** | Representar producto comercializable | nombre: String, precio: Double, stock: Int | validar(): Boolean, mostrarInfo(): String | precio > 0, stock ≥ 0, nombre no vacío | Productos digitales, servicios, materias primas |
| **almacenPrendas (MutableList)** | Colección dinámica de inventario | Elementos tipo Prenda | add(), filter(), sumOf(), etc. | No duplicados lógicos (mismo nombre) | Catálogo de cursos, biblioteca de medios, roster de empleados |

**Análisis de la Estructura Prenda:**
- **Inmutabilidad**: Al usar `val`, garantiza que una vez creada, la prenda no cambia estado
- **Validación externa**: `validar()` permite verificación sin lanzar excepciones
- **Representación string**: `mostrarInfo()` centraliza la lógica de formateo
- **Simplicidad**: Sin herencia compleja, fácil de entender y mantener

## 5. PATRONES Y PARADIGMAS IDENTIFICADOS

### 5.1 Patrones de Diseño

**Patrón encontrado**: **Data Transfer Object (DTO)**
- **Implementación**: La clase `Prenda` funciona como DTO inmutable
- **Beneficios**: Transporte seguro de datos, validación centralizada
- **Alternativas**: Value Object con comportamientos más ricos

**Patrón encontrado**: **Repository Pattern (Implícito)**
- **Implementación**: `almacenPrendas` actúa como repositorio en memoria
- **Beneficios**: Separación de lógica de persistencia
- **Alternativas**: Interface Repository con diferentes implementaciones (DB, File, API)

**Patrón encontrado**: **Template Method (Implícito)**
- **Implementación**: Estructura consistente en todas las funciones de menú
- **Beneficios**: Flujo predecible, fácil mantenimiento
- **Alternativas**: Command Pattern para operaciones de menú

### 5.2 Paradigmas de Programación

| Paradigma | Evidencia en Código | Integración |
|-----------|-------------------|-------------|
| **Orientado a Objetos** | Data class Prenda con encapsulación | Base del modelo de dominio |
| **Funcional** | `filter { }`, `sumOf { }`, `sortedByDescending { }` | Para operaciones de colección |
| **Imperativo** | Bucles while, estructuras de control secuencial | Control de flujo principal |
| **Declarativo** | Expresiones lambda, operaciones de alto nivel | Procesamiento de datos |
