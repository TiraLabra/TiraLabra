/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pakkaaja;

/**
 *
 * @author joonaskylliainen
 */
public class Node {
    
    private char character;
    private int frequency;
    private Node parent;
    private Node left;
    private Node right;
    
    public Node(char c, int f) {
        this.character = c;
        this.frequency = f;
    }
    
    public char getChar() {
        return this.character;
    }
    public int getFrequency() {
        return this.frequency;
    }
    public Node getLeft() {
        return this.left;
    }
    public Node getRight() {
        return this.right;
    }
    public Node getParent() {
        return this.parent;
    }
    public void setFrequency(int f) {
        this.frequency = f;
    }
    public void setParent(Node p) {
        this.parent = p;
    }
    public void setLeft(Node l) {
        this.left = l;
    }
    public void setRight(Node r) {
        this.right = r;
    }
    
    public void increaseFrequencyByOne() {
        this.frequency++;
    }
    
    public boolean equals(Node nod) {
        if (nod.getChar() == this.getChar()) {
            return true;
        }
        return false;
    }
    public int compareTo(Node nod) {
        if (nod.frequency < this.frequency) {
            return 1;
        }
        else if (nod.frequency == this.frequency) {
            return 0;
        }
        else {
            return -1;
        }
    }
}
