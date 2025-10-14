package charakter;
public class Testklasscha {
    public static void main(String[] args) throws Exception {
//test für charakter
        Spieler sp1 = new Spieler("sp1", 100, 10,7, 1);
        sp1.setaktLebenspunkte(80);
        sp1.bekommeErfahrung(20);
        sp1.bekommeErfahrung(30);
        System.out.println("lvl"+sp1.getLevel());
        Gegner g2 = Gegnergenerator.zufallsGegnerErschaffen(sp1);
        System.out.println("akterf"+sp1.getAktErfahrungspunkte());
        System.out.println("benerf"+sp1.getBenErfahrungspunkte());
        System.out.println("aktlp"+sp1.getaktLebenspunkte());
        System.out.println("maxlp"+sp1.getmaxLebenspunkte());
        System.out.println("atk"+sp1.getAngriffsWert());
        System.out.println("def"+sp1.getVerteidigungsWert());
        System.out.println("dodge"+sp1.getAusweichRate());
        System.out.println("hit"+sp1.getTrefferChance());
        System.out.println("crate"+sp1.getKritischeRate());
        System.out.println("cdmg"+sp1.getKrtischerSchaden());
        System.out.println("lvl"+g2.getLevel());
        System.out.println("name"+g2.getName());
        System.out.println("leben"+g2.getmaxLebenspunkte());
        System.out.println("atk"+g2.getAngriffsWert());
        System.out.println("def"+g2.getVerteidigungsWert());
        System.out.println("dodge"+g2.getAusweichRate());
        System.out.println("hit"+g2.getTrefferChance());

    }
}