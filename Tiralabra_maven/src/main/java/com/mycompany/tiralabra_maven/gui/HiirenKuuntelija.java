package com.mycompany.tiralabra_maven.gui;

import com.mycompany.tiralabra_maven.logiikka.SovellusOhjain;
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
    private final SovellusOhjain simulaatio;

    /**
     * Konstruktorissa annetaan sivun pituus ja simulaatio
     *
     * @param sivunPituus
     * @param simulaatio
     */
    public HiirenKuuntelija(int sivunPituus, SovellusOhjain simulaatio) {
        this.sivunPituus = sivunPituus;
        this.simulaatio = simulaatio;
    }

    /**
     * Hiirtä klikattiin
     * @param e event
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Hiirtä painettiin
     * @param e event
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (hiiriUlkopuolella(e)) {
            return;
        }
        simulaatio.hiiriPainettu(true);
    }

    /**
     * Hiiri irti
     * @param e event
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        simulaatio.hiiriPainettu(false);
    }

    /**
     * Hiiri tuli alueelle
     * @param e event
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Hiiri lähti pois
     * @param e event
     */
    @Override
    public void mouseExited(MouseEvent e) {
        simulaatio.hiiriPoistunut();
    }

    /**
     * Hiirtä raahattiin
     * @param e event
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (hiiriUlkopuolella(e)) {
            simulaatio.hiiriPoistunut();
            return;
        }
        simulaatio.hiiriRuudunPaalla(e.getX() / sivunPituus, e.getY() / sivunPituus);
    }

    /**
     * Hiirtä liikutettiin
     * @param e event
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        if (hiiriUlkopuolella(e)) {
            simulaatio.hiiriPoistunut();
            return;
        }
        simulaatio.hiiriRuudunPaalla(e.getX() / sivunPituus, e.getY() / sivunPituus);
    }

    private boolean hiiriUlkopuolella(MouseEvent e) {
        return e.getX() < 0 || e.getX() >= simulaatio.getLeveys() * sivunPituus || e.getY() < 0 || e.getY() >= simulaatio.getKorkeus() * sivunPituus;
    }
}
