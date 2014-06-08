 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.utilities;

import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import tiralabra.game.ai.AI;

/**
 * Implementation of list data-structure stored in an array.
 *
 * @author atte
 * @param <T>
 */
public class ArrayList<T> extends AbstractCollection<T> implements List<T> {

    public ArrayList() {
        super();
    }
    
    public ArrayList(int initialCapacity) {
        super(initialCapacity);
    }
    
    /**
     * Adds an object to the list.
     *
     * @param t
     */
    @Override
    public void add(T t) {
        if (size == array.length) {
            growCapacity();
        }

        array[size++] = t;
    }
    

    @Override
    public ListIterator<T> listIterator() {
        ListIterator<T> it = new ListIterator<T>() {

            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < size && array[i] != null;
            }

            @Override
            public T next() {
                T t = (T) array[i];
                i++;
                return t;
            }
            
            public <T extends Comparable<? super T>> void  set(T t) {
                array[i] = t;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean hasPrevious() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public T previous() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int nextIndex() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int previousIndex() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void set(T t) {
                array[i] = t;
            }

            @Override
            public void add(T t) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        return it;
    }
}
