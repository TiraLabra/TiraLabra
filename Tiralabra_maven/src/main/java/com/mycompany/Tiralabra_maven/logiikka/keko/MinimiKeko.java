package com.mycompany.Tiralabra_maven.logiikka.keko;

/**
 * Rajapinta minimikeolle.
 * Tätä rajapintaa käyttämällä koodia ei tarvitse muuttaa kun siirrytään
 * Javan PriorityQueue:n käyttämisestä minimikeon oman toteutuksen käyttämiseen.
 */
public interface MinimiKeko<E> {
    
    public void heapInsert(E kekoAlkio);
    public E heapDelMin();
    public void heapDelete(E kekoAlkio);
    public void heapDecreaseKey(E kekoAlkio);
    public boolean heapIsEmpty();
    
    
}
