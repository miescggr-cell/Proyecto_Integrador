package Arbol; 

/**
 * Nodo que representa un artículo en el almacén.
 * Contiene los datos del producto y los apuntadores a sus ramas izquierda y derecha.
 */
public class Producto {
    public String nombre;    // Llave alfabética única para el ordenamiento
    public int stock;
    public double precio;
    public Producto izq;     // Rama izquierda (menores alfabéticamente)
    public Producto der;     // Rama derecha (mayores alfabéticamente)

    public Producto(String nombre, int stock, double precio) {
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.izq = null;
        this.der = null;    
    }

    @Override
    public String toString() {
        return "Producto: [" + nombre + "] | Stock: " + stock + " pzas | Precio: $" + precio;
    }
}
