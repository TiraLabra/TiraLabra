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
    
    private int[] scores;
    private boolean[] markedColumns;
    
    public Player()
    {
        scores = new int[17];
        markedColumns = new boolean[17];
    }
    
    public abstract void playTurn();
    
    public int getScore(int index)
    {
        return scores[index];
    }
    
    public void setScore(int index, int score)
    {
        scores[index] = score;
        markedColumns[index] = true;
    }
    
}
