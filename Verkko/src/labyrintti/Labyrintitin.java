/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package labyrintti;

import verkko.KoordinoituSolmu;

/**
 *
 * @author Arvoitusmies
 */
public abstract class Labyrintitin {

    /**
     *
     */
    KoordinoituSolmu[][] solmut;

    /**
     * "Labyrintittää"
     *
     * @return
     */
    public abstract KoordinoituSolmu[][] labyrintitaLabyrintti();

    /**
     * Asettaa kenttään.
     * @param solmut
     */
    public Labyrintitin(KoordinoituSolmu[][] solmut) {
        this.solmut = solmut;
    }

}
