package charakter;
import java.util.concurrent.ThreadLocalRandom;
public class Gegnergenerator {

    public static int skalierung(Spieler spieler){
        int zufallsLevel = ThreadLocalRandom.current().nextInt(1, 3);
        int fixLevel = spieler.getLevel()-1;
        return zufallsLevel+ fixLevel;
    }
    public static Gegner orkErschaffen(Spieler spieler){
        int multi = skalierung(spieler);
        Gegner gegner = new Gegner("Ork", multi*70, multi*8, multi);
        return gegner;
    }

    public static Gegner goblinErschaffen(Spieler spieler){
        int multi = skalierung(spieler);
        Gegner gegner = new Gegner("Goblin", multi*50, multi*4, multi);
        return gegner;
    }

    public static Gegner bossErschaffen(Spieler spieler){
        int fixLevel = spieler.getLevel()+1;
        Gegner boss = new Gegner("Boss",(fixLevel)*200,(fixLevel)*12,(fixLevel));
        return boss;
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
