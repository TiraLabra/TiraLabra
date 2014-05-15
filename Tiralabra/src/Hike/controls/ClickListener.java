/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hike.controls;

import Hike.gameWindow.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author petri
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
            screen.createPlayingField("../map1.png");
            
        }
    }
}