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
import kampf.KampfListener;
import spiel.test.TestGameInhalt;
import spiel.test.TestGameInhalt.DemoRaum;
import spiel.test.TestGameInhalt.TestDungeon;
import stateManagement.GameStateManager;
import stateManagement.GameStates.GameStart;
import welt.Ebene;
import welt.Position;
import welt.Raum;

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
        mainFrame = new MainFrame();

        mainFrame.showMenu();

    }

    public static void running() {

        spieler = new Spieler("Oraclez", 10, 100, 1);
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
                break;
        }
    }

    private static void behandelGameOver() {
        mainFrame.showGameOver();
    }

    private static void beginneErkundenPhase() {
        aktuellePhase = SpielPhase.ERKUNDEN;
        Raum raum = holeAktuellenRaum();
        miniMap.zeigeRaumUebersicht(spieler, position);
        mainFrame.showMinimap();
    }

    private static void starteKampfPhase() {
        aktuellePhase = SpielPhase.KAMPF;
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
    }

    private static void kampfBeendet(boolean spielerHatGewonnen) {
        Raum raum = holeAktuellenRaum();
        if (spielerHatGewonnen) {
            spieler.bekommeErfahrung(gegner.getAusgabeErfahrungspunkte());
            raum.typWechsel();
            proceedNachKampf();
        } else {
            aktuellePhase = SpielPhase.GAME_OVER;
            verarbeiteNaechstenSchritt();
        }
    }

    private static Raum holeAktuellenRaum() {
        return position.getRaumListe().get(position.getAktuellePosition());
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

/*
 * ich möchte im spielablauf nun das erkunden hinzufügen. wie mach ich das am
 * besten sodass der statewechsel smooth läuft. ich möchte dass du mir das sagst
 * und nicht selber schreibst
 * 
 * 
 * 
 * ich möchte im spielablauf nun das erkunden hinzufügen. wie mach ich das am
 * besten sodass der statewechsel smooth läuft. ich möchte dass du mir das sagst
 * und nicht selber schreibst
 * 
 * 
 * Empfohlene Schritte
 * 
 * Nutze die MiniMap, die im Frame gebaut wird (src/gui/MainFrame.java:29),
 * anstatt in Spielablauf.running() jedes Mal eine neue Instanz zu erzeugen
 * (src/spiel/Spielablauf.java:61). Ergänze dafür einen Setter setMiniMap(...)
 * und ruf ihn aus dem Frame heraus auf; so arbeitet das CardLayout immer mit
 * derselben Komponente.
 * Zerlege den switch in verarbeiteNaechstenSchritt()
 * (src/spiel/Spielablauf.java:76-128) in kleine Hilfsmethoden wie
 * beginneErkundenPhase() und starteKampf(). In der ERKUNDEN-Variante solltest
 * du nur UI vorbereiten (Minimap aktualisieren, Ansicht umschalten) und kein
 * demoDungeon.naechsterRaum() mehr ziehen – der eigentliche Phasenwechsel
 * passiert erst über den Button.
 * Gib dem MiniMap-Button btnWeiter (src/gui/MiniMap.java:24-45) einen Listener,
 * der einen Callback (Runnable/Consumer<DemoRaum>) benutzt. Die MiniMap kennt
 * dann nicht den Spielablauf, sondern löst nur listener.onNextRoom() aus; der
 * Spielablauf verkabelt diese Methode beim Setzen der MiniMap.
 * Im Callback: DemoRaum raum = demoDungeon.naechsterRaum(); und
 * position.naechsterRaum(); (src/welt/Position.java:19-25). Danach setzt du
 * aktuellePhase abhängig vom Raumtyp (KAMPF, RUN_ABGESCHLOSSEN, erneutes
 * ERKUNDEN) und rufst wieder verarbeiteNaechstenSchritt(). Für Kämpfe bleibt
 * die bestehende Logik (src/spiel/Spielablauf.java:100-117) erhalten, aber du
 * wechselst erst nach dem Button-Klick in diese Phase.
 * Achte darauf, alle UI-Updates über SwingUtilities.invokeLater(...) zu
 * triggern (z. B. im KampfListener und beim Minimap-Callback), damit die
 * Übergänge ruckelfrei bleiben und du keine rekursiven Aufrufe aus
 * Eventhandlern heraus produzierst.
 * Wenn du das umgesetzt hast, lohnt es sich, einmal komplett durch einen
 * Dungeon zu klicken, um zu prüfen, ob Minimap, Kampf und Rücksprung ins Menü
 * ohne Doppelklicks oder verlorene Listener funktionieren.
 * 
 * also meinst du mit aus dem frame heraus soll ich mainFrame.showMinimap
 * machen? oder was genau meinst du? außerdem kannst du mir einmal zeigen wie
 * die hilfsmethoden aussehen sollten für erkunden und kampf? ich möchte
 * ebenfalls von dem demo dungeon weg und die klassen aus dem package welt
 * benutzen, wenns geht ohne listener
 * 
 * 
 * MainFrame-Anbindung
 * 
 * Die Idee war, dass MainFrame die einzige Instanz der MiniMap erzeugt
 * (src/gui/MainFrame.java:24-35) und sie direkt an den Spielablauf übergibt
 * (Spielablauf.setMiniMap(minimap);). Du rufst mainFrame.showMinimap() dann
 * weiterhin aus Spielablauf heraus – nur dass dort eben die vom Frame
 * verwaltete Minimap benutzt wird.
 * Hilfsmethoden im Spielablauf
 * 
 * Beispielskizze, wie du den switch entzerren kannst
 * (src/spiel/Spielablauf.java):
 * private static void beginneErkundenPhase() {
 * aktuellePhase = SpielPhase.ERKUNDEN;
 * Raum raum = holeAktuellenRaum();
 * miniMap.refresh(spieler, position, raum); // neue refresh-Methode einplanen
 * mainFrame.showMinimap();
 * }
 * 
 * private static void starteKampfPhase() {
 * aktuellePhase = SpielPhase.KAMPF;
 * gegner = Gegnergenerator.zufallsGegnerErschaffen(spieler);
 * kampfsystem = new Einzelkampf(spieler, gegner);
 * kampfsystem.addKampfListener(Spielablauf::kampfBeendet); // vorhandenen
 * Listener wiederverwenden
 * spielPanel.zeigeKampfFenster(spieler, gegner, kampfsystem);
 * }
 * 
 * private static void kampfBeendet(boolean spielerHatGewonnen) {
 * Raum raum = holeAktuellenRaum();
 * if (spielerHatGewonnen) {
 * spieler.bekommeErfahrung(gegner.getAusgabeErfahrungspunkte());
 * raum.typWechsel(); // markiert gegner/boss als besiegt
 * proceedNachKampf();
 * } else {
 * aktuellePhase = SpielPhase.GAME_OVER;
 * verarbeiteNaechstenSchritt();
 * }
 * }
 * 
 * private static Raum holeAktuellenRaum() {
 * return position.getRaumListe().get(position.getAktuellePosition());
 * }
 * verarbeiteNaechstenSchritt() ruft dann nur noch beginneErkundenPhase() oder
 * starteKampfPhase() und kümmert sich um Endzustände (RUN_ABGESCHLOSSEN,
 * GAME_OVER).
 * Welt statt DemoDungeon
 * 
 * Initialisiere im running()-Einstieg nur noch ebene = new Ebene(); position =
 * new Position(ebene);.
 * Für „weiter“-Aktionen nutzt du position.naechsterRaum();
 * (src/welt/Position.java:19-25). Danach prüfst du
 * holeAktuellenRaum().getTyp():
 * gegner/boss → starteKampfPhase().
 * leer/gegnerBesiegt/bossBesiegt → beginneErkundenPhase().
 * position.istLetzterRaum() und Raum schon besiegt → RUN_ABGESCHLOSSEN.
 * Button ohne extra Listener-Interface
 * 
 * Wenn du wirklich keine zusätzlichen Listener-Strukturen möchtest, reicht im
 * MiniMap-Button ein direkter Aufruf:
 * btnWeiter.addActionListener(e -> Spielablauf.handleWeiterButton());
 * handleWeiterButton() im Spielablauf führt dann position.naechsterRaum() aus
 * und entscheidet sofort, ob beginneErkundenPhase() oder starteKampfPhase()
 * dran ist. Damit bleibt alles zentral und du musst nur die MiniMap um eine
 * refresh(...)-Methode erweitern, die die vorhandenen UI-Elemente neu aufbaut.
 * Als nächstes einmal den gesamten Flow durchklicken: Start → mehrere leere
 * Räume → Kampf → zurück auf Minimap → Boss → Run-Abschluss. Achte darauf, dass
 * MiniMap.refresh wirklich immer vor dem Umschalten über
 * mainFrame.showMinimap() läuft und der Button nicht mehrfach Aktionen stapelt.
 * 
 * aber warum lagern wir kampfbeendet aus? das ist doch die methode von der
 * klasse des kampflisteners? kannst du auch mal aussehen wie der switch dann
 * aussehen würde?
 * 
 * 
 * Der KampfListener verlangt zwar eine kampfbBeendet(boolean)-Methode, aber du
 * musst sie nicht anonym definieren. Du kannst eine private Methode im
 * Spielablauf mit derselben Signatur hinterlegen und sie beim Registrieren
 * referenzieren (kampfsystem.addKampfListener(Spielablauf::kampfBeendet);). So
 * bleibt der Listener-Block schlank und die Nach-Kampf-Logik liegt an zentraler
 * Stelle, wo du z. B. Erfahrung verbuchen, Raumstatus umschalten und die
 * nächste Phase anschieben kannst.
 * 
 * Gedankliches Zielbild für den switch nach der Aufteilung:
 * 
 * public static void verarbeiteNaechstenSchritt() {
 * switch (aktuellePhase) {
 * case ERKUNDEN:
 * beginneErkundenPhase(); // Minimap aktualisieren, View wechseln
 * break;
 * 
 * case KAMPF:
 * starteKampfPhase(); // Gegner aufbauen, Kampfpanel zeigen
 * break;
 * 
 * case GAME_OVER:
 * handleGameOver(); // CardLayout auf GameOver, ggf. State setzen
 * break;
 * 
 * case RUN_ABGESCHLOSSEN:
 * handleRunAbgeschlossen(); // zurück ins Menü, Stats anzeigen etc.
 * break;
 * 
 * default:
 * throw new IllegalStateException("Unbekannte Spielphase: " + aktuellePhase);
 * }
 * }
 * Die jeweiligen Methoden kapseln dann nur noch ihren Teil:
 * beginneErkundenPhase() zieht den aktuellen Raum, ruft miniMap.refresh(...),
 * entscheidet über mainFrame.showMinimap(); starteKampfPhase() baut
 * Gegner/Kampfobjekt und registriert den Listener; handleGameOver() und
 * handleRunAbgeschlossen() erledigen die UI-/State-Wechsel.
 * 
 * Beim Weiter-Button rufst du eine klare Methode wie handleWeiterButton() auf.
 * Diese setzt position.naechsterRaum(), prüft den Raumtyp, setzt aktuellePhase
 * = SpielPhase.KAMPF oder ERKUNDEN, und löst dann wieder
 * verarbeiteNaechstenSchritt() aus. Dadurch bleibt die Steuerung vollständig im
 * Spielablauf und der Switch sieht angenehm schlank aus.
 * 
 * wie genau soll die refresh methode aussehen?
 * 
 * 
 * MiniMap.refresh Idee
 * 
 * Signatur: public void refresh(Spieler spieler, Position position) – hier
 * übergibst du die aktuellen Spiel- und Weltinfos.
 * Ablauf:
 * removeAll(); und revalidate()/repaint() erst am Ende, damit der
 * CardLayout-Inhalt clean ist.
 * Überschrift dynamisch aus position.getEbene().getStockwerk() (wenn du das
 * Feld exposed), sonst placeholder.
 * Button „Nächster Raum“ wieder anlegen und auf
 * Spielablauf.handleWeiterButton() verdrahten.
 * Räume neu iterieren (for (Raum raum : position.getRaumListe())) und pro Raum
 * ein Label erzeugen.
 * Bildwahl: aktuelle Position → findeBildfuerRaumtypAktuell, sonst
 * findeBildfuerRaumtyp.
 * Optional: zusätzliche Infos (Spieler-HP/XP) als kleine Sidebar ergänzen.
 * Beispielskizze
 * 
 * public void refresh(Spieler spieler, Position position) {
 * removeAll();
 * 
 * JPanel raumBereich = new JPanel(null);
 * raumBereich.setBounds(0, 0, 800, 800);
 * 
 * JLabel lblUeberschrift = new JLabel("Ebene " +
 * position.getAktuelleEbeneNummer());
 * lblUeberschrift.setBounds(350, 50, 120, 25);
 * lblUeberschrift.setFont(new Font("Courier New", Font.BOLD, 24));
 * raumBereich.add(lblUeberschrift);
 * 
 * JButton btnWeiter = new JButton("Nächster Raum");
 * btnWeiter.setBounds(600, 600, 150, 50);
 * btnWeiter.addActionListener(e -> Spielablauf.handleWeiterButton());
 * raumBereich.add(btnWeiter);
 * 
 * int index = 0;
 * for (Raum raum : position.getRaumListe()) {
 * JLabel lblRaum = erstelleRaumLabel(
 * raum.getTyp(),
 * index,
 * position.getAktuellePosition()
 * );
 * raumBereich.add(lblRaum);
 * index++;
 * }
 * 
 * add(raumBereich);
 * revalidate();
 * repaint();
 * }
 * position.getAktuelleEbeneNummer() musst du dir ggf. über einen Getter in
 * Position oder direkt aus Ebene bereitstellen.
 * Die Hilfsmethode erstelleRaumLabel(...) kannst du unverändert
 * weiterverwenden; sie weiß ja bereits, wie das Highlighting funktioniert.
 * Dadurch reicht in beginneErkundenPhase() ein miniMap.refresh(spieler,
 * position); plus mainFrame.showMinimap(); für einen konsistenten UI-Stand.
 * und wie würdest du handlerunabgeschlossen schreiben und handlegameover
 * außerdem proceed nachkampf?
 * 
 * 
 * handleRunAbgeschlossen() (z. B. src/spiel/Spielablauf.java):
 * 
 * aktuellePhase = SpielPhase.RUN_ABGESCHLOSSEN;
 * optional: stateManager.setState(new GameStart()); oder mainFrame.showMenu();
 * ggf. Spieler/Position zurücksetzen, wenn der Neustart direkt erfolgen soll
 * (z. B. spieler = null; ebene = null; position = null;).
 * Falls ihr einen Abschlussbildschirm plant, hier mainFrame.showRunFinished()
 * o. Ä.
 * handleGameOver():
 * 
 * mainFrame.showGameOver();
 * aktuellePhase = SpielPhase.GAME_OVER;
 * wenn ihr den State-Manager nutzen wollt: stateManager.setState(new
 * GameStart()); erst nach Button im GameOver-Screen, nicht sofort.
 * proceedNachKampf(boolean spielerHatGewonnen):
 * 
 * private static void proceedNachKampf(boolean spielerHatGewonnen) {
 * if (spielerHatGewonnen) {
 * position.getAktuellerRaum().typWechsel(); // Boss/ Gegner auf „besiegt“
 * setzen
 * if (position.istLetzterRaum() && !rausanstehenderKampfMehr()) {
 * handleRunAbgeschlossen();
 * } else {
 * aktuellePhase = SpielPhase.ERKUNDEN;
 * verarbeiteNaechstenSchritt();
 * }
 * } else {
 * handleGameOver();
 * }
 * }
 * Die Abfrage rausanstehenderKampfMehr() steht stellvertretend für eine
 * Prüfung, ob noch ein nicht-besiegter Raum folgt
 * (position.hatNochUnbesiegteGegner()).
 * Im Listener sieht das dann so aus:
 * 
 * kampfsystem.addKampfListener(spielerHatGewonnen ->
 * proceedNachKampf(spielerHatGewonnen));
 * handleGameOver() und handleRunAbgeschlossen() werden nur aus der zentralen
 * Stelle aufgerufen, damit der Switch in verarbeiteNaechstenSchritt() sauber
 * bleibt.
 * 
 * 
 * 
 * 
 * 
 * Auto context
 * 
 * 
 * Local
 * 
 * Agent
 * 
 * gpt-5-codex
 * medium
 */