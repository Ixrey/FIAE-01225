package charakter;
//Unterklasse Spieler erbt von Charakter und erweitert für den Spieler notwendige Dinge
public class Spieler extends Charakter{
    private int aktionspunkte;
    private int maxAktionspunkte;
    private int aktErfahrungspunkte;
    private int benErfahrungspunkte;
    private int steigerungsWert = 20;
    private int aktTraenke;
    private int maxTraenke;
//Konstruktor der Unterklasse
    public Spieler(String name, int maxLebenspunkte, int angriffsWert, int level) {
        super(name, maxLebenspunkte, angriffsWert, level);
        this.maxAktionspunkte = 1;
        this.aktionspunkte = maxAktionspunkte;
        this.aktErfahrungspunkte = 0;
        this.benErfahrungspunkte = 2;
        this.maxTraenke = 3;
        this.aktTraenke = maxTraenke;
    }
//Setter für Variablen der Unterklasse
    public void setAktionspunkte(int aktionspunkte) {
        this.aktionspunkte = aktionspunkte;
    }

    public void setmaxAktionspunkte(int maxAktionspunkte) {
        this.maxAktionspunkte = maxAktionspunkte;
    }

    public void setAktErfahrungspunkte(int aktErfahrungspunkte) {
        this.aktErfahrungspunkte = aktErfahrungspunkte;
    }

    public void setBenErfahrungspunkte(int benErfahrungspunkte) {
        this.benErfahrungspunkte = benErfahrungspunkte;
    }

    public void setSteigerungsWert(int steigerungsWert) {
        this.steigerungsWert = steigerungsWert;
    }

    public void setAktTraenke(int aktTraenke) {
        this.aktTraenke = aktTraenke;
    }

    public void setMaxTraenke(int maxTraenke) {
        this.maxTraenke = maxTraenke;
    }
//Getter für Variablen der Unterklasse
    public int getAktionspunkte() {
        return this.aktionspunkte;
    }

    public int getmaxAktionspunkte() {
        return this.maxAktionspunkte;
    }

    public int getAktErfahrungspunkte() {
        return this.aktErfahrungspunkte;
    }

    public int getBenErfahrungspunkte() {
        return this.benErfahrungspunkte;
    }

        public int getSteigerungsWert() {
        return steigerungsWert;
    }

    public int getAktTraenke() {
        return aktTraenke;
    }

    public int getMaxTraenke() {
        return maxTraenke;
    }
//Methoden der Unterklasse
    //Methode was passiert wenn der Spieler ein Level aufsteigt
    public void aufleveln(){
        this.setLevel(this.getLevel()+1);
        this.setBenErfahrungspunkte(getBenErfahrungspunkte()*2);
        lebenspunkteSteigern();
        angriffSteigern();
    }
    //Methode für die Verarbeitung von Erfahrungspunkten
    public void bekommeErfahrung(int erfWert){
        if ((erfWert+this.aktErfahrungspunkte)>=this.benErfahrungspunkte){
            this.setAktErfahrungspunkte(erfWert+this.aktErfahrungspunkte);
            do {
                this.setAktErfahrungspunkte((this.aktErfahrungspunkte)-this.benErfahrungspunkte);
                this.aufleveln();}
                while ((this.aktErfahrungspunkte)>=this.benErfahrungspunkte);
        }
        else {
            this.setAktErfahrungspunkte((erfWert+this.aktErfahrungspunkte));
        }
    }
    //Methode zur steigerung des Angriffs(wird bei "aufleveln()" aufgerufen)
    public void angriffSteigern(){
        int zwischenergebnis = this.getAngriffsWert()*steigerungsWert;
        if (zwischenergebnis-(zwischenergebnis/100*100)>=50){
            zwischenergebnis=zwischenergebnis+100;
        }
        this.setangriffsWert(this.getAngriffsWert()+zwischenergebnis/100);
    }
    //Methode zu Steigerung der Lebenspunkte(wird bei "aufleveln()" aufgerufen)
    public void lebenspunkteSteigern(){
        int zwischenergebnis = this.getmaxLebenspunkte()*steigerungsWert;
        if (zwischenergebnis-(zwischenergebnis/100*100)>=50){
            zwischenergebnis=zwischenergebnis+100;
        }
        int plusLebenspunkte = zwischenergebnis/100;
        this.setmaxLebenspunkte(this.getmaxLebenspunkte()+plusLebenspunkte);
        this.setaktLebenspunkte(this.getaktLebenspunkte()+plusLebenspunkte);
 
    }
}
