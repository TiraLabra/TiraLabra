/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smartyahtzee;

import org.junit.Test;
import smartyahtzee.AI.DecisionTree;

/**
 *
 * @author Essi
 */
public class PerformanceTest {
    
    
    /**
     * Test performance time when creating and getting EV from trees, depending on the
     * root size.
     */
    
    @Test
    public void testPerformanceRootOfOne()
    {
        System.out.println("Performance, root size 1");
        int[] root = { 6 };
        long time = System.currentTimeMillis();
        DecisionTree instance = new DecisionTree(root, new boolean[17]);
        instance.getEV();
        long timeAfter = System.currentTimeMillis();
        System.out.println(timeAfter-time + " milliseconds");
        
    }
    
    @Test
    public void testPerformanceRootOfTwo()
    {
        System.out.println("Performance, root size 2");
        int[] root = { 6, 5 };
        long time = System.currentTimeMillis();
        DecisionTree instance = new DecisionTree(root, new boolean[17]);
        instance.getEV();
        long timeAfter = System.currentTimeMillis();
        System.out.println(timeAfter-time + " milliseconds");
        
    }
    
    @Test
    public void testPerformanceRootOfThree()
    {
        System.out.println("Performance, root size 3");
        int[] root = { 6, 6, 5 };
        long time = System.currentTimeMillis();
        DecisionTree instance = new DecisionTree(root, new boolean[17]);
        instance.getEV();
        long timeAfter = System.currentTimeMillis();
        System.out.println(timeAfter-time + " milliseconds");
        
    }
    
    @Test
    public void testPerformanceRootOfFour()
    {
        System.out.println("Performance, root size 4");
        int[] root = { 6, 6, 5, 5 };
        long time = System.currentTimeMillis();
        DecisionTree instance = new DecisionTree(root, new boolean[17]);
        instance.getEV();
        long timeAfter = System.currentTimeMillis();
        System.out.println(timeAfter-time + " milliseconds");
        
    }
    
    @Test
    public void testPerformanceRootOfOneScoresHalfFilled()
    {
        System.out.println("Performance, half filled, root size 1");
        int[] root = { 6 };
        boolean[] marked = new boolean[17];
        for (int i = 0; i < 17; i++)
        {
            if (i % 2 == 0)
            {
                marked[i] = true;
            }
        }
        long time = System.currentTimeMillis();
        DecisionTree instance = new DecisionTree(root, marked);
        
        instance.getEV();
        long timeAfter = System.currentTimeMillis();
        System.out.println(timeAfter-time + " milliseconds");
        
    }
    
    @Test
    public void testPerformanceRootOfTwoScoresHalfFilled()
    {
        System.out.println("Performance, half filled, root size 2");
        int[] root = { 6, 5 };
        boolean[] marked = new boolean[17];
        for (int i = 0; i < 17; i++)
        {
            if (i % 2 == 0)
            {
                marked[i] = true;
            }
        }
        long time = System.currentTimeMillis();
        DecisionTree instance = new DecisionTree(root, marked);
        
        instance.getEV();
        long timeAfter = System.currentTimeMillis();
        System.out.println(timeAfter-time + " milliseconds");
        
    }
    
    @Test
    public void testPerformanceRootOfThreeScoresHalfFilled()
    {
        System.out.println("Performance, half filled, root size 3");
        int[] root = { 6, 6, 5 };
        boolean[] marked = new boolean[17];
        for (int i = 0; i < 17; i++)
        {
            if (i % 2 == 0)
            {
                marked[i] = true;
            }
        }
        long time = System.currentTimeMillis();
        DecisionTree instance = new DecisionTree(root, marked);
        
        instance.getEV();
        long timeAfter = System.currentTimeMillis();
        System.out.println(timeAfter-time + " milliseconds");
        
    }
    
    @Test
    public void testPerformanceRootOfFourScoresHalfFilled()
    {
        System.out.println("Performance, half filled, root size 4");
        int[] root = { 6, 6, 5, 5 };
        boolean[] marked = new boolean[17];
        for (int i = 0; i < 17; i++)
        {
            if (i % 2 == 0)
            {
                marked[i] = true;
            }
        }
        long time = System.currentTimeMillis();
        DecisionTree instance = new DecisionTree(root, marked);
        
        instance.getEV();
        long timeAfter = System.currentTimeMillis();
        System.out.println(timeAfter-time + " milliseconds");
        
    }
    
    
}
