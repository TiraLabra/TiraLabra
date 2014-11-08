/**
 * Node is a coordinate within the Map. Node can be a start node, end node, wall or path.
 *
 */

public class Node {
    /** Node's x coordinate */
    private int x;
    /** Node's y coordinate */
    private int y;
    /** A property to check if Node is path, end or start point */
    private boolean walkable;
    /** Contains info if the Node has a parent Node (ie previous step) */
    private Node parent;


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
     * Get the walkability of the node:
     * if node is start, end or path walkable is true
     * @return whether the node is walkable
     */
    public boolean isWalkable() {
        return walkable;
    }


    /**
     * Set if the node is walkable
     */
    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
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


}
