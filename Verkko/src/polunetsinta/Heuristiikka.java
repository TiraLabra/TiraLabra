/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package polunetsinta;

import verkko.Solmu;

/**
 * Kahden solmun välinen heuristiikkafunktio.
 *
 * @author Arvoitusmies
 */
public interface Heuristiikka {

    /**
     * Jonkinlainen laskelma, luultavimmin solmujen koordinaateista. 
     * @param ks1
     * @param ks2
     * @return 
     */
    public Double dist(Solmu ks1, Solmu ks2);
}
