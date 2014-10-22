package com.mycompany.tiralabra_maven.tyokalut;

/**
 * Linkitetyn listan käyttämä solmu.
 *
 * @see LinkitettyLista
 * @author Markus
 */
public class Solmu {

    int[] data;
    Solmu seuraava;

    /**
     * Luo uuden solmun joka sisältää parametrina annettavan taulukon.
     *
     * @param data Solmulle annettava taulukko.
     */
    public Solmu(int[] data) {
        this.data = data;
    }

    /**
     * Palauttaa solmun sisältämän taulukon
     *
     * @return Solmun sisältämä taulukko.
     */
    public int[] getData() {
        return data;
    }

    /**
     * Palauttaa viitteen seuraavaan solmuun listalla.
     *
     * @return Linkitetyllä listalla seuraava solmu tai null jos solmu on
     * viimeinen.
     */
    public Solmu getSeuraava() {
        return seuraava;
    }

    /**
     * Asettaa seuraavan solmun.
     *
     * @param seuraava Seuraavaksi solmuksi asetettava solmu.
     */
    public void setSeuraava(Solmu seuraava) {
        this.seuraava = seuraava;
    }

}
