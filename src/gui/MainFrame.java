package gui;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import spiel.Game;


public class MainFrame extends JFrame {
    public static final String SCREEN_MENU = "menue"; // bleibt konsistent
    public static final String SCREEN_SPIEL = "spiel";
    public static final String SCREEN_GAMEOVER = "gameover";

    private final CardLayout cards = new CardLayout();
    private final JPanel cardPanel = new JPanel(cards);

    public MainFrame() {
        
        setTitle("Dungeon Keepers");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panels vorbereiten – ACHTUNG: Felder verwenden (cards, cardPanel)
        HauptmenuePanel hauptmenuePanel = new HauptmenuePanel(cards, cardPanel);
        SpielPanel spielPanel = new SpielPanel();
        GameOver gameOver = new GameOver(cards, cardPanel);

        // Panels zum CardLayout hinzufügen – auf DEM Feld 'cardPanel'
        cardPanel.add(hauptmenuePanel, SCREEN_MENU);
        cardPanel.add(spielPanel, SCREEN_SPIEL);
        cardPanel.add(gameOver, SCREEN_GAMEOVER);

        // Game-Referenzen auf GENAU diese Instanzen
        Game.setHauptmenuPanel(hauptmenuePanel);
        Game.setSpielPanel(spielPanel);

        setContentPane(cardPanel);
        setVisible(true);
    }

    public void showMenu() {
        
        cards.show(cardPanel, SCREEN_MENU);
        cardPanel.revalidate();
        cardPanel.repaint();
    }

    public void showSpiel() {
        
        cards.show(cardPanel, SCREEN_SPIEL);
        cardPanel.revalidate();
        cardPanel.repaint();
    }

    public void showGameOver() {
        
        cards.show(cardPanel, SCREEN_GAMEOVER);
        cardPanel.revalidate();
        cardPanel.repaint();
    }


}

// public static void main(String[] args) {
// SwingUtilities.invokeLater(() -> new MainFrame());
// }
