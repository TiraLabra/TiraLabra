/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sequencealgorithms;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author riha
 */
public class ScoringMatrixTest {

    ScoringMatrix s;
    char[] alphabet = {'a', 't', 'c', 'g'};

    public ScoringMatrixTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        s = new ScoringMatrix(alphabet);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void setUpScoringMatrix() {
        for (char c : alphabet) {
            for (char g : alphabet) {
                assertEquals(0.0, s.getScore(c, g), 0.001);
            }
        }
    }
    
    @Test
    public void setIndelPenaltyTest() {
        s.setIndelPenalty(-1);
        assertEquals(-1, s.getScore('a', '-'), 0.00001);
        assertEquals(-1, s.getScore('-', 'g'), 0.00001);
    }
    
    @Test
    public void setMatchBonusTest() {
        s.setMatchBonus(5);
        for (char c : alphabet) {
            assertEquals(5.0, s.getScore(c, c), 0.00001);
        }
    }
    
    @Test
    public void setMismatchPenaltyTest() {
        s.setMismatchPenalty(-0.75);
        assertEquals(-0.75, s.getScore('a', 't'), 0.00001);
        assertEquals(-0.75, s.getScore('g', 'c'), 0.00001);
    }
    
}
