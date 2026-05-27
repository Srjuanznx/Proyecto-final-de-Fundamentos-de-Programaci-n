package vistas;

import modelo.Usuario;
import sistema.SistemaReservas;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistro extends JFrame {
    private final SistemaReservas sistema;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField preferencesField;
    private JLabel messageLabel;

    // Declaracion del frame de registro
    public VentanaRegistro(SistemaReservas sistema) {
        this.sistema = sistema;
        setTitle("Registro de Usuario");
        setSize(500, 350);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        add(createPanel(), BorderLayout.CENTER);
        setVisible(true);
    }

    // Declaracion de paneles
    private JPanel createPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Declaracion panel de campos
        JPanel fieldsPanel = new JPanel(new GridLayout(3, 2, 8, 8));
        fieldsPanel.setBorder(BorderFactory.createTitledBorder("Datos del usuario"));

        // Campo nombre
        JLabel nameLabel = new JLabel("Nombre completo:");
        nameLabel.setFont(Styles.mainFont);
        nameField = new JTextField();
        nameField.setFont(Styles.mainFont);

        // Campo Correo
        JLabel emailLabel = new JLabel("Correo electrónico:");
        emailLabel.setFont(Styles.mainFont);
        emailField = new JTextField();
        emailField.setFont(Styles.mainFont);

        // Campo Preferencia
        JLabel preferencesLabel = new JLabel("Preferencias:");
        preferencesLabel.setFont(Styles.mainFont);
        preferencesField = new JTextField();
        preferencesField.setFont(Styles.mainFont);

        // Se agregan los titulos y los campos de registro al panel central
        fieldsPanel.add(nameLabel);
        fieldsPanel.add(nameField);
        fieldsPanel.add(emailLabel);
        fieldsPanel.add(emailField);
        fieldsPanel.add(preferencesLabel);
        fieldsPanel.add(preferencesField);

        // Boton de registro
        JButton registerButton = new JButton("Registrar");
        registerButton.setFont(Styles.mainFont);
        registerButton.addActionListener(e -> registrarUsuario());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(registerButton);

        // Mensaje del norte
        messageLabel = new JLabel("Completa los datos y presiona Registrar.");
        messageLabel.setFont(Styles.mainFont);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);

        // Se agregan los paneles al frame
        panel.add(fieldsPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        panel.add(messageLabel, BorderLayout.NORTH);

        return panel;
    }

    // Funcion de Registro de usuario
    private void registrarUsuario() {
        try {
            String nombre = nameField.getText();
            String correo = emailField.getText();
            String preferencias = preferencesField.getText();

            Usuario usuario = sistema.registrarUsuario(nombre, correo, preferencias);
            messageLabel.setText("Usuario registrado: " + usuario.getNombre());
            messageLabel.setForeground(new Color(0, 100, 0));
            nameField.setText("");
            emailField.setText("");
            preferencesField.setText("");
        } /* Encontrar errores en el sistema */catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de registro", JOptionPane.ERROR_MESSAGE);
            messageLabel.setText("Error al registrar.");
            messageLabel.setForeground(Color.RED);
        }
    }
}
