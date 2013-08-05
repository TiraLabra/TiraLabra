package com.mycompany.tiralabra_maven.data_structures;

/**
 *
 * @author Joel Nummelin
 */
public class Stack {

    StackNode node;

    public Stack() {
        node = null;
    }
    
    public void put(StackNode sn){
        if (node == null){
            node = sn;
        } else {
            sn.setNext(node);
            node = sn;
        }
    }

    public StackNode pop() {
        StackNode sn = node;
        node = node.getNext();
        return sn;
    }
    
    public StackNode peek(){
        return node;
    }
    
    
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
