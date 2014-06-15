package Hike.structures;

import Hike.graph.Edge;
import java.util.Iterator;

/**
 * A singly linked list. 
 * New edges are linked with LinkElement and placed at the top of the list.
 * Implements Iterable to enable for-loops.
 */
public class LinkyList implements Iterable<Edge> {

    private LinkElement top;
    private LinkElement old;
    private LinkElement current;
    private int size;

    public LinkyList() {

        this.top = null;
        size = 0;


    }
/**
 * If list is empty, sets the first node as top.
 * @param node 
 */
    public void add(Edge edge) {
        size++;
        if (this.top == null) {
            this.top = new LinkElement(edge);
        } else {
            this.old = this.top;
            this.top = new LinkElement(edge);
            this.top.setNext(this.old);

        }
        current = this.top;

    }
    
    /**
     * Counts elements until it finds one without a child. Used for testing.
     * @return 
     */

    public int size() {
        return size;
    }
    
    public LinkElement getTopElement() {
    return this.top;
}

    /**
     * Method needed for for-looping. Iterable.
     *
     * @return Edge for for-loops
     */
    @Override
    public Iterator<Edge> iterator() {

        Iterator<Edge> iter = new Iterator<Edge>() {
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Edge next() {
                old = current;
                current = current.next;
                return old.getEdge();
            }

            @Override
            public void remove() {
            }
        };

        return iter;
    }
}
