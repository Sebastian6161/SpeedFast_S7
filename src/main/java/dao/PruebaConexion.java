package dao;

import modelo.Entrega;
import modelo.Pedido;
import modelo.Repartidor;

import java.time.LocalDate;
import java.time.LocalTime;

public class PruebaConexion {

    public static void main(String[] args) {

        Pedido pedido =
                new Pedido(
                        301,
                        "Las Condes 500",
                        "comida"
                );

        Repartidor repartidor =
                new Repartidor(
                        1,
                        "Carlos"
                );

        Entrega entrega =
                new Entrega(
                        0,
                        pedido,
                        repartidor,
                        LocalDate.now(),
                        LocalTime.now()
                );

        EntregaDAO entregaDAO =
                new EntregaDAO();

        boolean guardada =
                entregaDAO.guardar(entrega);

        if (guardada) {
            System.out.println(
                    "Entrega guardada correctamente en MySQL."
            );
        } else {
            System.out.println(
                    "No se pudo guardar la entrega."
            );
        }
    }
}
