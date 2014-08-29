/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smartyahtzee.AI;

import smartyahtzee.scoring.Scores;

/**
 *
 * @author essalmen
 */
public class TreeBuilder {
    
    private int[] dice;
    private TreeList expectedValues;         
    private boolean[] marked;
    
    public TreeBuilder(int[] dice, boolean[] marked)
    {
        this.dice = groupingSort(dice);
        this.marked = marked;
        this.expectedValues = new TreeList(8, this.dice, marked); //# of combinations
    }
    
    /**
     * Testausta varten.
     * @return 
     */
    
    public TreeList getEVs()
    {
        return expectedValues;
    }
    
    public int[] getDice()
    {
        return dice;
    }
    
    /**
     * Etsii puista kannattavimman.
     * 
     * Käy läpi luodut puut ja palauttaa sen juuren, eli noppakombinaation, josta
     * puuta lähdettiin rakentamaan.
     * 
     * @return 0-4 pituinen taulukko nopan silmälukuja
     */
    
    public int[] getDiceToLock()
    {
        double keepAllEV = Scores.calculateBestScore(dice, marked);
        
        DecisionTree biggestEVtree = expectedValues.getBiggestEVtree();
        
        double biggestEV;
        
        if (biggestEVtree != null)
        {
            biggestEV = biggestEVtree.getEV();
        } else {
            biggestEV = 0.0;
        }
        
                
        if (keepAllEV > biggestEV)  // move straight to marking scores
        {
            System.out.println("Keeping all dice");
            return dice;
        }
        
        if (biggestEVtree != null) {
            System.out.println("Keeping biggest tree");
            return biggestEVtree.getRoot();
        }
        System.out.println("Throwing all dice");
        return null;
    }
    
    /**
     * Etsii kannattavimman puun toisella vuorolla.
     * 
     * Tällä hetkellä vertailee vain seuraavia vaihtoehtoja: pidetään samat kuin viimeksi, pidetään kaikki,
     * pidetään yksi uusi uusista nopista
     * 
     * @param dice nopat
     * @param lockedDice edellisellä heitolla lukitut nopat
     * @return lukittavat nopat
     */
    
    public int[] getSecondTurnDiceToLock(int[] dice, int[] lockedDice)
    {
        dice = groupingSort(dice);
        int[] unlockedDice = arraySubtract(dice, lockedDice);
        double keepAllEV = Scores.calculateBestScore(dice, marked);
        double keepSameEV = 0;
        double[] evs;
        
        DecisionTree tree = expectedValues.getTree(lockedDice);     //ei aina toimi kun lukitut ei välttämättä ole arrayn alussa :S
        if (tree == null)
        {
            keepSameEV = 0.0;
        } else {
            keepSameEV = tree.getEV();
        }
        evs = new double[unlockedDice.length];
        for (int i = 0; i < unlockedDice.length; i++)           //todo: more combinations
        {
            evs[i] = tree.getEV(tree.findNode(i));
        }
        
       
        
        int biggestIndex = -1;
        double biggest = 0;
        int length;
        if (evs == null)
        {
            length = 0;
        } else {
            length = evs.length;
        }
        for (int i = 0; i < length; i++)            // finding biggest of the EVs
        {
            if (biggest < evs[i])
            {
                biggest = evs[i];
                biggestIndex = i;
            }
        }
        if (keepAllEV > biggest && keepAllEV > keepSameEV)
        {
            return dice;
        } else if (biggest > keepAllEV && biggest > keepSameEV)
        {
            return lockedDice;
        }
        
        //make new array with new dice added
        int[] diceToLock = new int[lockedDice.length+1];
        for (int i = 0; i < lockedDice.length; i++)
        {
            diceToLock[i] = lockedDice[i];
        }
        diceToLock[lockedDice.length] = unlockedDice[biggestIndex];
        return diceToLock;
        
    }

    /**
     * Ottaa nopista lupaavimmat kombinaatiot.
     * 
     * Oletuksena yksittäiset nopat kannattaa jättää ja parit tai useammat samat luvut
     * säästää. Myöhemmin lisättävä vielä lisää kombinaatioita.
     */
    
    
    
    /**
     * Ryhmittelee nopat.
     * 
     * Useimmin esiintyvä luku ensin ja yhtä usein esiintyvät laskevassa
     * suuruusjärjestyksessä.
     */
    
    private int[] groupingSort(int[] dice)
    {
        dice = descendingSort(dice);
        
        int[] freqDice = new int[6];
        for (int i = 0; i < 5; i++)
        {
            freqDice[dice[i]-1] = freqDice[dice[i]-1]+1;
        }
        
        int greatestFreq = 0;
        int greatestFreqValue = 0;
        int secondFreq = 0;
        int secondFreqValue = 0;
        
        for (int i = 5; i >= 0; i--)
        {
            if (freqDice[i] > greatestFreq)
            {
                secondFreq = greatestFreq;
                secondFreqValue = greatestFreqValue;
                greatestFreq = freqDice[i];
                greatestFreqValue = i+1;
            } else if (freqDice[i] > secondFreq)
            {
                secondFreq = freqDice[i];
                secondFreqValue = i+1;
            }
            
        }
                
        if (greatestFreq == 1)          //if no pairs 
        {
            for (int i = 0; i < 5; i++)
            {
                System.out.print(dice[i]);
            }
            return dice;
        }
        
        int[] sortedDice = new int[5];
        
        
        
        for (int i = 0; i < greatestFreq; i++)   //2 to 4 steps
        {
            sortedDice[i] = greatestFreqValue;
        }
        
        
        for (int i = greatestFreq; i < greatestFreq+secondFreq; i++)     //1 to 2 steps
        {
            sortedDice[i] = secondFreqValue;
        }
        
        int index = secondFreq+greatestFreq;
        
        for (int i = 0; i < 5; i++)
        {
            if (dice[i] != greatestFreqValue && dice[i] != secondFreqValue)
            {
                sortedDice[index] = dice[i];
                index ++;
            }
        }
        
        
        for (int i = 0; i < 5; i++)
        {
            System.out.print(sortedDice[i]);
        }
        
        return sortedDice;
    }
    
    /**
     * Laittaa nopat laskevaan järjestykseen.
     */
    
    private int[] descendingSort(int[] dice)
    {
        int[] reversed = new int[5];
        for (int i = 0; i < 5; i++)
        {
            reversed[i] = dice[4-i];
        }
        return reversed;
    }
    
    private int[] arraySubtract(int[] a, int[] b)
    {
        int[] result = new int[a.length-b.length];
        int index = 0;
        for (int i = b.length; i<a.length; i++)
        {
            result[index] = a[i];
            index++;
        }
        return result;
    }
    
}


