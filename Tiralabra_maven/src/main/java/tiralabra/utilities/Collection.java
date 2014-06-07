/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tiralabra.utilities;

/**
 *
 * @author atte
 */
public interface Collection<T> extends Iterable<T>{
    public void add(T t);
    public boolean remove(T t);
    public boolean contains(T t);
    public int size();
    public boolean isEmpty();
    public void growCapacity();
    public T[] toArray();
    public void clear();
}
