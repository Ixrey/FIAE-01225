package kampf;

import charakter.Gegner;
import charakter.Spieler;

public class TestKampf {
    public static void main(String[] args) {
        Spieler peter = new Spieler("Peter", 100, 10, 5, 1);
        Gegner retep = new Gegner("Retep", 50, 15, 3, 1);

        Einzelkampf test = new Einzelkampf(peter, retep);

        anzeige(peter, retep);
        test.standartangriff();
        anzeige(peter, retep);
    }

    public static void anzeige(Spieler sp, Gegner geg) {
        System.out.println("Spieler HP: " + sp.getaktLebenspunkte());
        System.out.println("Gegner HP: " + geg.getaktLebenspunkte());
    }
}