package com.mycompany.tiralabra_maven.gui;

import com.mycompany.tiralabra_maven.PeliOhjain;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Luokka tarjoaa metodin pelaajan hiirenklikkausten tulkitsemiseen
 * @author noora
 */
public class Hiirenkuuntelija implements MouseListener {
    private PeliOhjain peli;
    private int reuna;
    private int sivunPituus;
    
    public Hiirenkuuntelija(PeliOhjain peli){
        this.peli = peli;
        this.reuna = 4;
        this.sivunPituus = 40;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    /**
     * Metodi selvittää, mitä pelilaudan ruutua pelaaja on klikannut
     * @param e 
     */
    @Override
    public void mousePressed(MouseEvent e) {
        
            int sarake = (e.getX()-reuna) / sivunPituus;
            int rivi = (e.getY()-reuna) / sivunPituus;
            if (sarake >= 0 && sarake < 8 && rivi >= 0 && rivi < 8) {
                peli.valitseRuudutJoissaSiirtoTapahtuu(rivi, sarake);
            }        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}
