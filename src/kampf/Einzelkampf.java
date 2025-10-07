package kampf;

import java.util.concurrent.ThreadLocalRandom;

import charakter.Gegner;
import charakter.Spieler;
import charakter.Charakter;

public class Einzelkampf {

    private int runde;
    private Spieler spieler;
    private Gegner gegner;

    public Einzelkampf(Spieler spieler, Gegner gegner) {
        this.spieler = spieler;
        this.gegner = gegner;
        kampf();
    }

    public void kampf() {
        boolean gegnerBesiegt = kampfablauf();
        if (gegnerBesiegt) {
            erfahrungspunkteBekommen();
        }
    }

    public boolean kampfablauf() {
        boolean kampfende = false;
        runde = 0;

        while (!kampfende) {

            vorRundenbeginn();
            spieleraktion();
            kampfende = pruefeKampfende();
            gegneraktion();
            kampfende = pruefeKampfende();
        }
        if (!istLebendig(gegner)) {
            return true;
        } else {
            return false;
        }
    }

    public void vorRundenbeginn() {
        runde++;
        System.out.println("Runde " + runde); /// Später entfernen
        // Effekte werden um 1 verringert und bei 0 beendet
    }

    public void spieleraktion() {
        boolean rundevorbei = false;

        while (!rundevorbei) {
            // Button mit Standartangriff
            if (gegner.getaktLebenspunkte() > 0) {
                gegner.setaktLebenspunkte(gegner.getaktLebenspunkte() - 10);
                System.out.println(gegner.getaktLebenspunkte());
                rundevorbei = true;
            }

            // Button Fähigkeit

            // Button Trank

            // Button Runde beenden (rundevorbei = true) Erst später

            // Button Aufgeben (rundevorbei = true)

        }

    }

    public void gegneraktion() {
        int aktion = ThreadLocalRandom.current().nextInt(0, 3);
        // bound später zu einer Variable machen, falls es erweitert wird
        switch (aktion) {
            case 0: // Wenn die cases vom Datentyp Strings sein sollen, dann abändern
                // Fähigkeit 1 wird benutzt
                break;

            case 1:
                // Fähigkeit 2 wird benutzt
                break;

            case 2:
                // Fähigkeit 3 wird benutzt
                break;

            default:
                break;
        }

    }

    public boolean pruefeKampfende() {
        if (istLebendig(spieler) && !istLebendig(gegner) || hatAufgegeben()) {
            return true;
        }
        if (istLebendig(gegner)) {
            return false;
        }
        System.out.println("Fehler in der Methode pruefeKampfende");
        return false;
    }

    public boolean istLebendig(Charakter charakter) {
        if (charakter.getaktLebenspunkte() <= 0) {
            return false;
        }
        return true;
    }

    public boolean hatAufgegeben() {
        return false;
    }

    public void erfahrungspunkteBekommen() {
        spieler.setAktErfahrungspunkte(gegner.getAusgabeErfahrungspunkte());
    }
}