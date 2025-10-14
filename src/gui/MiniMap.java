package gui;

import java.awt.*;
import javax.swing.ImageIcon;
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


        int raumZaehler = 0;
        for (Raum raum: position.getRaumListe()){
            raumBereich.add(erstelleRaumLabel(raum.getTyp(), raumZaehler));
            raumZaehler++;
        }

        add(raumBereich);

        // revalidate();
        // repaint();


    }


    public JLabel erstelleRaumLabel(String raumTyp, int RaumNummer){

        setLayout(null);
        Image img = new ImageIcon(this.getClass().getResource(findeBildfuerRaumTyp(raumTyp))).getImage();
        JLabel lblNeuerRaum = new JLabel();
        lblNeuerRaum.setIcon(new ImageIcon(img));
        lblNeuerRaum.setBounds((RaumNummer * 125)+33, 100, 100, 100);
        return lblNeuerRaum;

    }



    public String findeBildfuerRaumTyp(String raumTyp){
        switch (raumTyp){
            case "gegner":
            return "/assets/Totenkopf_chatgpt.png";

            case "leer":
            return "/assets/clear_chatgpt.png";

            case "boss":
            return "/assets/bossIcon.png";

            case "gegnerBesiegt":
            return "";
            default:
            return "unbekannter Raumtyp übergeben";
        }
    }

        
    
}

