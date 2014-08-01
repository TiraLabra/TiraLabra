/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smartyahtzee;

import java.util.Scanner;
import smartyahtzee.scoring.Scores;

/**
 *
 * @author essalmen
 */
public class Human extends Player {
    
    Scanner scanner;
    
    
    public Human()
    {
        scanner = new Scanner(System.in);
        
        
    }

    
    
    @Override
    protected void rollDice()
    {
        
        dice.unlockAll();
        dice.throwDice();
        
        for (int throwsLeft = 2; throwsLeft > 0; throwsLeft--)
        {
            //printScoreboardWithScores();
            
            String command = "";
            while (!command.equals("t") && !command.equals("m"))
            {
                int parsedNumber = parseNumber(command);
                
                if (parsedNumber != 0)
                {
                    dice.toggleLock(parsedNumber-1);    //indexing starts from zero
                }
                
                System.out.println(dice);               //print dice with locked dice in parentheses
                
                command = scanner.nextLine();
            }
            
            if (command.equals("m"))
            {
                return;
            }
            
            dice.throwDice();
        }
        
    }
    
    @Override
    protected void markScore()
    {
        
        System.out.println(dice);
        //printScoreBoard();
        int parsedNumber = 0;
        while (parsedNumber < 1 || parsedNumber > 17 || parsedNumber == 7 || parsedNumber == 8 || marked(parsedNumber-1))      //TODO: error message when trying to mark occupied rows
        {
            parsedNumber = scanner.nextInt();
        }
        
        setScore(parsedNumber-1, Scores.calculateScore(parsedNumber-1, dice.asArray()));    //indexing again
            
    }
    
    private int parseNumber(String command)
    {
        int number;
        try {
            number = Integer.parseInt(command);
        } catch (NumberFormatException e) {
            return 0;
        }
        
        if (number < 6 && number > 0)
        {
            return number;
        }
        
        return 0;
      
    }

   
    
}
