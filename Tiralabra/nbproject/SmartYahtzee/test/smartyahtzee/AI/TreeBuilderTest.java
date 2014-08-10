/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smartyahtzee.AI;

import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author essalmen
 */
public class TreeBuilderTest {
    
    public TreeBuilderTest() {
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

    @Test
    public void testGroupingSort() {
        int[] dice = {1, 6, 3, 6, 3};
        TreeBuilder builder = new TreeBuilder(dice);
        int[] groupedDice = {6, 6, 3, 3, 1};
        assertTrue(Arrays.equals(builder.getDice(), groupedDice));
    }
    
}
