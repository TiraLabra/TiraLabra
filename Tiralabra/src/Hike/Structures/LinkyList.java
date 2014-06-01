package Hike.Structures;

import Hike.Graph.Node;
import java.util.Iterator;

/**
 * Linked list.
 *
 */
public class LinkyList implements Iterable<Node> {

    private LinkElement top;
    private LinkElement next;
    private LinkElement old;
    private LinkElement current;

    public LinkyList() {

        this.top = null;
        this.next = null;


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

    @Override
    public Iterator<Node> iterator() {

        Iterator<Node> it = new Iterator<Node>() {
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

        return it;
    }
}
