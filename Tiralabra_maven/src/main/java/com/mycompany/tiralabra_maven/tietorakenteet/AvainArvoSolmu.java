
package com.mycompany.tiralabra_maven.tietorakenteet;

/**
 * Kuten <b>Solmu</b>, mutta sisältää lisäksi avainkentän.
 * 
 * @see Solmu
 * @author John Lång
 */
final class AvainArvoSolmu<K, V> {
    
    protected final K AVAIN;
    protected final V ARVO;
    protected AvainArvoSolmu seuraaja;
    
    AvainArvoSolmu(K avain, V arvo) {
        AVAIN   = avain;
        ARVO    = arvo;
    }
    
}
