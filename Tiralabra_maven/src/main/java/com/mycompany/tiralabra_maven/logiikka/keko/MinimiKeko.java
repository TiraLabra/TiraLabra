package com.mycompany.Tiralabra_maven.logiikka.keko;

/**
 *
 * @author Hannu
 */
public interface MinimiKeko<E> {
    
    public void heapInsert(E kekoAlkio);
    public E heapDelMin();
    public void heapDelete(E kekoAlkio);
    public void heapDecreaseKey(E kekoAlkio);
    public boolean heapIsEmpty();
    
    
}
