/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smartyahtzee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author essalmen
 */
public class DiceSet {
    private ArrayList<Die> dice;
    private Random random;
    
    
    /**
     * Konstruktori.
     * 
     * Luo viisi noppaa ja random-olion.     * 
     */
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
    
    public Die getDie(int index)
    {
        return dice.get(index);
    }
    
    public ArrayList<Die> getDice()
    {
        return dice;
    }
    
    /**
     * Asettaa arvot nopille.
     * 
     * Arpoo uuden silmäluvun jokaiselle nopalle, jota ei ole lukittu.
     */
    
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
    
    /**
     * Lukitsee tekoälyn määräämät nopat.
     * 
     * @param toLock lukittavat silmäluvut
     */ 
    
    public void lockMany(int[] toLock)          //todo: optimize
    {
        unlockAll();
        for (int d : toLock)
        {
            for (int i = 0; i < dice.size(); i++)
            {
                if (!dice.get(i).isLocked() && d == dice.get(i).getNumber())
                {
                    toggleLock(i);
                }
            }
            
        }
    }
    
    /**
     * Toggle lukolle.
     * 
     * Lukitsee lukitsemattoman tai poistaa lukon lukitusta nopasta.
     * @param index monesko noppa
     */
    
    public void toggleLock(int index)
    {
        dice.get(index).lock();
    }
    
    /**
     * Vapauttaa kaikki nopat.
     * 
     * Käytetään uuden vuoron alussa.
     */
    
    public void unlockAll()
    {
        for (Die d : dice)
        {
            d.unlock();
        }
    }
    
    /**
     * Muuttaa nopat taulukoksi.
     * 
     * Muuttaa noppien silmäluvut taulukoksi ja järjestää ne.
     * Tällöin on nopeampi tutkia, mitä pisteitä nopat tuottavat.
     * @return taulukko noppien silmäluvuista
     */
    
    public int[] asArray()
    {
        int[] dicearray = new int[5];
        for (int i = 0; i<5; i++)
        {
            dicearray[i] = this.dice.get(i).getNumber();
        }
        Arrays.sort(dicearray);
        return dicearray;
    }
    
    /**
     * to-String.
     * 
     * Lukitut nopat on ympäröity sulkumerkeillä.
     * @return noppien silmäluvut
     */
    
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
