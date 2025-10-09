package gui;

import java.awt.*;
import javax.swing.*;

public class HauptmenuePanel extends JPanel {

    private Image backgroundImage;

    public HauptmenuePanel(CardLayout cl, JPanel cardPanel) {
        // === Hintergrundbild laden ===
        backgroundImage = new ImageIcon(getClass().getResource("/assets/hauptmenu.jpg")).getImage();

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false); // damit Hintergrund sichtbar bleibt

        Dimension btnSize = new Dimension(200, 40);

        JButton btnStart = createMenuButton("Neues Spiel starten", btnSize);
        JButton btnBeenden = createMenuButton("Beenden", btnSize);

        // Aktionen
        btnStart.addActionListener(e -> {
            cl.show(cardPanel, "spiel");
            var name = javax.swing.JOptionPane.showInputDialog("Enter a Username: ");
            
        });
        
        btnBeenden.addActionListener(e -> System.exit(0));

        // Buttons hinzufügen
        buttonPanel.add(btnStart);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(btnBeenden);

        add(buttonPanel, gbc);
    }

    private JButton createMenuButton(String text, Dimension size) {
        JButton button = new JButton(text);
        button.setMaximumSize(size);
        button.setPreferredSize(size);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFocusPainted(false);
        return button;
    }

    // === Hintergrundbild zeichnen ===
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
