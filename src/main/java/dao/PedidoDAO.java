package dao;

import modelo.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PedidoDAO {

    public boolean guardar(Pedido pedido) {

        String sql = "INSERT INTO pedido (id, direccion, tipo, estado) VALUES (?, ?, ?, ?)";

        Connection conexion = null;
        PreparedStatement statement = null;

        try {
            conexion = ConexionBD.conectar();

            statement = conexion.prepareStatement(sql);

            statement.setInt(1, pedido.getId());
            statement.setString(2, pedido.getDireccion());
            statement.setString(3, pedido.getTipo());
            statement.setString(4, pedido.getEstado().name());

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println("Error al guardar el pedido.");
            System.out.println(e.getMessage());

            return false;

        } finally {

            try {
                if (statement != null) {
                    statement.close();
                }

                if (conexion != null) {
                    conexion.close();
                }

            } catch (SQLException e) {
                System.out.println("Error al cerrar los recursos.");
                System.out.println(e.getMessage());
            }
        }
    }
}