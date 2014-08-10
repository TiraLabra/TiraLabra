/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smartyahtzee.AI;

import java.util.ArrayList;

/**
 *
 * @author essalmen
 */
public class TreeBuilder {
    
    private int[] dice;
    private ArrayList<DecisionTree> expectedValues;
    
    public TreeBuilder(int[] dice)
    {
        this.dice = groupingSort(dice);
        createTrees(this.dice);
        this.expectedValues = new ArrayList<>();
    }
    
    public int[] getDice()
    {
        return dice;
    }

    private void createTrees(int[] dice)
    {
        int[][] combinations = new int[4][];
        for (int j = 4; j > 0; j--)
        {
            int[] combination = new int[j];
            for (int k = 0; k < j; k++)
            {
                combination[k] = dice[k];
            }
            
            combinations[4-j] = combination;
        }
        
        for (int i = 0; i < combinations.length; i++)
        {
            DecisionTree tree = new DecisionTree(combinations[i]);
            expectedValues.add(tree);
        }
        
        
    }
    
    /**
     * Ryhmittelee nopat.
     * 
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
        
        System.out.println("Highest: " + greatestFreq + " Second: "+secondFreq);
        
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
    
}


