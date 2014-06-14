/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hike.Structures;

import Hike.Graph.Node;

/**
 *
 * Stack for printing path.
 *
 */
public class PathStack {

    private int top;
    private Node[] table;

    public PathStack(int size) {
        table = new Node[size];
        this.top = -1;
        
    }
    /**
     * Pushed node into stack. Gives some info if the path contains a loop.
     * @param node 
     */

    public void push(Node node) {
        top++;
        table[top] = node;
        
        if (this.top*1.05 > table.length) {
            System.out.println("Stack getting dangerously full! Node was: " + node.getY() + " " + node.getX());
        }
        
    }

    public Node pop() {
        Node ret = table[top];
        top--;
        return ret;
    }
    
    public int size() {
        return top;
    }
}
