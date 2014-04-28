/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.hashfunctions;

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
public class JavaHashForStringsTest {
    private JavaHashForStrings javaHash = new JavaHashForStrings();
    private GeneralHashFuncForStrings tiraHash = new GeneralHashFuncForStrings();
    private GeneralHashFuncForStrings2 tiraHashWithBigs = new GeneralHashFuncForStrings2();
    
    public JavaHashForStringsTest() {
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
     public void testHashValues() {
         String s = "abcdefghij";
         int hash = javaHash.getHash(s);
         System.out.println(hash);
         hash = tiraHash.getHash(s);
         System.out.println(hash);
         hash = tiraHashWithBigs.getHash(s);
         System.out.println(hash);
     }
}
