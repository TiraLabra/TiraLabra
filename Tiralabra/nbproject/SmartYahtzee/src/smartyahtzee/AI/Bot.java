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
        TreeBuilder decisions = new TreeBuilder(dice.asArray(), this.markedColumns);
        int[] lock = decisions.getDiceToLock();
        if (lock != null)
        {
            dice.lockMany(lock);
        } else if (lock != null && lock.length == 5) {
            return;
        }
        System.out.println(dice);
        dice.throwDice();       
        dice.unlockAll();
        
        if (lock != null)
        {
            int[] secondLock = decisions.getSecondTurnDiceToLock(dice.asArray(), lock);
            if (secondLock != null && secondLock.length == 5)
            {
                return;
            } else if (secondLock != null) {
                dice.lockMany(secondLock);
                
            }
        
            System.out.println(dice);
            dice.throwDice();
            
        } else {
            decisions = new TreeBuilder(dice.asArray(), this.markedColumns);
            lock = decisions.getDiceToLock();
            if (lock != null)
            {
                dice.lockMany(lock);
            } else if (lock != null && lock.length == 5) {
                return;
            }
            System.out.println(dice);
            dice.throwDice();
        }
        
        
        
        System.out.println(dice);
        dice.unlockAll();
        
        
    }
    
    @Override
    protected void markScore(){         //todo: refactor
        int highestIndex = 0;
        int highestScore = 0;

        for (int i = 0; i < 17; i++)
        {
            if (markedColumns[i] || i == 7 || i == 6)
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
        double lowest = 200;
        int lowestIndex = 0;
        
        for (int i = 0; i < 17; i++)
        {
            if (markedColumns[i] || i == 6 || i == 7)
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