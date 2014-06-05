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
public abstract class Labyrintitin2D {

    /**
     * Tähän talletetaan solmut.
     */
    final Solmu[][] solmut;

    /**
     * Asettaa kenttään.
     *
     * @param solmut
     */
    public Labyrintitin2D(Solmu[][] solmut) {
        this.solmut = solmut.clone();
    }

    /**
     * "Labyrintittää"
     *
     */
    public abstract void labyrintita();

}
