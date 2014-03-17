
package blokus.gui.kuuntelijat;

import blokus.logiikka.Blokus;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Kuuntelee näppäimistön nuolinäppäimiä ja kääntää valittua laattaa.
 * 
 * @author Simo Auvinen
 */


public class LaudanNappaimistoKuuntelija extends KeyAdapter {
    
    Blokus blokus;
    LaudanHiiriKuuntelija hiiri;
    
    /**
     *
     * @param blokus
     * @param hiiri
     */
    public LaudanNappaimistoKuuntelija(Blokus blokus,LaudanHiiriKuuntelija hiiri) {
        this.blokus = blokus;   
        this.hiiri = hiiri;
    }
        
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            blokus.getVuorossa().getValittuna().kaannaVasempaan();
            hiiri.paivitaVarjolautaa();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            blokus.getVuorossa().getValittuna().kaannaYmpari();
            hiiri.paivitaVarjolautaa();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            blokus.getVuorossa().getValittuna().kaannaOikeaan();
            hiiri.paivitaVarjolautaa();
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
