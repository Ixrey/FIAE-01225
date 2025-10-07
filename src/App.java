import charakter.Gegner;
import charakter.Spieler;
import kampf.Einzelkampf;

public class App {
    public static void main(String[] args) throws Exception {
        Spieler sp = new Spieler("Max Mustermann", 100, 10, 1);
        Gegner geg = new Gegner("Böser Troll", 70, 7, 1);

        Einzelkampf testkampf = new Einzelkampf(sp, geg);
    }
}