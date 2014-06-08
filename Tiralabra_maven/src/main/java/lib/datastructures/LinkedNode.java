
package lib.datastructures;

import java.util.Iterator;

/**
 *Yhteen suuntaan linkitetyn listan solmu.
 * @author Iiro
 */
public class LinkedNode<E>{
    private E key;
    private LinkedNode next;
    
    public LinkedNode(E key){
        this.key = key;
        next = null;
    }
    /**
     * Asettaa seuraajasolmun.
     * @param node 
     */
    public void setNext(LinkedNode node){
        next = node;
    }
    /**
     * Palauttaa solmun avaimen.
     * @return 
     */
    public E getKey() {
        return key;
    }
    /**
     * Palauttaa seuraajasolmun.
     * @return 
     */
    public LinkedNode getNext() {
        return next;
    }
    
    
}
