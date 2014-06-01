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
import java.util.Deque;
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
    private Deque stack;

    public DrawMap() {
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        pic = new ShowPicture("../map1.png");
        g.drawImage(pic.getImage(), 50, 50, this);

        table = new ImageTable(pic);
        droute = new Dijkstra(table.getNodeTable());
//        Node[][] nTable= table.getNodeTable();
//        nTable[0][0].printNeighbours();
        drawPath(g);
//        droute.getDijkstraTable()[0][0].printTable();
        



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
