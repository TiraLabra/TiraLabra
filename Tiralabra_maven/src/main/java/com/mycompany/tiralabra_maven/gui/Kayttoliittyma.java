package com.mycompany.tiralabra_maven.gui;

import com.mycompany.tiralabra_maven.logiikka.SovellusOhjain;

/**
 * Käyttöliittymän ikkunan ja siihen liitettävät komponentit tekevä luokka.
 *
 * @author mikko
 */
public class Kayttoliittyma implements Runnable {

    private SovellusIkkuna frame;
    private final SovellusOhjain simulaatio;
    private final int sivunPituus;

    /**
     * Konstruktorissa annetaan sivun pituus ja simulaatio
     *
     * @param sovellusOhjain
     * @param sivunPituus
     */
    public Kayttoliittyma(SovellusOhjain sovellusOhjain, int sivunPituus) {
        this.simulaatio = sovellusOhjain;
        this.sivunPituus = sivunPituus;
    }

    /**
     * Palauttaa käytössä olevan piirtoalustan. Jos piirtoalustaa ei ole vielä
     * luotu tai sitä ei muusta syystä ole, palauttaa null.
     *
     * @return piirtoalusta
     */
    public Piirtoalusta getPiirtoalusta() {
        if (frame == null) {
            return null;
        }
        return frame.getPiirtoalusta();
    }

    /**
     * Luo käyttöliittymän.
     */
    @Override
    public void run() {
        frame = new SovellusIkkuna(simulaatio, sivunPituus);
        frame.setVisible(true);
    }
}
