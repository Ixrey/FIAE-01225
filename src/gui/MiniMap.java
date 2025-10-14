package gui;

import java.awt.*;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import charakter.Spieler;
import welt.Position;
import welt.Raum;

public class MiniMap extends JPanel {

    public MiniMap() {

    }

    public void zeigeRaumUebersicht(Spieler spieler, Position position) {
        setLayout(null);
        JPanel raumBereich = new JPanel();
        raumBereich.setLayout(null);
        raumBereich.setBounds(0, 0, 800, 800);

        JLabel lblUeberschrift = new JLabel("Ebene 1"); // soll durch aktuelle Ebene ersetzt werden
        lblUeberschrift.setBounds(350, 50, 100, 25);
        lblUeberschrift.setFont(new Font("Courier New", Font.BOLD, 24));
        raumBereich.add(lblUeberschrift);

        JButton btnWeiter = new JButton("Nächster Raum");
        btnWeiter.setFont(new Font("Courier New",Font.BOLD,18));
        btnWeiter.setBackground(Color.DARK_GRAY);
        btnWeiter.setForeground(Color.WHITE);
        btnWeiter.setBounds(550, 580, 200, 50);
        raumBereich.add(btnWeiter);

        int raumZaehler = 0;
        for (Raum raum : position.getRaumListe()) {
            raumBereich.add(erstelleRaumLabel(raum.getTyp(), raumZaehler, position.getAktuellePosition()));
            raumZaehler++;
        }
        add(infoPanel(spieler));
        add(raumBereich);

    }

    public JLabel erstelleRaumLabel(String raumTyp, int raumNummer, int position) {
        Image img;
        setLayout(null);
        if (raumNummer == position) {
            img = new ImageIcon(this.getClass().getResource(findeBildfuerRaumtypAktuell(raumTyp))).getImage();
        } else {
            img = new ImageIcon(this.getClass().getResource(findeBildfuerRaumtyp(raumTyp))).getImage();
        }
        JLabel lblNeuerRaum = new JLabel();
        lblNeuerRaum.setIcon(new ImageIcon(img));
        lblNeuerRaum.setBounds((raumNummer * 125) + 33, 100, 100, 100);
        return lblNeuerRaum;

    }

    public String findeBildfuerRaumtypAktuell(String raumTyp) {
        switch (raumTyp) {
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

    public String findeBildfuerRaumtyp(String raumTyp) {
        switch (raumTyp) {
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

    public JPanel infoPanel(Spieler spieler) {
        JPanel stats = new JPanel();
        stats.setLayout(new BoxLayout(stats, BoxLayout.Y_AXIS));
        stats.setPreferredSize(new Dimension(150, 200));
        stats.setBounds(10, 400, 150, 300);

        // Spielerdaten für die INFOBOX

        JLabel lblSpielerIcon = new JLabel();
        Image spielerImage = new ImageIcon(this.getClass().getResource("/assets/Warrior_Idle1.jpg")).getImage();
        lblSpielerIcon.setIcon(new ImageIcon(spielerImage));
            

        JLabel lblNamenAnzeigeBox = new JLabel(spieler.getName());
        lblNamenAnzeigeBox.setForeground(Color.black);
        lblNamenAnzeigeBox.setFont(new Font("Courier New", Font.BOLD, 14));

        JLabel lblLvlBox = new JLabel("Level: " + spieler.getLevel());
        lblLvlBox.setForeground(Color.black);
        lblLvlBox.setFont(new Font("Courier New", Font.BOLD, 14));

        JLabel lblSpielerAngriffsWertBox = new JLabel("Angriffswert: " + spieler.getAngriffsWert());
        lblSpielerAngriffsWertBox.setForeground(Color.BLACK);
        lblSpielerAngriffsWertBox.setFont(new Font("Courier New", Font.BOLD, 14));

        JProgressBar erfahrungsXPBar = new JProgressBar(0, spieler.getBenErfahrungspunkte());
        erfahrungsXPBar.setFont(new Font("Courier New", Font.BOLD, 14));
        erfahrungsXPBar.setForeground(Color.green);
        erfahrungsXPBar.setString("XP " + spieler.getAktErfahrungspunkte() + "/" + spieler.getBenErfahrungspunkte());
        erfahrungsXPBar.setValue(spieler.getAktErfahrungspunkte());
        erfahrungsXPBar.setStringPainted(true);

        stats.add(lblSpielerIcon);
        stats.add(lblNamenAnzeigeBox);
        stats.add(lblLvlBox);
        stats.add(lblSpielerAngriffsWertBox);
        stats.add(erfahrungsXPBar);

        return stats;
    }
}
