package charakter;

public class Charakter {
    // Variablen der Hauptklasse
    private String name;
    private float aktLebenspunkte;
    private float maxLebenspunkte;
    private int level;
    private float angriffsWert;

    // Konstruktor der Hauptklasse
    public Charakter(String name, float maxLebenspunkte, float angriffsWert, int level) {
        this.name = name;
        this.maxLebenspunkte = maxLebenspunkte;
        this.aktLebenspunkte = maxLebenspunkte;
        this.angriffsWert = angriffsWert;
        this.level = level;
    }

    // Setter der Hauptklasse
    public void setName(String name) {
        this.name = name;
    }

    public void setaktLebenspunkte(float aktLebenspunkte) {
        this.aktLebenspunkte = aktLebenspunkte;
    }

    public void setmaxLebenspunkte(float maxLebenspunkte) {
        this.maxLebenspunkte = maxLebenspunkte;
    }

    public void setangriffsWert(float angriffsWert) {
        this.angriffsWert = angriffsWert;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    // Getter der Hauptklasse
    public String getName() {
        return this.name;
    }

    public float getaktLebenspunkte() {
        return this.aktLebenspunkte;
    }

    public float getmaxLebenspunkte() {
        return this.maxLebenspunkte;
    }

    public float getAngriffsWert() {
        return this.angriffsWert;
    }

    public int getLevel() {
        return this.level;
    }
}
