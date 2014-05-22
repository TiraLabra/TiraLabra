package Hike.gameWindow;

import Hike.Algorithms.Dijkstra;
import Hike.ImageTable.ImageTable;
import Hike.controls.ClickListener;
import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;


import javax.swing.*;

/**
 * Shows the welcome screen
 * 
 */

public class GameScreen extends JPanel {

    private ShowPicture picture;
    private ShowPicture pixel;
    private ActionListener listener;
    private MapGraphic map;
    private ImageTable table;

    public GameScreen() {
        setBackground(Color.gray);
        setLayout(null);

        JTextArea teksti = new JTextArea("Instruction, instructions. ");
        teksti.setBackground(Color.gray);
        teksti.setLineWrap(true);
        teksti.setBounds(50, 50, 200, 50);

        JButton ok = new JButton("Dijkstra");
        listener = new ClickListener(this);
        ok.setActionCommand(ClickListener.Actions.STARTGAME.name());
        ok.setBounds(600, 50, 100, 50);
        ok.addActionListener(listener);

        picture = new ShowPicture("../help.png");
        picture.setBounds(200, 200, 500, 300);
        add(teksti);
        add(ok);
        add(picture);

    }
   

    /**
     * Open the map screen to show path.
     * 
     */
    

    public void openMap() {
        map = new MapGraphic();
        map.run();
    }
    


}
