/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smartyahtzee;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author essalmen
 */
public class DiceSet {
    private Die firstDie;
    private Random random;
    
    
    /**
     * Konstruktori.
     * 
     * Luo viisi noppaa ja random-olion.     * 
     */
    public DiceSet()
    {
        random = new Random();
        firstDie = new Die();
        Die prevDie = firstDie;
        for (int d = 0; d < 4; d++)
        {
            Die newDie = new Die();
            prevDie.setNextDie(newDie);
            prevDie = newDie;
        }
    }
    
    public Die getDie(int index)
    {
        Die die = firstDie;
        for (int i = 0; i < index; i++)
        {
            die = die.nextDie();
        }
        return die;
    }
    
    public Die[] getDice()
    {
        Die[] dice = new Die[5];
        dice[0] = firstDie;
        for (int i = 1; i < 5; i++)
        {
            dice[i] = dice[i-1].nextDie();
        }
        return dice;
    }
    
    /**
     * Asettaa arvot nopille.
     * 
     * Arpoo uuden silmäluvun jokaiselle nopalle, jota ei ole lukittu.
     */
    
    public void throwDice()
    {
        Die instance = firstDie;
        for (int i = 0; i < 5; i++)
        {
            if (!instance.isLocked())
            {
                instance.setNumber(random.nextInt(6) + 1);
            }
            instance = instance.nextDie();
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
        Die die = firstDie;
        boolean matchFound = false;
        for (int i = 0; i < 5; i++)
        {
            matchFound = false;
            for (int n = 0; n < toLock.length; n++)
            {
                
                if (die.getNumber() == toLock[n])
                {
                    int[] newToLock = copyArray(toLock, n);
                    toLock = newToLock;
                    die.lock();
                    matchFound = true;
                    die = die.nextDie();
                    break;
                }
            }
            if (!matchFound)
            {
                die = die.nextDie();
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
        Die die = firstDie;
        for (int i = 0; i < index; i++)
        {
            die = die.nextDie();
        }
        die.lock();
    }
    
    /**
     * Vapauttaa kaikki nopat.
     * 
     * Käytetään uuden vuoron alussa.
     */
    
    public void unlockAll()
    {
        Die die = firstDie;
        for (int i= 0; i < 5; i++)
        {
            die.unlock();
            die = die.nextDie();
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
        Die die = firstDie;
        for (int i = 0; i<5; i++)
        {
            dicearray[i] = die.getNumber();
            die = die.nextDie();
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
        Die die = firstDie;
        
        for (int i = 0; i < 5; i++)
        {
            if (die.isLocked())
            {
                diceString = diceString + "(" + die.getNumber() + ") ";
            } else {
                diceString += die.getNumber() + " ";
            }
            die = die.nextDie();
        }
        
        return diceString;
    }
    
    /**
     * Apumetodi taulukon kopioimiseen.
     * 
     * 
     * @param array kopioitava taulukko
     * @param index paikka joka jätetään kopioimatta
     */
    
    private int[] copyArray(int[] array, int index)
    {
        int[] newArray = new int[array.length-1];
        boolean indexReached = false;
        
        for (int i = 0; i < newArray.length; i++)
        {
            if (i == index)
            {
                indexReached = true;
            }
            if (indexReached) {
                newArray[i] = array[i+1];
            } else {
                newArray[i] = array[i];
            }
            
        }
        
        return newArray;
    }
}
