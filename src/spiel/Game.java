package spiel;

import java.util.Scanner;

import StateManagement.GameStateManager;
import StateManagement.GameStates.GameRunning;

public class Game {

    private static GameStateManager stateManager;

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

        System.out.println("'Start' für Spiel starten.");
        String test = scanner.nextLine();
        if (test.equals("Start")) {
            stateManager.setState(new GameRunning());
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
    }

    public static void close() {
        // Hier wird Ihre Anwendung beendet.
        System.out.println("Das Spiel wird herunter gefahren");
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
