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

    
    /**
     * Tekee noppienpitämispäätökset.
     * 
     * Heittää nopat, tutkii mitkä kannattaa pitää, ja heittää uudelleen.
     */
    
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
     * Laskee, mihin sarakkeeseen pisteet kannattaa merkitä.
     * 
     * Valitsee isoimmat pisteet antavan kentän jättäen sattuman huomiotta ellei se ole ainoa vapaa
     * kenttä ja painottaen ensimmäistä kuutta saraketta bonuksen saamiseksi.
     */
    
    @Override
    protected void markScore() {         //todo: refactor
        dice.unlockAll();
        
        int highestIndex = 0;
        double highestScore = 0;

        
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
            
            if (i < 6 && score < 0.6*Scores.maxScores[i] && !Scores.onlyFreeColumn(i, markedColumns))       //not accepting small scores in upper columns
            {
                continue;
            }
            
            if (i < 6 && score >= 0.6*Scores.maxScores[i])       //painotus bonuksen saamiseksi
            {
                highestIndex = i;
                highestScore = score;
                //System.out.println("hey! this brings me closer to bonus");
                break;
            }
            
            double weightedScore = (double)score / Scores.expectedValues[i];
            
            
            
            if (weightedScore > highestScore)
            {
                highestIndex = i;
                highestScore = weightedScore;
            }
        }

        
        
        if (highestScore == 0)          //kannattaisiko merkitä nolla jossain muussakin tapauksessa?
        {
            if (!markedColumns[15])
            {
                setScore(15, Scores.calculateScore(15, dice.asArray()));
                return;
            } else {
                int highestScor3 = 0;               //katsotaan onko ekassa kuudessa sarakkeessa huono tulos laitettavaksi
                int highestInd = 0;
                for (int i = 0; i < 6; i++)
                {
                    if (!markedColumns[i])  
                    {
                        int score = Scores.calculateScore(i, dice.asArray());
                        if (score > highestScor3)
                        {
                            highestScor3 = score;
                            highestInd = i;
                        }
                    }
                }
                if (highestScor3 != 0)
                {
                    setScore(highestInd, Scores.calculateScore(highestInd, dice.asArray()));
                    return;
                }
     
            }
            markZero();
            
        } else {
            setScore(highestIndex, Scores.calculateScore(highestIndex, dice.asArray()));
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