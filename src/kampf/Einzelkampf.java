package kampf;

import charakter.Charakter;
import charakter.Gegner;
import charakter.Spieler;
<<<<<<< HEAD
import java.util.concurrent.ThreadLocalRandom;
=======
import gui.GameWindow;
import charakter.Charakter;
>>>>>>> 1c0b85c1b29d7eddfb62e4b84d00e6794cf51ce9

public class Einzelkampf {

    private Spieler spieler;
    private Gegner gegner;
    private int runde = 1;

    public Einzelkampf(Spieler spieler, Gegner gegner) {
        this.spieler = spieler;
        this.gegner = gegner;
<<<<<<< HEAD
        
    }

    public final void kampf() {
        boolean gegnerBesiegt = kampfablauf();
        if (gegnerBesiegt) {
            erfahrungspunkteBekommen();
        }
=======
    }

    public static void neuesSpiel(Spieler spieler, Gegner gegner) {
        new Einzelkampf(spieler, gegner);
>>>>>>> 1c0b85c1b29d7eddfb62e4b84d00e6794cf51ce9
    }

    // Methoden für Buttons im GUI

<<<<<<< HEAD
        while (!kampfende) {

            vorRundenbeginn();
            if (runde == 1){
                
            spieleraktion(0);
            }
            kampfende = pruefeKampfende();
            gegneraktion();
            kampfende = pruefeKampfende();
        }
        if (!istLebendig(gegner)) {
            return true;
        } else {
            return false;
        }
=======
    public void rundeBeenden() {
        spieler.setAktionspunkte(0);
        nachAktion();
>>>>>>> 1c0b85c1b29d7eddfb62e4b84d00e6794cf51ce9
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

<<<<<<< HEAD
    public void spieleraktion(int n) {
        boolean rundevorbei = false;

        while (!rundevorbei) {
            // Button mit Standartangriff

            switch(n){

                case 0:
                System.out.println("Spiel startet.");
                rundevorbei = true;
                break;
                case 1:
              System.out.println("Trank getrunken!");
            if (gegner.getaktLebenspunkte() > 0) {
                gegner.setaktLebenspunkte(gegner.getaktLebenspunkte() - 10);
                System.out.println("Gegner HP: " + gegner.getaktLebenspunkte());
                rundevorbei = true;
            } else{
                
            }
                break;
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

=======
    public boolean pruefeNaechsteRunde() {
        return true; // für die Basisversion
>>>>>>> 1c0b85c1b29d7eddfb62e4b84d00e6794cf51ce9
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