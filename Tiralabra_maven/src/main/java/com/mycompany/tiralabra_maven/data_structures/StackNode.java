package com.mycompany.tiralabra_maven.data_structures;

/**
 * StackNodes are used by Stack. 
 * @author Joel Nummelin
 */
public class StackNode {
    private StackNode next;
    private int move;
    private int result;
    
    
    public StackNode(int move, int result){
        this.move = move;
        this.result = result;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.move;
        hash = 41 * hash + this.result;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StackNode other = (StackNode) obj;
        if (this.move != other.move) {
            return false;
        }
        if (this.result != other.result) {
            return false;
        }
        return true;
    }

    
    
    
    
}
