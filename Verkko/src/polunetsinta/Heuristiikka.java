/*
 * Saa käyttää ihan vapasti
 * Public domain
 */

package polunetsinta;

import verkko.KoordinoituSolmu;

/**
 *
 * @author Arvoitusmies
 */
public interface Heuristiikka {
    public Double dist(KoordinoituSolmu ks1, KoordinoituSolmu ks2);
}
