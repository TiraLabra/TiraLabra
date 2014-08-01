/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smartyahtzee;


/**
 *
 * @author essalmen
 */
public abstract class Player {
    
    protected int[] scores;
    protected boolean[] markedColumns;
    protected DiceSet dice;
    
    public Player()
    {
        scores = new int[17];
        markedColumns = new boolean[17];
        dice = new DiceSet();
    }
    
    public void playTurn(){
        rollDice();
        markScore();
        checkForSum();
    }
    
    public String getScore(int index)
    {
        if (markedColumns[index])
        {
            return "" + scores[index];
        }
        return "-";
    }
    
    public void setScore(int index, int score)
    {
        scores[index] = score;
        markedColumns[index] = true;
    }
    
    public void checkForSum()
    {
        if (!markedColumns[8])
        {
            return;
        }
        
        int sum = 0;
        for (int i = 0; i < 6; i++)
        {
            if (!markedColumns[i])
            {
                return;
            }
            sum += scores[i];
        }
        
        scores[7] = sum;
        
        if (scores[7] >= 63)
        {
            scores[8] = 50;
        } else {
            scores[8] = 0;
        }
        
        markedColumns[7] = true;        //not that this is ever used or checked(?)
        markedColumns[8] = true;

    }
    
    public boolean marked(int index)
    {
        return markedColumns[index];
    }

    protected abstract void rollDice();

    protected abstract void markScore();
    
}
