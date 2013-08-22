/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohjelma.tietorakenteet;

import java.util.Map;

/**
 *
 * @author kkivikat
 */
public class iHashMapEntry<K,V> implements Map.Entry<K,V>{
    
    final int hash;
    final K avain;
    V arvo;
    iHashMapEntry<K,V> seuraava;
    
    public iHashMapEntry(int h, K k, V v, iHashMapEntry<K,V> n) {
        this.avain = k;
        this.hash = h;
        this.arvo = v;
        this.seuraava = n;
    }
    
    public void recordAccess(iHashMap<K,V> m) {
    }

    
    @Override
    public K getKey() {
        return avain;
    }

    @Override
    public V getValue() {
        return arvo;
    }



    @Override
    public Object setValue(Object arvo) {
        Object vanhaArvo = arvo;
        return vanhaArvo;
    }
}
