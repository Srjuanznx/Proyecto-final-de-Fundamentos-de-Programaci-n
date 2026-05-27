package vistas;

import java.awt.*;
import javax.swing.*;

public class Styles {

    // Personalizar la fuente del texto
    public static final Font mainFont = new Font("Arial", Font.BOLD, 16);
    public static final Font titleFont = new Font("Arial", Font.BOLD, 24);

    // Se personalizan los botones
    public static JButton createStyledButton(String title, String tooltip) {
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
