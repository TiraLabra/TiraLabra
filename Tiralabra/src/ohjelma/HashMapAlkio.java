/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohjelma;

/**
 *
 * @author kkivikat
 */
public class HashMapAlkio<K, V> {
    private final K avain;
    private V arvo;
    
    public HashMapAlkio(K avain, V arvo) {
        this.avain = avain;
        this.arvo = arvo;
    }
    
    /**
    * Palauttaa avaimen.
    **/
    public K getAvain() {
        return avain;
    }
    
    /**
    * Palauttaa arvon.
    **/
    public V getArvo() {
        return arvo;
    }
    
    /**
    * Asettaa uuden arvon.
    **/
    public void setArvo(V arvo) {
        this.arvo = arvo;
    }
    
}
