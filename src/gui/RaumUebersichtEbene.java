package gui;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class RaumUebersichtEbene extends JFrame {
      
    public RaumUebersichtEbene(){
    this.setBounds(0, 0, 800, 800);
    setLayout(null);
    setVisible(true);
    




    }

    public void zeigeRaumUebersicht(ArrayList<Raum> raumListe){
        JPanel raumBereich = new JPanel();
        JPanel 
        raumBereich.setBounds(0, 400, 800,400);

        JLabel lblUeberschrift = new JLabel("Ebene 1"); // soll durch aktuelle Ebene ersetzt werden
        int raumZaehler = 1;
        for (Raum raum: raumListe){
            raumBereich.add(erstelleRaumLabel(raum.getTyp(), raumZaehler));
        }
        this.add(raumBereich);

    }


    public JLabel erstelleRaumLabel(String raumTyp, int RaumNummer){
        JLabel lblNeuerRaum = new JLabel();
        lblNeuerRaum.setBounds(RaumNummer*150, 400, 100, 100);
        Image img = new ImageIcon(this.getClass().getResource(findeBildfuerRaumTyp(raumTyp))).getImage();
        lblNeuerRaum.setIcon(new ImageIcon(img));

        return lblNeuerRaum;

    }

    public String findeBildfuerRaumTyp(String raumTyp){
        switch (raumTyp){
            case "kampf":
            return "/assets/bg.png";

            case "leer":
            return "/assets/Warrior_idle1.png";

            default:
            return "unbekannter Raumtyp übergeben";
        }
    }
    
}

