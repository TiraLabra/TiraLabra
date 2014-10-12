package com.mycompany.tiralabra_maven.tyokalut;

import com.mycompany.tiralabra_maven.tietorakenteet.PuuOperaatiot;

/**
 * Luokka jonka tarkoitus on helpottaa mittaustulosten käsittelyä.
 *
 * @author Markus
 */
public class Vertailu {

    private Mittaustulos[] tulokset;
    private String kuvaus;

    /**
     * Luo uuden vertailun jolla on kuvaus ja tuntematon määrä mittaustuloksia
     *
     * @param kuvaus Kuvaus siitä mitä verrataan
     * @param tulokset Verrattavat tulokset
     */
    public Vertailu(String kuvaus, Mittaustulos[] tulokset) {
        this.tulokset = tulokset;
        this.kuvaus = kuvaus;
    }

    /**
     * Palauttaa vertailulle annetun kuvauksen
     *
     * @return Vertailulle annettu kuvaus
     */
    public String getKuvaus() {
        return kuvaus;
    }

    /**
     * Palauttaa vertailuun lisätyt tulokset arraynä.
     *
     * @return vertailuun lisätyt Mittaustulos oliot sisältävä array
     */
    public Mittaustulos[] getTulokset() {
        return tulokset;
    }

    @Override
    public String toString() {
        String tuloste = "";
        int i = 1;
        tuloste += kuvaus + "\n";
        for (Mittaustulos tulos : tulokset) {
            String puu = tulos.getPuu() != null ? tulos.getPuu().getNimi() : i + "";
            String korkeus = tulos.getPuu() != null ? PuuOperaatiot.korkeus(tulos.getPuu().getJuuri()) + "" : " - ";
            tuloste += puu + "\t"
                    + "Keskimääräinen aika:" + tulos.getKeskiarvo() + " ns\t"
                    + "Pienin aika: " + tulos.getPienin() + " ns\t"
                    + "Suurin aika: " + tulos.getSuurin() + " ns\t"
                    + "Puun korkeus: " + korkeus + "\n";
            i++;
        }
        return tuloste;
    }

}
