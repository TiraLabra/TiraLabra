/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smartyahtzee.AI;

import smartyahtzee.Player;

/**
 *
 * @author essalmen
 */
public class Bot extends Player {
    
    

    @Override
    public void playTurn() {
        
        rollDice();
        markScore();
        
    }
    
    @Override
    protected void rollDice()
    {
        dice.throwDice();
        
    }
    
    @Override
    protected void markScore(){
        
    }
    
}
