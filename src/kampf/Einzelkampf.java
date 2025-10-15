package kampf;

import charakter.Charakter;
import charakter.Gegner;
import charakter.Spieler;
import java.util.concurrent.ThreadLocalRandom;

public class Einzelkampf {

    private Spieler spieler;
    private Gegner gegner;
    private int runde = 1;
    private String text = "";
    private boolean kampfIstZuende = false;
    private boolean faehigkeitEinsetzen = true;
    private KampfListener listener;

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private final StringBuilder zugLog = new StringBuilder();

    private static class AngriffsErgebnis {
        private int schaden;
        private boolean hatGetroffen;
        private boolean warKritisch;

        private AngriffsErgebnis(int schaden, boolean hatGetroffen, boolean warKritisch) {
            this.schaden = schaden;
            this.hatGetroffen = hatGetroffen;
            this.warKritisch = warKritisch;
        }

        private int getSchaden() {
            return schaden;
        }

        private boolean hatGetroffen() {
            return hatGetroffen;
        }

        private boolean warKritisch() {
            return warKritisch;
        }
    }

    public Einzelkampf(Spieler spieler, Gegner gegner) {
        this.spieler = spieler;
        this.gegner = gegner;
    }

    public void addKampfListener(KampfListener listener) {
        this.listener = listener;
    }

    private void meldeKampfEnde() {
        if (listener != null) {
            listener.kampfBeendet(hatSpielerGewonnen());
        }
    }

    // Methoden für Buttons im GUI

    public void rundeBeenden() {
        if (kampfIstZuende) {
            return;
        }
        setzeZugLogZurück();
        logLineHinzufuegen("Spieler " + spieler.getName() + " beendet seine Runde.");
        spieler.setAktionspunkte(0);
        nachAktion();
        logLineHinzufuegen("");
        finalisiereZugLog();
    }

    public void standartangriff() {
        if (!kampfIstZuende) {
            setzeZugLogZurück();
            AngriffsErgebnis ergebnis = berechneAngriff(spieler, gegner, 1);
            if (ergebnis.hatGetroffen()) {
                gegner.setaktLebenspunkte(gegner.getaktLebenspunkte() - ergebnis.getSchaden());
            }
            protokolliereSpielerAngriff("Standardangriff", gegner, ergebnis);
            nachAktion();
            logLineHinzufuegen("");
            finalisiereZugLog();
        }
    }

    public void faehigkeit() {
        if (!kampfIstZuende && faehigkeitEinsetzen) {
            setzeZugLogZurück();
            AngriffsErgebnis ergebnis = berechneAngriff(spieler, gegner, 2);
            if (ergebnis.hatGetroffen()) {
                gegner.setaktLebenspunkte(gegner.getaktLebenspunkte() - ergebnis.getSchaden());
            }
            protokolliereSpielerAngriff("Faehigkeit", gegner, ergebnis);
            nachAktion();
            logLineHinzufuegen("");
            finalisiereZugLog();
            faehigkeitEinsetzen = false;
        } else if (!kampfIstZuende && !faehigkeitEinsetzen) {
            setCombatLog("Der Spieler " + spieler.getName() + " hat bereits seine Faehigkeit eingesetzt!"
                    + LINE_SEPARATOR + LINE_SEPARATOR);
        }
    }

    public void trank() {
        if (!kampfIstZuende) {
            setzeZugLogZurück();
            int differenz = spieler.getmaxLebenspunkte() - spieler.getaktLebenspunkte();
            int geheiltePunkte = 0;
            if (differenz >= 7) {
                geheiltePunkte = Math.min(14, differenz);
                spieler.setaktLebenspunkte(spieler.getaktLebenspunkte() + geheiltePunkte);
            }
            logLineHinzufuegen("Spieler " + spieler.getName() + " trinkt einen Trank.");
            if (geheiltePunkte > 0) {
                logLineHinzufuegen("  Heilung: " + geheiltePunkte + " Lebenspunkte.");
            } else {
                logLineHinzufuegen("  Keine Wirkung, der Spieler ist fast vollstaendig geheilt.");
            }
            nachAktion();
            logLineHinzufuegen("");
            finalisiereZugLog();
        }
    }

    // Methoden der Spiellogik

    public void naechsteRunde() {
        runde++;
    }

    public boolean pruefeNaechsteRunde() {
        return true; // fuer die Basisversion
    }

