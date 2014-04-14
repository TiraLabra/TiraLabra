/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.datastructures;

import java.util.Collection;
import java.util.Iterator;

/**
 * A fixed size hash set for holding any kind of objects. The hash value for an
 * object is determined by the hash function supplied for this set. The actual
 * size of the table is determined by the m-value calculated by the hash
 * function.
 *
 * @author skaipio
 */
public class MyHashSet<T> {

    private Object[] table;
    public final int tableSize;
    private final HashFuncs<T> hashFunction;
    private int numberOfItems = 0;

    /**
     * Creates a fixed size hash set.
     *
     * @param numberOfKeys Minimum number of keys this set should be able to
     * hold.
     * @param hashFunction Function used to determine hash value for an object.
     * @param size Fixed size for the set.
     */
    public MyHashSet(int numberOfKeys, HashFuncs<T> hashFunction) {
        this.hashFunction = hashFunction;
        this.tableSize = this.hashFunction.calculateM(numberOfKeys, 0.33);
        this.table = new Object[this.tableSize];
    }

    public int size() {
        return this.numberOfItems;
    }

    public boolean isEmpty() {
        return numberOfItems == 0;
    }

    public boolean contains(T obj) {
        if (obj == null) {
            return false;
        }

        int numberOfTries = 0;
        while (numberOfTries < this.tableSize) {
            int hash = this.hashFunction.getHash(obj, numberOfTries, this.tableSize);
            if (this.table[hash] == null) {
                return false;
            } else if (this.table[hash].equals(obj)) {
                return true;
            } else {
                numberOfTries++;
            }
        }
        return false;
    }

    /**
     * This iterator does not create a copy of the hash table and does not
     * support removing.
     *
     * @return Iterator for traversing this set.
     */
    public Iterator<T> iterator() {
        return new Iter<>();
    }

    /**
     * Adds an object to this set. If two objects have the same hash value, uses
     * equals() method to determine if the objects are same. If they are, then
     * the given object will not be added to the set.
     *
     * @param obj Object to add.
     * @return True if no equal object is in the set and the set is not full,
     * false otherwise.
     */
    public boolean add(T obj) {
        if (obj == null || this.numberOfItems == this.tableSize) {
            return false;
        }

        int numberOfTries = 0;
        while (numberOfTries < this.tableSize) {
            int hash = this.hashFunction.getHash(obj, numberOfTries, this.tableSize);
            if (this.table[hash] == null || this.table[hash] == Del.DEL) {
                this.table[hash] = obj;
                this.numberOfItems++;
                return true;
            } else if (this.table[hash].equals(obj)) {
                return false;
            }
            numberOfTries++;
        }
        return false;
    }

    /**
     * Removes the given object from the set.
     * @param obj Object to remove.
     * @return True if object was found in the set, false otherwise.
     */
    public boolean remove(T obj) {
        if (obj == null || this.numberOfItems == 0) {
            return false;
        }

        int numberOfTries = 0;
        while (numberOfTries < this.tableSize) {
            int hash = this.hashFunction.getHash(obj, numberOfTries, this.tableSize);
            if (this.table[hash] == null) {
                return false;
            } else if (this.table[hash].equals(obj)) {
                this.table[hash] = Del.DEL;
                this.numberOfItems--;
                return true;
            } else {
                numberOfTries++;
            }
        }
        return false;
    }

    /**
     * Replaces the hash table with a new empty table with the same size.
     */
    public void clear() {
        this.table = new String[tableSize];
    }

    private class Iter<T> implements Iterator<T> {

        private int i = 0;

        @Override
        public boolean hasNext() {
            int j = i;
            while (j < tableSize) {
                if (table[j] == null) {
                    j++;
                } else {
                    return true;
                }
            }
            return false;
        }

        @Override
        public T next() {
            int j = i;
            while (j < tableSize) {
                if (table[i] == null) {
                    i++;
                } else {
                    i = j + 1;
                    return (T) table[j];
                }
            }
            return null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    /**
     * Special Del value for marking hash table slots where an object has been removed from.
     */
    private enum Del {
        DEL
    }
}
