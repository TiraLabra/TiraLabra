
package com.mycompany.tiralabra_maven.rajapinnat;

/**
 * Tämän rajapinnan toteuttavan luokan tulee kapseloida jokin ehto joka toteutuu
 * tai on toteutumatta jollain syötteellä <b>Object</b>-instansseja. Parametrien
 * tarkka määrä riippuu ehdon syntaksista.
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 */
public interface Totuusfunktio {
    
    /**
     * Palauttaa arvon <i>true</i> jos ja vain jos syötteen
     * <b>Object</b>-instanssit täyttävät toteuttavan luokan määrittelemän ehdon.
     * 
     * @param SYOTE 
     * @return 
     */
    public boolean tayttaa(final Object... SYOTE);
    
}
