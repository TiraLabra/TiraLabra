/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hike.gameWindow;

import Hike.Algorithms.Dijkstra;
import Hike.Graph.Node;
import Hike.ImageTable.ImageTable;
import Hike.Structures.MinHeap;
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
        table = new ImageTable(pic);
        droute = new Dijkstra(table.getNodeTable(), table.getNodeTable().length - 1, table.getNodeTable()[0].length - 1);

        drawStats(g);
        drawPath(g);


    }

    /**
     * Draws the Dijkstra path with 1 pixel lines.
     *
     * @param g
     */
    public void drawStats(Graphics g) {
        g.drawString(
                "Operation took: 0" + (int) droute.getTotalTime() + "ms."
                + " Calculations: " + (int) droute.getC(), 20, 20);
        int heapsize = droute.getHeap().size();
        int max = table.getNodeTable().length * table.getNodeTable()[0].length;
        double percentage = ((double) heapsize / (double) max)*100;
        g.drawString("Distance to end: " +
                droute.getDijkstraTable()[499][799].getDistance() + ". Nodes left in heap: "
                + heapsize + "/" + max + " = " + percentage + "%", 20, 40);

    }

    public void drawPath(Graphics g) {
        droute.buildPath(droute.getTargety(), droute.getTargetx());
        Node u = droute.nextPath();
        g.setColor(Color.RED);
        while (u != null) {
            g.drawLine(u.getX() + 50, u.getY() + 50, u.getX() + 50, u.getY() + 50);
            u = droute.nextPath();
        }


    }
}
