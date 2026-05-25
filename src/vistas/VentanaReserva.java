package vistas;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import modelo.DestinoEspacial;
import modelo.Reserva;
import modelo.Usuario;
import modelo.Viaje;
import sistema.SistemaReservas;

public class VentanaReserva extends JFrame {
    private final Font mainFont = new Font("Segoe Print", Font.BOLD, 14);
    private final SistemaReservas sistema;
    private JComboBox<String> usuarioCombo;
    private JComboBox<String> destinoCombo;
    private JTextField fechaField;
    private JTextArea observacionesArea;
    private JTextArea infoArea;

    public VentanaReserva(SistemaReservas sistema) {
        this.sistema = sistema;
        setTitle("Crear Reserva de Viaje");
        setSize(700, 600);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        add(createPanel(), BorderLayout.CENTER);
        setVisible(true);
    }

    private JPanel createPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainPanel.add(createFormPanel(), BorderLayout.NORTH);
        mainPanel.add(createInfoPanel(), BorderLayout.CENTER);

        return mainPanel;
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 8, 8));
        panel.setBorder(BorderFactory.createTitledBorder("Datos de la reserva"));

        JLabel usuarioLabel = new JLabel("Usuario:");
        usuarioLabel.setFont(mainFont);
        usuarioCombo = new JComboBox<>();
        usuarioCombo.setFont(mainFont);
        actualizarComboUsuarios();

        JLabel destinoLabel = new JLabel("Destino:");
        destinoLabel.setFont(mainFont);
        destinoCombo = new JComboBox<>();
        destinoCombo.setFont(mainFont);
        actualizarComboDestinos();

        JLabel fechaLabel = new JLabel("Fecha de viaje:");
        fechaLabel.setFont(mainFont);
        fechaField = new JTextField();
        fechaField.setFont(mainFont);
        fechaField.setToolTipText("Ejemplo: 21/08/2026");

        JLabel observacionesLabel = new JLabel("Observaciones:");
        observacionesLabel.setFont(mainFont);
        observacionesArea = new JTextArea(3, 10);
        observacionesArea.setFont(mainFont);
        observacionesArea.setLineWrap(true);
        observacionesArea.setWrapStyleWord(true);

        JButton reservarButton = new JButton("Crear Reserva");
        reservarButton.setFont(mainFont);
        reservarButton.addActionListener(e -> crearReserva());

        panel.add(usuarioLabel);
        panel.add(usuarioCombo);
        panel.add(destinoLabel);
        panel.add(destinoCombo);
        panel.add(fechaLabel);
        panel.add(fechaField);
        panel.add(observacionesLabel);
        panel.add(new JScrollPane(observacionesArea));

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        southPanel.add(reservarButton);

        JPanel formContainer = new JPanel(new BorderLayout());
        formContainer.add(panel, BorderLayout.CENTER);
        formContainer.add(southPanel, BorderLayout.SOUTH);

        return formContainer;
    }

    private JPanel createInfoPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Información del viaje"));

        infoArea = new JTextArea();
        infoArea.setFont(mainFont);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        infoArea.setEditable(false);
        infoArea.setBackground(new Color(250, 250, 255));
        infoArea.setText("Aquí aparecerán los detalles del viaje después de crear la reserva.");

        panel.add(new JScrollPane(infoArea), BorderLayout.CENTER);

        return panel;
    }

    private void actualizarComboUsuarios() {
        usuarioCombo.removeAllItems();
        List<Usuario> usuarios = sistema.listarUsuarios();
        if (usuarios.isEmpty()) {
            usuarioCombo.addItem("-- Registra un usuario primero --");
        } else {
            for (Usuario usuario : usuarios) {
                usuarioCombo.addItem(usuario.getNombre());
            }
        }
    }

    private void actualizarComboDestinos() {
        destinoCombo.removeAllItems();
        List<DestinoEspacial> destinos = sistema.listarDestinos();
        for (DestinoEspacial destino : destinos) {
            destinoCombo.addItem(destino.getNombre());
        }
    }

    private void crearReserva() {
        try {
            List<Usuario> usuarios = sistema.listarUsuarios();
            if (usuarios.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Primero registra un usuario en la ventana de Registro.",
                        "Usuario no encontrado",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            String usuarioNombre = (String) usuarioCombo.getSelectedItem();
            Usuario usuario = null;
            for (Usuario u : usuarios) {
                if (u.getNombre().equals(usuarioNombre)) {
                    usuario = u;
                    break;
                }
            }

            if (usuario == null) {
                JOptionPane.showMessageDialog(this,
                        "Selecciona un usuario válido.",
                        "Usuario inválido",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            String destinoNombre = (String) destinoCombo.getSelectedItem();
            DestinoEspacial destino = sistema.buscarDestino(destinoNombre);
            if (destino == null) {
                JOptionPane.showMessageDialog(this,
                        "Selecciona un destino válido.",
                        "Destino inválido",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            String fechaViaje = fechaField.getText();
            if (fechaViaje == null || fechaViaje.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Ingresa la fecha del viaje.",
                        "Fecha faltante",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            String observaciones = observacionesArea.getText();

            Reserva reserva = sistema.crearReserva(usuario, destino, fechaViaje, observaciones);
            mostrarInformacionReserva(reserva);

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error de validación: " + ex.getMessage(),
                    "Datos inválidos",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarInformacionReserva(Reserva reserva) {
        Viaje viaje = sistema.obtenerInformacionViaje(reserva.getDestino());
        StringBuilder sb = new StringBuilder();
        sb.append("═════════════════════════════════════\n");
        sb.append("✓ RESERVA CONFIRMADA\n");
        sb.append("═════════════════════════════════════\n\n");
        sb.append("Usuario: ").append(reserva.getUsuario().getNombre()).append("\n");
        sb.append("Correo: ").append(reserva.getUsuario().getCorreo()).append("\n");
        sb.append("Destino: ").append(reserva.getDestino().getNombre()).append("\n");
        sb.append("Fecha: ").append(reserva.getFechaViaje()).append("\n");
        sb.append("Precio: $").append(String.format("%.2f", reserva.getDestino().getPrecio())).append("\n\n");
        sb.append("--- ITINERARIO ---\n").append(viaje.getItinerario()).append("\n\n");
        sb.append("--- EQUIPO NECESARIO ---\n").append(viaje.getEquipoNecesario()).append("\n\n");
        sb.append("--- RECOMENDACIONES ---\n").append(viaje.getRecomendaciones()).append("\n");

        infoArea.setText(sb.toString());
    }
}

