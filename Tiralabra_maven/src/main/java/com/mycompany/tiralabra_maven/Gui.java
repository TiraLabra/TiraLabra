/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven;

import haku.AStar;
import haku.Laskin;
import haku.SatunnainenLaskin;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import tira.Hajautustaulu;
import tira.Lista;
import verkko.Pysakki;
import verkko.Verkko;
import verkko.rajapinnat.Graph;
import verkko.rajapinnat.Node;
import verkko.rajapinnat.Value;
import verkko.satunnainen.Polku;
import verkko.satunnainen.SatunnainenVerkko;
import verkko.satunnainen.V;

/**
 *
 * @author E
 */
public class Gui extends JFrame {

    private Piirto piirto;

    private Graph verkko;
    private AStar aStar;
    private Laskin laskin;
    private Node reitti;
    private Value alku, loppu;

    public Gui() {

        dev(); // oletusarvot

        piirto = new Piirto();
        this.setSize(1000, 600);

        this.setJMenuBar(teeMenu());
        this.add(piirto);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        piirto.addMouseListener(piirto);
        // this.addMouseListener(piirto);
        this.setVisible(true);
    }

    /**
     * Testiprofiilin asetukset
     */
    private void dev() {
        SatunnainenVerkko satunnainenVerkko = new SatunnainenVerkko(300, 300, 1, 9);
        SatunnainenLaskin satunnainenLaskin = new SatunnainenLaskin(1);
        verkko = satunnainenVerkko; // new Verkko();
        laskin = satunnainenLaskin;
        aStar = new AStar(verkko, laskin);
        // alku = satunnainenVerkko.getSolmu(0, 0);
        // loppu = satunnainenVerkko.getSolmu(7, 6);
        // reitti = aStar.etsiReittiOma(alku, loppu);
    }

    /**
     * Luo käyttöliittymän valikon
     *
     * @return
     */
    private JMenuBar teeMenu() {
        JMenuBar menu = new JMenuBar();
        return menu;
    }

    /**
     * Etsii reitin alku- ja loppusolmujen välille
     */
    private void etsiReitti() {
        if (alku != null && loppu != null) {
            reitti = aStar.etsiReittiOma(alku, loppu);
            piirto.repaint();
        }
    }

    /**
     * Vaihdetaan laskinta
     */
    private void toggleLaskin(Laskin laskin) {

    }

    /**
     * Vaihdetaan verkkoa
     */
    private void toggleVerkko(Verkko verkko) {

    }

    /**
     * Tekee Tulokset-paneelista uuden ikkunan
     */
    private void laskeToimintaa() {
        JFrame jframe = new JFrame();
        jframe.setSize(this.getSize());
        jframe.setTitle("Reitinhaun tehokkuus");
        jframe.add(new Tulokset());
        jframe.setVisible(true);

    }

    /**
     * Satunnaisen verkon visualisointi
     */
    private class Piirto extends JPanel implements MouseListener {

        /**
         * Satunnaisen verkon piirtäminen
         */
        private int blockW = 14, blockH = 10;

        /**
         * Pysäkkiverkon piirtäminen
         */
        private int pW = 10, pH = 10, modX = 20, modY = 15, skaala = 8, isoLuku = 2000;
        /**
         * Pysäkkiverkon koordinaatit-pysäkit
         */
        private Hajautustaulu<Integer, Pysakki> hitboksit; // = new Hajautustaulu(140);
        /**
         * Pysäkkiverkon pysäkit-koordinaatit
         */
        private Hajautustaulu<Pysakki, Integer> drawboksit; // = new Hajautustaulu(140);

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            // int blocksize=10;
            if (verkko != null) {
                try {
                    piirraSatunnainen(g);
                } catch (Exception e) {
                    alustaPysakkiverkko();
                    piirraPysakki(g);
                }
            }

            // g.fillRect(33, 66, 555, 333);
        }

