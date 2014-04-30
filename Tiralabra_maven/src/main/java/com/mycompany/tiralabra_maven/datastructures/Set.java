package com.mycompany.tiralabra_maven.datastructures;

import java.util.Iterator;

/**
 * A simple set that is implemented as a list.
 * @author Yessergire Mohamed
 * @param <E> the type of this set.
 */
public class Set<E> implements Iterable<E> {
    private final List<E> list;

    public Set() {
        this.list = new List<E>();
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    /**
     * Adds element iff it doesn't contain it already.
     * @param elem
     */
    public void add(E elem) {
        if (!list.contains(elem))
            list.insertLast(elem);
    }

    public boolean contains(E elem) {
        return list.contains(elem);
    }

    public void remove(E elem) {
        list.delete(elem);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }
}
