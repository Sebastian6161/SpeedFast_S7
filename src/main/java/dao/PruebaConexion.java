package dao;

import modelo.Pedido;

public class PruebaConexion {

    public static void main(String[] args) {

        Pedido pedido =
                new Pedido(
                        301,
                        "Las Condes 500",
                        "comida"
                );

        PedidoDAO pedidoDAO =
                new PedidoDAO();

        boolean guardado =
                pedidoDAO.guardar(pedido);

        if (guardado) {
            System.out.println(
                    "Pedido guardado correctamente en MySQL."
            );
        } else {
            System.out.println(
                    "No se pudo guardar el pedido."
            );
        }
    }
}
