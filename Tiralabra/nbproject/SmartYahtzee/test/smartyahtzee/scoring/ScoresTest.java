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
//    @Test
//    public void testCalculateBestScore() {
//        System.out.println("calculateBestScore");
//        int[] dice = {6, 6, 4, 4, 1};
//        boolean[] marked = new boolean[17];
//        marked[15] = true;      //not counting chance or pair
//        marked[8] = true;
//        double expResult = (20/22) * 3.78259;
//        double result = Scores.calculateBestScore(dice, marked);
//        assertEquals(expResult, result, 0.1);
//    }

    /**
     * Test of calculateScore method, of class Scores.
     */
    @Test
    public void testSmallStraightScore() {
        System.out.println("calculateScore");
        int index = 12;
        int[] dice = {5, 4, 3, 2, 1};
        int expResult = 15;
        int result = Scores.calculateScore(index, dice);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testLargeStraightScore() {
        System.out.println("calculateScore");
        int index = 13;
        int[] dice = {6, 5, 4, 3, 2};
        int expResult = 20;
        int result = Scores.calculateScore(index, dice);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testTwoPairsScore()
    {
        System.out.println("twoPairsScore");
        int index = 9;
        int[] dice = {6, 6, 4, 4, 1};
        int expResult = 6*2 + 4*2;
        int result = Scores.calculateScore(index, dice);
        assertEquals(expResult, result);
    }
    
    
    @Test
    public void testFullHouseScore()
    {
        System.out.println("fullHouseScore");
        int index = 14;
        int[] dice = {6, 6, 4, 4, 4};
        int expResult = 6*2 + 4*3;
        int result = Scores.calculateScore(index, dice);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testTwoPairsScore2()                //testaa että viisi samaa ei kelpaa täyskädeksi
    {
        System.out.println("fullHouseScore2");
        int index = 14;
        int[] dice = {4, 4, 4, 4, 4};
        int expResult = 6*2 + 4*2;
        int result = Scores.calculateScore(index, dice);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testPairScore()
    {
        System.out.println("twoPairsScore");
        int index = 8;
        int[] dice = {6, 6, 4, 4, 1};
        int expResult = 6*2;
        int result = Scores.calculateScore(index, dice);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testThreeOfAKindScore()
    {
        System.out.println("twoPairsScore");
        int index = 10;
        int[] dice = {6, 6, 6, 4, 1};
        int expResult = 6*3;
        int result = Scores.calculateScore(index, dice);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testFourOfAKindScore()
    {
        System.out.println("twoPairsScore");
        int index = 11;
        int[] dice = {4, 4, 4, 4, 1};
        int expResult = 4*4;
        int result = Scores.calculateScore(index, dice);
        assertEquals(expResult, result);
    }
    
}
