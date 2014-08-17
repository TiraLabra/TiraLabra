package com.mycompany.tiralabra_maven.gui;

import com.mycompany.tiralabra_maven.Peli;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Luokka tarjoaa metodin pelaajan hiirenklikkausten tulkitsemiseen
 * @author noora
 */
public class Hiirenkuuntelija implements MouseListener {
    private Peli peli;
    
    public Hiirenkuuntelija(Peli peli){
        this.peli = peli;
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
        if (!this.peli.isPeliKaynnissa()) {
            this.peli.getPaivitettava().naytaViesti("Aloita ensin uusi peli");
        } else if (this.peli.getPelaaja() != null){
            int sarake = (e.getX() - 4) / 40;
            int rivi = (e.getY() - 4) / 40;
            if (sarake >= 0 && sarake < 8 && rivi >= 0 && rivi < 8) {
                peli.getPelaaja().valitseRuudutJoissaSiirtoTapahtuu(rivi, sarake);
            }
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
