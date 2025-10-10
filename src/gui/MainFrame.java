package gui;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import spiel.Game;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Dungeon Keepers");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // CardLayout vorbereitung

        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        // Panels vorbereiten

        HauptmenuePanel hauptmenuePanel = new HauptmenuePanel(cardLayout, cardPanel);
        SpielPanel spielPanel = new SpielPanel();
        HomeTown homeTown = new HomeTown();

        // Panels zum CardLayout hinzufügen

        cardPanel.add(hauptmenuePanel, "menue");
        cardPanel.add(spielPanel, "spiel");
        cardPanel.add(homeTown, "home");
        Game.setHauptmenuPanel(hauptmenuePanel);

        setContentPane(cardPanel);
        setVisible(true);
    }

    // public static void main(String[] args) {
    // SwingUtilities.invokeLater(() -> new MainFrame());
    // }

}
