package charakter;
//Hauptklasse für die Charaktere
public class Charakter{
//Variablen der Hauptklasse
    private String name;
    private int aktLebenspunkte;
    private int maxLebenspunkte;
    private int level;
    private int angriffsWert;
//Konstruktor der Hauptklasse
    public Charakter(String name, int maxLebenspunkte, int angriffsWert, int level){
        this.name = name;
        this.maxLebenspunkte = maxLebenspunkte;
        this.aktLebenspunkte = maxLebenspunkte;
        this.angriffsWert = angriffsWert;
        this.level = level;
    }
//Setter der Hauptklasse
    public void setName(String name){
        this.name = name;
    }

    public void setaktLebenspunkte(int aktLebenspunkte){
        this.aktLebenspunkte = aktLebenspunkte;
    }

    public void setmaxLebenspunkte(int maxLebenspunkte){
        this.maxLebenspunkte = maxLebenspunkte;
    }

    public void setangriffsWert(int angriffsWert){
        this.angriffsWert = angriffsWert;
    }

    public void setLevel(int level){
        this.level = level;
    }
//Getter der Hauptklasse
    public String getName(){
        return this.name;
    }

    public int getaktLebenspunkte(){
        return this.aktLebenspunkte;
    }

    public int getmaxLebenspunkte() {
        return this.maxLebenspunkte;
    }

    public int getAngriffsWert() {
        return this.angriffsWert;
    }

    public int getLevel() {
        return this.level;
    }
}
