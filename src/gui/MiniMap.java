package gui;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import welt.Ebene;
import welt.Position;
import welt.Raum;

public class MiniMap extends JPanel { 

    public MiniMap(){
        setLayout(null);
        Ebene ebene = new Ebene();
        Position spielerposition = new Position(ebene);
        zeigeRaumUebersicht(spielerposition);
    }




    

    public void zeigeRaumUebersicht(Position position){
        
        JPanel raumBereich = new JPanel();
        raumBereich.setLayout(null);
        raumBereich.setBounds(0, 0, 800,800);

        JLabel lblUeberschrift = new JLabel("Ebene 1"); // soll durch aktuelle Ebene ersetzt werden
        lblUeberschrift.setBounds(350, 50, 100, 25);
        lblUeberschrift.setFont(new Font("Courier New", Font.BOLD, 24));
        raumBereich.add(lblUeberschrift);

        JButton btnWeiter = new JButton("Nächster Raum");
        btnWeiter.setBounds(600, 600, 150, 50);
        raumBereich.add(btnWeiter);


        int raumZaehler = 0;
        for (Raum raum: position.getRaumListe()){
            raumBereich.add(erstelleRaumLabel(raum.getTyp(), raumZaehler, position.getAktuellePosition()));
            raumZaehler++;
        }

        add(raumBereich);
    }


    public JLabel erstelleRaumLabel(String raumTyp, int raumNummer, int position){
        Image img;
        setLayout(null);
        if(raumNummer == position){
        img = new ImageIcon(this.getClass().getResource(findeBildfuerRaumtypAktuell(raumTyp))).getImage();
        }
        else{
        img = new ImageIcon(this.getClass().getResource(findeBildfuerRaumtyp(raumTyp))).getImage();
        }
        JLabel lblNeuerRaum = new JLabel();
        lblNeuerRaum.setIcon(new ImageIcon(img));
        lblNeuerRaum.setBounds((raumNummer * 125)+33, 100, 100, 100);
        return lblNeuerRaum;

    

    }

    public String findeBildfuerRaumtypAktuell(String raumTyp){
        switch (raumTyp){
            case "gegner":
            return "/assets/Gegner_aktuell.png";

            case "gegnerBesiegt":
            return "/assets/Gegner_besiegt.png";

            case "leer":
            return "/assets/Leer_aktuell.png";

            case "boss":
            return "/assets/boss_aktuell.png";

            case "boss_besiegt":
            return "/assets/Boss_besiegt_aktuell.png";


            default:
            return "unbekannter Raumtyp übergeben";
        }
    }

    public String findeBildfuerRaumtyp(String raumTyp){
        switch (raumTyp){
            case "gegner":
            return "/assets/Gegner.png";

            case "gegnerBesiegt":
            return "/assets/Gegner_besiegt.png";

            case "leer":
            return "/assets/Leer.png";

            case "boss":
            return "/assets/boss.png";

            case "boss_besiegt":
            return "/assets/Boss_besiegt.png";


            default:
            return "unbekannter Raumtyp übergeben";
        }
    }        
    
}

