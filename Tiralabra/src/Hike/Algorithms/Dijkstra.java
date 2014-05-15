/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hike.Algorithms;

import Hike.ImageTable.ImageTable;
import Hike.Values;

/**
 *
 * @author petri
 */
public class Dijkstra {
    private int[][] table;
    private int[][] d;
    private int[][] previous;
    private int startx;
    private int starty;
    private int goalx;
    private int goaly;
    private final int max = 2147483647;
    
    
    public Dijkstra(ImageTable table) {
        this.table = table.getTable();
        d = new int[Values.IMAGEHEIGHT][Values.IMAGEWIDTH];
        
        
    }
    
    
    
}
