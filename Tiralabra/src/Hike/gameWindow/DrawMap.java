/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hike.gameWindow;

import Hike.Algorithms.Pathfinder;
import Hike.Graph.Node;
import Hike.ImageTable.ImageTable;
import Hike.Values;
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
    private Pathfinder route;

    public DrawMap() {
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        pic = new ShowPicture("../map1.png");


        g.drawImage(pic.getImage(), 50, 50, this);

            table = new ImageTable(pic);

        

        //Run normal dijkstra
        route = new Pathfinder(table.getNodeTable(), table.getNodeTable().length - 1, table.getNodeTable()[0].length - 1, Values.HEURISTIC);
        drawStats(g);

        if (Values.SHOWSEARCHED == true) {
            drawSearchArea(g);
        }

        drawPath(g);





    }

    /**
     * Draws the Dijkstra path with 1 pixel lines.
     *
     * @param g
     */
    public void drawStats(Graphics g) {
        g.drawString(
                "Operation took: 0" + (int) route.getTotalTime() + "ms."
                + " Calculations: " + (int) route.getC() + " (Very approximate!)", 20, 20);
        int heapsize = route.getHeap().size();
        int max = table.getNodeTable().length * table.getNodeTable()[0].length;
        double percentage = ((double) heapsize / (double) max) * 100;
        g.drawString("Distance to end: "
                + route.getDijkstraTable()[499][799].getDistance() + ". Nodes left in heap: "
                + heapsize + "/" + max + " = " + percentage + "%", 20, 40);

    }

    public void drawPath(Graphics g) {
        route.buildPath(route.getTargety(), route.getTargetx());
        Node u = route.nextPath();
        g.setColor(Color.RED);
        while (u != null) {
            g.drawLine(u.getX() + 50, u.getY() + 50, u.getX() + 50, u.getY() + 50);
            u = route.nextPath();
        }


    }

    public void drawSearchArea(Graphics g) {
        long timeStart = System.currentTimeMillis();
        Color transparentred = new Color(255, 0, 0, 100);
        Node[][] t = route.getDijkstraTable();
        g.setColor(transparentred);
        for (int h = 0; h < t.length; h++) {

            for (int w = 0; w < t[0].length; w++) {
                if (t[h][w].getDistance() < 2000000) {
                    g.drawLine(w + 50, h + 50, w + 50, h + 50);

                }
            }


        }
        long timeEnd = System.currentTimeMillis();
        System.out.println("Drawing checked areas took: " + (timeEnd - timeStart) + "ms.");

    }
}
