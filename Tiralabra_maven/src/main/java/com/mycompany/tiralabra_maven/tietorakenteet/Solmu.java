
package com.mycompany.tiralabra_maven.tietorakenteet;

/**
 * Tämän luokan olion on tarkoitus toimia linkitetyn tietorakenteen 
 * peruskomponenttina joka ei näy suoraan muualla ohjelmassa. Solmu ei tarvitse
 * set/get metodeja tai syötteen tarkastuksia koska ne on tarkoituksenmukaisinta
 * suorittaa itse solmun sisältävässä tietorakenteessa. Solmun viitteen arvo
 * voidaan asettaa vain kerran.
 * 
 * @author John Lång
 */
class Solmu<V> {
    
    protected final V ARVO;
    protected Solmu seuraaja;
    
    Solmu(final V ARVO) {
        this.ARVO = ARVO;
    }

}
