package com.mycompany.tiralabra_maven.datastructures;

import com.mycompany.tiralabra_maven.datastructures.List.Node;
import java.util.Iterator;

public class ListIterator<E> implements Iterator<E> {

    private  Node<E> head;
    private  Node<E> last;
    private final List<E> list;

    public ListIterator(List<E> list) {
        head = list.head;
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return head != null;
    }

    @Override
    public E next() {
        last = head;
        head = head.next;
        return last.elem;
    }

    @Override
    public void remove() {
        list.delete(last);
    }
}
