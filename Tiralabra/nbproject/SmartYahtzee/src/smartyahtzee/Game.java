/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smartyahtzee;

import smartyahtzee.AI.Bot;
import java.util.ArrayList;
import smartyahtzee.scoring.Scores;

/**
 *
 * @author essalmen
 */
public class Game {
    
    private final int rows = 17;
    private ArrayList<Player> players;
    
    
    /**
     * Konstruktori.
     * 
     * Luo pelaajat.
     */
    
    public Game(int humans, int bots) {
        
        players = new ArrayList<Player>();
        
        for (int i = 0; i<humans; i++) {
            Human humanPlayer = new Human();
            players.add(humanPlayer);
        }
        
        for (int i = 0; i<bots; i++)
        {
            Bot botPlayer = new Bot();
            players.add(botPlayer);
        }
        
    }
    
    /**
     * Simulaatio pistekeskiarvojen laskemiseksi.
     * 
     */
    
    public void runTests()
    {
        int iterations = 1000000;
        DiceSet dice = new DiceSet();
        int[] totalScores = new int[17];
        for (int i = 0; i<iterations; i++)
        {
            dice.throwDice();
            int[] diceArray = dice.asArray();
            for (int j = 0; j<17; j++)
            {
                totalScores[j] += Scores.calculateScore(j, diceArray);
            }
        }
        
        for (int i = 0; i < 17; i++)
        {
            System.out.print(" " + (double) totalScores[i]/iterations);
        }
        
    }
    
    
    /**
     * Pyörittää peliä.
     * 
     * Piirtää pistetaulukon jokaisen vuoron päätteeksi.
     */
    
    
    
    
    public void runGame()
    {
        for (int i = 0; i < 16; i++)
        {
            for (Player player : players)
            {
                drawScoreboard();
                player.playTurn();
            }
            System.out.println("---");
        }
    }
    
    /**
     * Piirtää pistetaulukon.
     */
    
    private void drawScoreboard()
    {
        for (int i = 0; i < rows; i++)
        {
            System.out.print(i+1 + " | " + Scores.scoreDescriptions[i]);
            for (Player player : players)
            {
                System.out.print(" | " + player.getScore(i));
            }
            System.out.println("");
            
        }
        System.out.print("   | Total:           | ");
        for (Player player : players)
        {
            System.out.print(player.totalPoints());
        }
        System.out.println("");
    }

   
    
    
    
}
