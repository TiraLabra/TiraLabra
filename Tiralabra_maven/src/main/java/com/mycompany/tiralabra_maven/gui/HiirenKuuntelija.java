/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.gui;

import com.mycompany.tiralabra_maven.algoritmi.Simulaatio;
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

    private int sivunPituus;
    private Simulaatio simulaatio;

    public HiirenKuuntelija(int sivunPituus, Simulaatio simulaatio) {
        this.sivunPituus = sivunPituus;
        this.simulaatio = simulaatio;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        simulaatio.hiiriPainettu(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        simulaatio.hiiriPainettu(false);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        simulaatio.hiiriRuudunPaalla(e.getX() / sivunPituus, e.getY() / sivunPituus);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        simulaatio.hiiriRuudunPaalla(e.getX() / sivunPituus, e.getY() / sivunPituus);
    }

}
