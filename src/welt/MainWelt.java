package welt;

import java.util.ArrayList;

public class MainWelt {

    static int index;
    static ArrayList<Raum> raumListe;

    public static void main(String[] args) {

        Ebene ebene = new Ebene();
        Position position = new Position(ebene);
        raumListe = ebene.getRaumListe();
        index = position.getAktuellePosition();

        aktuell(position);
        position.naechsterRaum();
        aktuell(position);

        raumListe.get(index).typWechsel();

        aktuell(position);
    }

    public static void aktuell(Position position) {
        System.out.println("\n-------------------\n");
        System.out.println("Aktuelle Position: " + position.getAktuellePosition());
        // System.out.println("Aktueller Zustand: " + position.getAktuellerZustand());
        System.out.println("Aktueller Raum-Typ: " + raumListe.get(index).getTyp());
    }
}