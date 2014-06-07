 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.utilities;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Implementation of list data-structure stored in an array.
 *
 * @author atte
 * @param <T>
 */
public class ArrayList<T> implements Collection<T> {

    private Object[] list;
    private int size;
    private final int initialCapacity;
    private static final int DEFAULTCAPACITY = 12;

    public ArrayList(int initialCapacity) {
        this.list = new Object[initialCapacity];
        this.size = 0;
        this.initialCapacity = initialCapacity;
    }

    public ArrayList() {
        this(DEFAULTCAPACITY);
    }

    /**
     * Adds an object to the list.
     *
     * @param t
     */
    @Override
    public void add(T t) {
        if (size == list.length) {
            growCapacity();
        }

        list[size++] = t;
    }

    /**
     * Removes an object from the list.
     *
     * @param t
     * @return whether a removal was done
     */
    @Override
    public boolean remove(T t){
        if (size == list.length) {
            return false;
        }

        if (t == null) {
            return false;
        }

        boolean removed = false;
        int i = 0;
        while (true) {
            if (list[i].equals(t)) {
                removed = true;
                break;
            }
            i++;
        }

        size--;

        while (i < size) {
            list[i] = list[++i];
        }
        list[size++] = null;

        return removed;
    }

    /**
     * Number of objects stored in the list.
     *
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Whether the list is empty.
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks whether the list contains the given object.
     *
     * @param t
     * @return
     */
    @Override
    public boolean contains(T t) {
        if (t == null) {
            return false;
        }

        for (T l : (T[]) list) {
            if (l.equals(t)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns all of the objects stored in the list as an array.
     *
     * @return
     */
    @Override
    public T[] toArray() {
        return (T[]) Arrays.copyOf(list, size);
    }

    /**
     * Replaces the list with an empty array, effectively wiping out data stored
     * in the array.
     */
    @Override
    public void clear() {
        list = new Object[initialCapacity];
    }

    /**
     * Grows the capacity of the array.
     */
    @Override
    public void growCapacity() {
        int newSize = size() * 2;
        list = Arrays.copyOf(list, newSize);
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {

            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < size && list[i] != null;
            }

            @Override
            public T next() {
                return (T) list[i++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }

}
