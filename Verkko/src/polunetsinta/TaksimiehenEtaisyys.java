/*
 * Saa käyttää ihan vapasti
 * Public domain
 */

package polunetsinta;

import verkko.KoordinoituSolmu;


public class TaksimiehenEtaisyys implements Heuristiikka{

    @Override
    public Double dist(KoordinoituSolmu ks1, KoordinoituSolmu ks2) {
        return ks1.taksimiehenEtaisyys(ks2);
    }
    
}
