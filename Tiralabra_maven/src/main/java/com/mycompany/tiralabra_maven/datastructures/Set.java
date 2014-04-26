package com.mycompany.tiralabra_maven.datastructures;

import java.util.Iterator;

public class Set<E> implements Iterable<E> {
    private final List<E> list;

    public Set() {
        this.list = new List<>();
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

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
