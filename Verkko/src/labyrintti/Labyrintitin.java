/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package labyrintti;

import verkko.Solmu;

/**
 *
 * @author Arvoitusmies
 */
public abstract class Labyrintitin {

    /**
     *
     */
    Solmu[][] solmut;

    /**
     * "Labyrintittää"
     *
     * @return
     */
    public abstract Solmu[][] labyrintitaLabyrintti();

    /**
     * Asettaa kenttään.
     * @param solmut
     */
    public Labyrintitin(Solmu[][] solmut) {
        this.solmut = solmut;
    }

}
