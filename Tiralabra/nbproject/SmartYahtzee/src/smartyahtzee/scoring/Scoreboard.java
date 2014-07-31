/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smartyahtzee.scoring;

import java.util.ArrayList;

/**
 *
 * @author essalmen
 */
public class Scoreboard {
    
    private ArrayList<Score> unusedScores;
    
    private int[] scores;
    
    public Scoreboard()
    {
        scores = new int[17];
    }
    
    public void markScore(int index, int score)
    {
        scores[index] = score;
    }
    
    public int getScore(int index)
    {
        return scores[index];
    }
    
    
    
    
}
