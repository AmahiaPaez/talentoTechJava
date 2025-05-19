package src.pedidos;

import src.productos.Producto;

public class EnLineaPedido {
    private Producto producto;
    private int cantidad;

    public EnLineaPedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public double calcularSubTotal() {
        return producto.getPrecio() * cantidad;
    }

    @Override
    public String toString() {
        return producto.getNombre() + " x " + cantidad + " = $" + calcularSubTotal();
    }
}
