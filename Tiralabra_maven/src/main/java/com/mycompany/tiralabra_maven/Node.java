package com.mycompany.tiralabra_maven;
/**
 * Node is a coordinate within the Map. Node can be a start node, end node, wall or path.
 * */

public class Node implements Comparable<Node> {
    /** Node's x coordinate */
    private int x;
    /** Node's y coordinate */
    private int y;
    /** Contains info if the Node has a parent Node (ie previous step) */
    private Node parent;
    /** Node's cost */
    private int cost;
    /** Node's char in map */
    private Character character;
    /** Heuristic cost of the node; Priority rank in the queue */
    private int heuristic;

    /** Returns the character assigned to the node */
    public Character getCharacter() {
        return character;
    }
    /** Assigns a certain character to the node */
    public void setCharacter(Character character) {
        this.character = character;
    }

    /** Returns cost of the node */
    public int getCost() {
        return cost;
    }
    /** Sets cost of the node */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /** Returns heuristic cost of the node */
    public int getHeuristic() {
        return heuristic;
    }
    /** Sets heuristic cost of the node */
    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }


    /**
     * Create a new node
     * @param x The x coordinate on the map
     * @param y The y coordinate on the map
     */
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the x coordinate of the node
     * @return The x coordinate of the node
     */
    public int getX() {
        return x;
    }


    /**
     * Set the x coordinate of the node
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Get the y coordinate of the node
     * @return The y coordinate of the node
     */
    public int getY() {
        return y;
    }


    /**
     * Set the y coordinate of the node
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Get if the node is a wall
     * @return whether the node is a wall
     */
    public boolean isWall() {
        return this.character.equals('#');
    }

    /**
     * Gets the parent node
     * @return parent node
     */
    public Node getParent() {
        return this.parent;
    }

    /**
     * Sets the specified node as the parent node
     */
    public void setParent(Node node) {
        this.parent = node;
    }


    /** Compares the two nodes and returns which one is cheaper:
     * @return 1 if the cost is bigger (this > compare)
     *         0 if the cost is equal
     *         -1 if the cost is smaller (this < compare)
     * */
    @Override
    public int compareTo(Node o) {
        if (this.getHeuristic() > o.getHeuristic()) {
            return 1;
        } else if (this.getHeuristic() == o.getHeuristic()) {
            return 0;
        }
        return -1;
    }

    /** Test if two nodes are in the same coordinate */
    public boolean isSameNode(Node compare) {
        return compare.getX() == this.getX() && compare.getY() == this.getY();
    }
}
