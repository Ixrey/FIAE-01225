package spiel.test;

import charakter.Gegner;
import charakter.Spieler;
import java.util.List;
import java.util.Random;

// Sammlung von Dummy-Komponenten, die den Spielablauf provisorisch simulieren.

public class TestGameInhalt {

    private static Random rng = new Random();

    private TestGameInhalt() {
    }

    public enum DemoKampfErgebnis {
        SPIELER_BESIEGT, GEGNER_BESIEGT
    }

    public static TestDungeon erstelleTestDungeon() {
        return TestDungeon.demo();
    }

    public static Gegner erstelleRandomTestGegner() {
        return DemoGegnerGenerator.demo();
    }

    public static DemoKampfErgebnis simulateKampf(Spieler spieler, Gegner gegner) {
        boolean spielerGewinnt = rng.nextDouble() < 0.75;
        System.out.println("Kampf gegen " + gegner.getName());
        return spielerGewinnt ? DemoKampfErgebnis.GEGNER_BESIEGT : DemoKampfErgebnis.SPIELER_BESIEGT;
    }

    public static class TestDungeon {
        private List<DemoRaum> raumListe;
        private int zaehler = -1;

        private TestDungeon(List<DemoRaum> raumListe) {
            this.raumListe = raumListe;
        }

        public static TestDungeon demo() {
            return new TestDungeon(List.of(
                    new DemoRaum("Raum 1", "LEER"),
                    new DemoRaum("Raum 2", "KAMPF"),
                    new DemoRaum("Raum 3", "LEER"),
                    new DemoRaum("Raum 4", "KAMPF"),
                    new DemoRaum("Raum 5", "LEER")));
        }

        public DemoRaum naechsterRaum() {
            zaehler++;
            if (zaehler >= raumListe.size()) {
                return null;
            }
            return raumListe.get(zaehler);
        }
    }

    public static class DemoRaum {
        private String name;
        private String typ;

        public DemoRaum(String name, String typ) {
            this.name = name;
            this.typ = typ;
        }

        public String getName() {
            return name;
        }

        public String getTyp() {
            return typ;
        }

        public boolean istKampf() {
            return "KAMPF".equals(typ);
        }
    }

    private static class DemoGegnerGenerator {

        private DemoGegnerGenerator() {
        }

        static Gegner demo() {
            return rng.nextBoolean() ? new Gegner("Goblin", 50, 10, 1) : new Gegner("Ork", 70, 12, 1);
        }
    }
}
