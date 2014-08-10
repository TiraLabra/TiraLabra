/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.gui.Ruutu;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Sovelluslogiikkaan kuuluva luokka, joka sisältää tiedon piirrettävästä
 * ruudukosta ja ruutujen tilasta. Saatetaan myöhemmässä vaiheessa siirtää
 * osaksi simulaatiota jos siltä tuntuu. Tämä luokka toimii apuna
 * käyttöliittymäluokkien piirtokoodille, ja sisältää kaiken tarpeellisen
 * käyttöliittymän piirtämiseen ollen kuitenkin itse riippumaton
 * käyttöliittymäluokista.
 *
 * @author mikko
 */
public class Piirtologiikka {

    private final Ruutu[][] ruudut;
    private Koordinaatit hiiri;
    private boolean hiiriPainettu;
    private int leveys;
    private int korkeus;

    public Piirtologiikka(int leveys, int korkeus) {
        this.ruudut = new Ruutu[korkeus][leveys];
        this.leveys = leveys;
        this.korkeus = korkeus;
    }

    public Ruutu[][] getRuudukko() {
        return this.ruudut;
    }

    public Ruutu getRuutu(int x, int y) {
        return this.ruudut[y][x];
    }

    public void setRuutu(int x, int y, Ruutu ruutu) {

        this.ruudut[y][x] = ruutu;
    }

    public int getLeveys() {
        return this.leveys;
    }

    /**
     * Tämän metodin avulla piirtologiikka saa tiedon siitä, että hiiri on tällä
     * hetkellä jonkun ruudukon ruudun päällä. Tätä tietoa voidaan käyttää
     * avuksi varsinaisessa käyttöliittymäkoodissa.
     *
     * @param x
     * @param y
     */
    public void hiiriRuudunPaalla(int x, int y) {
        //System.out.println("hiiri ruudun päällä " + x + ", " + y);
        if (hiiri == null || x != hiiri.getX() || y != hiiri.getY()) {
            this.hiiri = new Koordinaatit(x, y);
        }
    }

    /**
     * Tämän metodin avulla piirtologiikka saa tiedon siitä, että hiiri on
     * poistunut ruudukon päältä.
     */
    public void hiiriPoistunut() {
        this.hiiri = null;
    }

    /**
     * Palauttaa sen ruudun koordinaatit, jonka päällä hiiri on, tai null, jos
     * hiiri ei ole minkään ruudun päällä.
     *
     * @return hiiren koordinaatit
     */
    public Koordinaatit hiirenKoordinaatit() {
        return this.hiiri;
    }

    /**
     * Tämän metodin avulla piirtologiikka saa tiedon siitä, että hiiren nappi
     * on painettu pohjaan tai päästetty irti.
     */
    public void hiiriPainettu(boolean painettu) {
        this.hiiriPainettu = painettu;
    }

    /**
     * Palauttaa true jos hiiren nappi on painettuna pohjaan.
     * @return onko painettu pohjaan
     */
    public boolean onkoHiiriPainettu() {
        return this.hiiriPainettu;
    }

    public int getKorkeus() {
        return this.korkeus;
    }
}
