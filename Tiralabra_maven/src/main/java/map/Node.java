package map;

/**Node class for holding position, g() and f() data.
 * 
 */

public class Node {
    private int x;
    private int y;
    private int g;
    private int f;
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

    /**
     * @param f the f to set
     */
    public void setF(int f) {
        this.f = f;
    }
}
