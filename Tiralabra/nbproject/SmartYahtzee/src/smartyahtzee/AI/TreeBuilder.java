/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smartyahtzee.AI;

/**
 *
 * @author essalmen
 */
public class TreeBuilder {
    
    
    public TreeBuilder(int[] dice)
    {
        dice = sort(dice);
        createTrees(dice);
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
        
        
    }
    
    private int[] sort(int[] dice)
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
        
        for (int i = 0; i < 6; i++)
        {
            if (freqDice[i] > greatestFreq)
            {
                secondFreq = greatestFreq;
                secondFreqValue = greatestFreqValue;
                greatestFreq = freqDice[i];
                greatestFreqValue = i+1;
            }
            
        }
        
        if (greatestFreq == 1)          //if no pairs 
        {
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


