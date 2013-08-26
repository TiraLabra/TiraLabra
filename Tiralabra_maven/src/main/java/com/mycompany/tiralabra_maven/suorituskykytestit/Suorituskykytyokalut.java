
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
     * @param LUVUT
     * @return 
     */
    public static float keskiarvo(final long[] LUVUT) {
        long summa = 0;
        for (long l : LUVUT) {
            summa += l;
        }
        return summa / LUVUT.length;
    }
    
    public static long pienin(final long[] LUVUT) {
        long pienin = Long.MAX_VALUE;
        
        for (long l : LUVUT) {
            if (l < pienin) {
                pienin = l;
            }
        }
        
        return pienin;
    }
    
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
