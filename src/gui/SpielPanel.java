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

    }

    public void zeigeKampfFenster(Spieler sp, Gegner gn, Einzelkampf kampf) {
        removeAll();
        setLayout(null);

        playerImage = new ImageIcon(getClass().getResource("/assets/Warrior_Idle1.jpg")).getImage();

        JTextArea combatLogArea = new JTextArea();
        combatLogArea.setEditable(false);
        combatLogArea.setLineWrap(true);
        combatLogArea.setWrapStyleWord(true);

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

        System.out.println("[DEBUG] SpielPanel.zeigeKampfFenster()");
        add(scrollPane);
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (playerImage != null) {
            g.drawImage(playerImage, 250, 250, 128, 128, this);
        }
    }
}
