package Hike.gameWindow;

import Hike.ImageTable.ImageTable;
import Hike.controls.ClickListener;
import java.awt.Color;

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

        JTextArea teksti = new JTextArea("Finds the shortest route in a drawn image.");
        teksti.setBackground(Color.gray);
        teksti.setWrapStyleWord(true);
        teksti.setLineWrap(true);
        teksti.setBounds(50, 50, 200, 500);

        JButton ok = new JButton("Dijkstra");
        listener = new ClickListener(this);
        ok.setActionCommand(ClickListener.Actions.STARTGAME.name());
        ok.setBounds(600, 50, 100, 50);
        ok.addActionListener(listener);


        add(teksti);
        add(ok);
// Image is out of date.
//        helpfulImage = new ShowPicture("../help.png");
//        picture.setBounds(200, 200, 500, 300);
//        add(helpfulImage);

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
