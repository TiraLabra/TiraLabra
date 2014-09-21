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
     * @return Parhaan siirron koordinaatti.
     */
    public Koordinaatti etsiParasRistinSiirto();

    /**
     * Etsii parhaan Nollan siirron. ToDo
     *
     * @return Parhaan siirron koordinaatti.
     */
    public Koordinaatti etsiParasNollanSiirto();

}
