package com.mycompany.tiralabra_maven.datastructures;

import java.util.Iterator;

/**
 * Doubly linked list
 *
 * @author Yessergire Mohamed
 * @param <E> The type of elements that this list contains.
 */
public class List<E> implements Iterable<E> {

    protected Node head;
    private int size;

    public void insertLast(E elem) {
        Node x = new Node(elem);
        x.next = head;
        if (head != null) {
            Node next = x.next;
            next.prev = x;
        }
        head = x;
        size++;
    }

    protected Node search(E elem) {
        for (Node p = head; p != null; p = p.next) {
            if (elem.equals(p.elem)) {
                return p;
            }
        }
        return null;
    }

    public boolean contains(E elem) {
        return search(elem) != null;
    }

    protected void delete(Node x) {
        if (x.prev != null) {
            x.prev.next = x.next;
        } else {
            head = x.next;
        }
        if (x.next != null) {
            x.next.prev = x.prev;
        }
        size--;
    }

    public void delete(E elem) {
        if (contains(elem)) {
            delete(search(elem));
        }
    }

    protected static class Node<E> {

        Node next;
        Node prev;
        E elem;

        /**
         *
         * @param elem
         */
        public Node(E elem) {
            this.elem = elem;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator<E>(this);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
