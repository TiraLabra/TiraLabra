

package Tietorakenteet;

/**
 * Varastoluokka - varastoi kaksi mielivaltaista arvoa. Vrt. c++ std::pair
 *
 */
// HUOM: oliomuuttujat tarkoituksella julkisia koska get\set tuo vaan turhaa koodia mukaan
// tämä on varastoluokka, ei implementoi metodeja
public class Pari<K, V> {
    /**
     * Ensimmäinen arvo
     */
    public K ensimmainen;
    /**
     * Toinen arvo
     */
    public V toinen;
}
