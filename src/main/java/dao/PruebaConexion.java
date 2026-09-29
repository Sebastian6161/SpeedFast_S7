package dao;

import modelo.Repartidor;

import java.util.List;

public class PruebaConexion {

    public static void main(String[] args) {

        RepartidorDAO repartidorDAO =
                new RepartidorDAO();

        List<Repartidor> repartidores =
                repartidorDAO.listarTodos();

        System.out.println("REPARTIDORES REGISTRADOS:");

        for (Repartidor repartidor : repartidores) {

            System.out.println(
                    repartidor.getId()
                            + " - "
                            + repartidor.getNombre()
            );
        }
    }
}
