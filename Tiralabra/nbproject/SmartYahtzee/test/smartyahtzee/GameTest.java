/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smartyahtzee;

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
public class GameTest {
    
    public GameTest() {
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
     * Test of runTests method, of class Game.
     */
//    @Test
//    public void testRunTests() {
//        System.out.println("runTests");
//        Game instance = null;
//        instance.runTests();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of runGame method, of class Game.
     */
    @Test
    public void testRunGameOneBot() {
        System.out.println("runGame");
        Game instance = new Game(0, 1);
        instance.runGame();
        for (boolean b : instance.getPlayers()[0].markedColumns)
        {
            assertTrue(b);
        }
    }
    
   
    
}
