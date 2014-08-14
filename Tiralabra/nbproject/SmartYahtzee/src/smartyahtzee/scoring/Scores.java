/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smartyahtzee.scoring;

import java.util.Arrays;
import smartyahtzee.Player;

/**
 *
 * @author Essi
 */
public class Scores {
    
    /** Pistetaulukon tekstit */
    
    public static final String[] scoreDescriptions = {
        "Ones:            ", "Twos:            ", "Threes:          ", "Fours:           ",
        "Fives:           ", "Sixes:           ", "Sum:             ", "Bonus:           ",
        "Pair:            ", "Two pairs:      ", "Three of a kind:",
        "Four of a kind: ", "Small straight: ", "Large straight: ",
        "Full house:     ", "Chance:         ", "Yahtzee:        ",
        "Total:      "
    };
    
    /** Maksimitulokset pistekategorioille */
    
    public static final int[] maxScores = {
        5, 10, 15, 20, 25, 30, 105, 50,
        12, 22, 18, 24, 15, 20, 28, 30, 50
    };
    
    public static final double[] expectedValues = {           // calculated average scores from 1 000 000 throws
        0.832511, 1.666188, 2.501655, 3.33348, 4.168105, 5.001114, 0.0, 0.0, 6.986218, 3.78259, 2.233836, 0.280384, 0.23184, 0.3118, 0.690615, 17.503053, 0.04025
    };
    
    public static double calculateBestScore(int[] dice, boolean[] marked)
    {
        double bestScore = 0;
        for (int i = 0; i < 17; i++)
        {
            if (marked[i] || i == 7 || i == 6)  
            {
                continue;
            }
            double score = calculateScore(i, dice) / maxScores[i] * expectedValues[i];
            if (score > bestScore)
            {
                bestScore = score;
            }
        }
        return bestScore;
    }
    
    /**
     * Laskee pisteet.
     * 
     * Monitulkintaiset tilanteet lasketaan pelaajan eduksi.
     * @param index monesko rivi
     * @param dice noppien tulokset
     * @return 
     */
    
    public static int calculateScore(int index, int[] dice)
    {
        int score = 0;
        switch (index) 
        {
            case 0:
                return numbersScore(1, dice);
            case 1:
                return numbersScore(2, dice);
            case 2:
                return numbersScore(3, dice);
            case 3:
                return numbersScore(4, dice);
            case 4:
                return numbersScore(5, dice);
            case 5:
                return numbersScore(6, dice);
            case 6:
                return 0;
            case 7:
                return 0;
            case 8:
                return pairScore(dice);
            case 9:
                return twopairsScore(dice);
            case 10:
                return threeofakindScore(dice);
            case 11:
                return fourofakindScore(dice);
            case 12:
                return smallstraightScore(dice);
            case 13:
                return largestraightScore(dice);
            case 14:
                return fullhouseScore(dice);
            case 15:
                return chanceScore(dice);
            case 16:
                return yahtzeeScore(dice);
       
        }
        return score;
    }
    
    /**
     * Ensimmäisten kuuden rivin pisteytys.
     * 
     * @param number rivin numero
     * @param dice nopat
     * @return 
     */
    private static int numbersScore(int number, int[] dice)
    {
        int sum = 0;
        for (int i = 0; i < 5; i++){
            if (dice[i] == number)
            {
                sum += number;
            }
        }
        return sum;
    }
    
    private static int pairScore(int[] dice)
    {
        int prev = 0;
        for (int i = 4; i >= 0; i--)
        {
            if (prev == dice[i])
            {
                return dice[i]*2;
            }
            prev = dice[i];
        }
        
        return 0;
    }
    
    private static int twopairsScore(int[] dice)
    {
        int prev = 0;
        for (int i = 4; i >= 0; i--)
        {
            if (prev == dice[i])
            {
                int pair = dice[i]*2;
                if (i > 1 && dice[i-1] != dice[i] && dice[i-1] == dice[i-2])
                {
                    return pair + dice[i-1] * 2;
                } else if (i > 2 && dice[i] != dice[i-2] && dice[i-2] == dice[i-3])
                {
                    return pair + dice[i-2] * 2;
                }
            }
            prev = dice[i];
        }
        
        return 0;
    }
    
    private static int threeofakindScore(int[] dice)
    {
        if (dice[2] == dice[4])
        {
            return dice[2]*3;
        } else if (dice[1] == dice[3])
        {
            return dice[1]*3;
        } else if (dice[0] == dice[2])
        {
            return dice[0]*3;
        }
        
        return 0;
    }
    
    private static int fourofakindScore(int[] dice)
    {
        if (dice[1] == dice[4])
        {
            return dice[1]*4;
        } else if (dice[0] == dice[3])
        {
            return dice[0]*4;
        }
        
        return 0;
    }
    
    private static int smallstraightScore(int[] dice)
    {
        int[] straight = {1, 2, 3, 4, 5};
        if (Arrays.equals(dice, straight))
        {
            return 15;
        } 
        return 0;
    }
    
    private static int largestraightScore(int[] dice)
    {
        int[] straight = {2, 3, 4, 5, 6};
        if (Arrays.equals(dice, straight))
        {
            return 20;
        } 
        return 0;
    }
    
    private static int fullhouseScore(int[] dice)
    {
        if ((dice[0] == dice[1] && dice[2] == dice[4]) || (dice[0] == dice[2] && dice[3] == dice[4]))
        {
            return dice[0] + dice[1] + dice[2] + dice[3] + dice[4];
        }
        
        return 0;
    }
    
    private static int chanceScore(int[] dice)
    {
        return dice[0] + dice[1] + dice[2] + dice[3] + dice[4];
    }
    
    private static int yahtzeeScore(int[] dice)
    {
        if (dice[0] == dice[4]){
            return 50;
        }
        return 0;
    }
    
    
    
}
