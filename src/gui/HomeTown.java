package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HomeTown extends JPanel {


    public HomeTown(){
        setLayout(null);





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
