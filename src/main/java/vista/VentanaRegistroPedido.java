package vista;

import controladores.PedidoController;
import dao.PedidoDAO;
import modelo.Pedido;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistroPedido extends JFrame {

    private PedidoController pedidoController;
    private PedidoDAO pedidoDAO;

    private JTextField campoId;
    private JTextField campoDireccion;
    private JComboBox<String> comboTipo;

    public VentanaRegistroPedido(PedidoController pedidoController) {

        this.pedidoController = pedidoController;
        this.pedidoDAO = new PedidoDAO();

        setTitle("Registrar Pedido");
        setSize(450, 280);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(4, 2, 10, 10));

        JLabel etiquetaId = new JLabel("ID:");
        JLabel etiquetaDireccion = new JLabel("Dirección:");
        JLabel etiquetaTipo = new JLabel("Tipo:");

        campoId = new JTextField();
        campoDireccion = new JTextField();

        comboTipo = new JComboBox<>();

        comboTipo.addItem("comida");
        comboTipo.addItem("encomienda");
        comboTipo.addItem("express");

        JButton botonGuardar = new JButton("Guardar");

        add(etiquetaId);
        add(campoId);

        add(etiquetaDireccion);
        add(campoDireccion);

        add(etiquetaTipo);
        add(comboTipo);

        add(new JLabel());
        add(botonGuardar);

        botonGuardar.addActionListener(e -> guardarPedido());
    }

    private void guardarPedido() {

        String idTexto = campoId.getText().trim();
        String direccion = campoDireccion.getText().trim();
        String tipo = comboTipo.getSelectedItem().toString();

        if (idTexto.isEmpty() || direccion.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe completar todos los campos."
            );

            return;
        }

        int id;

        try {

            id = Integer.parseInt(idTexto);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "El ID debe ser un número."
            );

            return;
        }

        if (id <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "El ID debe ser mayor que 0."
            );

            return;
        }

        Pedido pedido =
                new Pedido(id, direccion, tipo);

        boolean agregado =
                pedidoController.agregarPedido(pedido);

        if (!agregado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ya existe un pedido con ese ID."
            );

            return;
        }

        boolean guardadoBD =
                pedidoDAO.guardar(pedido);

        if (!guardadoBD) {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo guardar el pedido en la base de datos."
            );

            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "Pedido registrado correctamente."
        );

        campoId.setText("");
        campoDireccion.setText("");
    }
}