package Arbol;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArbolBinarioDeBusqueda inventario = new ArbolBinarioDeBusqueda();
        int opcion = 0;

        // Carga inicial para pruebas
        inventario.insertar("Teclado", 25, 450.0);
        inventario.insertar("Mouse", 50, 280.0);
        inventario.insertar("Monitor", 12, 3200.0);
        inventario.insertar("Audifonos", 30, 600.0);
        inventario.insertar("Webcam", 15, 850.0);

        do {
            System.out.println("\n=============================================");
            System.out.println("   SISTEMA DE CONTROL DE INVENTARIO (ABB)    ");
            System.out.println("=============================================");
            System.out.println("1. Registrar / Insertar nuevo producto");
            System.out.println("2. Buscar producto por nombre");
            System.out.println("3. Dar de baja / Eliminar producto");
            System.out.println("4. Mostrar inventario completo");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opción no válida. Ingrese un número entre 1 y 5.");
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del producto: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Cantidad en stock: ");
                    int stock = Integer.parseInt(scanner.nextLine());
                    System.out.print("Precio unitario ($): ");
                    double precio = Double.parseDouble(scanner.nextLine());
                    inventario.insertar(nombre, stock, precio);
                    break;

                case 2:
                    System.out.print("Nombre a buscar: ");
                    String buscado = scanner.nextLine();
                    Producto res = inventario.buscar(buscado);
                    if (res != null) {
                        System.out.println("-> ENCONTRADO: " + res);
                    } else {
                        System.out.println("(!) El producto \"" + buscado + "\" no existe.");
                    }
                    break;

                case 3:
                    System.out.print("Nombre del producto a eliminar: ");
                    String eliminar = scanner.nextLine();
                    inventario.eliminar(eliminar);
                    break;

                case 4:
                    inventario.mostrarInOrder();
                    break;

                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción fuera de rango.");
            }
        } while (opcion != 5);

        scanner.close();
    }
}