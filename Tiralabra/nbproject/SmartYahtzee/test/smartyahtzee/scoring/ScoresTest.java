/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smartyahtzee.scoring;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Essi
 */
public class ScoresTest {
    
    public ScoresTest() {
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
     * Test of calculateBestScore method, of class Scores.
     */
    @Test
    public void testCalculateBestScore() {
        System.out.println("calculateBestScore");
        int[] dice = null;
        boolean[] marked = null;
        double expResult = 0.0;
        double result = Scores.calculateBestScore(dice, marked);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of calculateScore method, of class Scores.
     */
    @Test
    public void testCalculateScore() {
        System.out.println("calculateScore");
        int index = 14;
        int[] dice = {5, 5, 5, 2, 2};
        int expResult = 5*3 + 2*2;
        int result = Scores.calculateScore(index, dice);
        assertEquals(expResult, result);
    }
    
}
