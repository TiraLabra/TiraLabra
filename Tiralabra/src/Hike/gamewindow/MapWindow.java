/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hike.gamewindow;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *  Opens the map and route in a new window
 * @author petri
 */

public class MapWindow extends JPanel implements Runnable {

    private JFrame frame;
    private MapDraw map;


    MapWindow() {
    }



    public void run() {
        frame = new JFrame("Pathfinder");
        frame.setPreferredSize(new Dimension(900, 600));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        

        createComponents(frame.getContentPane());


        frame.pack();
        frame.setVisible(true);


    }

    private void createComponents(Container container) {
        this.map = new MapDraw();
        container.add(map);
    }



}
