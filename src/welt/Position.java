package welt;

import java.util.ArrayList;

public class Position {
    private String aktuellerRaumTyp;
    private int aktuellePosition;
    private ArrayList<Raum> raumListe;
    private Ebene ebene;

    public Position(Ebene ebene) {
        this.ebene = ebene;
        this.raumListe = ebene.getRaumListe();
        this.aktuellePosition = 0;
        this.aktuellerRaumTyp = raumListe.get(aktuellePosition).getTyp();
        System.out.println(raumListe);
    }

    // Navigation durch Räume
    public void naechsterRaum() {
        if (aktuellePosition < raumListe.size() - 1) {
            aktuellePosition++;
            aktuellerRaumTyp = raumListe.get(aktuellePosition).getTyp();
        } else {
            System.out.println("Du befindest dich im letzten Raum!");
        }
    }

    public void vorherigerRaum() {
        if (aktuellePosition > 0) {
            aktuellePosition--;
            aktuellerRaumTyp = raumListe.get(aktuellePosition).getTyp();
        } else {
            System.out.println("Du befindest dich im ersten Raum!");
        }
    }

    /*
     * // Raumtypwechsel
     * public void raumBesiegt() {
     * Raum aktuellerRaum = raumListe.get(aktuellePosition);
     * aktuellerRaum.typWechsel(aktuellerRaum.getTyp());
     * System.out.println("Gegner besiegt! Raumtyp geändert zu " +
     * aktuellerRaum.getTyp());
     * }
     */
    // Getter & Setter
    public String getAktuellerRaumTyp() {
        return aktuellerRaumTyp;
    }

    public int getAktuellePosition() {
        return aktuellePosition;
    }

    /*
     * public String getAktuellerZustand() {
     * return raumListe.get(aktuellePosition).zustand();
     * }
     */
    public boolean istLetzterRaum() {
        return aktuellePosition == raumListe.size() - 1;
    }

    public ArrayList<Raum> getRaumListe(){
        return raumListe;
    }
}