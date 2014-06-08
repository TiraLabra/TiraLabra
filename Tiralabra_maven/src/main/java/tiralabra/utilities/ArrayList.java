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
 * @param <E>
 */
public class ArrayList<E> extends AbstractCollection<E> {

    public ArrayList() {
        super();
    }
    
    public ArrayList(int initialCapacity) {
        super(initialCapacity);
    }
    
    /**
     * Adds an object to the list.
     *
     * @param e
     */
    @Override
    public void add(E e) {
        if (size == array.length) {
            growCapacity();
        }

        array[size++] = e;
    }

}
