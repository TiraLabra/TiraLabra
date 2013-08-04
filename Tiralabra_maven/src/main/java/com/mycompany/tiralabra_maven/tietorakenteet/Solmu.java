/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven.tietorakenteet;

/**
 * Tämän luokan olion on tarkoitus toimia linkitetyn tietorakenteen 
 * peruskomponenttina joka ei näy suoraan muualla ohjelmassa. Solmu ei tarvitse
 * set/get metodeja tai syötteen tarkastuksia koska ne on tarkoituksenmukaisinta
 * suorittaa itse solmun sisältävässä tietorakenteessa. Solmun arvo ei voi 
 * muuttua.
 * 
 * @author John Lång
 */
final class Solmu<T> {
    
    protected final T ARVO;
    protected Solmu seuraaja;
    
    Solmu(final T ARVO) {
        this.ARVO = ARVO;
    }

}
