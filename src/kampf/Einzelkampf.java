package kampf;

import java.util.concurrent.ThreadLocalRandom;

import charakter.Gegner;
import charakter.Spieler;
import gui.GameWindow;
import charakter.Charakter;

public class Einzelkampf {

    private Spieler spieler;
    private Gegner gegner;
    private int runde = 1;

    public Einzelkampf(Spieler spieler, Gegner gegner) {
        this.spieler = spieler;
        this.gegner = gegner;
    }

    public static void neuesSpiel(Spieler spieler, Gegner gegner) {
        new Einzelkampf(spieler, gegner);
    }

    // Methoden für Buttons im GUI

    public void rundeBeenden() {
        spieler.setAktionspunkte(0);
        nachAktion();
    }

    public void standartangriff() {
        gegner.setaktLebenspunkte(gegner.getaktLebenspunkte() - spieler.getAngriffsWert());
        nachAktion();
    }

    public void faehigkeit() {
        gegner.setaktLebenspunkte(gegner.getaktLebenspunkte() - spieler.getAngriffsWert());
        nachAktion();
    }

    public void trank() {
        if ((spieler.getmaxLebenspunkte() - spieler.getaktLebenspunkte()) >= 7) {
            spieler.setaktLebenspunkte(spieler.getaktLebenspunkte() + 7);
        }
        nachAktion();
    }

    // Schaden geben und nehmen

    public int aktion(Charakter )

    // Methoden der Spiellogik

    public void naechsteRunde() {
        runde++;
    }

    public boolean pruefeNaechsteRunde() {
        return true; // für die Basisversion
    }

    public boolean pruefeKampfende() {
        if (charakter.Charakter.istLebendig(spieler) && charakter.Charakter.istLebendig(gegner)) {
            return false;
        }
        return true;
    }

    public void nachAktion() {
        boolean pruefeEnde = pruefeKampfende();
        if (!pruefeEnde) {
            boolean pruefeNaechste = pruefeNaechsteRunde();

            if (pruefeNaechste) {
                gegnerRunde();
                naechsteRunde();
            }
        }
    }

    public boolean einzelkampfEnde() {
        if (charakter.Charakter.istLebendig(spieler) == false) {
            return false;
        } else if (charakter.Charakter.istLebendig(gegner) == false) {
            return true;
        }
        System.out.println("Fehler ist in der einzelkampfEnde-Methode aufgetreten");
        return false;
    }

    public void gegnerRunde() {
        spieler.setaktLebenspunkte(spieler.getaktLebenspunkte() - 5);
    }
}