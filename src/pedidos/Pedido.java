package src.pedidos;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int contador = 1;
    private int id;
    private List<EnLineaPedido> enLinea;

    public Pedido() {
        this.id = contador++;
        this.enLinea = new ArrayList<>();
    }

    public void agregarPedidoEnLinea(EnLineaPedido pedidoEnLinea) {
        enLinea.add(pedidoEnLinea);
    }

    public double calcularTotal() {
        return enLinea.stream().mapToDouble(EnLineaPedido::calcularSubTotal).sum();
    }

    @Override
    public String toString() {
        StringBuilder textoId = new StringBuilder("Pedido #" + id + "\n");
        for (EnLineaPedido cadaProd : enLinea) {
            textoId.append(cadaProd).append("\n");
        }
        textoId.append("Total: $").append(calcularTotal());
        return textoId.toString();
    }
}
