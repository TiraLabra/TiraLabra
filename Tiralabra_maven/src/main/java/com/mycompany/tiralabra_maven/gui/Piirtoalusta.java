package com.mycompany.tiralabra_maven.gui;

import com.mycompany.tiralabra_maven.peli.Nappula;
import com.mycompany.tiralabra_maven.peli.PeliOhjain;
import com.mycompany.tiralabra_maven.peli.Siirto;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Luokka tarjoaa metodit, jotka tarvitaan peliruudukon ja nappuloiden piirtämiseen
 *
 * @author noora
 */
public class Piirtoalusta extends JPanel implements Runnable {

    private PeliOhjain peli;

    /**
     * Konstruktori saa parametrinaan pelin
     *
     * @param peli Käynnissä oleva peli
     */
    public Piirtoalusta(PeliOhjain peli) {
        this.peli = peli;
        setBackground(Color.BLACK);
        new Thread(this).start();
    }

    public Piirtoalusta() {

    }

    public void paivita() {
        super.repaint();
    }

    /**
     * Piirtoalusta piirretään omassa säikeessään tietyn ajan välein uudestaan
     */
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
     * Piirtoalustan piirtäminen piirtää aina pelilaudan ruudut ja nappulat sekä pelin ollessa käynnissä myös
     * merkitsee ne nappulat joiden liikuttaminen on mahdollista ja ruudut, joihin valitun nappulan voi liikuttaa
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.peli == null) {
            return;
        }

        piirraRuudut(g);
        if (peli.isPeliKaynnissa()) {
            merkitseVaihtoehtoisetLiikutettavat(g);
            if (peli.getValittuRivi() >= 0) {
                merkitseLiikutettavaNappula(g);
            }
        }
    }

    private void piirraRuudut(Graphics g) {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (y % 2 == x % 2) {
                    g.setColor(Color.LIGHT_GRAY);
                } else {
                    g.setColor(Color.DARK_GRAY);
                }
                g.fillRect(4 + x * 40, 4 + y * 40, 40, 40);
                piirraNappulat(y, x, g);

            }
        }
    }

    private void piirraNappulat(int y, int x, Graphics g) {
        if (peli.getPelilauta() == null) {
            return;
        }
        Nappula nappula = this.peli.getPelilauta().getNappula(y, x);
        if (nappula != null) {
            switch (nappula) {
                case MUSTA:
                    g.setColor(Color.BLACK);
                    g.fillOval(8 + x * 40, 8 + y * 40, 30, 30);
                    return;
                case VALKOINEN:
                    g.setColor(Color.WHITE);
                    g.fillOval(8 + x * 40, 8 + y * 40, 30, 30);
                    return;
                case KRUUNATTU_MUSTA:
                    g.setColor(Color.BLACK);
                    g.fillOval(8 + x * 40, 8 + y * 40, 30, 30);
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("a", Font.BOLD, 25));
                    g.drawString("K", 14 + x * 40, 32 + y * 40);
                    return;
                case KRUUNATTU_VALKOINEN:
                    g.setColor(Color.WHITE);
                    g.fillOval(8 + x * 40, 8 + y * 40, 30, 30);
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("a", Font.BOLD, 25));
                    g.drawString("K", 14 + x * 40, 32 + y * 40);
            }
        }
    }

    private void merkitseVaihtoehtoisetLiikutettavat(Graphics g) {
        g.setColor(Color.BLUE);
        Siirto[] sallitut = peli.getSallitutSiirrot();
        if (sallitut == null) {
            return;
        }
        if (sallitut.length != 0) {
            for (Siirto sallitut1 : sallitut) {
                g.drawRect(4 + sallitut1.getAlkuSarake() * 40, 4 + sallitut1.getAlkuRivi() * 40, 38, 38);
                g.drawRect(6 + sallitut1.getAlkuSarake() * 40, 6 + sallitut1.getAlkuRivi() * 40, 34, 34);
            }
        }
    }

    private void merkitseLiikutettavaNappula(Graphics g) {
        g.setColor(Color.CYAN);
        g.drawRect(4 + peli.getValittuSarake() * 40, 4 + peli.getValittuRivi() * 40, 38, 38);
        g.drawRect(6 + peli.getValittuSarake() * 40, 6 + peli.getValittuRivi() * 40, 34, 34);
        merkitseMihinVoidaanLiikkua(g);
    }

    private void merkitseMihinVoidaanLiikkua(Graphics g) {
        Siirto[] sallitut = peli.getSallitutSiirrot();

        g.setColor(Color.GREEN);
        for (int i = 0; i < sallitut.length; i++) {
            if (sallitut[i].getAlkuSarake() == peli.getValittuSarake() && sallitut[i].getAlkuRivi() == peli.getValittuRivi()) {
                g.drawRect(4 + sallitut[i].getLoppuSarake() * 40, 4 + sallitut[i].getLoppuRivi() * 40, 38, 38);
                g.drawRect(6 + sallitut[i].getLoppuSarake() * 40, 6 + sallitut[i].getLoppuRivi() * 40, 34, 34);
            }
        }
    }

}
