/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.utilities;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Abstract class which implements most of the methods specified in the
 * Collection interface. Most data structures should extend this.
 *
 * @author atte
 */
public abstract class AbstractCollection<E> implements Collection<E> {

    /**
     * Holds all the objects stored in the collection.
     */
    protected Object[] array;
    /**
     * Number of objects currently stored in the collection.
     */
    protected int size;
    /**
     * The initial capacity for the array. When clear() is used, the new array's
     * length will be this.
     */
    protected final int initialCapacity;
    /**
     * The default initial capacity for the array, if none was specified.
     */
    protected static final int DEFAULTCAPACITY = 12;

    public AbstractCollection(int initialCapacity) {
        this.array = new Object[initialCapacity];
        this.size = 0;
        this.initialCapacity = initialCapacity;
    }

    public AbstractCollection() {
        this(DEFAULTCAPACITY);
    }

    @Override
    public abstract boolean add(E e);

    @Override
    public boolean remove(E e) {
        if (size == 0) {
            return false;
        }

        if (e == null) {
            return false;
        }

        boolean removed = false;

        int i = 0;
        while (true) {
            if (array[i].equals(e)) {
                removed = true;
                break;
            }
            i++;
        }

        size--;

        while (i < size) {
            array[i] = array[++i];
        }
        array[size] = null;
        
        return removed;
    }

    @Override
    public boolean contains(E e) {
        if (e == null) {
            return false;
        }

        for (Object l : array) {
            if (e.equals(l)) {
                return true;
            }
        }

        return false;
    }
    
    @Override
    public E get(int i) {
        return (E) array[i];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E[] toArray() {
        return (E[]) Arrays.copyOf(array, size);
    }

    /**
     * Replaces the list with an empty array, effectively wiping out data stored
     * in the array.
     */
    @Override
    public void clear() {
        array = new Object[initialCapacity];
        size = 0;
    }

    /**
     * Grows the capacity of the array.
     */
    @Override
    public void growCapacity() {
        int newSize = size() * 2;
        array = Arrays.copyOf(array, newSize);
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {

            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < size && array[i] != null;
            }

            @Override
            public E next() {
                return (E) array[i++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }

}
