
package lib.datastructures;

import java.util.Iterator;

/**
 * Jono toteutettuna linkitettynä listana.
 * @author Iiro
 */
public class LinkedQueue<E> implements Iterable<E>{
    private LinkedNode<E> first;
    private LinkedNode<E> last;
    private int size;
    
    public LinkedQueue(){
        first = null;
        last = null;
        size = 0;
    }
    /**
     * Lisää annetun avaimen jonon viimeiseksi.
     * @param key 
     */
    public void enqueue(E key){
        LinkedNode<E> node = new LinkedNode<E>(key);
        if(last != null){
            last.setNext(node);
            last = node;
        } else {
            first = node;
            last = first;
        }
        size++;
    }
    /**
     * Palauttaa ja poistaa jonon ensimmäisen alkion.
     * @return 
     */
    public E dequeue(){
        if(first == null){
            return null;
        }
        E key = first.getKey();
        first = first.getNext();
        if(first == null){
            last = null;
        }
        size--;
        return key;
    }
    /**
     * Palauttaa jonon ensimmäisen alkion poistamatta sitä.
     * @return 
     */
    public E getFirst(){
        return first.getKey();
    }
    
    public int size(){
        return size;
    }    

    public boolean isEmpty(){
        return size == 0;
    }
    
    public Iterator<E> iterator() {
        return new LinkedQueueIterator<E>(first);
    }
    
}