    public boolean pruefeKampfende() {
        if (charakter.Charakter.istLebendig(spieler) && charakter.Charakter.istLebendig(gegner)) {
            return false;
        }
        kampfIstZuende = true;
        meldeKampfEnde();
        return true;
    }

    public void nachAktion() {

        if (pruefeKampfende()) {
            logKampfErgebnis();
            return;
        }

        if (pruefeNaechsteRunde()) {
            gegnerRunde();

            if (pruefeKampfende()) {
                logKampfErgebnis();
                return;
            }

            naechsteRunde();
        }
    }

    private void logKampfErgebnis() {
        boolean spielerGewonnen = hatSpielerGewonnen();
        if (spielerGewonnen) {
            System.out.println("Spieler hat gewonnen.");
        } else if (!spielerGewonnen) {
            System.out.println("Gegner hat gewonnen.");
        } else {
            System.out.println("Fehler in der nachAktion()-Methode");
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
        AngriffsErgebnis ergebnis = berechneAngriff(gegner, spieler, 1);
        if (ergebnis.hatGetroffen()) {
            spieler.setaktLebenspunkte(spieler.getaktLebenspunkte() - ergebnis.getSchaden());
        }
        protokolliereGegnerAngriff(ergebnis);
    }

    public boolean wahrscheinlichkeit(int wahrscheinlich) {
        int zufall = ThreadLocalRandom.current().nextInt(0, 100);
        if (zufall < wahrscheinlich) {
            return true;
        } else {
            return false;
        }
    }

    // Methoden des Schaden

    private AngriffsErgebnis berechneAngriff(Charakter angreifer, Charakter angegriffene, int multiplikator) {
        int finalerAngriffswert = angreifer.getAngriffsWert();
        boolean hatGetroffen = true;
        boolean warKritisch = false;
        if (angreifer instanceof Spieler) {
            int kritMultiplikator = krit();
            if (kritMultiplikator > 1) {
                warKritisch = true;
                finalerAngriffswert *= kritMultiplikator;
            }
        }
        hatGetroffen = trefferchance(angreifer);
        if (hatGetroffen) {
            hatGetroffen = ausweichChance(angegriffene);
        }

        int schaden = 0;
        if (hatGetroffen) {
            schaden = Math.max(0, finalerAngriffswert - angegriffene.getVerteidigungsWert());
            schaden *= multiplikator;
        }
        return new AngriffsErgebnis(schaden, hatGetroffen, warKritisch);
    }

    public int krit() {
        if (wahrscheinlichkeit(spieler.getKritischeRate())) {
            return spieler.getKrtischerSchaden();
        }
        return 1;
    }

    public boolean trefferchance(Charakter angreifer) {
        if (wahrscheinlichkeit(angreifer.getTrefferChance())) {
            return true;
        }
        return false;
    }

    public boolean ausweichChance(Charakter angegriffene) {
        if (wahrscheinlichkeit(angegriffene.getAusweichRate())) {
            return false;
        }
        return true;
    }

    public void setCombatLog(String text) {
        this.text = text;
    }

    public String getCombatLog() {
        return this.text == null ? "" : this.text;
    }

    private void setzeZugLogZurück() {
        zugLog.setLength(0);
    }

    private void logLineHinzufuegen(String inhalt) {
        zugLog.append(inhalt).append(LINE_SEPARATOR);
    }

    private void finalisiereZugLog() {
        setCombatLog(zugLog.toString());
    }

    private void protokolliereSpielerAngriff(String aktion, Charakter ziel, AngriffsErgebnis ergebnis) {
        logLineHinzufuegen("Spieler " + spieler.getName() + " - " + aktion + " auf " + ziel.getName());
        if (ergebnis.hatGetroffen()) {
            StringBuilder zeile = new StringBuilder("  Treffer! Schaden: ").append(ergebnis.getSchaden());
            if (ergebnis.warKritisch()) {
                zeile.append(" (kritisch)");
            }
            logLineHinzufuegen(zeile.toString());
        } else {
            logLineHinzufuegen("  Angriff verfehlt.");
        }
    }

    private void protokolliereGegnerAngriff(AngriffsErgebnis ergebnis) {
        logLineHinzufuegen("Gegner " + gegner.getName() + " greift " + spieler.getName() + " an");
        if (ergebnis.hatGetroffen()) {
            logLineHinzufuegen("  Treffer! Schaden: " + ergebnis.getSchaden());
        } else {
            logLineHinzufuegen("  Angriff verfehlt.");
        }
    }

    public int getRunde() {
        return runde;
    }
}
