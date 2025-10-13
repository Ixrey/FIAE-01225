package charakter;

//Unterklasse Gegner erbt von Charakter und erweitert für die Gegner notwendige Dinge
public class Gegner extends Charakter {
    private int ausgabeErfahrungspunkte;

    // Konstruktor der Unterklasse
    public Gegner(String name, int maxLebenspunkte, int angriffsWert, int level) {
        super(name, maxLebenspunkte, angriffsWert, level);
        this.ausgabeErfahrungspunkte = 2 + level;
    }

    // Setter für Variablen der Unterklasse
    public void setAusgabeErfahrungspunkte(int ausgabeErfahrungspunkte) {
        this.ausgabeErfahrungspunkte = ausgabeErfahrungspunkte;
    }

    // Getter für Variablen der Unterklasse
    public int getAusgabeErfahrungspunkte() {
        return this.ausgabeErfahrungspunkte;
    }
}
