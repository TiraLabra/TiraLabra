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
public class iHashMapTaulu<K,V> implements Map.Entry<K,V>{
    
    final int hash;
    final K avain;
    V arvo;
    iHashMapTaulu<K,V> seuraava;
    
    public iHashMapTaulu(int h, K k, V v, iHashMapTaulu<K,V> n) {
        this.avain = k;
        this.hash = h;
        this.arvo = v;
        this.seuraava = n;
    }

     /**
     * Palauttaa avaimen.
     */
    @Override
    public K getKey() {
        return avain;
    }

     /**
     * Palauttaa arvon.
     */
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
