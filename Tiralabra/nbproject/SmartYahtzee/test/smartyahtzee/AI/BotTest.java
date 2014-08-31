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
    
    public BotTest() {
        Bot bot = new Bot();
        scores = bot.getScores();
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
     * Test of rollDice method, of class Bot.
     */
    @Test
    public void testRollDice() {
        System.out.println("rollDice");
        Bot instance = new Bot();
        instance.rollDice();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of markScore method, of class Bot.
     */
    @Test
    public void testMarkScore() {
        System.out.println("markScore");
        Bot instance = new Bot();
        instance.markScore();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
        
    @Test
    public void testIntelligentChoices1()
    {
        
    }
    
    @Test
    public void testIntelligentChoices2()
    {
        
    }
    
    @Test
    public void testIntelligentChoices3()
    {
        
    }
    
    @Test
    public void testIntelligentChoices4()
    {
        
    }
    
    @Test
    public void testIntelligentChoices5()
    {
        
    }
    
    @Test
    public void testIntelligentChoices6()
    {
        
    }

}
