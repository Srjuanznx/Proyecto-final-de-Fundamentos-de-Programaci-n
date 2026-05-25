package vistas;

import modelo.DestinoEspacial;
import sistema.SistemaReservas;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaDestinos extends JFrame {
    private final Font mainFont = new Font("Segoe Print", Font.BOLD, 14);
    private final SistemaReservas sistema;
    private JComboBox<String> destinationCombo;
    private JTextArea destinationDetailsArea;

    public VentanaDestinos(SistemaReservas sistema) {
        this.sistema = sistema;
        setTitle("Seleccionar Destino");
        setSize(600, 450);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        add(createPanel(), BorderLayout.CENTER);
        setVisible(true);
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel topPanel = new JPanel(new BorderLayout(8, 8));
        topPanel.setBorder(BorderFactory.createTitledBorder("Destinos disponibles"));

        JLabel selectLabel = new JLabel("Selecciona un destino:");
        selectLabel.setFont(mainFont);

        destinationCombo = new JComboBox<>();
        destinationCombo.setFont(mainFont);
        actualizarComboDestinos();
        destinationCombo.addActionListener(e -> actualizarDetalles());

        topPanel.add(selectLabel, BorderLayout.NORTH);
        topPanel.add(destinationCombo, BorderLayout.CENTER);

        destinationDetailsArea = new JTextArea();
        destinationDetailsArea.setFont(mainFont);
        destinationDetailsArea.setLineWrap(true);
        destinationDetailsArea.setWrapStyleWord(true);
        destinationDetailsArea.setEditable(false);
        destinationDetailsArea.setBackground(new Color(245, 245, 255));

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(destinationDetailsArea), BorderLayout.CENTER);

        actualizarDetalles();
        return panel;
    }

    private void actualizarComboDestinos() {
        destinationCombo.removeAllItems();
        List<DestinoEspacial> destinos = sistema.listarDestinos();
        for (DestinoEspacial destino : destinos) {
            destinationCombo.addItem(destino.getNombre());
        }
    }

    private void actualizarDetalles() {
        String seleccionado = (String) destinationCombo.getSelectedItem();
        if (seleccionado == null) {
            destinationDetailsArea.setText("No hay destino seleccionado.");
            return;
        }
        DestinoEspacial destino = sistema.buscarDestino(seleccionado);
        if (destino == null) {
            destinationDetailsArea.setText("Destino no encontrado.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("═════════════════════════════════════\n");
        sb.append("DESTINO: ").append(destino.getNombre()).append("\n");
        sb.append("═════════════════════════════════════\n\n");
        sb.append("Descripción:\n").append(destino.getDescripcion()).append("\n\n");
        sb.append("Duración: ").append(destino.getDuracionDias()).append(" días\n");
        sb.append("Precio: $").append(String.format("%.2f", destino.getPrecio())).append("\n");
        destinationDetailsArea.setText(sb.toString());
    }
}
