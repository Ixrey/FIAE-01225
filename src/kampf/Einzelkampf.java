package kampf;

import charakter.Gegner;
import charakter.Spieler;

public class Einzelkampf {

    private Spieler spieler;
    private Gegner gegner;
    private int runde = 1;
    private String text = "";
    private boolean kampfIstZuende = false;

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
        if (!kampfIstZuende) {
            gegner.setaktLebenspunkte(gegner.getaktLebenspunkte() - spieler.getAngriffsWert());

            nachAktion();
            setCombatLog("Spieler " + spieler.getName() + " greift Gegnger " + gegner.getName() + " mit "
                    + spieler.getAngriffsWert() + " Schaden an.\n");
        }
    }

    public void faehigkeit() {
        if (!kampfIstZuende) {
            gegner.setaktLebenspunkte(gegner.getaktLebenspunkte() - spieler.getAngriffsWert());
            nachAktion();
            setCombatLog("Spieler " + spieler.getName() + " greift Gegnger " + gegner.getName() + " mit "
                    + spieler.getAngriffsWert() + " Schaden an.\n");
        }
    }

    public void trank() {
        if (!kampfIstZuende) {
            if ((spieler.getmaxLebenspunkte() - spieler.getaktLebenspunkte()) >= 7) {
                spieler.setaktLebenspunkte(spieler.getaktLebenspunkte() + 7);
            }
            nachAktion();

            setCombatLog("Durch den Trank hat " + spieler.getName() + " 7 Lebenspunkte bekommen.\n"); // Später abändern
        }
    }

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
        kampfIstZuende = true;
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
        } else {
            if (hatSpielerGewonnen()) {
                System.out.println("Spieler hat gewonnen.");
            } else if (!hatSpielerGewonnen()) {
                System.out.println("Gegner hat gewonnen.");
            } else {
                System.out.println("Fehler in der nachAktion()-Methode");
            }
        }
    }

    public boolean hatSpielerGewonnen() {
        if (charakter.Charakter.istLebendig(spieler) == false) {
            return false;
        } else if (charakter.Charakter.istLebendig(gegner) == false) {
            return true;
        }
        System.out.println("Spiel ist noch nicht beendet!");
        return false;
    }

    public void gegnerRunde() {
        spieler.setaktLebenspunkte(spieler.getaktLebenspunkte() - gegner.getAngriffsWert());
        setCombatLog("Gegner " + gegner.getName() + " greift Spieler " + spieler.getName() + " mit "
                + gegner.getAngriffsWert() + " Schaden an.\n");
    }

    public void setCombatLog(String text) {
        this.text = text;
    }

    public String getCombatLog() {
        return this.text;
    }

    public int getRunde() {
        return runde;
    }
}