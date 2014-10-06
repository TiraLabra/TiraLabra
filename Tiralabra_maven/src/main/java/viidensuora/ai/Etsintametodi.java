package viidensuora.ai;

import viidensuora.logiikka.Koordinaatti;

/**
 * Rajapinta Tekoälyn käyttämille Etsintämetodeille.
 *
 * @author juha
 */
public interface Etsintametodi {

    /**
     * Etsii parhaan ristin siirron.
     *
     * @param syvyys Syvyys, jolta pelipuusta etsitään.
     * @return Parhaan siirron koordinaatti
     */
    public Koordinaatti etsiRistinSiirto(int syvyys);

    /**
     * Etsii parhaan nollan siirron.
     *
     * @param syvyys Syvyys, jolta pelipuusta etsitään.
     * @return Parhaan siirron koordinaatti
     */
    public Koordinaatti etsiNollanSiirto(int syvyys);
}
