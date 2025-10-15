package spiel;

import charakter.Gegner;
import charakter.Gegnergenerator;
import charakter.Spieler;
import gui.HauptmenuePanel;
import gui.MainFrame;
import gui.MiniMap;
import gui.SpielPanel;
import java.awt.CardLayout;
import javax.swing.JPanel;
import kampf.Einzelkampf;
import stateManagement.GameStateManager;
import stateManagement.GameStates.GameStart;
import welt.Ebene;
import welt.Position;
import welt.Raum;

public class Spielablauf {

    private static GameStateManager stateManager;
    private static Spieler spieler;
    private static Gegner gegner;
    private static SpielPhase aktuellePhase;
    private static Einzelkampf kampfsystem;
    private static HauptmenuePanel hauptmenuPanel;
    private static MainFrame mainFrame;
    private static SpielPanel spielPanel;
    private static MiniMap miniMap;
    private static Position position;
    private static Ebene ebene;
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
        if (mainFrame == null) {
            mainFrame = new MainFrame();
        }
        mainFrame.showMenu();

    }

    public static void running() {

        spieler = new Spieler("Oraclez", 100, 30, 5, 1);
        position = new Position(ebene = new Ebene());
        mainFrame.showSpiel();
        aktuellePhase = SpielPhase.ERKUNDEN;

        System.out.println("Das Spiel ist im laufenden Zustand");

        verarbeiteNaechstenSchritt();

    }

    public static void verarbeiteNaechstenSchritt() {
        switch (aktuellePhase) {
            case ERKUNDEN:
                beginneErkundenPhase();
                break;

            case KAMPF:
                starteKampfPhase();
                break;

            case GAME_OVER:
                behandelGameOver();
                break;

            case RUN_ABGESCHLOSSEN:
                behandelRunAbgeschlossen();
                break;
        }
    }

    private static void behandelGameOver() {
        mainFrame.showGameOver();
        aktuellePhase = SpielPhase.GAME_OVER;
    }

    private static void beginneErkundenPhase() {
        aktuellePhase = SpielPhase.ERKUNDEN;
        miniMap.zeigeRaumUebersicht(spieler, position);
        mainFrame.showMinimap();
    }

    private static void starteKampfPhase() {
        aktuellePhase = SpielPhase.KAMPF;
        Raum aktuellerRaum = getAktuellenRaum();

        switch (aktuellerRaum.getTyp()) {
            case "gegner":
                gegner = Gegnergenerator.zufallsGegnerErschaffen(spieler);
                break;
            case "boss":
                gegner = Gegnergenerator.bossErschaffen(spieler);
                break;
        }

        kampfsystem = new Einzelkampf(spieler, gegner);
        kampfsystem.addKampfListener(Spielablauf::kampfBeendet);
        spielPanel.zeigeKampfFenster(spieler, gegner, kampfsystem);
        mainFrame.showSpiel();
    }

    private static void kampfBeendet(boolean spielerHatGewonnen) {
        Raum raum = getAktuellenRaum();
        if (spielerHatGewonnen) {
            spieler.bekommeErfahrung(gegner.getAusgabeErfahrungspunkte());
            raum.typWechsel();
            proceedNachKampf(spielerHatGewonnen);
        } else {
            aktuellePhase = SpielPhase.GAME_OVER;
            verarbeiteNaechstenSchritt();
        }
    }

    private static void proceedNachKampf(boolean spielerHatGewonnen) {
        boolean istBossBesiegt = position.getAktuellerRaum().getTyp().contains("bossBesiegt");

        if (spielerHatGewonnen) {

            if (position.istLetzterRaum() && istBossBesiegt) {
                behandelRunAbgeschlossen();
            } else {
                aktuellePhase = SpielPhase.ERKUNDEN;
                verarbeiteNaechstenSchritt();
            }
        } else {
            behandelGameOver();
        }
    }

    public static void behandelRunAbgeschlossen() {
        aktuellePhase = SpielPhase.RUN_ABGESCHLOSSEN;
        stateManager.setState(new GameStart());
        mainFrame.showMenu();

        spieler = null;
        ebene = null;
        position = null;
        // mainFrame.showRunFinished();

    }

    private static Raum getAktuellenRaum() {
        return position.getRaumListe().get(position.getAktuellePosition());
    }

    public static void behandelWeiterButton() {
        if (aktuellePhase != SpielPhase.ERKUNDEN || position == null) {
            return;
        }

        if (!position.istLetzterRaum()) {
            position.naechsterRaum();
        }

        Raum aktuellerRaum = getAktuellenRaum();
        String typ = aktuellerRaum.getTyp();

        if ("gegner".equals(typ) || "boss".equals(typ)) {
            aktuellePhase = SpielPhase.KAMPF;
        } else if (position.istLetzterRaum() && typ.contains("bossBesiegt")) {
            aktuellePhase = SpielPhase.RUN_ABGESCHLOSSEN;
        } else {
            aktuellePhase = SpielPhase.ERKUNDEN;
        }

        verarbeiteNaechstenSchritt();
    }

    public static void setHauptmenuPanel(HauptmenuePanel hauptmenu) {
        hauptmenuPanel = hauptmenu;
    }

    public static void setSpielPanel(SpielPanel spiel) {
        spielPanel = spiel;
    }

    public static void setMiniMap(MiniMap mMap) {
        miniMap = mMap;
    }

    public static void close() {
        // Hier wird Ihre Anwendung beendet.
        System.out.println("Das Spiel wird herunter gefahren");
        System.exit(0);
    }

}
