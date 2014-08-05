/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smartyahtzee.AI;

import smartyahtzee.Player;
import smartyahtzee.scoring.Scores;

/**
 *
 * @author essalmen
 */
public class Bot extends Player {
    
    

    
    @Override
    protected void rollDice()
    {
        dice.throwDice();
        System.out.println(dice);
        
    }
    
    @Override
    protected void markScore(){
        int highestIndex = 0;
        int highestScore = 0;

        for (int i = 0; i < 17; i++)
        {
            if (markedColumns[i])
            {
                continue;
            }
            int score = Scores.calculateScore(i, dice.asArray());
            if (score > highestScore)
            {
                highestIndex = i;
                highestScore = score;
            }
        }
        
        if (highestScore == 0)
        {
            markZero();
        } else {
            setScore(highestIndex, highestScore);
        }
        
    }
    
    private void markZero()
    {
        double lowest = 0;
        int lowestIndex = 0;
        
        for (int i = 0; i < 17; i++)
        {
            if (markedColumns[i])
            {
                continue;
            }
            double expectedValue = Scores.maxScores[i] * Scores.expectedValues[i];
            if (lowest > expectedValue)
            {
                lowestIndex = i;
                lowest = expectedValue;
            }
        }
        
        setScore(lowestIndex, 0);
    }
    
}
