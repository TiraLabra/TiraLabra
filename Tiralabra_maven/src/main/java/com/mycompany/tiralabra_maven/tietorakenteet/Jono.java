
package com.mycompany.tiralabra_maven.tietorakenteet;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

/**
 * Jonotietorakenne joka toteuttaa operaatiot <tt>add</tt>, <tt>poll</tt>,
 * <tt>isEmpty</tt> ja <tt>clear</tt>.
 *
 * @author John Lång
 */
public class Jono<T> implements Queue<T> {
    
    private Solmu<T>    ensimmainen, viimeinen;
    private int         pituus;

    public boolean add(T e) {
        Solmu seuraava = new Solmu<T>(e);
        if (ensimmainen == null) {
            ensimmainen = seuraava;
            viimeinen   = seuraava;
            pituus      = 1;
            return true;
        }
        viimeinen.seuraaja = seuraava;
        viimeinen = seuraava;
        pituus++;
        return true;
    }

    public boolean offer(T e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public T remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public T poll() {
        if (ensimmainen == null) {
            return null;
        }
        Solmu<T> solmu = ensimmainen;
        ensimmainen = solmu.seuraaja;
        pituus--;
        return solmu.ARVO;
    }

    public T element() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public T peek() {
//        if (ensimmainen == null) {
//            return null;
//        }
//        return ensimmainen.ARVO;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isEmpty() {
        return pituus == 0;
    }

    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void clear() {
        while (!isEmpty()) {
            poll();
        }
        ensimmainen = null;
        viimeinen   = null;
        pituus      = 0;
    }

}
