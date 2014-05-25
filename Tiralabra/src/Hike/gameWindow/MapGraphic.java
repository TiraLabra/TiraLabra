/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hike.gameWindow;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *  Opens the map and route in a new window
 * @author petri
 */
public class MapGraphic extends JPanel implements Runnable {

    private JFrame frame;
    private DrawMap map;

    public void run() {
        frame = new JFrame("Dijkstra");
        frame.setPreferredSize(new Dimension(900, 600));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        createComponents(frame.getContentPane());


        frame.pack();
        frame.setVisible(true);


    }

    private void createComponents(Container container) {
        this.map = new DrawMap();
        container.add(map);
    }

}
