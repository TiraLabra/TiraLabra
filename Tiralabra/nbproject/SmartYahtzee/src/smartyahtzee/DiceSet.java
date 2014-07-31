/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smartyahtzee;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author essalmen
 */
public class DiceSet {
    private ArrayList<Die> dice;
    private Random random;
    
    public DiceSet()
    {
        random = new Random();
        dice = new ArrayList<Die>();
        for (int d = 0; d < 5; d++)
        {
            Die newDie = new Die();
            dice.add(newDie);
        }
    }
    
    public void throwDice()
    {
        for (Die d : dice)
        {
            if (!d.isLocked())
            {
                d.setNumber(random.nextInt(6) + 1);
            }
        }
    }
    
    public void toggleLock(int index)
    {
        dice.get(index).lock();   
    }
    
    public void unlockAll()
    {
        for (Die d : dice)
        {
            d.unlock();
        }
    }
    
    public int[] asArray()
    {
        int[] dice = new int[5];
        for (int i = 0; i<5; i++)
        {
            dice[i] = this.dice.get(i).getNumber();
        }
        return dice;
    }
    
    @Override
    public String toString()
    {
        
        String diceString = "";
        
        for (Die d : dice)
        {
            if (d.isLocked())
            {
                diceString = diceString + "(" + d.getNumber() + ") ";
            } else {
                diceString += d.getNumber() + " ";
            }
        }
        
        return diceString;
    }
}
