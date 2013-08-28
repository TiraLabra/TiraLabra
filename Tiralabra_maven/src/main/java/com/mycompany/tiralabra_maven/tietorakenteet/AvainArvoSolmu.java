
package com.mycompany.tiralabra_maven.tietorakenteet;

/**
 * Kuten <b>Solmu</b>, mutta sisältää lisäksi avainkentän. Avaimen viite on
 * muuttumaton.
 * 
 * @author John Lång <jllang@cs.helsinki.fi>
 * @see Solmu
 */
final class AvainArvoSolmu<K, V> extends Solmu<V> {
    
    protected final K AVAIN;
    protected AvainArvoSolmu seuraaja;
    
    /**
     * Palauttaa luokan uuden instanssin.
     * 
     * @param avain
     * @param arvo 
     */
    AvainArvoSolmu(K avain, V arvo) {
        super(arvo);
        AVAIN   = avain;
    }
    
}
