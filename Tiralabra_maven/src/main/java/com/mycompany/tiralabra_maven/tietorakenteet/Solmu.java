
package com.mycompany.tiralabra_maven.tietorakenteet;

/**
 * Tämän luokan olion on tarkoitus toimia linkitetyn tietorakenteen 
 * peruskomponenttina joka ei näy suoraan muualla ohjelmassa. Solmu ei tarvitse
 * set/get metodeja tai syötteen tarkastuksia koska ne on tarkoituksenmukaisinta
 * suorittaa itse solmun sisältävässä tietorakenteessa. Solmun kentän
 * <tt>ARVO</tt> viite voidaan asettaa vain kerran.
 * 
 * @author John Lång <jllang@cs.helsinki.fi>
 * @see Pino
 * @see Jono
 */
class Solmu<V> {
    
    protected final V ARVO;
    protected Solmu seuraaja;
    
    /**
     * Palauttaa luokan uuden instanssin, joka muistaa syötteenä annetun arvon.
     * 
     * @param ARVO Solmuun säilöttävä arvo.
     */
    Solmu(final V ARVO) {
        this.ARVO = ARVO;
    }

}
