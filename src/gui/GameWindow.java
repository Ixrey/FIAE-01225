package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import charakter.Gegner;
import charakter.Spieler;
import kampf.Einzelkampf;

public class GameWindow extends JFrame {
    public static void main(String[] args) throws Exception {

        Spieler testSpieler = new Spieler("Test", 150, 12, 1);
        Gegner goblin = new Gegner("Testgegner", 110, 8, 1);

        Einzelkampf x = new Einzelkampf(testSpieler, goblin);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameWindow();// Your constructor here
            }
        });
    }

    public GameWindow() {
        this.setTitle("RPG Game");
        this.setBounds(600, 250, 600, 600);

        JPanel contentPane = new JPanel();
        contentPane.setBounds(0, 0, 600, 600);
        this.setContentPane(contentPane);
        contentPane.setLayout(null);

        // Button

        // btn Angriff
        JButton btnAngriff = new JButton("Angriff");
        btnAngriff.setBounds(100, 225, 100, 25);
        contentPane.add(btnAngriff);
        btnAngriff.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }

        });

        // btn Tränke
        JButton btnTraenke = new JButton("Trank");
        btnTraenke.setBounds(100, 250, 100, 25);
        contentPane.add(btnTraenke);
        btnTraenke.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                getSpieler().warteEingabe();
            }

        });

        // btn fähigkeit
        JButton btnFaehigkeit = new JButton("Fähigkeit");
        btnFaehigkeit.setBounds(100, 200, 100, 25);
        contentPane.add(btnFaehigkeit);
        btnFaehigkeit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }

        });

        // btn Aufgeben

        JButton btnAufgeben = new JButton("Aufgeben");
        btnAufgeben.setBounds(100, 275, 100, 25);
        contentPane.add(btnAufgeben);
        btnAufgeben.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }

        });

        // LABEL

        // Label für namensanzeige

        JLabel lblNamenAnzeige = new JLabel("Oraclez");// Name variable
        lblNamenAnzeige.setBounds(300, 100, 100, 25);
        contentPane.add(lblNamenAnzeige);

        // Label für Level

        JLabel lblLvl = new JLabel("Level 1");
        lblLvl.setBounds(300, 125, 50, 25);
        contentPane.add(lblLvl);

        // Progressbar für healthbar

        JProgressBar healthBar = new JProgressBar();
        healthBar.setForeground(Color.RED);
        healthBar.setStringPainted(true);
        // int health = 80;
        healthBar.setValue(80);
        // String half = Integer.toString(health);
        // healthBar.setString(half+"/100");
        healthBar.setBounds(300, 400, 150, 25);
        contentPane.add(healthBar);

        setVisible(true);

    }
}
