package map;

/**Node class for holding position, g() and f() data.
 * 
 */

public class Node {
    /** 
     * value of the node on the x axis of the map.
     */
    private int x;
    /** 
     * value of the node on the y axis of the map.
     */
    private int y;
    /**
     * how long it takes to reach the node in the map.
     */
    private int g;
    /**
     * estimate for reaching the goal in the map.
     */
    private int f;
    /**
     * the node in the same stack with the next biggest f value.
     */
    private Node next;
    /**
     * which node the route came from to this node.
     */
    private Node cameFrom;
    
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @return the cameFrom
     */
    public Node getCameFrom() {
        return cameFrom;
    }

    /**
     * @param cameFrom the cameFrom to set
     */
    public void setCameFrom(Node cameFrom) {
        this.cameFrom = cameFrom;
    }

    /**
     * @return the g
     */
    public int getG() {
        return g;
    }

    /**
     * @param g the g to set
     */
    public void setG(int g) {
        this.g = g;
    }

    /**
     * @return the f
     */
    public int getF() {
        return f;
    }
    
    public void setNext(Node next) {
        this.next = next;
    }
    
    public Node getNext() {
        return next;
    }

    /**
     * @param f the f to set
     */
    public void setF(int f) {
        this.f = f;
    }
}
