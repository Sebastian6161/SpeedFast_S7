package controladores;

import modelo.EstadoPedido;
import modelo.Pedido;
import modelo.Repartidor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PedidoController {

    private List<Pedido> pedidos;
    private List<Repartidor> repartidores;
    private Queue<Pedido> colaPedidos;

    public PedidoController() {
        pedidos = new ArrayList<>();
        colaPedidos = new LinkedList<>();

        repartidores = new ArrayList<>();
        repartidores.add(new Repartidor(1, "Carlos"));
        repartidores.add(new Repartidor(2, "María"));
        repartidores.add(new Repartidor(3, "Pedro"));
    }

    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
        colaPedidos.add(pedido);
    }

    public List<Pedido> obtenerPedidos() {
        return pedidos;
    }

    public List<Repartidor> obtenerRepartidores() {
        return repartidores;
    }

    public Queue<Pedido> obtenerColaPedidos() {
        return colaPedidos;
    }

    public void configurarRepartidores(int cantidad) {
        repartidores.clear();
        for (int i = 1; i <= cantidad; i++) {
            repartidores.add(new Repartidor(i, "Repartidor " + i));
        }
    }

    public void procesarCola() {
        int indexRepartidor = 0;
        while (!colaPedidos.isEmpty()) {
            Pedido pedido = colaPedidos.poll();
            if (pedido != null && !repartidores.isEmpty()) {
                Repartidor repartidor = repartidores.get(indexRepartidor % repartidores.size());
                pedido.asignarRepartidor(repartidor);
                pedido.entregar();
                indexRepartidor++;
            }
        }
    }

    public void procesarConInterrupcion(int limiteAntesDeInterrumpir) {
        int indexRepartidor = 0;
        int procesados = 0;

        while (!colaPedidos.isEmpty()) {
            if (procesados >= limiteAntesDeInterrumpir) {
                while (!colaPedidos.isEmpty()) {
                    Pedido pedidoInterrumpido = colaPedidos.poll();
                    if (pedidoInterrumpido != null) {
                        pedidoInterrumpido.interrumpir();
                    }
                }
                break;
            }

            Pedido pedido = colaPedidos.poll();
            if (pedido != null && !repartidores.isEmpty()) {
                Repartidor repartidor = repartidores.get(indexRepartidor % repartidores.size());
                pedido.asignarRepartidor(repartidor);
                pedido.entregar();
                indexRepartidor++;
                procesados++;
            }
        }
    }
}