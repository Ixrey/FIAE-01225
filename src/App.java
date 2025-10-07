public class App {
    public static void main(String[] args) throws Exception {
//test für charakter
        Spieler sp1 = new Spieler("sp1", 100, 10, 1);
        sp1.setaktLebenspunkte(80);
        sp1.bekommeErfahrung(3);
        sp1.bekommeErfahrung(4);
        sp1.bekommeErfahrung(36);
        System.out.println("lvl"+sp1.getLevel());
        System.out.println("akterf"+sp1.getAktErfahrungspunkte());
        System.out.println("benerf"+sp1.getBenErfahrungspunkte());
        System.out.println("aktlp"+sp1.getaktLebenspunkte());
        System.out.println("atk"+sp1.getAngriffsWert());
    }
    
}
