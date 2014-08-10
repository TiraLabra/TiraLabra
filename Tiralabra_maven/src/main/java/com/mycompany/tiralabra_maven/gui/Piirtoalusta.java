/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.gui;

import com.mycompany.tiralabra_maven.Koordinaatit;
import com.mycompany.tiralabra_maven.Piirtologiikka;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Käyttöliittymäluokka, joka piirtää piirtologiikkaa apunaan käyttäen ruudukon.
 *
 * @author mikko
 */
public class Piirtoalusta extends JPanel implements Paivitettava, Runnable {

    private Piirtologiikka piirtologiikka;
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
     * Konstruktori ottaa parametrina piirtologiikan ja sivun pituuden.
     *
     * @param piirtologiikka
     * @param sivunPituus
     */
    public Piirtoalusta(Piirtologiikka piirtologiikka, int sivunPituus) {
        this.piirtologiikka = piirtologiikka;
        this.sivunPituus = sivunPituus;
        this.alustettu = true;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!alustettu) {
            return;
        }
        for (int x = 0; x < piirtologiikka.getLeveys(); x++) {
            for (int y = 0; y < piirtologiikka.getKorkeus(); y++) {
                Ruutu r = piirtologiikka.getRuutu(x, y);
                if (r == null) {
                    r = Ruutu.KOSKEMATON;
                }
                switch (r) {
                    case KOSKEMATON:
                        g.setColor(Color.LIGHT_GRAY);
                        break;
                    case TUTKIMATON:
                        g.setColor(Color.PINK);
                        break;
                    case TUTKITTU:
                        g.setColor(Color.CYAN);
                        break;
                    case REITTI:
                        g.setColor(Color.YELLOW);
                        break;
                    case SEINA:
                        g.setColor(Color.DARK_GRAY);
                        break;
                    case ALKU:
                        g.setColor(Color.RED);
                        break;
                    case MAALI:
                        g.setColor(Color.GREEN);
                        break;
                    case KASITTELYSSA:
                        g.setColor(Color.ORANGE);
                }
                g.fill3DRect(x * sivunPituus, y * sivunPituus, sivunPituus, sivunPituus, true);

            }
        }

        hiiri = piirtologiikka.hiirenKoordinaatit();
        if (hiiri != null) {
            g.setColor(Color.magenta);
            g.draw3DRect(hiiri.getX() * sivunPituus, hiiri.getY() * sivunPituus, sivunPituus, sivunPituus, !piirtologiikka.onkoHiiriPainettu());
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

}
