package spiel;

import charakter.Gegner;
import charakter.Gegnergenerator;
import charakter.Spieler;
import gui.HauptmenuePanel;
import gui.MainFrame;
import gui.SpielPanel;
import java.awt.CardLayout;
import javax.swing.JPanel;
import kampf.Einzelkampf;
import kampf.KampfListener;
import spiel.test.TestGameInhalt;
import spiel.test.TestGameInhalt.DemoRaum;
import spiel.test.TestGameInhalt.TestDungeon;
import stateManagement.GameStateManager;
import stateManagement.GameStates.GameStart;

public class Spielablauf {

    private static GameStateManager stateManager;
    private static Spieler spieler;
    private static Gegner gegner;
    private static TestDungeon demoDungeon;
    private static SpielPhase aktuellePhase;
    private static Einzelkampf kampfsystem;
    private static HauptmenuePanel hauptmenuPanel;
    private static MainFrame mainFrame;
    private static SpielPanel spielPanel;
    CardLayout cardLayout;
    JPanel cardPanel;

    public static void setStateManager(GameStateManager manager) {
        stateManager = manager;
    }

    public static GameStateManager getStateManager() {
        return stateManager;
    }

    public static Spieler getSpieler() {
        return spieler;
    }

    public static void start() {
        System.out.println("Spiel gestartet");
        mainFrame = new MainFrame();

        mainFrame.showMenu();

    }

    public static void running() {

        spieler = new Spieler("Oraclez", 100, 20, 1);
        mainFrame.showSpiel();
        aktuellePhase = SpielPhase.KAMPF;

        System.out.println("Das Spiel ist im laufenden Zustand");

        if (demoDungeon == null) {
            demoDungeon = TestGameInhalt.erstelleTestDungeon();
        }
        verarbeiteNaechstenSchritt();

    }

    public static void verarbeiteNaechstenSchritt() {

        switch (aktuellePhase) {
            case ERKUNDEN:
                DemoRaum raum = demoDungeon.naechsterRaum();
                if (raum == null) {
                    aktuellePhase = SpielPhase.RUN_ABGESCHLOSSEN;
                    break;
                }
                System.out.println("Raum: " + raum.getName() + " Raumtyp: " + raum.getTyp());
                if (raum.istKampf()) {
                    aktuellePhase = SpielPhase.KAMPF;
                    break;
                } else {
                    System.out.println("Nix, weitermachen.");
                    aktuellePhase = SpielPhase.ERKUNDEN;
                }
                break;

            case KAMPF:
                gegner = Gegnergenerator.zufallsGegnerErschaffen(spieler);
                kampfsystem = new Einzelkampf(spieler, gegner);

                kampfsystem.addKampfListener(new KampfListener() {
                    @Override
                    public void kampfBeendet(boolean spielerHatGewonnen) {
                        if (spielerHatGewonnen) {
                            spieler.bekommeErfahrung(gegner.getAusgabeErfahrungspunkte());
                            aktuellePhase = SpielPhase.ERKUNDEN;
                        } else {
                            aktuellePhase = SpielPhase.GAME_OVER;
                        }
                        verarbeiteNaechstenSchritt();
                    }
                });
                spielPanel.zeigeKampfFenster(spieler, gegner, kampfsystem);

                break;

            case GAME_OVER:
                System.out.println("Verloren, du Loser. Game Over");
                mainFrame.showGameOver();
                // stateManager.setState(new GameStart());
                return;

            case RUN_ABGESCHLOSSEN:
                System.out.println("Win, zurück zum Menü. Yay.");
                stateManager.setState(new GameStart());
                break;

        }
    }

    public static void setHauptmenuPanel(HauptmenuePanel hauptmenu) {
        hauptmenuPanel = hauptmenu;
    }

    public static void setSpielPanel(SpielPanel spiel) {
        spielPanel = spiel;
    }

    public static void close() {
        // Hier wird Ihre Anwendung beendet.
        System.out.println("Das Spiel wird herunter gefahren");
        System.exit(0);
    }

}
