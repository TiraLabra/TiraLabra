package com.mycompany.tiralabra_maven;

public class Kartta {

    /** Width of the map */
    private int maxX;
    /** Height of the map */
    private int maxY;
    /** Starting Node */
    private Node start;
    /** End Node */
    private Node end;
    /** The Map itself */
    private Node[][] map;

    public Kartta(Node start, Node end, int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.start = start;
        this.end = end;
    }

    /** Creates the map according to the items:
     * Creates the map with the spesifics, sets cost for each node, sets walls and characters for each node
     * representing visually each point on the map
     * */
    public void createMap() {
        this.map = new Node[maxX][maxY];
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                this.map[y][x] = new Node(x, y);
                this.map[y][x].setCost(2000);
                //set wall manually
                if ((x == 2 && y == 1||(x == 2 && y == 2) || (x == 2 && y == 3)) || (x == 2 & y == 4) ){
                    this.map[y][x].setWall();
                    this.map[y][x].setCharacter('#');
                } else if (isSamePointOnMap(x, y, start)) {
                    this.map[y][x].setCharacter('o');
                } else if (isSamePointOnMap(x, y, end)) {
                    this.map[y][x].setCharacter('x');
                } else {
                    this.map[y][x].setCharacter('_');
                }

            }
        }
    }

    /** returns the map */
    public Node[][] getMap() {
        return this.map;
    }

    /** Prints the map */
    public void printMap() {
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                System.out.print(map[y][x].getCharacter());
            }
            System.out.println("");
        }
    }

    /** Compares two points on the map
     * @param x X coordinate of the point on the map
     * @param y Y coordinate of the point on the map
     * @param comparable Node which it is being compared to
     * return true if two points are the same
     * */
    public boolean isSamePointOnMap(int x, int y, Node comparable) {
        return (this.map[y][x].getX() == comparable.getX()) && (this.map[y][x].getY() == comparable.getY());
    }


}
