package src.app;

import java.util.*;
import src.productos.Producto;
import src.pedidos.Pedido;
import src.pedidos.EnLineaPedido;

public class main {
    private static Scanner entradaDatos = new Scanner(System.in);
    private static List<Producto> productos = new ArrayList<>();
    private static List<Pedido> pedidos = new ArrayList<>();

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n====== SISTEMA DE GESTIÓN - TECHLAB ======");
            System.out.println("1) Agregar producto");
            System.out.println("2) Listar productos");
            System.out.println("3) Buscar/Actualizar producto");
            System.out.println("4) Eliminar producto");
            System.out.println("5) Crear un pedido");
            System.out.println("6) Listar pedidos");
            System.out.println("7) Salir");
            System.out.print("Elija una opción: ");
            opcion = Integer.parseInt(entradaDatos.nextLine());

            switch (opcion) {
                case 1 -> agregarProducto();
                case 2 -> listarProductos();
                case 3 -> buscarActualizarProducto();
                case 4 -> eliminarProducto();
                case 5 -> crearPedido();
                case 6 -> listarPedidos();
            }
        } while (opcion != 7);
    }

    private static void agregarProducto() {
        System.out.print("Nombre: ");
        String nombre = entradaDatos.nextLine();
        System.out.print("Precio: ");
        double precio = Double.parseDouble(entradaDatos.nextLine());
        System.out.print("Stock: ");
        int stock = Integer.parseInt(entradaDatos.nextLine());

        productos.add(new Producto(nombre, precio, stock));
        System.out.println("Producto agregado con éxito.");
    }

    private static void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            productos.forEach(System.out::println);
        }
    }

    private static void buscarActualizarProducto() {
        System.out.print("Ingrese ID del producto: ");
        int id = Integer.parseInt(entradaDatos.nextLine());

        Producto producto = buscarProductoPorId(id);
        if (producto != null) {
            System.out.println("Producto encontrado: " + producto);
            System.out.print("¿Desea actualizar precio? : ");
            if (entradaDatos.nextLine().equalsIgnoreCase("si")) {
                System.out.print("Nuevo precio: ");
                producto.setPrecio(Double.parseDouble(entradaDatos.nextLine()));
            }
            System.out.print("¿Desea actualizar stock? : ");
            if (entradaDatos.nextLine().equalsIgnoreCase("si")) {
                System.out.print("Nuevo stock: ");
                producto.setStock(Integer.parseInt(entradaDatos.nextLine()));
            }
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private static Producto buscarProductoPorId(int id) {
        return productos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    private static void eliminarProducto() {
        System.out.print("Ingrese ID del producto a eliminar: ");
        int id = Integer.parseInt(entradaDatos.nextLine());
        Producto producto = buscarProductoPorId(id);
        if (producto != null) {
            System.out.print("¿Está segura que desea eliminarlo? : ");
            if (entradaDatos.nextLine().equalsIgnoreCase("si")) {
                productos.remove(producto);
                System.out.println("Producto eliminado.");
            }
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private static void crearPedido() {
        Pedido pedido = new Pedido();
        while (true) {
            listarProductos();
            System.out.print("Ingrese ID del producto o 0 para terminar: ");
            int id = Integer.parseInt(entradaDatos.nextLine());
            if (id == 0) break;
            Producto producto = buscarProductoPorId(id);
            if (producto == null) {
                System.out.println("Producto no encontrado.");
                continue;
            }
            System.out.print("Cantidad: ");
            int cantidad = Integer.parseInt(entradaDatos.nextLine());
            if (cantidad <= producto.getStock()) {
                producto.setStock(producto.getStock() - cantidad);
                pedido.agregarPedidoEnLinea(new EnLineaPedido(producto, cantidad));
            } else {
                System.out.println("Stock insuficiente.");
            }
        }
        pedidos.add(pedido);
        System.out.println("Pedido creado con éxito.");
    }

    private static void listarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos.");
        } else {
            pedidos.forEach(p -> {
                System.out.println("-----------------------------");
                System.out.println(p);
            });
        }
    }
}
