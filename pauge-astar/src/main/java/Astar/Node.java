package Astar;
import java.math.*;
/**
 *@
 * @author Arto
 */
public class Node {
    
    private char value;
    private int x;
    private int y;
    private Node prev;
    private int toStart;
    private double toGoal;
    private int cost;
    /**
     * Constructor
     * 
     * @param v what kind of a node is this
     * @param y where is this node on y-axis
     * @param x where is this node on x-axis
     */
    Node(char v, int y, int x) {
        value = v;
        this.y = y;            
        this.x = x;
        prev = null;
        cost = 0;
    }
    
    /**
     * 
     * @return what kind of node this is
     */
    public char getValue() {
        return value;
    }
    /**
     * 
     * @return y-value
     */
    public int getY() {
        return this.y;
    }
    /**
     * 
     * @return x-value
     */
    public int getX() {
          return this.x;
    }
    
    public void setCost(int i) {
        cost = i;
    }
    /**
     * 
     * @param c change what the node looks like to visualize map
     */
    public void setValue(char c) {
        value = c;
    }
    /**
     * 
     * @param n which node was this accessed from
     */
    public void setPrev(Node n) {
        prev = n;
    }
    /**
     * 
     * @return which node was this accessed from 
     */
    public Node getPrev() {
        return prev;
    }
    /**
     * 
     * @param i the cost from start to this node
     */
    public void setToStart(int i) {
        toStart = i;
    }
    /**
     * 
     * @return cost from start to this node
     */
    public int getToStart(){
        return toStart;
    }
    /**
     * Count the heuristic (direct distance to goal) used in choosing the next node to analyze
     * 
     * @param y the y-value of the goal
     * @param x the x-value of the goal
     */
    public void setToGoal(int y, int x) {
        if(y < this.y)   {               //if the distance is to a "negative direction"
            toGoal = (y-this.y)*(-1);   //turn into positive
            toGoal = toGoal*toGoal;}
        else {
            toGoal = y-this.y;
            toGoal = toGoal*toGoal;}
        if(x < this.x)                  //see above
            toGoal = toGoal + (x-this.x)*(x-this.x);
        else
            toGoal =toGoal + (x-this.x)*(x-this.x);
        if(this.value=='T')
            cost = cost+2;
    }
    
    /**
     * 
     * @return direct distance to goal
     */
    public double getToGoal() {
        return Math.sqrt(toGoal)+cost;
    }
    
    /**
     * 
     * @return direct distance to goal (plus possible extra cost when going trough this node) + traveled distance from start
     */
    public double getPrio() {
        return toStart+Math.sqrt(toGoal)+cost;
    }
    
    /**
     * 
     * @return Nodes location as a textual representation
     */
    public String toString() {
        return "("+this.x+","+this.y+")";
    }
}
