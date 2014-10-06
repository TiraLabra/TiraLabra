package viidensuora.ai;

import viidensuora.logiikka.Ristinolla;

/**
 * Rajapinta Etsintämetodien käyttämille Evaluoijille.
 *
 * @author juha
 */
public interface Evaluointimetodi {

    /**
     * Palauttaa heuristisen arvion tämän hetkisen pelin tilanteesta.
     *
     * @param rn pelitilanne
     * @return Mitä suurempi arvo, sitä todennäköisemmin Risti voittaa. Mitä
     * pieniempi, Nolla voitta. 0 jos pelitilannne vaikuttaa tasapeliltä.
     */
    public int evaluoiPelitilanne(Ristinolla rn);
}
