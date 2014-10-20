package com.mycompany.tiralabra_maven.tyokalut;


/**
 * Luokka jonka tarkoitus on helpottaa mittaustulosten käsittelyä.
 *
 * @author Markus
 */
public class Vertailu {

    private Mittaustulos[] tulokset;

    /**
     * Luo uuden vertailun jolla on kuvaus ja tuntematon määrä mittaustuloksia
     *
     * @param tulokset Verrattavat tulokset
     */
    public Vertailu(Mittaustulos[] tulokset) {
        this.tulokset = tulokset;
    }


    /**
     * Palauttaa vertailuun lisätyt tulokset arraynä.
     *
     * @return vertailuun lisätyt Mittaustulos oliot sisältävä array
     */
    public Mittaustulos[] getTulokset() {
        return tulokset;
    }


}
