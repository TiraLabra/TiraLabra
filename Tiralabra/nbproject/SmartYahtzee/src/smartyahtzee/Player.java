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
    private int totalPoints;
    
    
    /**
     * Konstruktori.
     * 
     * Luo nopat.
     */
    
    public Player()
    {
        scores = new int[17];
        markedColumns = new boolean[17];
        dice = new DiceSet();
    }
    
    /**
     * Yhden vuoron kulku.
     */
    
    public void playTurn(){
        rollDice();
        markScore();
        checkForSum();
    }
    
    public int totalPoints()
    {
        return totalPoints;
    }
    
    /**
     * Getteri.
     * 
     * Käyttämätön rivi on -, ei nolla.
     * @return pisteet 
     */
    
    
    public String getScore(int index)
    {
        if (markedColumns[index])
        {
            return "" + scores[index];
        }
        return "-";
    }
    
    /**
     * Setteri.
     * 
     * Merkitsee myös rivin käytetyksi.
     */
    
    public void setScore(int index, int score)
    {
        if (markedColumns[index])
        {
            System.out.println("trying to remark score");
        } else {
        scores[index] = score;
        markedColumns[index] = true;
        totalPoints += score;
        }
    }
    
    /**
     * Summatarkistus.
     * 
     * Tarkistaa, onko ensimmäiset kuusi riviä täytetty, ja jos on,
     * laskee summan ja bonuksen.
     */
    
    private void checkForSum()
    {
        if (markedColumns[7])
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
        
        scores[6] = sum;
        
        if (scores[6] >= 63)
        {
            scores[7] = 50;
        } else {
            scores[7] = 0;
        }
        
        markedColumns[6] = true;        //not that this is ever used or checked(?)
        markedColumns[7] = true;

    }
    
    public boolean marked(int index)
    {
        return markedColumns[index];
    }

    protected abstract void rollDice();

    protected abstract void markScore();
    
}
