/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package labyrintti;

import verkko.Solmu;

/**
 * Abstrakti luokka Labyrintti2D labyrintittäville algoritmeille
 *
 * @author Arvoitusmies
 */
public abstract class Labyrintitin {

    /**
     * Tähän talletetaan solmut.
     */
    final Solmu[][] solmut;

    /**
     * Asettaa kenttään.
     *
     * @param solmut
     */
    public Labyrintitin(Solmu[][] solmut) {
        this.solmut = solmut.clone();
    }

    /**
     * "Labyrintittää"
     *
     * @return
     */
    public abstract Solmu[][] labyrintitaLabyrintti();

}
