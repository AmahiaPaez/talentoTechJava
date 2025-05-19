package src.productos;

public class Producto {
    private static int idContador = 0;
    private int id, stock;
    private String nombre;
    private double precio;

    public Producto(String nombre, double precio, int stock) {
        this.id = ++idContador;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public int getStock() {
        return stock;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "- ID: " + id + " | Nombre: " + nombre + " | Precio: $ " + precio + " | Stock: " + stock + " -";
    }
}
