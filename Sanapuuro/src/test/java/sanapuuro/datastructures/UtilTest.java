/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.datastructures;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author skaipio
 */
public class UtilTest {

    public UtilTest() {
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
    public void picksNumberThatIsFarthestFromPowerOfTwo(){
        assertEquals(20, Util.pickNumberThatIsFarthestFromPowerOfTwo(new int[]{5,10,20}));
    }
    
    @Test
    public void findsClosestPowerOfTwoToANumber(){
        assertEquals(4, Util.findClosestPowerOfTwoTo(5));
        assertEquals(16, Util.findClosestPowerOfTwoTo(14));
    }

    @Test
    public void findsPrimesCloseToAGivenNumber() {
        int[] twoPrimes = Util.findPrimesCloseTo(360);
        assertEquals(359, twoPrimes[0]);
        assertEquals(367, twoPrimes[1]);
    }
    
    @Test
    public void detectsPrimesCorrectly() {
        assertFalse(Util.isPrime(361));
    }
}
