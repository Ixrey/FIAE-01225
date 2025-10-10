package gui;

import java.awt.*;
import javax.swing.*;
import spiel.Game;
import stateManagement.GameStates.GameRunning;

public class HauptmenuePanel extends JPanel {
    private CardLayout cl;
    private JPanel cardPanel;
    private Image backgroundImage;
    private GridBagConstraints gbc;

    public HauptmenuePanel(CardLayout cl, JPanel cardPanel) {
        this.cl = cl;
        this.cardPanel = cardPanel;

        // === Hintergrundbild laden ===
        backgroundImage = new ImageIcon(getClass().getResource("/assets/bg.png")).getImage();
        gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0);

        zeigeHauptfenster();

    }

    public void zeigeHauptfenster() {
        JPanel buttonPanel = new JPanel();
        Font font = new Font("Latin Modern Math", Font.BOLD, 16);
        Font fontbnden = new Font("Caladea", Font.BOLD, 16);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false); // damit Hintergrund sichtbar bleibt

        Dimension btnSize = new Dimension(200, 40);

        JButton btnStart = createMenuButton("Neues Spiel starten", btnSize);
        JButton btnBeenden = createMenuButton("Beenden", btnSize);
        btnStart.setFont(font);
        btnBeenden.setFont(fontbnden);

        // Aktionen
        btnStart.addActionListener(e -> {
            // cl.show(cardPanel, "spiel");
            Game.getStateManager().setState(new GameRunning());
            var name = javax.swing.JOptionPane.showInputDialog("Enter a Username: ");
            Game.getSpieler().setName(name);

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
