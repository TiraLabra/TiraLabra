

package Tietorakenteet;

import java.util.HashMap;


public class OmaHashMap<K, V> implements OmaMap<K, V> {
    // alkulukuja
   /* private static int [] ALKULUKUJA = { 3, 7, 13, 23, 41, 83, 163, 317, 751, 1511, 3041, 6089, 122143, 24691, 48023,
                                         81973, 104729 };*/
    
    private HashMap<K, V> hashMap;
    
    public OmaHashMap() {
        hashMap = new HashMap<K, V>();
    }
    
    private void kasvata() {
        
    }

    
    private void rehash(Object [] uusiTaulukko) {
        
    }

    @Override
    public void clear() {
        hashMap.clear();
    }

    @Override
    public boolean containsKey(Object key) {
        return hashMap.containsKey(key);
    }

    @Override
    public V get(Object key) {
        return hashMap.get(key);
    }

    @Override
    public boolean isEmpty() {
        return hashMap.isEmpty();
    }

    @Override
    public V put(K key, V value) {
        return hashMap.put(key, value);
    }
    
    @Override
    public OmaList<K> avaimet() {
        OmaList<K> avainLista = new OmaArrayList<K>();
        for (K avain : hashMap.keySet()) {
            avainLista.add(avain);
        }
        return avainLista;
       
    }
  
}
