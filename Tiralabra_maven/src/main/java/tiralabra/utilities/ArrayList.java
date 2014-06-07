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
public class ArrayList<T> implements Iterable<T> {

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
     * @param e
     */
    public void add(T e) {
        if (size == list.length) {
            growCapacity();
        }

        list[size++] = e;
    }

    /**
     * Removes an object from the list.
     *
     * @param e
     * @return whether a removal was done
     */
    public boolean remove(T e) {
        if (size == list.length) {
            return false;
        }

        if (e == null) {
            return false;
        }

        boolean removed = false;
        int i = 0;
        while (true) {
            if (list[i].equals(e)) {
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
    public int size() {
        return size;
    }

    /**
     * Whether the list is empty.
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks whether the list contains the given object.
     *
     * @param e
     * @return
     */
    public boolean contains(T e) {
        if (e == null) {
            return false;
        }

        for (T l : (T[]) list) {
            if (l.equals(e)) {
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
    public T[] toArray() {
        return (T[]) Arrays.copyOf(list, size);
    }

    /**
     *
     */
    public void clear() {
        list = new Object[initialCapacity];
    }

    private void growCapacity() {
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
