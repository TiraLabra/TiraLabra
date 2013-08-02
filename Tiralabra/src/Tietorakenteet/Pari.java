

package Tietorakenteet;

/**
 * Varastoluokka - varastoi key - value - parin
 */
// HUOM: oliomuuttujat tarkoituksella julkisia koska get\set tuo vaan turhaa koodia mukaan
// tämä on varastoluokka, ei implementoi metodeja
public class Pari<K, V> {
    public K ensimmainen;
    public V toinen;
}
