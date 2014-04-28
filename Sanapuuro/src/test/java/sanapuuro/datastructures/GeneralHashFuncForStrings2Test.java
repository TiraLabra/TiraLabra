/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.datastructures;

import sanapuuro.hashfunctions.GeneralHashFuncForStrings2;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sanapuuro.hashfunctions.HashFunction;

/**
 *
 * @author skaipio
 */
public class GeneralHashFuncForStrings2Test {

    private final GeneralHashFuncForStrings2 func = new GeneralHashFuncForStrings2();

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

//    @Test
//    public void calculatesMValueByFindingAPrimeNumberNotCloseToAPowerOfTwo() {
//        int m = this.func.calculateM(10, 0.33);
//        // 29 is further from a power of two than 31
//        assertEquals(29, m);
//        m = this.func.calculateM(5, 1);
//        // primes 5 and 7 considered, 5 is picked since 5-2^2 = 2^3-7
//        assertEquals(5, m);
//    }
    
//    @Test
//    public void calculatesMValueByFindingAPrimeNumberNotCloseToAPowerOfTwoWhenOnlyOneKey() {
//        int m = this.func.calculateM(1, 0.33);
//        assertEquals(3, m);
//    }
    
    @Test
    public void calculatesCorrectHashForAShortStringWithoutNumberOfTriesAccountedFor() {
        // m = 29
        // int m = this.func.calculateM(10, 0.33);
        String testString = "abc"; // ascii values: a = 97, b = 98, c = 99
        int hash = this.func.getHash(testString);
        // hash should be 128^0*97 +128^1*98 + 128^2*99 = 1634657
        assertEquals(1634657, hash);
    }
    
    @Test
    public void calculatesCorrectHashForAShortStringWithNumberOfTriesAccountedFor() {
        // m = 300007
        // int m = this.func.calculateM(100000, 0.33333);
        String testString = "abc";
        int hash = this.func.getHash(testString,0);
        assertEquals(1634657, hash);
        hash = this.func.getHash(testString, 1);
        assertEquals(1634658, hash);
        hash = this.func.getHash(testString, 100);
        assertEquals(1634757, hash);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void negativeTryNumberThrowsAnException3() {
        this.func.getHash("abc", -100);
    }
}
