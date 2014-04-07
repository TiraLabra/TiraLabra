/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.datastructures;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Iterator;

/**
 * Hash table data structure for strings.
 *
 * @author skaipio
 */
public class MyHashSet<T> {

    private Object[] table;
    private int tableSize;
    private int numberOfItems = 0;
    private HashFuncs<T> hashFunction;

    /**
     *
     * @param estimatedNumberOfKeys
     * @param hashFunction
     * @param size Fixed size for the set.
     */
    public MyHashSet(int estimatedNumberOfKeys, HashFuncs<T> hashFunction) {
        this.hashFunction = hashFunction;
        this.tableSize = this.hashFunction.calculateM(estimatedNumberOfKeys, 0.33);
        this.table = new Object[this.tableSize];        
    }

    public int size() {
        return this.numberOfItems;
    }

    public boolean isEmpty() {
        return numberOfItems == 0;
    }

    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {

            private int i = 0;

            @Override
            public boolean hasNext() {
                int j = i;
                while (j < tableSize){
                    if (table[j] == null){
                        j++;
                    }else{
                        return true;
                    }
                }
                return false;
            }

            @Override
            public T next() {
                int j = i;
                while (j < tableSize){
                    if (table[i] == null){
                        i++;
                    }else{
                        i = j+1;
                        return (T)table[j];
                    }
                }
                return null;
            }

            @Override
            public void remove() {
                // TODO Auto-generated method stub
            }
        };
        return it;
    }

    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object[] toArray(Object[] a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean add(T obj) {
        if (obj == null || obj.equals("")) {
            return false;
        }

        int numberOfTries = 0;
        while (numberOfTries < this.tableSize) {
            int hash = this.hashFunction.getHash(obj, numberOfTries, this.tableSize);
            if (this.table[hash] == null) {
                this.table[hash] = obj;
                return true;
            } else if (this.table[hash] == obj) {
                return false;
            }
            numberOfTries++;
        }
        return false;
    }

    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean containsAll(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean addAll(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void clear() {
        this.table = new String[tableSize];
    }

    
}
