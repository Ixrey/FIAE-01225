package gui;

import charakter.Gegner;
import charakter.Spieler;
import java.awt.*;
import javax.swing.*;
import kampf.Einzelkampf;

public class SpielPanel extends JPanel {
    private Image playerImage;
    private Image enemyImage;
    private Image gameMapExampleOne;

    public SpielPanel() {
        setLayout(null); // du benutzt aktuell absolutes Layout

    }

    public void zeigeKampfFenster(Spieler sp, Gegner gn, Einzelkampf kampf) {
        removeAll();
        setLayout(null);
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setPreferredSize(new Dimension(150, 200));
        infoPanel.setBounds(10, 10, 150, 100);
        
        gameMapExampleOne = new ImageIcon(getClass().getResource("/assets/backgroundgame.png")).getImage();
        enemyImage = new ImageIcon(getClass().getResource("/assets/evil.png")).getImage();
        playerImage = new ImageIcon(getClass().getResource("/assets/Warrior_Idle1.jpg")).getImage();

        JTextArea combatLogArea = new JTextArea();
        combatLogArea.setEditable(false);
        combatLogArea.setLineWrap(true);
        combatLogArea.setWrapStyleWord(true);

        //Spieler namenanzeige
        JLabel lblNamenAnzeige = new JLabel(sp.getName());
        lblNamenAnzeige.setBounds(275, 225, 150, 25);
        lblNamenAnzeige.setForeground(Color.WHITE);
        add(lblNamenAnzeige);

        //Spieler levelanzeige

        JLabel lblLvl = new JLabel("lvl " + sp.getLevel());
        lblLvl.setForeground(Color.white);
        lblLvl.setBounds(350, 225, 40, 25);

        //Gegner namenanzeige
        JLabel lblNamenAnzeigeGegner = new JLabel(gn.getName());
        lblNamenAnzeigeGegner.setForeground(Color.white);
        lblNamenAnzeigeGegner.setBounds(525, 225, 150, 25);
        add(lblNamenAnzeigeGegner);

        



        //Spielerdaten für die INFOBOX

        JLabel lblNamenAnzeigeBox = new JLabel(sp.getName());
        lblNamenAnzeigeBox.setForeground(Color.black);
        
        JLabel lblLvlBox = new JLabel("Level: " + sp.getLevel());
        lblLvlBox.setForeground(Color.black);
        
        
        JLabel lblSpielerAngriffsWertBox = new JLabel("Angriffswert: "+sp.getAngriffsWert());
        lblSpielerAngriffsWertBox.setForeground(Color.BLACK);

        JProgressBar erfahrungsXPBar = new JProgressBar(sp.getAktErfahrungspunkte(),sp.getBenErfahrungspunkte());
        erfahrungsXPBar.setForeground(Color.green);
        erfahrungsXPBar.setString("XP "+sp.getAktErfahrungspunkte()+"/"+sp.getBenErfahrungspunkte());
        erfahrungsXPBar.setStringPainted(true);
        
        

    

        //Gegner lvl
        JLabel lblLvlGegner = new JLabel("lvl "+ gn.getLevel());
        lblLvlGegner.setForeground(Color.black);
        lblLvlGegner.setBounds(425, 225, 40, 25);

        // Healtbar spieler
        JProgressBar healthBarSp = new JProgressBar(0, sp.getmaxLebenspunkte());
        healthBarSp.setForeground(Color.RED);
        healthBarSp.setStringPainted(true);
        healthBarSp.setValue(sp.getaktLebenspunkte());
        healthBarSp.setBounds(250, 200, 150, 20);
        add(healthBarSp);

        // healthbar gegnger
        JProgressBar healthBarGegner = new JProgressBar(0, gn.getmaxLebenspunkte());
        healthBarGegner.setForeground(Color.RED);
        healthBarGegner.setStringPainted(true);
        healthBarGegner.setValue(gn.getaktLebenspunkte());
        healthBarGegner.setBounds(500, 200, 150, 20);
        add(healthBarGegner);

        // Panel für combatlog textarea
        JScrollPane scrollPane = new JScrollPane(combatLogArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(250, 500, 400, 150);

        

        JButton btnAngriff = new JButton("Angriff");
        btnAngriff.setBounds(100, 500, 100, 25);
        add(btnAngriff);
        btnAngriff.addActionListener(e -> {
            kampf.standartangriff(); // Aktion ausführen
            combatLogArea.append(kampf.getCombatLog());
            healthBarSp.setValue(sp.getaktLebenspunkte()); 
            healthBarGegner.setValue(gn.getaktLebenspunkte()); 
            // Textfeld aktualisieren
        });

        JButton btnTraenke = new JButton("Trank");
        btnTraenke.setBounds(100, 550, 100, 25);
        add(btnTraenke);
        btnTraenke.addActionListener(e -> {
            kampf.trank(); // Aktion ausführen
            combatLogArea.append(kampf.getCombatLog());
            healthBarSp.setValue(sp.getaktLebenspunkte()); 
            
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
        


        //Alle kompo. für meine Infobox hinzufügen
        infoPanel.add(lblNamenAnzeigeBox);
        infoPanel.add(lblLvlBox);
        infoPanel.add(lblSpielerAngriffsWertBox);
        infoPanel.add(erfahrungsXPBar);
        

        add(scrollPane);
        add(infoPanel);
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (playerImage != null) {
            g.drawImage(gameMapExampleOne, 0, 0, getWidth(), getHeight(), this);
            g.drawImage(playerImage, 250, 250, 128, 128, this);
            g.drawImage(enemyImage, 500, 250, 128, 128, this);
        }
    }
}
