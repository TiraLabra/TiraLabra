package com.mycompany.tiralabra_maven.data_structures;

/**
 *
 * @author Joel Nummelin
 */
public class StackNode {
    private StackNode next;
    private int move;
    private int result;
    
    public StackNode(int move, int result){
        this.move = move;
        this.result = -2;
        this.next = null;
    }

    public StackNode getNext() {
        return next;
    }

    public int getMove() {
        return move;
    }

    public int getResult() {
        return result;
    }

    public void setNext(StackNode next) {
        this.next = next;
    }

    
    
    
    
}
