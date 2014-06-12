/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hike.Graph;

import Hike.Structures.LinkyList;

/**
 * Class containing a cell in the table created in ImageTable. Nodes have a y
 * and x coordinate, weight based on the terrain, list of neighbours (3-8
 * neighbours), link to a previous node and distance for Dijkstra.
 *
 * @author petri
 */
public class Node {

    private int x;
    private int y;
    private int weight;
    private Node[][] table;
    private LinkyList neighbours;
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
    private int heapIndex;
    private boolean checked;

    /**
     * Creates a node
     *
     * @param y
     * @param x
     * @param w weight of node
     */
    public Node(int y, int x, int w) {


        this.neighbours = new LinkyList();

        this.y = y;
        this.x = x;
        this.weight = w;
        this.distance = 2000000;
        this.heapIndex = -1;
        this.checked = false;



    }

    public int getWeight() {
        return this.weight;
    }

    public void setPrevious(Node prev) {

        this.previous = prev;
    }

    /**
     * Goes through the whole table and sets links to adjacent cells. Diagonal
     * links can be turned off by commenting their statements out.
     *
     * @param table A table of nodes sent from ImageTable class
     */
    public void setNeighbours(Node[][] table) {
        this.table = table;
        int h = table.length;
        int w = table[0].length;

        for (int py = 0; py < h; py++) {
            for (int px = 0; px < w; px++) {


                if (px + 1 < w) {
                    table[py][px].E = table[py][px + 1];
                    table[py][px].getNeighbours().add(table[py][px + 1]);
                }

                if (py - 1 >= 0) {
                    table[py][px].N = table[py - 1][px];
                    table[py][px].getNeighbours().add(table[py - 1][px]);
                }

                if (py + 1 < h) {
                    table[py][px].S = table[py + 1][px];
                    table[py][px].getNeighbours().add(table[py + 1][px]);
                }
                if (px - 1 >= 0) {
                    table[py][px].W = table[py][px - 1];
                    table[py][px].getNeighbours().add(table[py][px - 1]);
                }
                if (py + 1 < h && px - 1 >= 0) {
                    table[py][px].SW = table[py + 1][px - 1];
                    table[py][px].getNeighbours().add(table[py + 1][px - 1]);
                }

                if (py + 1 < h && px + 1 < w) {
                    table[py][px].SE = table[py + 1][px + 1];
                    table[py][px].getNeighbours().add(table[py + 1][px + 1]);
                }


                if (py - 1 >= 0 && px - 1 >= 0) {
                    table[py][px].NW = table[py - 1][px - 1];
                    table[py][px].getNeighbours().add(table[py - 1][px - 1]);
                }

                if (py - 1 >= 0 && px + 1 < w) {
                    table[py][px].NE = table[py - 1][px + 1];
                    table[py][px].getNeighbours().add(table[py - 1][px + 1]);
                }
            }
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Node[][] getTable() {
        return this.table;
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
     * Prints the table with various details, used for checking if the values
     * are valid.
     */
    public void printTable() {
        for (int h = 0; h < table.length; h++) {
            System.out.println("");
            for (int w = 0; w < table[0].length; w++) {
                System.out.print("| " + this.table[h][w].x + " " + this.table[h][w].y + " D: " + table[h][w].getDistance() + " W: " + table[h][w].getWeight() + " P: " + table[h][w].getPrevious());
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

    public LinkyList getNeighbours() {
        return this.neighbours;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setHeapIndex(int index) {
        this.heapIndex = index;
    }

    public int getHeapIndex() {
        return this.heapIndex;
    }

    public void setChecked() {
        this.checked = true;
    }

    public boolean getChecked() {
        return this.checked;
    }
}
