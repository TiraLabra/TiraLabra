
package com.mycompany.tiralabra_maven.tietorakenteet;

/**
 * Kuten <b>Solmu</b>, mutta sisältää lisäksi avainkentän.
 * 
 * @see Solmu
 * @author John Lång
 */
final class AvainArvoSolmu<K, V> extends Solmu<V> {
    
    protected final K AVAIN;
    protected AvainArvoSolmu seuraaja;
    
    AvainArvoSolmu(K avain, V arvo) {
        super(arvo);
        AVAIN   = avain;
    }
    
}
