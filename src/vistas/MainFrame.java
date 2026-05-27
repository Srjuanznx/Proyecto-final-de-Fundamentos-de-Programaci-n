package vistas;

import java.awt.*;
import javax.swing.*;
import sistema.SistemaReservas;

public class MainFrame extends JFrame {
    private final SistemaReservas sistema;

    // Declaracion del frame principal
    public MainFrame() {
        sistema = new SistemaReservas();
        setTitle("Sistema de Reservas de Viajes Espaciales");
        setSize(600, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));
        add(createMainPanel(), BorderLayout.CENTER);
        setVisible(true);
    }

    // Panel Norte del frame principal
    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(240, 248, 255));

        JLabel titleLabel = new JLabel("Sistema de Reservas Espaciales");
        titleLabel.setFont(Styles.titleFont);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(new Color(0, 102, 204));

        JPanel buttonsPanel = createButtonsPanel();

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(buttonsPanel, BorderLayout.CENTER);

        return panel;
    }

    // Panel Central Botones de menu
    private JPanel createButtonsPanel() {
        // Botones
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 20));
        panel.setOpaque(false);

        JButton registroButton = Styles.createStyledButton("Registrar Usuario", "Crea una nueva cuenta de usuario");
        registroButton.addActionListener(e -> new VentanaRegistro(sistema));

        JButton destinosButton = Styles.createStyledButton("Ver Destinos", "Explora los destinos disponibles");
        destinosButton.addActionListener(e -> new VentanaDestinos(sistema));

        JButton reservaButton = Styles.createStyledButton("Crear Reserva", "Realiza una nueva reserva de viaje");
        reservaButton.addActionListener(e -> new VentanaReserva(sistema));

        JButton verReservasButton = Styles.createStyledButton("Ver Mis Reservas",
                "Consulta todas las reservas realizadas");
        verReservasButton.addActionListener(e -> new VentanaReservas(sistema));

        // Agregar botones al panel central
        panel.add(registroButton);
        panel.add(destinosButton);
        panel.add(reservaButton);
        panel.add(verReservasButton);

        return panel;
    }
}
