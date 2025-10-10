package charakter;
import java.util.concurrent.ThreadLocalRandom;
public class Gegnergenerator {
    public static Gegner orkErschaffen(Spieler spieler){
        int zufallsLevel = ThreadLocalRandom.current().nextInt(1, 3);
        int fixLevel = spieler.getLevel()-1;
        Gegner g1 = new Gegner("Ork",(zufallsLevel+fixLevel)*70,(zufallsLevel+fixLevel)*8,(zufallsLevel+fixLevel));
        return g1;
    }

    public static Gegner goblinErschaffen(Spieler spieler){
        int zufallsLevel = ThreadLocalRandom.current().nextInt(1, 3);
        int fixLevel = spieler.getLevel()-1;
        Gegner g1 = new Gegner("Goblin",(zufallsLevel+fixLevel)*50,(zufallsLevel+fixLevel)*4,(zufallsLevel+fixLevel));
        return g1;
    }

    public static Gegner bossErschaffen(Spieler spieler){
        int fixLevel = spieler.getLevel()+1;
        Gegner b1 = new Gegner("Boss",(fixLevel)*200,(fixLevel)*12,(fixLevel));
        return b1;
    }

    public static Gegner zufallsGegnerErschaffen(Spieler spieler){
        int welcherGegner = ThreadLocalRandom.current().nextInt(1, 3);
        Gegner gegner = null;
        switch (welcherGegner){
            case 1:
                gegner = goblinErschaffen(spieler);
                break;
            case 2:
                gegner = orkErschaffen(spieler);
                break;
        }
        return gegner;
    }
}
