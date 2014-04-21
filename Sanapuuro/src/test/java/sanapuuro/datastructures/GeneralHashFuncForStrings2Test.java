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
public class GeneralHashFuncForStrings2Test {

    private GeneralHashFuncForStrings2 func = new GeneralHashFuncForStrings2();

    public GeneralHashFuncForStrings2Test() {
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
    public void calculatesMValueByFindingAPrimeNumberNotCloseToAPowerOfTwo() {
        int m = this.func.calculateM(10, 0.33);
        // 29 is further from a power of two than 31
        assertEquals(29, m);
        m = this.func.calculateM(5, 1);
        // primes 5 and 7 considered, 5 is picked since 5-2^2 = 2^3-7
        assertEquals(5, m);
    }
    
    @Test
    public void calculatesMValueByFindingAPrimeNumberNotCloseToAPowerOfTwoWhenOnlyOneKey() {
        int m = this.func.calculateM(1, 0.33);
        assertEquals(3, m);
    }
    
    @Test
    public void calculatesCorrectHashForAShortStringWithoutNumberOfTriesAccountedFor() {
        // m = 29
        int m = this.func.calculateM(10, 0.33);
        String testString = "abc"; // ascii values: a = 97, b = 98, c = 99
        int hash = this.func.getNormalHash(testString, m);
        // hash should be (128^0*97 +128^1*98 + 128^2*99) % m = 14
        assertEquals(14, hash);
    }
    
    @Test
    public void calculatesCorrectHashForAMediumStringWithoutNumberOfTriesAccountedFor() {
        // m = 300007
        int m = this.func.calculateM(100000, 0.33333);
        String testString = "abcdefghij";
        int hash = this.func.getNormalHash(testString, m);
        assertEquals(268900, hash);
    }
    
    @Test
    public void calculatesCorrectHashForAMediumStringWithNumberOfTriesAccountedFor() {
        // m = 300007
        int m = this.func.calculateM(100000, 0.33333);
        String testString = "abcdefghij";
        int hash = this.func.getHash(testString, m, 0);
        assertEquals(268900, hash);
        hash = this.func.getHash(testString, m, 1);
        assertEquals(268901, hash);
        hash = this.func.getHash(testString, m, 100);
        assertEquals(269000, hash);
    }
}
