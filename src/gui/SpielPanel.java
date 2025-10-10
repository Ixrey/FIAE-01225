package gui;

import charakter.Gegner;
import charakter.Spieler;
import java.awt.*;
import javax.swing.*;
import kampf.Einzelkampf;

public class SpielPanel extends JPanel {
    private Image playerImage;

    public SpielPanel() {
        setLayout(null); // du benutzt aktuell absolutes Layout
        playerImage = new ImageIcon(getClass().getResource("/assets/Warrior_Idle1.jpg")).getImage();
        Spieler sp = new Spieler("Oraclez", 100, 10, 1);
        Gegner gn = new Gegner("Orc", 70, 1, 1);
        Einzelkampf kampf = new Einzelkampf(sp, gn);

        JTextArea combatLogArea = new JTextArea();
        combatLogArea.setEditable(false);
        combatLogArea.setLineWrap(true);
        combatLogArea.setWrapStyleWord(true);

        // Alle Buttons
        // JButton btnStart = new JButton("Start Game");
        // btnStart.setBounds(100, 175, 100, 25);
        // add(btnStart);
        // btnStart.addActionListener(e -> Einzelkampf.neuesSpiel(sp, gn));

        JButton btnAngriff = new JButton("Angriff");
        btnAngriff.setBounds(100, 500, 100, 25);
        add(btnAngriff);
        btnAngriff.addActionListener(e -> {
            kampf.standartangriff(); // Aktion ausführen
            combatLogArea.append(kampf.getCombatLog()); // Textfeld aktualisieren
        });

        JButton btnTraenke = new JButton("Trank");
        btnTraenke.setBounds(100, 550, 100, 25);
        add(btnTraenke);
        btnTraenke.addActionListener(e -> {
            kampf.trank(); // Aktion ausführen
            combatLogArea.append(kampf.getCombatLog());
            // Textfeld aktualisieren
        });
        JButton btnFaehigkeit = new JButton("Fähigkeit");
        btnFaehigkeit.setBounds(100, 525, 100, 25);
        add(btnFaehigkeit);
        btnFaehigkeit.addActionListener(e -> {
            kampf.faehigkeit();
            ; // Aktion ausführen
            combatLogArea.append(kampf.getCombatLog());
            // Textfeld aktualisieren
        });

        // JButton btnAufgeben = new JButton("Beenden");
        // btnAufgeben.setBounds(100, 275, 100, 25);
        // add(btnAufgeben);

        JLabel lblNamenAnzeige = new JLabel(sp.getName());
        lblNamenAnzeige.setBounds(275, 250, 50, 25);
        add(lblNamenAnzeige);

        JLabel lblLvl = new JLabel("lvl " + sp.getLevel());
        lblLvl.setBounds(350, 250, 25, 25);
        add(lblLvl);

        // Healtbar spieler
        JProgressBar healthBarSp = new JProgressBar(0, 100);
        healthBarSp.setForeground(Color.RED);
        healthBarSp.setStringPainted(true);
        healthBarSp.setValue(50);
        healthBarSp.setBounds(250, 200, 150, 25);
        add(healthBarSp);

        // healthbar gegnger
        JProgressBar healthBarGegner = new JProgressBar(0, 100);
        healthBarGegner.setForeground(Color.RED);
        healthBarGegner.setStringPainted(true);
        healthBarGegner.setValue(50);
        healthBarGegner.setBounds(500, 200, 150, 25);
        add(healthBarGegner);

        // Panel für combatlog textarea
        JScrollPane scrollPane = new JScrollPane(combatLogArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(250, 500, 400, 150);

        add(scrollPane);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (playerImage != null) {
            g.drawImage(playerImage, 300, 250, 128, 128, this);
        }
    }
}
