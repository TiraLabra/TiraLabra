
package chess.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * Tämä luokka kuuntelee, mitä käyttöliittymän nappeja painetaan.
 */
public class ButtonActions implements ActionListener  {
    /**
     * Gui luokka jonka nappeja kuunnellaan.
     */     
    private Gui ui;
       
    /**
     * Luokan konstruktori.
     *
     * @param ui luokka, jonka nappeja kuunnellaan.
     */    
    public ButtonActions(Gui ui) {
        this.ui = ui;
        
    }

    /**
     * Tämä metodi kertoo mitä tehdä, kun jotain nappia painetaan.
     *
     * @param ae Napinpainallus.
     */    
    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton button = ((JButton) ae.getSource());
        
        if (button.getText().equals("Exit")) {
            System.exit(0);
        } 
        
        if (button.getText().equals("Start new game")) {
            ui.startGame();
        }
        
        if (button.getText().equals("Give up")) {
            ui.endGame();
        }                  
    }
    
}
