package charakter;
//Hauptklasse für die Charaktere
public class Charakter{
//Variablen der Hauptklasse
    private String name;
    private int aktLebenspunkte;
    private int maxLebenspunkte;
    private int level;
    private int angriffsWert;
    private int ausweichRate;
    private int verteidigungsWert;
    private int trefferChance;
//Konstruktor der Hauptklasse
    public Charakter(String name, int maxLebenspunkte, int angriffsWert, int verteidigungsWert, int level){
        this.name = name;
        this.maxLebenspunkte = maxLebenspunkte;
        this.aktLebenspunkte = maxLebenspunkte;
        this.angriffsWert = angriffsWert;
        this.verteidigungsWert = verteidigungsWert;
        this.level = level;
        this.ausweichRate=5;
        this.trefferChance=90;
        
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

    public void setAusweichRate(int ausweichRate) {
        this.ausweichRate = ausweichRate;
    }

    public void setVerteidigungsWert(int verteidigungsWert) {
        this.verteidigungsWert = verteidigungsWert;
    }

    public void setTrefferChance(int trefferChance) {
        this.trefferChance = trefferChance;
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

    public int getAusweichRate() {
        return ausweichRate;
    }

    public int getVerteidigungsWert() {
        return verteidigungsWert;
    }

    public int getTrefferChance() {
        return trefferChance;
    }
}
