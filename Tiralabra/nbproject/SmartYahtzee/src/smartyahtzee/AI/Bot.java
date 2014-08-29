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
    protected void rollDice()    //todo: refactor
    {
        dice.throwDice();
        System.out.println(dice);
        TreeBuilder decisions = new TreeBuilder(dice.asArray(), this.markedColumns);
        int[] lock = decisions.getDiceToLock();
        if (lock != null && lock.length == 5)
        {
            return;
            
        } else if (lock != null) {
            
            dice.lockMany(lock);
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
        
        
        
    }
    
    /**
     * 
     */
    
    @Override
    protected void markScore() {         //todo: refactor
        dice.unlockAll();
        
        int highestIndex = 0;
        int highestScore = 0;

        for (int i = 0; i < 17; i++)
        {
            if (i == 15 && !Scores.onlyFreeColumn(i, markedColumns))        //not marking chance unless only available column
            {
                continue;
            }
            if (markedColumns[i] || i == 7 || i == 6)
            {
                continue;
            }
            int score = Scores.calculateScore(i, dice.asArray());
            
            if (i < 6 && score > 0.7*Scores.maxScores[i])       //painotus bonuksen saamiseksi
            {
                highestIndex = i;
                highestScore = score;
                break;
            }
            
            if (score > highestScore)
            {
                highestIndex = i;
                highestScore = score;
            }
        }
        
        if (highestScore == 0 || (highestIndex < 6 && highestScore < 0.4*Scores.maxScores[highestIndex]))          //kannattaisiko merkitä nolla jossain muussakin tapauksessa?
        {
            if (!markedColumns[15])
            {
                setScore(15, Scores.calculateScore(15, dice.asArray()));
            } else {
                markZero();
            }
        } else {
            setScore(highestIndex, highestScore);
        }
        
    }
    
    /**
     * Arvioi, mihin sarakkeeseen kannattaa laittaa 0.
     *
     * 0 laitetaan vapaaseen sarakkeeseen, jonka odotusarvo on huonoin.
     */
    
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