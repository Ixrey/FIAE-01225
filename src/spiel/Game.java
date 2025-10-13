package spiel;

import charakter.Gegner;
import charakter.Spieler;
import gui.HauptmenuePanel;
import gui.MainFrame;
import gui.SpielPanel;
import java.awt.CardLayout;
import javax.swing.JPanel;
import kampf.Einzelkampf;
import stateManagement.GameStateManager;
import stateManagement.GameStates.GameStart;
import spiel.demo.DemoGameInhalt;
import spiel.demo.DemoGameInhalt.TestDungeon;
import spiel.demo.DemoGameInhalt.DemoRaum;

public class Game {

    private static GameStateManager stateManager;
    private static Spieler spieler;
    private static Gegner gegner;
    private static TestDungeon demoDungeon;
    private static RunPhase run;
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
        run = RunPhase.KAMPF_SETUP;

        System.out.println("Das Spiel ist im laufenden Zustand");

        if (demoDungeon == null) {
            demoDungeon = DemoGameInhalt.erstelleTestDungeon();
        }
        naechsterSchritt();

    }

    public static void naechsterSchritt() {

        switch (run) {
            case ERKUNDEN:
                DemoRaum raum = demoDungeon.naechsterRaum();
                if (raum == null) {
                    run = RunPhase.RUN_ABGESCHLOSSEN;
                    break;
                }
                System.out.println("Raum: " + raum.getName() + " Raumtyp: " + raum.getTyp());
                if (raum.istKampf()) {
                    run = RunPhase.KAMPF_SETUP;
                    break;
                } else {
                    System.out.println("Nix, weitermachen.");
                    run = RunPhase.ERKUNDEN;
                }
                break;

            case KAMPF_SETUP:
                gegner = DemoGameInhalt.erstelleRandomTestGegner();
                kampfsystem = new Einzelkampf(spieler, gegner);
                spielPanel.zeigeKampfFenster(spieler, gegner, kampfsystem);
                Einzelkampf.neuesSpiel(spieler, gegner);
                run = RunPhase.KAMPF;
                break;

            case KAMPF:
                if (kampfsystem.pruefeKampfende()) {
                    if (!kampfsystem.hatSpielerGewonnen()) {
                        run = RunPhase.GAME_OVER;
                    } else {
                        spieler.bekommeErfahrung(gegner.getAusgabeErfahrungspunkte());
                        System.out.println("Kampf win.");
                        run = RunPhase.ERKUNDEN;
                    }
                }
                break;

            case GAME_OVER:
                System.out.println("Verloren, du Loser. Game Over");
                stateManager.setState(new GameStart());
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
