package Tietorakenteet;

/**
 * Oma karsittu interface Map-rakenteesta
 *
 */
public interface OmaMap<K, V> {

    void clear();

    void containsKey(Object key);
    
    V get(Object key);
    
    boolean isEmpty();
    
    V put(K key, V value);
    
}
