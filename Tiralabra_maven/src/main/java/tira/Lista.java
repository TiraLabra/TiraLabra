/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;

import java.util.Iterator;

/**
 *
 * @author E
 */
public interface Lista<E> extends Iterable<E> {
    public void add(E e);
    public boolean isEmpty();
    public int     size();
    public E       remove( int index );
    public E       get( int index );
    public Iterator<E> iterator();
}
