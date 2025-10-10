package charakter;

//Unterklasse Spieler erbt von Charakter und erweitert für
public class Spieler extends Charakter {
    private int aktionspunkte;
    private int maxAktionspunkte;
    private int aktErfahrungspunkte;
    private int benErfahrungspunkte;

    // Konstruktor der Unterklasse
    public Spieler(String name, float maxLebenspunkte, float angriffsWert, int level) {
        super(name, maxLebenspunkte, angriffsWert, level);
        this.maxAktionspunkte = 1;
        this.aktionspunkte = maxAktionspunkte;
        this.aktErfahrungspunkte = 0;
        this.benErfahrungspunkte = 2;
    }

    public Spieler(float maxLebenspunkte, float angriffsWert, int level) {
        super(maxLebenspunkte, angriffsWert, level);
        this.maxAktionspunkte = 1;
        this.aktionspunkte = maxAktionspunkte;
        this.aktErfahrungspunkte = 0;
        this.benErfahrungspunkte = 2;
    }

    // Setter für Variablen der Unterklasse
    public void setAktionspunkte(int aktionspunkte) {
        this.aktionspunkte = aktionspunkte;
    }

    public void setAktErfahrungspunkte(int aktErfahrungspunkte) {
        this.aktErfahrungspunkte = aktErfahrungspunkte;
    }

    public void setBenErfahrungspunkte(int benErfahrungspunkte) {
        this.benErfahrungspunkte = benErfahrungspunkte;
    }

    // Getter für Variablen der Unterklasse
    public int getAktionspunkte() {
        return this.aktionspunkte;
    }

    public int getAktErfahrungspunkte() {
        return this.aktErfahrungspunkte;
    }

    public int getBenErfahrungspunkte() {
        return this.benErfahrungspunkte;
    }

    // Methoden der Unterklasse
    public void aufleveln(boolean levelup) {
        if (levelup) {
            float plusLebenspunkte = (float) (this.getmaxLebenspunkte() * 0.2);
            if ((this.getmaxLebenspunkte() * 0.2) % 1 != 0) {
                plusLebenspunkte = plusLebenspunkte - ((float) (this.getmaxLebenspunkte() * 0.2) % 1) + 1;
            }
            this.setLevel(this.getLevel() + 1);
            this.setBenErfahrungspunkte(getBenErfahrungspunkte() * 2);
            this.setmaxLebenspunkte(this.getmaxLebenspunkte() + plusLebenspunkte);
            this.setaktLebenspunkte(this.getaktLebenspunkte() + plusLebenspunkte);
            if (((this.getAngriffsWert() * 1.2) % 1) != 0) {
                this.setangriffsWert(
                        (float) ((this.getAngriffsWert() * 1.2) - ((this.getAngriffsWert() * 1.2) % 1) + 1));
            } else {
                this.setangriffsWert((float) ((this.getAngriffsWert() * 1.2)));
            }
        }
    }

    public void bekommeErfahrung(int erfWert) {
        if ((erfWert + this.aktErfahrungspunkte) >= this.benErfahrungspunkte) {
            this.setAktErfahrungspunkte(erfWert + this.aktErfahrungspunkte);
            do {
                this.setAktErfahrungspunkte((this.aktErfahrungspunkte) - this.benErfahrungspunkte);
                this.aufleveln(true);
            } while ((this.aktErfahrungspunkte) >= this.benErfahrungspunkte);
        } else {
            this.setAktErfahrungspunkte((erfWert + this.aktErfahrungspunkte));
        }
    }
}
