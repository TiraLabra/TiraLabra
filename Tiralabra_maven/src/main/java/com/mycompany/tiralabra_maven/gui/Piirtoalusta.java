/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.gui;

import com.mycompany.tiralabra_maven.Koordinaatit;
import com.mycompany.tiralabra_maven.algoritmi.Simulaatio;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Käyttöliittymäluokka, joka piirtää piirtologiikkaa apunaan käyttäen ruudukon.
 *
 * @author mikko
 */
public class Piirtoalusta extends JPanel implements Paivitettava, Runnable {

    private Simulaatio simulaatio;
    private int sivunPituus;
    private Koordinaatit hiiri;
    boolean alustettu;

    /**
     * Konstruktori, joka ei ota parametrejä. Käytetään jos yritetään tehdä
     * parempi gui Netbeansin Gui Builderin avulla.
     */
    public Piirtoalusta() {
        this.alustettu = false;
    }

    /**
     * Konstruktori ottaa parametrina simulaation ja sivun pituuden.
     *
     * @param simulaatio
     * @param sivunPituus
     */
    public Piirtoalusta(Simulaatio simulaatio, int sivunPituus) {
        this.simulaatio = simulaatio;
        this.sivunPituus = sivunPituus;
        this.alustettu = true;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!alustettu) {
            return;
        }
        for (int x = 0; x < simulaatio.getLeveys(); x++) {
            for (int y = 0; y < simulaatio.getKorkeus(); y++) {
                PiirrettavaRuutu r = simulaatio.getMaailmaRuutu(x, y);
                if (r == null) {
                    throw new IllegalStateException("Maailmasta löytyi piirrettävä ruutu joka oli null");
                }
                g.setColor(r.getVari());
                g.fill3DRect(x * sivunPituus, y * sivunPituus, sivunPituus, sivunPituus, true);

                r = simulaatio.getTilaRuutu(x, y);
                if (r != null) {
                    g.setColor(r.getVari());
                    //g.draw3DRect(x * sivunPituus, y * sivunPituus, sivunPituus, sivunPituus, true);
                    g.fill3DRect(x * sivunPituus + sivunPituus / 6, y * sivunPituus + sivunPituus / 6, 2 * sivunPituus / 3, 2 * sivunPituus / 3, true);
                }

            }
        }

        g.setColor(Color.green);
        Koordinaatit alku = simulaatio.getAlkuPiste();
        //g.fill3DRect(alku.getX() * sivunPituus, alku.getY() * sivunPituus, sivunPituus, sivunPituus, true);
        g.fill3DRect(alku.getX() * sivunPituus + sivunPituus / 6, alku.getY() * sivunPituus + sivunPituus / 6, 2 * sivunPituus / 3, 2 * sivunPituus / 3, true);

        g.setColor(Color.red);
        Koordinaatit maali = simulaatio.getMaali();
        //g.fill3DRect(maali.getX() * sivunPituus, maali.getY() * sivunPituus, sivunPituus, sivunPituus, true);
        g.fill3DRect(maali.getX() * sivunPituus + sivunPituus / 6, maali.getY() * sivunPituus + sivunPituus / 6, 2 * sivunPituus / 3, 2 * sivunPituus / 3, true);
        hiiri = simulaatio.hiirenKoordinaatit();
        if (hiiri != null) {
            g.setColor(Color.magenta);
            g.draw3DRect(hiiri.getX() * sivunPituus, hiiri.getY() * sivunPituus, sivunPituus, sivunPituus, !simulaatio.onkoHiiriPainettu());
        }

    }

    @Override
    public void paivita() {
        super.repaint();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
            repaint();
        }
    }

    /**
     * Palauttaa tämän halutun koon.
     *
     * @return koko
     */
    @Override
    public Dimension getPreferredSize() {
        if (simulaatio == null) {
            return new Dimension(300, 200);
        }
        return new Dimension(simulaatio.getLeveys() * sivunPituus, simulaatio.getKorkeus() * sivunPituus);
    }

}
