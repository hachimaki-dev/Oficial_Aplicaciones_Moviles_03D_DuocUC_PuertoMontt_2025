# Guía del Estudiante - Sistema de Ventas de Pasajes
## Terminal de Buses de Puerto Montt

---

## 📋 Análisis del Sistema

### ¿Qué necesitamos construir?
Un sistema de consola que simule la venta de pasajes de bus desde Puerto Montt hacia diferentes destinos. El sistema debe ser **interactivo**, **validar datos** y **mantener un estado** durante la ejecución.

### Componentes Principales:
1. **Modelo de Datos**: Representar un viaje con sus propiedades
2. **Almacenamiento**: Lista para guardar los viajes disponibles
3. **Lógica de Negocio**: Funciones para cada operación
4. **Interfaz de Usuario**: Menú interactivo en consola
5. **Validaciones**: Verificar que los datos sean correctos

---

## 🏗️ Arquitectura del Sistema (Diagrama de Flujo Textual)

### Flujo Principal del Programa:

```
INICIO
├── Inicializar sistema (lista vacía de viajes)
├── Mostrar menú principal
├── BUCLE PRINCIPAL:
│   ├── Leer opción del usuario
│   ├── EVALUAR opción:
│   │   ├── Opción 1: Registrar Viaje
│   │   │   ├── Solicitar datos del viaje
│   │   │   ├── Validar datos ingresados
│   │   │   ├── Crear objeto Viaje
│   │   │   └── Agregar a lista de viajes
│   │   │
│   │   ├── Opción 2: Ver Viajes
│   │   │   ├── Verificar si hay viajes registrados
│   │   │   ├── SI hay: Mostrar todos los viajes
│   │   │   └── SI NO: Mostrar mensaje informativo
│   │   │
│   │   ├── Opción 3: Comprar Pasaje
│   │   │   ├── Mostrar viajes disponibles
│   │   │   ├── Solicitar selección de viaje
│   │   │   ├── Validar que existe el viaje
│   │   │   ├── Validar que hay pasajes disponibles
│   │   │   ├── Procesar venta (descontar pasaje)
│   │   │   └── Actualizar métricas
│   │   │
│   │   ├── Opción 4: Ver Métricas
│   │   │   ├── Calcular pasajes vendidos totales
│   │   │   ├── Calcular recaudación total
│   │   │   ├── Calcular precio promedio
│   │   │   └── Mostrar resultados
│   │   │
│   │   ├── Opción 5: Salir
│   │   │   └── Terminar programa
│   │   │
│   │   └── Opción inválida: Mostrar error
│   │
│   └── Repetir hasta que usuario elija salir
└── FIN
```

---

## 🎯 Sugerencias de Implementación

### 1. **Empezar por el Modelo de Datos**
**¿Qué hacer?** Crear la clase `Viaje`
**¿Cuándo?** Al inicio, es la base de todo
**¿Por qué?** Define la estructura de datos que usarás en todo el sistema

```kotlin
// Propiedades esenciales:
// - destino: String
// - precio: Int (en CLP)
// - pasajesDisponibles: Int
// - pasajesIniciales: Int (para calcular vendidos)
```

### 2. **Crear la Estructura del Menú**
**¿Qué hacer?** Función para mostrar opciones y capturar entrada
**¿Cuándo?** Segundo paso, después del modelo
**¿Por qué?** Te permite probar el flujo básico del programa

### 3. **Implementar Funcionalidades Una por Una**
**¿Qué hacer?** Cada opción del menú como función separada
**¿Cuándo?** En orden de complejidad (registrar → ver → comprar → métricas)
**¿Por qué?** Permite probar cada función independientemente

### 4. **Validaciones Graduales**
**¿Qué hacer?** Agregar validaciones a medida que desarrollas
**¿Cuándo?** Después de que cada función básica funcione
**¿Por qué?** Evita complejidad innecesaria al inicio

---

## 🧩 Componentes Clave a Considerar

### Manejo de Datos:
- **Lista mutable** para almacenar viajes
- **Índices** para identificar viajes específicos
- **Validación** de entrada de usuario

### Operaciones Principales:
- **Agregar** elementos a la lista
- **Iterar** sobre la lista para mostrar datos
- **Buscar** elementos específicos
- **Modificar** propiedades de objetos existentes

### Cálculos Necesarios:
- **Suma** de pasajes vendidos
- **Multiplicación** para recaudación (precio × vendidos)
- **Promedio** de precios
- **Diferencia** entre pasajes iniciales y disponibles

### Manejo de Errores:
- **Validar** que los números sean positivos
- **Verificar** que las cadenas no estén vacías
- **Controlar** índices fuera de rango
- **Manejar** entradas inválidas del usuario

---

## 📝 Checklist de Desarrollo

### Fase 1: Estructura Básica
- [ ] Crear clase `Viaje` con propiedades básicas
- [ ] Crear lista mutable para almacenar viajes
- [ ] Implementar menú básico con bucle principal
- [ ] Función para mostrar opciones del menú

### Fase 2: Funcionalidades Core
- [ ] Función para registrar nuevo viaje
- [ ] Función para mostrar todos los viajes
- [ ] Función para comprar pasaje
- [ ] Función para mostrar métricas

### Fase 3: Validaciones y Mejoras
- [ ] Validar entrada numérica del usuario
- [ ] Validar que los precios sean positivos
- [ ] Validar que la cantidad de pasajes sea positiva
- [ ] Manejar casos cuando no hay viajes registrados

### Fase 4: Pulimiento
- [ ] Mejorar formato de salida (presentación)
- [ ] Agregar mensajes informativos
- [ ] Manejar errores de entrada graciosamente
- [ ] Probar todos los casos límite

---

## 🚀 Consejos Prácticos

### Para Empezar:
1. **No trates de hacer todo perfecto desde el inicio**
2. **Haz que funcione primero, luego mejóralo**
3. **Prueba cada función individualmente**
4. **Usa `println()` para debuggear valores**

### Durante el Desarrollo:
- **Compila frecuentemente** para detectar errores temprano
- **Prueba con datos de ejemplo** para verificar lógica
- **Comenta tu código** para recordar qué hace cada parte

### Para Debugging:
- **Imprime variables** para ver sus valores
- **Prueba casos extremos** (lista vacía, números negativos, etc.)
- **Simplifica el problema** si algo no funciona

---

## 📊 Datos de Prueba Sugeridos

Para probar tu sistema, puedes usar estos datos de ejemplo:

```
Viaje 1: Santiago, $15000, 50 pasajes
Viaje 2: Osorno, $3000, 20 pasajes  
Viaje 3: Valdivia, $5000, 30 pasajes
Viaje 4: Temuco, $8000, 25 pasajes
```

Esto te permitirá probar:
- Registro de múltiples viajes
- Compra de pasajes
- Cálculo de métricas
- Casos cuando se agotan los pasajes

---

## 🎓 Conceptos de Kotlin que Practicarás

- **Clases y objetos**: Modelado de datos con `class Viaje`
- **Listas mutables**: `mutableListOf<Viaje>()`
- **Bucles**: `while`, `for`, `forEach`
- **Condicionales**: `when`, `if/else`
- **Funciones**: Organización de código en funciones
- **Validaciones**: Verificación de entrada de usuario
- **String templates**: Formateo de salida `"${variable}"`
- **Manejo de entrada**: `readLine()` y conversiones de tipo

¡Éxito en tu proyecto! 🚌💨