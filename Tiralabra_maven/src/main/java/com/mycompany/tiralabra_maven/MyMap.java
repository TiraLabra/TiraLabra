package com.mycompany.tiralabra_maven;

public class MyMap {

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

    /**
     * Constructor
     */
    public MyMap() {
    }

    /**
     * @return Maximum width of the map
     */
    public int getMaxX() {
        return maxX;
    }
    /**
     * @return Maximum height of the map
     */
    public int getMaxY() {
        return maxY;
    }
    /**
     * @return Start node
     */
    public Node getStart() {
        return start;
    }
    /**
     * @return End node
     */
    public Node getEnd() {
        return end;
    }

    /** returns the map */
    public Node[][] getMap() {
        return this.map;
    }


    /** Creates a map according to the inserted String.
     * Reads the map size, creates the nodes, creates a start and end point
     * by reading each character of the string.
     *
     * @param mapString The wanted map.
     */

    public void createMap(String mapString) {
        String [] mapLines = mapString.split("\n");
        this.maxX = mapLines[0].length();
        this.maxY = mapLines.length;

        this.map = new Node[maxY][maxX];
        /** Read the string stored line by line, creating nodes: */
        for (int y = 0; y < maxY; y++) {
            String _y = mapLines[y];
            for (int x = 0; x < maxX; x++) {
                this.map[y][x] = new Node(x, y);
                this.map[y][x].setCost(2000);
                if(_y.charAt(x) == 'o' ) {
                    this.start = this.map[y][x];
                } else if (_y.charAt(x) == 'x' ) {
                    this.end = this.map[y][x];
                }

                this.map[y][x].setCharacter(_y.charAt(x));

            }
        }

    }



    /** Prints the map */
    public void printMap() {
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                characterColor(map[y][x].getCharacter());
                //System.out.print(map[y][x].getCharacter());
            }
            System.out.println("");
        }
    }

    /**
     * Prints the character and adds colors to it.
     * After being printed, resets the color to terminal default.
     *
     * @param c character which is being printed
     */
    public void characterColor(Character c) {
        String resetColor = "\u001B[0m";
        String out = "";
        if (c == '#') {
            out = "" + c;
        } else if (c == '_') {
           out = "\u001B[02;37m" + c;
        } else if (c == 'P') {
            out = "\u001B[34m" + c;
        } else if (c == 'o') {
            out = "\u001B[31m" + c;
        } else if (c == 'x') {
            out = "\u001B[01;31m" + c;
        }
        System.out.print(out += resetColor);
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
