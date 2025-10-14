package gui;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import spiel.Game;


public class MainFrame extends JFrame {
    public static final String SCREEN_MENU = "menue"; // bleibt konsistent
    public static final String SCREEN_SPIEL = "spiel";
    public static final String SCREEN_MINIMAP = "minimap";

    private final CardLayout cards = new CardLayout();
    private final JPanel cardPanel = new JPanel(cards);

    public MainFrame() {
        
        setTitle("Dungeon Keepers");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panels vorbereiten – ACHTUNG: Felder verwenden (cards, cardPanel)
        HauptmenuePanel hauptmenuePanel = new HauptmenuePanel(cards, cardPanel);
        SpielPanel spielPanel = new SpielPanel();
        HomeTown homeTown = new HomeTown();
        MiniMap minimap = new MiniMap();

        // Panels zum CardLayout hinzufügen – auf DEM Feld 'cardPanel'
        cardPanel.add(hauptmenuePanel, SCREEN_MENU);
        cardPanel.add(spielPanel, SCREEN_SPIEL);
        cardPanel.add(homeTown, "home");
        cardPanel.add(minimap,SCREEN_MINIMAP);

        // Game-Referenzen auf GENAU diese Instanzen
        Game.setHauptmenuPanel(hauptmenuePanel);
        Game.setSpielPanel(spielPanel);

        setContentPane(cardPanel);
        setVisible(true);
    }

    public void showMenu() {
        System.out.println("[DBG] showMenu on cardPanel id=" + System.identityHashCode(cardPanel));
        cards.show(cardPanel, SCREEN_MENU);
        cardPanel.revalidate();
        cardPanel.repaint();
    }

    public void showSpiel() {
        System.out.println("[DBG] showSpiel on cardPanel id=" + System.identityHashCode(cardPanel));
        cards.show(cardPanel, SCREEN_MINIMAP);
        cardPanel.revalidate();
        cardPanel.repaint();
    }
}

// public static void main(String[] args) {
// SwingUtilities.invokeLater(() -> new MainFrame());
// }
