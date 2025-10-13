package welt;

public class Raum {
    private String typ;

    public Raum(String typ) {
        this.typ = typ;
    }

    /*
     * // Zustände 'erkundung' und 'kampf'
     * public String zustand() {
     * if (typ.equals("leer") || typ.equals("gegnerBesiegt") ||
     * typ.equals("bossBesiegt")) {
     * return "erkundung";
     * } else if (typ.equals("gegner") || typ.equals("boss")) {
     * return "kampf";
     * } else {
     * System.out.println("Fehler in der zustand-Methode, in der Klasse Raum");
     * return "";
     * }
     * }
     */
    // Wechsel von 'gegner' auf 'gegnerBesiegt' usw.
    public void typWechsel() {
        if (typ.equals("gegner")) {
            setTyp("gegnerBesiegt");
        } else if (typ.equals("boss")) {
            setTyp("bossBesiegt");
        } else {
            System.out.println("Fehler in der typWechsel-Methode, in der Klasse Raum");
        }
    }

    // Getter & Setter
    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }
}