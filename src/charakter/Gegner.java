public class Gegner extends Charakter {
    private int ausgabeErfahrungspunkte;
//Konstruktor der Unterklasse 
    public Gegner(String name, float maxLebenspunkte, float angriffsWert, int level) {
        super(name, maxLebenspunkte, angriffsWert, level);
        this.ausgabeErfahrungspunkte = 2+level;
    }
//Setter für Variablen der Unterklasse
    public void setAusgabeErfahrungspunkte(int ausgabeErfahrungspunkte) {
        this.ausgabeErfahrungspunkte = ausgabeErfahrungspunkte;
    }
//Getter für Variablen der Unterklasse
    public int getAusgabeErfahrungspunkte() {
        return this.ausgabeErfahrungspunkte;
    }


}
