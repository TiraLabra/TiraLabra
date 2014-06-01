package Hike.Structures;

import Hike.Graph.Node;
import java.util.Iterator;

/**
 * A singly linked list.
 *
 */
public class LinkyList implements Iterable<Node> {

    private LinkElement top;
    private LinkElement old;
    private LinkElement current;

    public LinkyList() {

        this.top = null;


    }

    public void add(Node node) {
        if (this.top == null) {
            this.top = new LinkElement(node);
        } else {
            this.old = this.top;
            this.top = new LinkElement(node);
            this.top.setNext(this.old);

        }
        current = this.top;

    }

    public int size() {
        int count = 0;
        LinkElement element = this.top;
        while (element != null) {
            element = element.next;
            count++;

        }
        return count;

    }
    
    public LinkElement getTopElement() {
    return this.top;
}

    /**
     * Method needed for for-looping.
     *
     * @return Node for for-loops
     */
    @Override
    public Iterator<Node> iterator() {

        Iterator<Node> iter = new Iterator<Node>() {
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Node next() {
                old = current;
                current = current.next;
                return old.getNode();
            }

            @Override
            public void remove() {
            }
        };

        return iter;
    }
}
