package com.mycompany.tiralabra_maven.data_structures;

/**
 * Stack is for saving the whole game. 
 * @author Joel Nummelin
 */
public class Stack {

    StackNode node;

    public Stack() {
        node = null;
    }
    
    /**
     * Puts a new node on the stack. 
     * @param sn 
     */
    public void put(StackNode sn){
        if (node == null){
            node = sn;
            sn.setNext(null);
        } else {
            sn.setNext(node);
            node = sn;
        }
    }

    /**
     * Removes and returns the top node of the stack. 
     * @return stackNode
     */
    public StackNode pop() {
        StackNode sn = node;
        node = node.getNext();
        return sn;
    }
    
    /**
     * Returns the top node of the stack.
     * @return stackNode
     */
    public StackNode peek(){
        return node;
    }
    
    
    /**
     * Gives the number of nodes in stack.
     * @return number
     */
    public int size(){
        if (node == null){
            return 0;
        }
        int i = 1;
        StackNode sn = node;
        while(sn.getNext() != null){
            i++;
            sn = sn.getNext();
        }
        return i;
    }
}
