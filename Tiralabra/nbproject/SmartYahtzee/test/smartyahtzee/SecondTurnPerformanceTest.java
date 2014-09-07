/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smartyahtzee;

import java.util.Random;
import org.junit.Test;
import smartyahtzee.AI.TreeBuilder;

/**
 *
 * @author Essi
 */
public class SecondTurnPerformanceTest {
    
    private TreeBuilder treebuilder;
    private int[] dice;
    private boolean[] marked;
    private Random random;
    
    public SecondTurnPerformanceTest()          //plays first throw
    {
        this.random = new Random();
        this.marked = new boolean[17];
        this.dice = new int[5];
        
        
        
    }
    
    /**
     * Test performance time when getting EV from pre-made trees.
     */
    
    @Test
    public void testPerformanceSecondLock()
    {
        int sum = 0;
        for (int i = 0; i < 1000; i ++)
        {
            for (int j = 0; j < 5; j++)
            {
                dice[j] = random.nextInt(6)+1;
            }
            this.treebuilder = new TreeBuilder(dice, marked);
            
            int[] lock = treebuilder.getDiceToLock();
            System.out.println("2nd turn performance");
            long time = System.currentTimeMillis();
            treebuilder.getSecondTurnDiceToLock(dice, lock);
            long timeAfter = System.currentTimeMillis();
            System.out.println(timeAfter - time +" milliseconds");
            sum += timeAfter-time;
        }
        
        System.out.println("Average second lock time: "+sum/1000.0+" ms");
        
    }
    
}
