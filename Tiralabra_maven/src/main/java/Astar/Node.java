package Astar;

/**
 *
 * @author Arto
 */
public class Node {
    
    private char value;
    private int x;
    private int y;
    private Node prev;
    private int toStart;
    private int toGoal;
    
    Node(char v, int y, int x) {
        value = v;
        this.y = y;            
        this.x = x;
        prev = null;
    }
    
    public char getValue() {
        return value;
    }
    public int getY() {
        return this.y;
    }
    public int getX() {
          return this.x;
    }
    public void setValue(char c) {
        value = c;
    }
    public void setPrev(Node n) {
        prev = n;
    }
    
    public Node getPrev() {
        return prev;
    }
    public void setToStart(int i) {
        toStart = i;
    }
    public int getToStart(){
        return toStart;
    }
    public void setToGoal(int y, int x) {
        if(y < this.y)                  //if the distance is to a "negative direction"
            toGoal = (y-this.y)*(-1);   //turn into positive
        else
            toGoal = y-this.y;
        if(x < this.x)                  //see above
            toGoal = toGoal + (x-this.x)*(-1);
        else
            toGoal =toGoal + (x-this.x);
    }
    public int getToGoal() {
        return toGoal;
    }
    public int getPrio() {
        return toStart+toGoal;
    }
}
