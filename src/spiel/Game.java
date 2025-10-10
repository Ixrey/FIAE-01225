package spiel;

import charakter.Gegner;
import charakter.Spieler;
import java.awt.CardLayout;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JPanel;
import stateManagement.GameStateManager;
import stateManagement.GameStates.GameClose;
import stateManagement.GameStates.GameRunning;
import stateManagement.GameStates.GameStart;

public class Game {

    private static GameStateManager stateManager;
    private static Spieler spieler;
    private static DemoDungeon demoDungeon;
    private static RunPhase run;
    CardLayout cardLayout;
    JPanel cardPanel;
   

    // Platzhalter bis echter kampf da ist
    public enum DemoKampfErgebnis {
        SPIELER_BESIEGT, GEGNER_BESIEGT
    }

    public static void setStateManager(GameStateManager manager) {
        stateManager = manager;
    }

    // Die Konsolenausgaben sind nur für Sie für die erste Orientierung.
    // Die Logik in den Methoden sollen Sie für Ihr Projekt anpassen.

    // Hier sollten sie erst beginnen, wenn Ihre Klassen und Methoden in Ihrer
    // Testklasse ohne Fehler funktionieren.

    public static void start() {
        System.out.println("Spiel gestartet");
        Scanner scanner = new Scanner(System.in);

        // Hier gehört alles was beim Starten dieses Spiels ausgewählt werden kann rein.
        // Beispielsweise "Neues Spiel","Spielstand laden","Spielstand löschen", "Spiel
        // schließen"
        // Falls kein Spielstand vorhanden ist, sollte die Option "Spielstand laden" und
        // "Spielstand löschen" nicht auswählbar sein
        // bzw. nicht in der Konsole angezeigt werden.

        // Bei den Optionen "Neues Spiel" und "Spielstand laden" muss der State auf
        // "GameRunning()" gesetzt werden.
        // stateManager.setState(new GameRunning());

        // Bei "Spiel schließen" muss der State auf GameClose() gesetzt werden.
        // stateManager.setState(new GameClose());
        
        System.out.println("[1] für Spiel starten.");
        System.out.println("[2] für Spiel beenden.");
        String test = scanner.nextLine();
        if (test.equals("1")) {
            spieler = new Spieler("Krasser Spieler", 100, 20, 1);
            demoDungeon = DemoDungeon.demo();
            stateManager.setState(new GameRunning());
        } else if (test.equals("2")) {
            stateManager.setState(new GameClose());
        }

        scanner.close();
    }

    public static void running() {
        // Hier gehört die Logik für Ihr Spiel hinein.
        // Der Spielstand sollte bereits aus ihrer Datenquelle geladen sein bzw. neu
        // erstellt worden sein.

        // Hinweis: Denken Sie daran das der Fortschritt des Spielers in einer
        // Datenquelle(json) gespeichert werden muss.
        // Beispielsweise nachdem ein Kampf erfolgreich abgeschlossen wurde.
        // Geben Sie dem Spieler die Möglichkeit, wenn er beispielsweise einen
        // Storyabschnitt beendet hat,
        // dass Spiel zu schließen oder ins Startmenu zurück zu kehren.
        // Spätestens dann muss der Fortschritt in der Datenquelle gespeichert werden,
        // sonst geht dieser verloren.

        System.out.println("Das Spiel ist im laufenden Zustand");
        naechsterSchritt();

    }

    public static void naechsterSchritt() {
        run = RunPhase.ERKUNDEN;

        while (true) {
            switch (run) {
                case ERKUNDEN:
                    DemoRaum raum = demoDungeon.naechsterRaum();
                    if (raum == null) {
                        run = RunPhase.RUN_ABGESCHLOSSEN;
                        break;
                    }
                    System.out.println("Raum: " + raum.name + " Raumtyp: " + raum.typ);
                    if (raum.istKampf()) {
                        run = RunPhase.KAMPF;
                        break;
                    } else {
                        System.out.println("Nix, weitermachen.");
                        run = RunPhase.ERKUNDEN;
                    }
                    break;

                case KAMPF:
                    Gegner gegner = DemoGegnerGenerator.demo();
                    DemoKampfErgebnis ergebnis = DemoKampfsystem.start(spieler, gegner);

                    if (ergebnis == DemoKampfErgebnis.SPIELER_BESIEGT) {
                        run = RunPhase.GAME_OVER;
                    } else {
                        spieler.bekommeErfahrung(gegner.getAusgabeErfahrungspunkte());
                        System.out.println("Kampf win.");
                        run = RunPhase.ERKUNDEN;
                    }
                    break;

                case GAME_OVER:
                    System.out.println("Verloren, du looser. Game Over");
                    stateManager.setState(new GameStart());
                    return;

                case RUN_ABGESCHLOSSEN:
                    System.out.println("Win, zurück zum Menü. Yay.");
                    stateManager.setState(new GameStart());
                    break;

            }
        }
    }

    public static void close() {
        // Hier wird Ihre Anwendung beendet.
        System.out.println("Das Spiel wird herunter gefahren");
        System.exit(0);
    }

    // platzhalter kampfsystem zum testen bis echte Klasse kommt. random chance auf
    // win
    static class DemoKampfsystem {
        private static Random rnd = new Random();

        static DemoKampfErgebnis start(Spieler spieler, Gegner gegner) {
            boolean spielerGewinnt = rnd.nextDouble() < 0.75;
            System.out.println("Kampf gegen " + gegner.getName());
            return spielerGewinnt ? DemoKampfErgebnis.GEGNER_BESIEGT : DemoKampfErgebnis.SPIELER_BESIEGT;
        }
    }

    // platzhalter welt zum testen bis echte klasse kommt.
    static class DemoDungeon {
        private List<DemoRaum> raumListe;
        private int zaehler = -1;

        private DemoDungeon(List<DemoRaum> raumListe) {
            this.raumListe = raumListe;
        }

        static DemoDungeon demo() {
            return new DemoDungeon(List.of(
                    new DemoRaum("Raum 1", "LEER"),
                    new DemoRaum("Raum 2", "KAMPF"),
                    new DemoRaum("Raum 3", "LEER"),
                    new DemoRaum("Raum 4", "KAMPF"),
                    new DemoRaum("Raum 4", "LEER")));
        }

        DemoRaum naechsterRaum() {
            zaehler++;
            if (zaehler >= raumListe.size()) {
                return null;
            }
            return raumListe.get(zaehler);
        }
    }

    static class DemoRaum {
        String name;
        String typ;

        DemoRaum(String name, String typ) {
            this.name = name;
            this.typ = typ;
        }

        boolean istKampf() {
            return "KAMPF".equals(typ);
        }
    }

    // platzhalter bis echte methode kommt
    static class DemoGegnerGenerator {
        private static Random rng = new Random();

        static Gegner demo() {
            return rng.nextBoolean() ? new Gegner("Goblin", 50, 10, 1) : new Gegner("Ork", 70, 12, 1);
        }
    }

}

// Mit diesen Methoden ändern Sie den state des Games und rufen so die
// unterschiedlichen Methoden der Klasse Game auf.

// Startet das Spiel
// stateManager.setState(new GameStart());
// Können Sie auch nutzen, wenn der Spieler die Möglichkeit erhalten soll ins
// Startmenu zu zurück zu kehren.

// Set den State auf GameRunning().
// stateManager.setState(new GameRunning());
// Die sollte ausgeführt werden, wenn der Spieler entweder ein "Neues Spiel"
// oder "Spielladen auswählt"

// Setzt den State auf GameClose() und beendet das Programm.
// stateManager.setState(new GameClose());
