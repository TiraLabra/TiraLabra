
package chess.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;

/**
 * Tämä luokka kuuntelee, mitä käyttöliittymän menunnappeja painetaan.
 */
public class MenuActions implements ActionListener {
    /**
     * Gui luokka jonka menua kuunnellaan.
     */    
    private Gui ui;

    /**
     * Luokan konstruktori.
     *
     * @param ui luokka, jonka menua kuunnellaan.
     */     
    public MenuActions(Gui ui) {
        this.ui = ui;
    }

    /**
     * Tämä metodi kertoo mitä tehdä, kun menusta valitaan jokin asia.
     *
     * @param ae valinta.
     */    
    @Override
    public void actionPerformed(ActionEvent ae) {
        String menutext = ((JMenuItem)ae.getSource()).getText(); 
        
        if (menutext.equals("Load game")) {
            try {
                ui.loadGame();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ButtonActions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (menutext.equals("Save game")) {
            try {
                ui.saveGame();
            } catch (IOException ex) {
                Logger.getLogger(ButtonActions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
