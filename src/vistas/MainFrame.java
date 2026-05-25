package vistas;

import java.awt.*;
import javax.swing.*;
import sistema.SistemaReservas;

public class MainFrame extends JFrame {
    private final Font mainFont = new Font("Segoe Print", Font.BOLD, 16);
    private final Font titleFont = new Font("Segoe Print", Font.BOLD, 24);
    private final SistemaReservas sistema;

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

    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(240, 248, 255));

        JLabel titleLabel = new JLabel("🚀 Sistema de Reservas Espaciales");
        titleLabel.setFont(titleFont);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(new Color(0, 102, 204));

        JPanel buttonsPanel = createButtonsPanel();

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(buttonsPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createButtonsPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 20));
        panel.setOpaque(false);

        JButton registroButton = createStyledButton("Registrar Usuario", "Crea una nueva cuenta de usuario");
        registroButton.addActionListener(e -> new VentanaRegistro(sistema));

        JButton destinosButton = createStyledButton("Ver Destinos", "Explora los destinos disponibles");
        destinosButton.addActionListener(e -> new VentanaDestinos(sistema));

        JButton reservaButton = createStyledButton("Crear Reserva", "Realiza una nueva reserva de viaje");
        reservaButton.addActionListener(e -> new VentanaReserva(sistema));

        JButton verReservasButton = createStyledButton("Ver Mis Reservas", "Consulta todas las reservas realizadas");
        verReservasButton.addActionListener(e -> new VentanaReservas(sistema));

        panel.add(registroButton);
        panel.add(destinosButton);
        panel.add(reservaButton);
        panel.add(verReservasButton);

        return panel;
    }

    private JButton createStyledButton(String title, String tooltip) {
        JButton button = new JButton(title);
        button.setFont(mainFont);
        button.setPreferredSize(new Dimension(300, 80));
        button.setFocusPainted(false);
        button.setBackground(new Color(0, 153, 255));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setToolTipText(tooltip);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 102, 204));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 153, 255));
            }
        });

        return button;
    }
}
