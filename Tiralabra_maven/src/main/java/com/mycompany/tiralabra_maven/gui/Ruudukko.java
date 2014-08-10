package com.mycompany.tiralabra_maven.gui;

import com.mycompany.tiralabra_maven.Nappula;
import com.mycompany.tiralabra_maven.Peli;
import com.mycompany.tiralabra_maven.Siirto;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 * Luokka tarjoaa metodit, jotka tarvitaan peliruudukon piirtämiseen
 * @author noora
 */
public class Ruudukko extends JPanel {

    private final Peli peli;

    /**
     * Konstruktori saa parametrinaan pelin
     * @param peli Käynnissä oleva peli
     */
    public Ruudukko(Peli peli) {
        this.peli = peli;
        setBackground(Color.BLACK);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        piirraRuudut(g);
        if (peli.isPeliKaynnissa()) {
            merkitseVaihtoehtoisetLiikutettavat(g);
            if (peli.getValittuRivi() >= 0) {
                merkitseLiikutettavaNappula(g);
            }
        }
    }

    /**
     * Metodi piirtää pelilaudan ruudut
     * @param g 
     */
    private void piirraRuudut(Graphics g) {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (y % 2 == x % 2) {
                    g.setColor(Color.LIGHT_GRAY);
                } else {
                    g.setColor(Color.DARK_GRAY);
                }
                g.fillRect(2 + x * 20, 2 + y * 20, 20, 20);
                piirraNappulat(y, x, g);

            }
        }
    }

    /**
     * Metodi piirtää pelilaudan nappulat
     * @param y
     * @param x
     * @param g 
     */
    private void piirraNappulat(int y, int x, Graphics g) {
        Nappula nappula = this.peli.getPelilauta().getNappula(y, x);
        if (nappula != null) {
            switch (nappula) {
                case MUSTA:
                    g.setColor(Color.BLACK);
                    g.fillOval(4 + x * 20, 4 + y * 20, 15, 15);
                    return;
                case VALKOINEN:
                    g.setColor(Color.WHITE);
                    g.fillOval(4 + x * 20, 4 + y * 20, 15, 15);
                    return;
                case KRUUNATTU_MUSTA:
                    g.setColor(Color.BLACK);
                    g.fillOval(4 + x * 20, 4 + y * 20, 15, 15);
                    g.setColor(Color.WHITE);
                    g.drawString("K", 7 + x * 20, 16 + y * 20);
                    return;
                case KRUUNATTU_VALKOINEN:
                    g.setColor(Color.WHITE);
                    g.fillOval(4 + x * 20, 4 + y * 20, 15, 15);
                    g.setColor(Color.BLACK);
                    g.drawString("K", 7 + x * 20, 16 + y * 20);
            }
        }
    }

    /**
     * Metodi merkitsee nappulat, joita on mahdollista liikuttaa
     * @param g 
     */
    private void merkitseVaihtoehtoisetLiikutettavat(Graphics g) {
        g.setColor(Color.red);
        Siirto[] sallitut = peli.getSallitutSiirrot();
        if (sallitut.length != 0) {
            for (Siirto sallitut1 : sallitut) {
                g.drawRect(2 + sallitut1.getAlkuSarake() * 20, 2 + sallitut1.getAlkuRivi() * 20, 19, 19);
                g.drawRect(3 + sallitut1.getAlkuSarake() * 20, 3 + sallitut1.getAlkuRivi() * 20, 17, 17);
            }
        }
    }

    /**
     * Metodi merkitsee nappulan, jota pelaaja on päättänyt liikuttaa
     * @param g 
     */
    private void merkitseLiikutettavaNappula(Graphics g) {
        g.setColor(Color.GREEN);
        g.drawRect(2 + peli.getValittuSarake() * 20, 2 + peli.getValittuRivi() * 20, 19, 19);
        g.drawRect(3 + peli.getValittuSarake() * 20, 3 + peli.getValittuRivi() * 20, 17, 17);
        merkitseMihinVoidaanLiikkua(g);
    }

    /**
     * Metodi merkitsee ruudut, joihin valittu nappula on mahdollista liikuttaa
     * @param g 
     */
    private void merkitseMihinVoidaanLiikkua(Graphics g) {
        Siirto[] sallitut = peli.getSallitutSiirrot();

        g.setColor(Color.GREEN);
        for (int i = 0; i < sallitut.length; i++) {
            if (sallitut[i].getAlkuSarake() == peli.getValittuSarake() && sallitut[i].getAlkuRivi() == peli.getValittuRivi()) {
                g.drawRect(2 + sallitut[i].getLoppuSarake() * 20, 2 + sallitut[i].getLoppuRivi() * 20, 19, 19);
                g.drawRect(3 + sallitut[i].getLoppuSarake() * 20, 3 + sallitut[i].getLoppuRivi() * 20, 17, 17);
            }
        }
    }

}
