package welt;

import java.util.ArrayList;

public class Ebene {
    private ArrayList<Raum> raumListe;
    private static int stockwerkzaehler;
    private int stockwerk;

    public Ebene() {
        this.raumListe = new ArrayList<>();
        this.stockwerk = stockwerkzaehler;
        stockwerkzaehler++;
        erschaffeEbene();
        System.out.println(raumListe);
    }

    // Ebene für die Basisversion
    public void erschaffeEbene() {
        raumListe.add(new Raum("leer"));
        raumListe.add(new Raum("gegner"));
        raumListe.add(new Raum("leer"));
        raumListe.add(new Raum("gegner"));
        raumListe.add(new Raum("leer"));
        raumListe.add(new Raum("gegner"));
    }

    // Getter
    public ArrayList<Raum> getRaumListe() {
        return new ArrayList<>(raumListe);
    }

    public int getStockwerk() {
        return stockwerk;
    }
}