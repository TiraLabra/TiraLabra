/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohjelma.tietorakenteet;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author kkivikat
 */
public class iHashSet<E> extends AbstractSet<E> implements Set<E> {

    private iHashMap<E, Object> taulu;
    private static final Object nykyinen = new Object();

    public iHashSet() {
        taulu = new iHashMap();
    }

    @Override
    public boolean add(E e) {
        return taulu.put(e, nykyinen) == null;
    }

    @Override
    public Iterator<E> iterator() {
        return taulu.keySet().iterator();
    }

    @Override
    public int size() {
        return taulu.getArvojenMaara();
    }

    public Set palauta() {
        Set set = taulu.entrySet();
        return set;
    }
}
