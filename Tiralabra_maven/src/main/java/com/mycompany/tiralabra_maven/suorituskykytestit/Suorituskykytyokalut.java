
package com.mycompany.tiralabra_maven.suorituskykytestit;

/**
 * Tämä luokka sisältää luokkien suorituskyvyn mittaamisen kannalta tarvittavaa
 * koodia jota ei kannata toistaa erikseen joka luokassa.
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 */
public final class Suorituskykytyokalut {
    
    /**
     * Laskee annetun taulukon alkioiden aritmeettisen keskiarvon.
     * 
     * @param LUVUT Taulukko luvuista, joiden aritmeettinen keskiarvo lasketaan.
     * @return Taulukon alkioiden aritmeettinen keskiarvo.
     */
    public static float keskiarvo(final long[] LUVUT) {
        long summa = 0;
        for (long l : LUVUT) {
            summa += l;
        }
        return (summa * 1.0f) / LUVUT.length;
    }
    
    /**
     * Palauttaa annetun taulukon pienimmän luvun.
     * 
     * @param LUVUT Taulukko luvuista, joista etsitään pienintä.
     * @return Taulukon pienin luku.
     */
    public static long pienin(final long[] LUVUT) {
        long pienin = Long.MAX_VALUE;
        
        for (long l : LUVUT) {
            if (l < pienin) {
                pienin = l;
            }
        }
        
        return pienin;
    }
    
    /**
     * Palauttaa annetun taulukon suurimman luvun.
     * 
     * @param LUVUT Taulukko luvuista, joista suurinta etsitään.
     * @return Taulukon suurin luku.
     */
    public static long suurin(final long[] LUVUT) {
        long suurin = 0;
        
        for (long l : LUVUT) {
            if (l > suurin) {
                suurin = l;
            }
        }
        
        return suurin;
    }
    
}
