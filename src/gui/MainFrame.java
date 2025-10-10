package gui;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import spiel.Game;

// public class MainFrame extends JFrame {
//     public static final String SCREEN_MENU = "menue";
//     public static final String SCREEN_SPIEL = "spiel";

//     private final CardLayout cards = new CardLayout();
//     private final JPanel cardPanel = new JPanel(cards);

// public MainFrame() {
// System.out.println("[DBG] MainFrame ctor id=" +
// System.identityHashCode(this));
// setTitle("Dungeon Keepers");
// setSize(800, 800);
// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// setLocationRelativeTo(null);

// // CardLayout vorbereitung

// CardLayout cardLayout = new CardLayout();
// JPanel cardPanel = new JPanel(cardLayout);

// // Panels vorbereiten

// HauptmenuePanel hauptmenuePanel = new HauptmenuePanel(cardLayout, cardPanel);
// SpielPanel spielPanel = new SpielPanel();
// HomeTown homeTown = new HomeTown();

// // Panels zum CardLayout hinzufügen

// cardPanel.add(hauptmenuePanel, MainFrame.SCREEN_MENU);
// cardPanel.add(spielPanel, MainFrame.SCREEN_SPIEL);
// cardPanel.add(homeTown, "home");
// Game.setHauptmenuPanel(hauptmenuePanel);
// Game.setSpielPanel(spielPanel);

// setContentPane(cardPanel);
// setVisible(true);
// }

// public void showMenu() {
// cards.show(cardPanel, SCREEN_MENU);
// System.out.println("[DBG] showMenu on cardPanel id=" +
// System.identityHashCode(cardPanel));
// cardPanel.revalidate();
// cardPanel.repaint();
// }

// public void showSpiel() {
// cards.show(cardPanel, SCREEN_SPIEL);
// System.out.println("[DBG] showMenu on cardPanel id=" +
// System.identityHashCode(cardPanel));
// cardPanel.revalidate();
// cardPanel.repaint();

// }
// }
public class MainFrame extends JFrame {
    public static final String SCREEN_MENU = "menue"; // bleibt konsistent
    public static final String SCREEN_SPIEL = "spiel";

    private final CardLayout cards = new CardLayout();
    private final JPanel cardPanel = new JPanel(cards);

    public MainFrame() {
        System.out.println("[DBG] MainFrame ctor id=" + System.identityHashCode(this));
        setTitle("Dungeon Keepers");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panels vorbereiten – ACHTUNG: Felder verwenden (cards, cardPanel)
        HauptmenuePanel hauptmenuePanel = new HauptmenuePanel(cards, cardPanel);
        SpielPanel spielPanel = new SpielPanel();
        HomeTown homeTown = new HomeTown();

        // Panels zum CardLayout hinzufügen – auf DEM Feld 'cardPanel'
        cardPanel.add(hauptmenuePanel, SCREEN_MENU);
        cardPanel.add(spielPanel, SCREEN_SPIEL);
        cardPanel.add(homeTown, "home");

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
        cards.show(cardPanel, SCREEN_SPIEL);
        cardPanel.revalidate();
        cardPanel.repaint();
    }
}

// public static void main(String[] args) {
// SwingUtilities.invokeLater(() -> new MainFrame());
// }
