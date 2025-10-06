//Unterklasse Spieler erbt von Charakter und erweitert für
public class Spieler extends Charakter{
    private int aktionspunkte;
    private int aktErfahrungspunkte;
    private int benErfahrungspunkte;
    
//Konstruktor der Unterklasse
    public Spieler(String name, float maxLebenspunkte, float angriffsWert, int level) {
        super(name, maxLebenspunkte, angriffsWert, level);
        this.aktErfahrungspunkte = 0;
        this.benErfahrungspunkte = 2;
    }
//Setter für Variablen der Unterklasse
    public void setAktionspunkte(int aktionspunkte) {
        this.aktionspunkte = aktionspunkte;
    }

    public void setAktErfahrungspunkte(int aktErfahrungspunkte) {
        this.aktErfahrungspunkte = aktErfahrungspunkte;
    }

    public void setBenErfahrungspunkte(int benErfahrungspunkte) {
        this.benErfahrungspunkte = benErfahrungspunkte;
    }
//Getter für Variablen der Unterklasse
    public int getAktionspunkte() {
        return this.aktionspunkte;
    }

    public int getAktErfahrungspunkte() {
        return this.aktErfahrungspunkte;
    }

    public int getBenErfahrungspunkte() {
        return this.benErfahrungspunkte;
    }
//Methoden der Unterklasse
    public void aufleveln(boolean lvlup){
        if (lvlup){
            float lpPlus = (float) (this.getmaxLebenspunkte()*0.2);
            this.setLevel(this.getLevel()+1);
            this.setmaxLebenspunkte(this.getmaxLebenspunkte()+lpPlus);
            this.setaktLebenspunkte(this.getaktLebenspunkte()+lpPlus);
            this.setangriffsWert((float) (this.getAngriffsWert()*1.2));
            this.setBenErfahrungspunkte(getBenErfahrungspunkte()*2);

        }
    }

    public void bekommeErfahrung(int erfWert){
        if ((erfWert+this.aktErfahrungspunkte)>=this.benErfahrungspunkte){
            this.setAktErfahrungspunkte((erfWert+this.aktErfahrungspunkte)-this.benErfahrungspunkte);
            this.aufleveln(true);
        }
        else {
            this.setAktErfahrungspunkte((erfWert+this.aktErfahrungspunkte));
        }
    }
}
