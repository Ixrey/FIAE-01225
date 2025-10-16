package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import charakter.Spieler;
import spiel.Spielablauf;
import welt.Ebene;
import welt.Position;
import welt.Raum;

public class MiniMap extends JPanel {

    public MiniMap() {
    }

    public void zeigeRaumUebersicht(Spieler spieler, Position position) {
        if (spieler == null || position == null) {
            System.err.println("MiniMap: Spieler oder Position fehlt, keine Aktualisierung.");
            return;
        }

        removeAll();

        setLayout(null);
        JPanel raumBereich = new JPanel();
        raumBereich.setLayout(null);
        raumBereich.setBounds(0, 0, 800, 800);

        JLabel lblUeberschrift = new JLabel("Ebene 1"); // soll durch aktuelle Ebene ersetzt werden
        lblUeberschrift.setBounds(350, 50, 100, 25);
        lblUeberschrift.setFont(new Font("Courier New", Font.BOLD, 24));
        raumBereich.add(lblUeberschrift);

        JButton btnWeiter = new JButton("Naechster Raum");
        btnWeiter.setBounds(600, 600, 150, 50);
        raumBereich.add(btnWeiter);

        btnWeiter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Spielablauf.behandelWeiterButton();
            }
        });

        int raumZaehler = 0;
        for (Raum raum : position.getRaumListe()) {
            raumBereich.add(erstelleRaumLabel(raum.getTyp(), raumZaehler, position.getAktuellePosition()));
            raumZaehler++;
        }

        add(infoPanel(spieler));
        add(raumBereich);

        revalidate();
        repaint();
    }

    public JLabel erstelleRaumLabel(String raumTyp, int raumNummer, int position) {
        setLayout(null);
        String bildPfad = raumNummer == position ? findeBildfuerRaumtypAktuell(raumTyp)
                : findeBildfuerRaumtyp(raumTyp);
        ImageIcon icon = ladeIcon(bildPfad);
        JLabel lblNeuerRaum = new JLabel();
        if (icon != null) {
            lblNeuerRaum.setIcon(icon);
        } else {
            lblNeuerRaum.setText(raumTyp);
            lblNeuerRaum.setHorizontalAlignment(JLabel.CENTER);
            lblNeuerRaum.setVerticalAlignment(JLabel.CENTER);
            lblNeuerRaum.setOpaque(true);
            lblNeuerRaum.setBackground(new Color(200, 200, 200));
            lblNeuerRaum.setForeground(Color.RED);
        }
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
                return "/assets/Boss_aktuell.png";

            case "bossBesiegt":
                return "/assets/Boss_besiegt_aktuell.png";

            default:
                System.err.println("MiniMap: Unbekannter aktueller Raumtyp '" + raumTyp + "'");
                return null;
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
                return "/assets/Boss.png";

            case "bossBesiegt":
                return "/assets/Boss_besiegt.png";

            default:
                System.err.println("MiniMap: Unbekannter Raumtyp '" + raumTyp + "'");
                return null;
        }
    }

    public JPanel infoPanel(Spieler spieler) {
        JPanel stats = new JPanel();
        stats.setLayout(new BoxLayout(stats, BoxLayout.Y_AXIS));
        stats.setPreferredSize(new Dimension(150, 200));
        stats.setBounds(10, 10, 150, 100);

        if (spieler == null) {
            JLabel placeholder = new JLabel("Spieler nicht verfuegbar");
            placeholder.setForeground(Color.black);
            placeholder.setFont(new Font("Courier New", Font.BOLD, 14));
            stats.add(placeholder);
            return stats;
        }

        // Spielerdaten fuer die INFOBOX

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

        stats.add(lblNamenAnzeigeBox);
        stats.add(lblLvlBox);
        stats.add(lblSpielerAngriffsWertBox);
        stats.add(erfahrungsXPBar);

        return stats;
    }

    private ImageIcon ladeIcon(String pfad) {
        if (pfad == null || pfad.isBlank()) {
            return null;
        }
        URL resource = getClass().getResource(pfad);
        if (resource == null) {
            System.err.println("MiniMap: Ressource nicht gefunden: " + pfad);
            return null;
        }
        return new ImageIcon(resource);
    }
}
