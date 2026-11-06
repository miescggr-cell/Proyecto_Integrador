# Sistema de Control de Inventario con Árbol Binario de Búsqueda (ABB)

**Instituto Tecnológico Superior de Xalapa (ITSX)**  
**División de Ingeniería en Sistemas Computacionales**  
**Asignatura:** Estructura de Datos  
**Docente:** Mtro. Víctor Hugo Vásquez Herrera  
**Alumno:** González Ramírez Gerardo (No. Control: 247O02562), Pale Mendoza Alan Cristopher (No. Control: 247O00961) 
**Semestre:** Sabatino — Noviembre 2026  

---

## Descripción del Proyecto

Aplicación interactiva en consola Java que simula la gestión de mercancía y control de existencias en un almacén en tiempo real. 

A diferencia de las colecciones lineales ($O(n)$), cada artículo se modela dinámicamente como un nodo dentro de un **Árbol Binario de Búsqueda (ABB)**, utilizando el nombre del producto como llave primaria alfabética para optimizar las operaciones de búsqueda y descarte masivo mediante la técnica de *Divide y Vencerás* ($O(\log n)$).

---

## Adaptación del ABB con Cadenas (`compareTo`)

El ordenamiento de los nodos se rige mediante el método `compareToIgnoreCase()` de la clase `String` de Java:

* **`nuevo.compareTo(actual) < 0`:** El nombre es menor alfabéticamente; el flujo desciende hacia la **rama izquierda**.
* **`nuevo.compareTo(actual) > 0`:** El nombre es mayor alfabéticamente; el flujo desciende hacia la **rama derecha**.
* **`nuevo.compareTo(actual) == 0`:** La clave alfabética ya existe en el árbol; se bloquea la inserción para prevenir inconsistencias y duplicados.

---

## Gestión de Eliminación (Los 3 Casos del ABB)

1. **Caso 1 (Nodo Hoja - 0 hijos):** Se desconecta la referencia del padre asignándola a `null`.
2. **Caso 2 (Nodo con 1 hijo):** El nodo abuelo puentea su apuntador directamente con el nodo nieto, adoptando la descendencia.
3. **Caso 3 (Nodo con 2 hijos - Sucesor Inorden):** Se localiza el menor absoluto de la rama derecha mediante `obtenerMinimo(actual.der)` (un paso a la derecha y todo a la izquierda hasta el tope), se copian sus datos al nodo actual y se suprime la hoja duplicada inferior.

---

### Salida de Ejecución en Consola (Validada)

Al ejecutar las opciones en la terminal de tu computadora, las respuestas del menú para cada requerimiento de la práctica son las siguientes:

```text
=== CARGA INICIAL DEL SISTEMA ===
-> Producto registrado con éxito: Teclado
-> Producto registrado con éxito: Mouse
-> Producto registrado con éxito: Monitor
-> Producto registrado con éxito: Audifonos
-> Producto registrado con éxito: Webcam

=== PRUEBA 1: MOSTRAR INVENTARIO (Opción 4 - InOrder A-Z) ===
--- INVENTARIO EN ORDEN ALFABÉTICO (InOrder) ---
Producto: [Audifonos] | Stock: 30 pzas | Precio: $600.00
Producto: [Monitor]   | Stock: 12 pzas | Precio: $3200.00
Producto: [Mouse]     | Stock: 50 pzas | Precio: $280.00
Producto: [Teclado]   | Stock: 25 pzas | Precio: $450.00
Producto: [Webcam]    | Stock: 15 pzas | Precio: $850.00
------------------------------------------------

=== PRUEBA 2: DETECCIÓN DE DUPLICADO (Opción 1 - Intentar ingresar "Teclado") ===
(!) Error: El producto "Teclado" ya existe en el inventario.

=== PRUEBA 3: BÚSQUEDA (Opción 2) ===
Buscando "Mouse": 
-> ENCONTRADO: Producto: [Mouse] | Stock: 50 pzas | Precio: $280.00

Buscando "Laptop": 
(!) El producto "Laptop" no existe.

=== PRUEBA 4: ELIMINACIÓN CON 2 HIJOS (Opción 3 - Eliminar "Teclado") ===
-> Producto "Teclado" eliminado del almacén.

=== PRUEBA 5: INVENTARIO TRAS ELIMINAR (Opción 4) ===
--- INVENTARIO EN ORDEN ALFABÉTICO (InOrder) ---
Producto: [Audifonos] | Stock: 30 pzas | Precio: $600.00
Producto: [Monitor]   | Stock: 12 pzas | Precio: $3200.00
Producto: [Mouse]     | Stock: 50 pzas | Precio: $280.00
Producto: [Webcam]    | Stock: 15 pzas | Precio: $850.00
------------------------------------------------
