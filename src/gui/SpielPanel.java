package gui;

import charakter.Gegner;
import charakter.Spieler;
import java.awt.*;
import javax.swing.*;
import kampf.Einzelkampf;

public class SpielPanel extends JPanel {

    public SpielPanel() {
        setLayout(null); // du benutzt aktuell absolutes Layout

        Spieler sp = new Spieler("Oraclez", 100, 10, 1);
        Gegner gn = new Gegner("Orc", 70, 1, 1); 
        Einzelkampf kampf = new Einzelkampf(sp, gn);        

        // Alle Buttons
        JButton btnStart = new JButton("Start Game");
        btnStart.setBounds(100, 175, 100, 25);
        add(btnStart);
        btnStart.addActionListener(e -> kampf.kampf());

        JButton btnAngriff = new JButton("Angriff");
        btnAngriff.setBounds(100, 225, 100, 25);
        add(btnAngriff);
        btnAngriff.addActionListener(e -> kampf.kampfablauf());

        JButton btnTraenke = new JButton("Trank");
        btnTraenke.setBounds(100, 250, 100, 25);
        add(btnTraenke);
        btnTraenke.addActionListener(e -> kampf.spieleraktion(1));

        JButton btnFaehigkeit = new JButton("Fähigkeit");
        btnFaehigkeit.setBounds(100, 200, 100, 25);
        add(btnFaehigkeit);

        JButton btnAufgeben = new JButton("Aufgeben");
        btnAufgeben.setBounds(100, 275, 100, 25);
        add(btnAufgeben);

        JLabel lblNamenAnzeige = new JLabel();
        lblNamenAnzeige.setBounds(300, 100, 100, 25);
        add(lblNamenAnzeige);

        JLabel lblLvl = new JLabel();
        lblLvl.setBounds(300, 125, 50, 25);
        add(lblLvl);


        //Healtbar spieler
        JProgressBar healthBarSp = new JProgressBar(0, 100);
        healthBarSp.setForeground(Color.RED);
        healthBarSp.setStringPainted(true);
        healthBarSp.setValue(50);
        healthBarSp.setBounds(250, 200, 150, 25);
        add(healthBarSp);

        //healthbar gegnger
        JProgressBar healthBarGegner = new JProgressBar(0, 100);
        healthBarGegner.setForeground(Color.RED);
        healthBarGegner.setStringPainted(true);
        healthBarGegner.setValue(50);
        healthBarGegner.setBounds(500, 200, 150, 25);
        add(healthBarGegner);

        //CombatLOGarea
        JTextArea combatLogArea = new JTextArea();
        combatLogArea.setEditable(false);
        combatLogArea.setLineWrap(true);
        combatLogArea.setWrapStyleWord(true);


        //Panel für combatlog textarea
        JScrollPane scrollPane = new JScrollPane(combatLogArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(250, 500, 300, 150);
        add(scrollPane);
    }
}