        /**
         * Esitys satunnaiselle verkolle
         *
         * @param g
         */
        public void piirraSatunnainen(Graphics g) {
            SatunnainenVerkko v = (SatunnainenVerkko) verkko;
            int x = v.getRivit();
            int y = v.getSarakkeet();
            double[][] painot = v.getPainot();
            System.out.println("" + reitti);

            // piirretään solmut, joissa on käyty
            g.setColor(Color.PINK);
            if (aStar.getKaydytSolmut() != null) {
                for (Value value : aStar.getKaydytSolmut()) {
                    V solmu = (V) value;
                    g.fillRect(blockW * solmu.getX(), blockH * (solmu.getY()), blockW, blockH);
                }
            }
            // piirretään reitti
            g.setColor(Color.red);
            if (reitti != null) {
                Polku polku = (Polku) reitti;
                Lista<Value> kuljettuReitti = polku.solmut();
                for (Value value : kuljettuReitti) {
                    V solmu = (V) value;

                    g.fillRect(blockW * solmu.getX(), blockH * (solmu.getY()), blockW, blockH);

                }
            }
            // piirretään alkusolmu
            g.setColor(Color.YELLOW);
            if (alku != null) {
                V solmu = (V) alku;
                g.fillRect(blockW * solmu.getX(), blockH * (solmu.getY()), blockW, blockH);
            }
            // piirretään loppusolmu
            g.setColor(Color.GREEN);
            if (loppu != null) {
                V solmu = (V) loppu;
                g.fillRect(blockW * solmu.getX(), blockH * (solmu.getY()), blockW, blockH);
            }
            // piirretään verkon painot
            g.setColor(Color.BLACK);
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    g.drawString("" + (int) painot[i][j], blockW * i, blockH * (j + 1));
                }
            }
        }

        /**
         * Esitys pysäkkiverkolle
         *
         * @param g
         */
        public void piirraPysakki(Graphics g) {

            BufferedImage img = null;
            // huhhuh, huh
            // kartta
            try {
                img = ImageIO.read(new File("kartta.jpg"));
            } catch (Exception ex) {
            }

            g.drawImage(img, pH, pH, rootPane);

            g.setColor(Color.red);
            if (drawboksit != null) {
                for (Pysakki p : drawboksit.keySet()) {
                    if (p == alku) {
                        piirraPysakki(g,Color.YELLOW,p);
                    } else if (p == loppu) {
                        piirraPysakki(g,Color.GREEN,p);
                    } else {
                        piirraPysakki(g,Color.red,p);
                    }
                }
            }

        }

        /**
         * Piirtää pysäkin paneeliin annetulla värillä
         *
         * @param g
         * @param k
         * @param pysakki
         */
        private void piirraPysakki(Graphics g, Color k, Pysakki pysakki) {
            int avain = drawboksit.get(pysakki);
            g.setColor(k);
            g.drawRect(avain % isoLuku, avain / isoLuku, pW, pH);

        }

        private Pysakki haePysakki() {
            return null;
        }

        private void alustaPysakkiverkko() {
            if ( hitboksit == null ) hitboksit = new Hajautustaulu(140);
            if ( drawboksit == null ) drawboksit = new Hajautustaulu(140);
            Verkko v = (Verkko) verkko;

            Pysakki[] pysakit = v.getPysakit();
            // normalisoidaan nämä
            int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
            for (Pysakki p : pysakit) {
                int x = p.getX(), y = p.getY();
                // System.out.println(""+p.getX()+" "+p.getY());
                if (x < minX) {
                    minX = x;
                }
                if (x > maxX) {
                    maxX = x;
                }
                if (y < minY) {
                    minY = y;
                }
                if (y > maxY) {
                    maxY = y;
                }
            }
            for (Pysakki p : pysakit) {
                int x = p.getX() - minX, y = maxY - p.getY();//-minY;
                x /= skaala;
                x += modX;
                y /= skaala;
                y += modY;

                int avain = y * isoLuku + x;

                hitboksit.put(avain, p);
                drawboksit.put(p, avain);

                if (p.getKoodi().equals("1230407")) {
                    // g.setColor(Color.BLACK);
                    //piirraPysakki(g, Color.BLACK, p);
                } else {
                    // g.setColor(Color.red);
                }

                // g.drawRect(x, y, pW, pH);
            }
            // System.out.println("Range X:" + (maxX - minX) + ", Range Y:" + (maxY - minY));
        }

        /**
         * Hiirikuuntelija. Vaihtaa vasemmalla klikkauksella lähtösolmua,
         * oikealla klikkauksella maalisolmua
         *
         * @param e
         */
        public void mouseClicked(MouseEvent e) {
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            // System.out.println("bttn: " + e.getButton() + ", x:" + e.getX() + ", y:" + e.getY());
            int x = (e.getX() - this.getX()) / blockW;
            int y = (e.getY() - this.getY()) / blockH;

            if (e.getButton() == 1) { // left click
                try {
                    alku = ((SatunnainenVerkko) verkko).getSolmu(x, y);
                } catch (Exception ex) {

                    System.out.println("" + e.getX() + " - " + e.getY());
                }

            } else if (e.getButton() == 3) { // left click
                try {
                    loppu = ((SatunnainenVerkko) verkko).getSolmu(x, y);
                } catch (Exception ex) {
                }
            } else {

                return;
            }

            etsiReitti();

        }

        public void mousePressed(MouseEvent e) {
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public void mouseReleased(MouseEvent e) {
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public void mouseEntered(MouseEvent e) {
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public void mouseExited(MouseEvent e) {
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    /**
     * Tulosten visualisointi
     */
    private class Tulokset extends JPanel {

        public Tulokset() {
            this.setPreferredSize(new Dimension(500, 500));
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);

            if (verkko != null) {
                g.fillRect(33, 66, 555, 333);
            }

        }
    }

}
