/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.gui;

import com.mycompany.tiralabra_maven.Piirtologiikka;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Käyttöliittymäluokka, jota käytetään hiiren liikkeiden ja toimintojen
 * kuuntelemiseen.
 *
 * @author mikko
 */
public class HiirenKuuntelija implements MouseListener, MouseMotionListener {

    private final int sivunPituus;
    private final Piirtologiikka piirtologiikka;

    public HiirenKuuntelija(int sivunPituus, Piirtologiikka piirtologiikka) {
        this.sivunPituus = sivunPituus;
        this.piirtologiikka = piirtologiikka;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        piirtologiikka.hiiriPainettu(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        piirtologiikka.hiiriPainettu(false);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        piirtologiikka.hiiriRuudunPaalla(e.getX() / sivunPituus, e.getY() / sivunPituus);
    }

}
