/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package polunetsinta;

import verkko.Solmu;

/**
 * Heuristiikka joka palauttaa aina 0.0
 *
 * @author Arvoitusmies
 */
public class NollaHeuristiikka implements Heuristiikka {

    @Override
    public Double dist(Solmu ks1, Solmu ks2) {
        return 0.0;
    }

}
