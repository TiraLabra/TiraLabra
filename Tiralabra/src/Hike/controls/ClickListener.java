
package Hike.controls;

import Hike.gameWindow.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listens to mouse clicks.
 * 
 */
public class ClickListener implements ActionListener {
    
    private GameScreen screen;

    public ClickListener(GameScreen a) {
        screen = a;
        
    }

    public enum Actions {

        STARTGAME,
        SAVESCORE
    }

    public ClickListener() {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == Actions.STARTGAME.name()) {
            screen.openMap();
            
            
        }
    }
}