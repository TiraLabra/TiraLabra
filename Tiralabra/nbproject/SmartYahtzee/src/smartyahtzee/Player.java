/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smartyahtzee;

import smartyahtzee.scoring.Scoreboard;

/**
 *
 * @author essalmen
 */
public abstract class Player {
    
    private Scoreboard scores;
    
    public Player()
    {
        scores = new Scoreboard();
    }
    
    public abstract void playTurn();
}
