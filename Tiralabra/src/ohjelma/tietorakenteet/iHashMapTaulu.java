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
public class iHashMapTaulu<K,V> {
    
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
    public K getKey() {
        return avain;
    }

     /**
     * Palauttaa arvon.
     */
    public V getValue() {
        return arvo;
    }
}
