/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples.Adventurer;

import Structures.Grid.Coordinate;
import Structures.LinkedList.LinkedList;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

public class Window {
    private int width;
    private int height;
    private JFrame frame;
    private Canvas canvas;
    private Graphics graphics;
    private BufferStrategy buffer;
    private LinkedList<Image> images;
    private LinkedList<Coordinate> waypoints;
    public Window(int width, int height, LinkedList<Image> images, LinkedList<Coordinate> waypoints){
        this.width=width;
        this.height=height;
        this.images=images;
        this.waypoints=waypoints;
        createCanvas();
    }
    private void createCanvas(){
        canvas=new Canvas();
    }
    public void initialize(){
        frame=new JFrame();
        frame.setPreferredSize(new Dimension(width+50,height+100));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Map(images,waypoints));
        frame.pack();
        frame.setVisible(true);
    }
}
