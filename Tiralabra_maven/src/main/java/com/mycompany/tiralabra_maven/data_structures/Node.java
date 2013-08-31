package com.mycompany.tiralabra_maven.data_structures;

/**
 * StackNodes are used by Stack. 
 * @author Joel Nummelin
 */
public class Node {
    private Node next;
    private int move;
    private int result;
    
    
    public Node(int move, int result){
        this.move = move;
        this.result = result;
        this.next = null;
    }

    
    public Node getNext() {
        return next;
    }

    public int getMove() {
        return move;
    }

    public int getResult() {
        return result;
    }

    public void setNext(Node next) {
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
        final Node other = (Node) obj;
        if (this.move != other.move) {
            return false;
        }
        if (this.result != other.result) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String s = "";
        if (move == 0){
            s += "Rock, ";
        } else if (move == 1){
            s += "Paper, ";
        } else if (move == 2){
            s += "Scissors, ";
        }
        if (result == -1){
            s += "bot lost";
        } else if (result == 0){
            s += "draw";
        } else if (result == 1){
            s += "bot won";
        }
        return s;
    }

    
    
    
    
}
