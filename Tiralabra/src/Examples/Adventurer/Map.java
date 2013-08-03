/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples.Adventurer;

import Structures.Grid.Coordinate;
import Structures.LinkedList.LinkedList;
import Utils.Iterator;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 *
 * @author Kalle
 */
public class Map extends JComponent{
    private LinkedList<Image> images;
    private LinkedList<Coordinate> waypoints;
    Map(LinkedList<Image> images, LinkedList<Coordinate> waypoints) {
        this.images=images;
        this.waypoints=waypoints;
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Iterator<Image> i = new Iterator<Image>(images);
        while(i.hasNext()){
            Image m = i.getNext();
            m.draw(g);
        }
        Iterator<Coordinate> n = new Iterator<Coordinate>(waypoints);
        while(n.hasNext()){
            Coordinate c = n.getNext();
            g.setColor(Color.red);
            g.fillOval(c.getX(), c.getY(), 7, 7);
        }
    }
}
