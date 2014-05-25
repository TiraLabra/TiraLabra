/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hike.Graph;

import Hike.Values;
import java.util.LinkedList;

/**
 *  Class containing a cell in the table created in ImageTable.
 * Nodes have a y and x coordinate, weight based on the terrain,
 * list of neighbours (3-8 neighbours), link to a previous node and distance for Dijkstra.
 * 
 * @author petri
 */
public class Node {

    private int x;
    private int y;
    private int weight;
    private Node[][] table;
    private LinkedList<Node> neighbours;
    private Node S = null;  // All the compass directions are currently unused
    private Node SW = null;
    private Node SE = null;
    private Node N = null;
    private Node NW = null;
    private Node NE = null;
    private Node W = null;
    private Node E = null;
    private Node previous;
    private int distance;
    
    /**
     * Creates a node
     * @param y
     * @param x
     * @param w weight of node
     */

    public Node(int y, int x, int w) {

        this.neighbours = new LinkedList<Node>();

        this.y = y;
        this.x = x;
        this.weight = w;
        this.distance = 2000000;

        if (this.table == null) {
            this.table = table;
        }




    }

    public int getWeight() {
        return this.weight;
    }

    public void setPrevious(Node prev) {

        this.previous = prev;
    }
    
    /**
     * Goes through the whole table and sets links to adjecent cells. Diagonal links can be turned off by commenting their statements out.
     * 
     * @param table A table of nodes sent from ImageTable class
     */

    public void setNeighbours(Node[][] table) {
        this.table = table;

        for (int py = 0; py < Values.IMAGEHEIGHT; py++) {
            for (int px = 0; px < Values.IMAGEWIDTH; px++) {


                if (px + 1 < Values.IMAGEWIDTH) {
                    table[py][px].E = table[py][px + 1];
                    table[py][px].getNeighbours().add(table[py][px + 1]);
                }

                if (py - 1 >= 0) {
                    table[py][px].N = table[py - 1][px];
                    table[py][px].getNeighbours().add(table[py - 1][px]);
                }

                if (py + 1 < Values.IMAGEHEIGHT) {
                    table[py][px].S = table[py + 1][px];
                    table[py][px].getNeighbours().add(table[py + 1][px]);
                }
                if (px - 1 >= 0) {
                    table[py][px].W = table[py][px - 1];
                    table[py][px].getNeighbours().add(table[py][px - 1]);
                }
                if (py + 1 < Values.IMAGEHEIGHT && px - 1 >= 0) {
                    table[py][px].SW = table[py + 1][px - 1];
                    table[py][px].getNeighbours().add(table[py + 1][px - 1]);
                }

                if (py + 1 < Values.IMAGEHEIGHT && px + 1 < Values.IMAGEWIDTH) {
                    table[py][px].SE = table[py + 1][px + 1];
                    table[py][px].getNeighbours().add(table[py + 1][px + 1]);
                }


                if (py - 1 >= 0 && px - 1 >= 0) {
                    table[py][px].NW = table[py - 1][px - 1];
                    table[py][px].getNeighbours().add(table[py - 1][px - 1]);
                }

                if (py - 1 >= 0 && px + 1 < Values.IMAGEWIDTH) {
                    table[py][px].NE = table[py - 1][px + 1];
                    table[py][px].getNeighbours().add(table[py - 1][px + 1]);
                }






            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Node[][] getTable() {
        return table;
    }

    public Node getS() {
        return S;
    }

    public Node getSW() {
        return SW;
    }

    public Node getSE() {
        return SE;
    }

    public Node getN() {
        return N;
    }

    public Node getNW() {
        return NW;
    }

    public Node getNE() {
        return NE;
    }

    public Node getW() {
        return W;
    }

    public Node getE() {
        return E;
    }

    public Node getPrevious() {
        return this.previous;
    }

    public void setDistance(int i) {
        this.distance = i;
    }
    
    /**
     * Prints the table with various details, used for checking if the values are valid.
     */

    public void printTable() {
        for (int y = 0; y < Values.IMAGEHEIGHT; y++) {
            System.out.println("");
            for (int x = 0; x < Values.IMAGEWIDTH; x++) {
                System.out.print("| " + table[y][x].getY() + " " + table[y][x].getX() + " D: " + table[y][x].getDistance() + " W: " + table[y][x].getWeight() + " P: " + table[y][x].getPrevious());
            }

        }
    }

    public void printNeighbours() {
        for (Node node : neighbours) {
            System.out.println(node);
        }
    }

    public int getDistance() {
        return this.distance;
    }



    public LinkedList<Node> getNeighbours() {
        return this.neighbours;
    }
}
