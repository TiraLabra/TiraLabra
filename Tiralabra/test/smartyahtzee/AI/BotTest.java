/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smartyahtzee.AI;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author iorena
 */
public class BotTest {
    
    int[] scores;
    Bot bot;
    boolean[] marked;
    
    public BotTest() {
        bot = new Bot();
        scores = bot.getScores();
        marked = bot.getMarked();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    /**
     * Test of markScore method, of class Bot.
     * Tests that one column is marked every time markScore is called.
     */
    @Test
    public void testRolDiceAndMarkScore() {
        System.out.println("markScore");
        int columnsMarked = getMarkedColumns();
        for (int i = 0; i < 15; i++)
        {
            bot.rollDice();
            bot.markScore();
            int columnsMarkedNow = getMarkedColumns();
            assertTrue(columnsMarkedNow == columnsMarked +1);
            columnsMarked = columnsMarkedNow;
        }
        

    }
    
    private int getMarkedColumns()
    {
        int sum = 0;
        for (int i = 0; i < 17; i++)
        {
            if (marked[i] && i != 6 && i != 7)      //not counting sum and bonus
            {
                sum++;
            }
        }
        return sum;
    }
    
        

}
