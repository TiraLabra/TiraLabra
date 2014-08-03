/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.gui;

import com.mycompany.tiralabra_maven.Piirtologiikka;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author mikko
 */
public class Piirtoalusta extends JPanel {

    private Piirtologiikka piirtologiikka;
    private int sivunPituus;

    public Piirtoalusta(Piirtologiikka piirtologiikka, int sivunPituus) {
        this.piirtologiikka = piirtologiikka;
        this.sivunPituus = sivunPituus;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int x = 0; x<piirtologiikka.getLeveys(); x++) {
            for (int y = 0; y<piirtologiikka.getKorkeus(); y++) {
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
                g.fill3DRect(x*sivunPituus, y*sivunPituus, sivunPituus, sivunPituus, true);
              
            }
        }

    }
    
    public void paivita() {
        super.repaint();
    }

}
