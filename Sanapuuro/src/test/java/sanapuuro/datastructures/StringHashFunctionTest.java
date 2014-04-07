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
public class StringHashFunctionTest {
    
    private StringHashFuncs hashFunction = new StringHashFuncs();
    
    public StringHashFunctionTest() {
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
    public void getHashReturnsSameValueForSameString() {
        String s1 = "abcde";
        String s2 = "abcde";
        int hash = this.hashFunction.getNormalHash(s1, 10);
        int hash2 = this.hashFunction.getNormalHash(s2, 10);
        assertEquals(hash, hash2);
    }
}
