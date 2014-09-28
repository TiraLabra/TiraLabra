package viidensuora.ai;

import viidensuora.peli.Koordinaatti;
import viidensuora.peli.Peli;

/**
 * Rajapinta Tekoälyn etsintämetodeille.
 *
 * @author juha
 */
public interface Etsintametodi {

    /**
     * Aseta uusi pelitilanne. (tarve..?)
     * 
     * @param peli 
     */
    public void setPeli(Peli peli);

    /**
     * Etsii parhaan Ristin siirron. ToDo
     *
     * @param syvyys
     * @return Parhaan siirron koordinaatti.
     */
    public Koordinaatti etsiParasRistinSiirto(int syvyys);

    /**
     * Etsii parhaan Nollan siirron. ToDo
     *
     * @param syvyys
     * @return Parhaan siirron koordinaatti.
     */
    public Koordinaatti etsiParasNollanSiirto(int syvyys);

}
