package vistas;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.Reserva;
import sistema.SistemaReservas;

public class VentanaReservas extends JFrame {
    private final SistemaReservas sistema;
    private JTable reservasTable;
    private JLabel countLabel;

    public VentanaReservas(SistemaReservas sistema) {
        this.sistema = sistema;
        setTitle("Ver Reservas Realizadas");
        setSize(800, 500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        add(createPanel(), BorderLayout.CENTER);
        setVisible(true);
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        countLabel = new JLabel();
        countLabel.setFont(Styles.mainFont);
        topPanel.add(countLabel);

        String[] columnNames = {"Usuario", "Correo", "Destino", "Fecha de Viaje", "Precio"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        cargarReservas(model);

        reservasTable = new JTable(model);
        reservasTable.setFont(Styles.mainFont);
        reservasTable.setRowHeight(25);
        reservasTable.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(reservasTable);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void cargarReservas(DefaultTableModel model) {
        List<Reserva> reservas = sistema.listarReservas();
        
        for (Reserva reserva : reservas) {
            Object[] row = {
                    reserva.getUsuario().getNombre(),
                    reserva.getUsuario().getCorreo(),
                    reserva.getDestino().getNombre(),
                    reserva.getFechaViaje(),
                    "$" + String.format("%.2f", reserva.getDestino().getPrecio())
            };
            model.addRow(row);
        }

        countLabel.setText("Total de reservas: " + reservas.size());
        if (reservas.isEmpty()) {
            countLabel.setForeground(Color.RED);
            countLabel.setText("No hay reservas realizadas aún.");
        } else {
            countLabel.setForeground(new Color(0, 100, 0));
        }
    }
}
