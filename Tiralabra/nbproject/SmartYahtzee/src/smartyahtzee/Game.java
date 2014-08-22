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
    
    public ArrayList<Player> getPlayers()
    {
        return players;
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
     * Testaa botin pistetuloksia.
     * 
     * Testaa yhteispisteet, yatzyn esiintymisen ja bonuksen esiintymisen.
     */
    
    public void testBot()
    {
        int iterations = 5000;
        int scores = 0;
        int bonuses = 0;
        int yahtzees = 0;
        int fourofakinds = 0;
        
        
        
        int[] scorez = null;
                
        for (int i = 0; i < iterations; i++)
        {
            Player bot = new Bot();
            for (int j = 0; j < 16; j++)
            {
                bot.playTurn();
                scorez = bot.getScores();
                
            }
            if (scorez[7] > 0)
            {
                bonuses++;
            }
            if (scorez[16] == 50)
            {
                yahtzees++;
            }
            if (scorez[11] > 0)
            {
                fourofakinds++;
            }
            scores += bot.totalPoints();
        }
        System.out.println(scores);
        System.out.println("Average score: " + (double)scores/(double)iterations);
        double bonuspercent = (float)bonuses/(float)iterations;
        double yahtzeepercent = (float)yahtzees/(float)iterations;
        System.out.println("Bonus percent: " + bonuspercent*100.0);
        System.out.println("Four of a kind percent: "+(float)fourofakinds/(float)iterations);
        System.out.println("Yahtzee percent: "+yahtzeepercent*100.0);
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
                if (i < 15)
                {
                    player.playTurn();
                }
                
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
