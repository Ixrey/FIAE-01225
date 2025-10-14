package gui;

import charakter.Spieler;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import spiel.Game;
import stateManagement.GameStates.GameRunning;

public class GameOver extends JPanel {
    private CardLayout cl;
    private JPanel cardPanel;
    private Image backgroundImage;
    private GridBagConstraints gbc;
    private Spieler sp;


    public GameOver(CardLayout cl, JPanel cardPanel) {
        this.cl = cl;
        this.cardPanel = cardPanel;

        // === Hintergrundbild laden ===
        backgroundImage = new ImageIcon(getClass().getResource("/assets/GameOver.png")).getImage();
        gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0);

        zeigeGameOver(sp);

    }

    public void zeigeGameOver(Spieler sp) {
        JPanel buttonPanel = new JPanel();
        Font font = new Font("Courier New", Font.BOLD, 16);
        Font fontbnden = new Font("Courier New", Font.BOLD, 16);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false); // damit Hintergrund sichtbar bleibt

        Dimension btnSize = new Dimension(200, 40);

        JButton btnStart = createMenuButton("Erneuter Versuch", btnSize);
        JButton btnBeenden = createMenuButton("Beenden", btnSize);
        btnStart.setFont(font);
        btnBeenden.setFont(fontbnden);

        // Aktionen
        btnStart.addActionListener(e -> {
            Game.getStateManager().setState(new GameRunning());

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
