/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package polunetsinta;

import verkko.Solmu;

public class TaksimiehenEtaisyys implements Heuristiikka {

    @Override
    public Double dist(Solmu ks1, Solmu ks2) {
        return ks1.taksimiehenEtaisyys(ks2);
    }

}
