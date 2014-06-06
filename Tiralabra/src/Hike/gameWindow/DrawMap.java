/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hike.gameWindow;

import Hike.Algorithms.Dijkstra;
import Hike.Graph.Node;
import Hike.ImageTable.ImageTable;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * Draws the map and path
 *
 */
class DrawMap extends JPanel {

    private ShowPicture pic;
    private ImageTable table;
    private Dijkstra droute;

    public DrawMap() {
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        pic = new ShowPicture("../map1.png");

        g.drawImage(pic.getImage(), 50, 50, this);

        long startTime = System.currentTimeMillis();
        table = new ImageTable(pic);
        long endTime = System.currentTimeMillis();


        droute = new Dijkstra(table.getNodeTable());

        g.drawString("Operation took: 0" + (int) droute.getTotalTime() + "ms " 
                + ". Calculations: " + (int) droute.getC() + " (at least...)", 20, 20);

        drawPath(g);


    }

    /**
     * Draws the Dijkstra path with 1 pixel lines.
     *
     * @param g
     */
    public void drawPath(Graphics g) {
        droute.buildPath(499, 799);
        Node u = droute.nextPath();
        g.setColor(Color.RED);
        while (u != null) {
            g.drawLine(u.getX() + 50, u.getY() + 50, u.getX() + 50, u.getY() + 50);
            u = droute.nextPath();
        }


    }
}
