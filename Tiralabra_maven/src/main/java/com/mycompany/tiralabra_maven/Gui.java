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
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import tira.Lista;
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

    private Graph verkko = new SatunnainenVerkko(10, 10, 1, 5);
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
        SatunnainenVerkko satunnainenVerkko = new SatunnainenVerkko(300, 300, 1, 5);
        SatunnainenLaskin satunnainenLaskin = new SatunnainenLaskin(1);
        verkko = satunnainenVerkko;
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
     * Satunnaisen verkon visualisointi
     */
    private class Piirto extends JPanel implements MouseListener {

        private int blockW = 10, blockH = 10;

        public Piirto() {
            // this.setPreferredSize( new Dimension(500,500));
            // this.
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            // int blocksize=10;
            if (verkko != null) {
                try {
                    SatunnainenVerkko v = (SatunnainenVerkko) verkko;
                    int x = v.getRivit();
                    int y = v.getSarakkeet();
                    double[][] painot = v.getPainot();
                    System.out.println("" + reitti);
                    g.setColor(Color.red);
                    if (reitti != null) {
                        Polku polku = (Polku) reitti;
                        Lista<Value> kuljettuReitti = polku.solmut();
                        for (Value value : kuljettuReitti) {
                            V solmu = (V) value;

                            g.fillRect(blockW * solmu.getX(), blockH * (solmu.getY()), blockW, blockH);

                        }
                    }

                    g.setColor(Color.YELLOW);
                    if (alku != null) {
                        V solmu = (V) alku;
                        g.fillRect(blockW * solmu.getX(), blockH * (solmu.getY()), blockW, blockH);
                    }
                    g.setColor(Color.GREEN);
                    if (loppu != null) {
                        V solmu = (V) loppu;
                        g.fillRect(blockW * solmu.getX(), blockH * (solmu.getY()), blockW, blockH);
                    }

                    g.setColor(Color.BLACK);
                    for (int i = 0; i < x; i++) {
                        for (int j = 0; j < y; j++) {
                            g.drawString("" + (int) painot[i][j], blockW * i, blockH * (j + 1));
                        }
                    }

                } catch (Exception e) {
                }
            }

            // g.fillRect(33, 66, 555, 333);
        }

        public void mouseClicked(MouseEvent e) {
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            // System.out.println("bttn: " + e.getButton() + ", x:" + e.getX() + ", y:" + e.getY());
            int x = (e.getX() - this.getX()) / blockW;
            int y = (e.getY() - this.getY()) / blockH;

            if (e.getButton() == 1) { // left click
                try {
                    alku = ((SatunnainenVerkko) verkko).getSolmu(x, y);
                } catch (Exception ex) {
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
