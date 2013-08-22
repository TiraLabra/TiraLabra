/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohjelma.tietorakenteet;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author kkivikat
 */
public class iHashMap<K,V> extends AbstractMap<K,V> implements Map<K,V> {
    
    private int maxKapasiteetti = 16;
    private int arvojenmaara = 0;
    private iHashMapEntry<K, V>[] table;

    public iHashMap() {
        table = new iHashMapEntry[maxKapasiteetti];
    }

     /**
     * Tarkastaa onko HashMap tyhjä.
     */
    public boolean onkoTyhja() {
        return arvojenmaara == 0;
    }
    
     /**
     * Palauttaa koko HashMapin maksimikapasiteetin.
     */
    public int getMaxKoko() {
        return maxKapasiteetti;
    }
    
    /**
     * Palauttaa HashMapissa olevien arvojen määrän.
     */
    public int getArvojenMaara() {
        return arvojenmaara;
    }

    
    public int haeIndeksi(int h, int pituus) {
        return h & (pituus - 1);
    }

    public int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

     /**
     * Palauttaa avaimelle kuuluvan arvon.
     */
    @Override
    public V get(Object avain) {
        if (avain == null) {
            return getNullArvo();
        }
        int hash = hash(avain.hashCode());
        for (iHashMapEntry<K, V> e = table[haeIndeksi(hash, table.length)]; e != null; e = e.seuraava) {
            Object k;
            if (e.hash == hash && ((k = e.avain) == avain || avain.equals(k))) {
                return e.arvo;
            }
        }
        return null;
    }

     /**
     * Lisää avain-arvo parin.
     */
    @Override
    public V put(K avain, V arvo) {
        if (avain == null) {
            return putNull(arvo);
        }

        int hash = hash(avain.hashCode());
        int koriIndeksi = haeIndeksi(hash, table.length);


        for (iHashMapEntry<K, V> e = table[koriIndeksi]; e != null; e = e.seuraava) {
            Object k;
            if (e.hash == hash && ((k = e.avain) == avain || avain.equals(k))) {
                V oldValue = e.arvo;
                e.arvo = arvo;
                e.recordAccess(this);
                return oldValue;
            }
        }
        uusiEntry(hash, avain, arvo, koriIndeksi);
        ++arvojenmaara;
        return null;
    }

     /**
     * Lopullinen avain-arvo parin lisäys mikäli arvoa ei ollut vielä olemassa eikä se ole null.
     */
    public void uusiEntry(int hash, K avain, V arvo, int bucketIndeksi) {
        iHashMapEntry<K, V> e = table[bucketIndeksi];
        table[bucketIndeksi] = new iHashMapEntry<K, V>(hash, avain, arvo, e);
    }

    private V putNull(V arvo) {
        for (iHashMapEntry<K, V> e = table[0]; e != null; e = e.seuraava) {
            if (e.seuraava == null) {
                V oldValue = e.arvo;
                e.arvo = arvo;
                e.recordAccess(this);
                return oldValue;
            }
        }
        uusiEntry(0, null, arvo, 0);
        return null;
    }

     /**
     * Hakee nullille arvon.
     */
    private V getNullArvo() {
        for (iHashMapEntry<K, V> e = table[0]; e != null; e = e.seuraava) {
            if (e.avain == null) {
                return e.arvo;
            }
        }
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
