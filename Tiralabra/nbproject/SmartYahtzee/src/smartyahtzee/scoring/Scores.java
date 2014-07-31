/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smartyahtzee.scoring;

import java.util.ArrayList;
import smartyahtzee.DiceSet;
import smartyahtzee.Die;

/**
 *
 * @author Essi
 */
public class Scores {
    
    public static final String[] scoreDescriptions = {
        "Ones: ", "Twos: ", "Threes: ", "Fours: ", "Fives: ",
        "Sixes: ", "Sum: ", "Bonus: ", "Pair: ", "Two pairs: ",
        "Three of a kind: ", "Four of a kind: ", "Small straight: ",
        "Large straight: ", "Full house: ", "Chance: ", "Yahtzee: ",
        "Total: "
    };
    
    
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
        return 0;
    }
    
    private static int twopairsScore(int[] dice)
    {
        return 0;
    }
    
    private static int threeofakindScore(int[] dice)
    {
        return 0;
    }
    
    private static int fourofakindScore(int[] dice)
    {
        return 0;
    }
    
    private static int smallstraightScore(int[] dice)
    {
        return 0;
    }
    
    private static int largestraightScore(int[] dice)
    {
        return 0;
    }
    
    private static int fullhouseScore(int[] dice)
    {
        return 0;
    }
    
    private static int chanceScore(int[] dice)
    {
        return 0;
    }
    
    private static int yahtzeeScore(int[] dice)
    {
        return 0;
    }
    
    
    
}
