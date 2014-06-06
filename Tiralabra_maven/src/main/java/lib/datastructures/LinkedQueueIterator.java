/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lib.datastructures;

import java.util.Iterator;

/**
 * Iteraattori LinkedQueue -luokalle.
 * @author Iiro
 */
public class LinkedQueueIterator<E> implements Iterator<E> {
    private LinkedNode<E> node;
    
    public LinkedQueueIterator(LinkedNode first){
        node = first;
    }
    
    public boolean hasNext() {
        return node != null;
    }

    public E next() {
        E key = node.getKey();        
        node = node.getNext();          
        return key;
    }
    
}
