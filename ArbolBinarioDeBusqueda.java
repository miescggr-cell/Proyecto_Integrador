package Arbol;

public class ArbolBinarioDeBusqueda {
    
    public Producto raiz;

    public ArbolBinarioDeBusqueda() {
        this.raiz = null;
    }

    
    // 1. INSERCIÓN: Respetando la propiedad del ABB con Strings

    public void insertar(String nombre, int stock, double precio) {
        this.raiz = insertarRecursivo(this.raiz, nombre, stock, precio);
    }

    // Definimos el método que nos regresa nodo. Los parámetros: actual es donde estamos parados
    private Producto insertarRecursivo(Producto actual, String nombre, int stock, double precio) {
        // CASO BASE (El freno): Si chocamos contra un espacio vacío (null),
        // aquí mero se crea el nodo físicamente en memoria con el nuevo producto.
        if (actual == null) {
            System.out.println("-> Producto registrado con éxito: " + nombre);
            return new Producto(nombre, stock, precio);
        }

        // Comparación de cadenas con compareToIgnoreCase
        int comparacion = nombre.compareToIgnoreCase(actual.nombre);

        // Si el nombre es MENOR alfabéticamente, te mueves hacia la RAMA IZQUIERDA.
        if (comparacion < 0) {
            actual.izq = insertarRecursivo(actual.izq, nombre, stock, precio);
        } 
        // Si el nombre es MAYOR alfabéticamente, te mueves hacia la RAMA DERECHA.
        else if (comparacion > 0) {
            actual.der = insertarRecursivo(actual.der, nombre, stock, precio);
        } 
        // Si da 0, significa que el producto ya existe (evitar duplicados en un ABB puro)
        else {
            System.out.println("(!) Error: El producto \"" + nombre + "\" ya existe en el inventario.");
        }

        // Regresa el nodo enlazado para mantener unida la estructura.
        return actual;
    }

    // 2. BÚSQUEDA: Divide y Vencerás con complejidad O(log n)
    public Producto buscar(String nombre) {
        return buscarRecursivo(this.raiz, nombre);
    }

    private Producto buscarRecursivo(Producto actual, String nombre) {
        // 1. Si llegamos a un hueco vacío, el producto no existe
        if (actual == null) {
            return null;
        }

        int comparacion = nombre.compareToIgnoreCase(actual.nombre);

        // 2. Encontrado en el nodo actual
        if (comparacion == 0) {
            return actual;
        }

        // 3. Si es menor, buscamos a la izquierda; si es mayor, a la derecha
        if (comparacion < 0) {
            return buscarRecursivo(actual.izq, nombre);
        } else {
            return buscarRecursivo(actual.der, nombre);
        }
    }

    // 3. ELIMINACIÓN: Manejo de los 3 casos
   
    public void eliminar(String nombre) {
        if (buscar(nombre) == null) {
            System.out.println("(!) Error: No se puede eliminar. El producto no existe.");
            return;
        }
        this.raiz = eliminarRecursivo(this.raiz, nombre);
        System.out.println("-> Producto \"" + nombre + "\" eliminado del almacén.");
    }

    private Producto eliminarRecursivo(Producto actual, String nombre) {
        if (actual == null) {
            return null;
        }

        int comparacion = nombre.compareToIgnoreCase(actual.nombre);

        if (comparacion < 0) {
            actual.izq = eliminarRecursivo(actual.izq, nombre);
        } else if (comparacion > 0) {
            actual.der = eliminarRecursivo(actual.der, nombre);
        } else {
            // ¡ENCONTRADO! Evaluamos los 3 casos:

            // CASO 1: Nodo Hoja (0 hijos)
            if (actual.izq == null && actual.der == null) {
                return null; // Se desconecta la rama y pasa a null
            }

            // CASO 2: Exactamente 1 hijo (Se hace un puente con el nieto)
            if (actual.izq == null) {
                return actual.der; // El padre adopta al nieto derecho
            }
            if (actual.der == null) {
                return actual.izq; // El padre adopta al nieto izquierdo
            }

            // CASO 3: Tiene 2 hijos (Sucesor Inorden)
            // 1 paso a la DERECHA y todo a la IZQUIERDA hasta el fondo
            Producto sucesor = obtenerMinimo(actual.der);

            // Se copian los datos del nuevo jefe
            actual.nombre = sucesor.nombre;
            actual.stock = sucesor.stock;
            actual.precio = sucesor.precio;

            // Se borra el nodo duplicado que quedó abajo
            actual.der = eliminarRecursivo(actual.der, sucesor.nombre);
        }

        return actual;
    }

    // Método auxiliar para el Caso 3: busca el menor absoluto de la rama derecha
    private Producto obtenerMinimo(Producto actual) {
        Producto cursor = actual;
        while (cursor.izq != null) {
            cursor = cursor.izq;
        }
        return cursor;
    }

    // 4. RECORRIDO INORDER: Orden alfabético A-Z
    
    public void mostrarInOrder() {
        if (this.raiz == null) {
            System.out.println("El inventario está vacío.");
            return;
        }
        System.out.println("\n--- INVENTARIO EN ORDEN ALFABÉTICO (InOrder) ---");
        recorrerInOrder(this.raiz);
        System.out.println("------------------------------------------------");
    }

    private void recorrerInOrder(Producto actual) {
        if (actual != null) {
            recorrerInOrder(actual.izq);     // 1. Todo lo menor (izquierda)
            System.out.println(actual);       // 2. Imprime el actual
            recorrerInOrder(actual.der);     // 3. Todo lo mayor (derecha)
        }
    }
}