/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tiralabra.HashedByteDictionary;

import Dictionary.HashedByteDictionary;
import MultiByteEntities.MultiByte;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author virta
 */
public class HashedDictionaryTest {

    HashedByteDictionary dictionary;
    byte[] randomData;

    public HashedDictionaryTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.randomData = new byte[2000000];
        Random randomByteGenerator = new Random(2719); //seed is prime number for maximum randomness.
        randomByteGenerator.nextBytes(randomData);
    }

    @After
    public void tearDown() {
    }

     @Test
     public void loadTest() {
         for (int i = 2; i < 4; i++) {
             
             testWidth(i);
             
         }
     }
     
     private void testWidth(int width){
         dictionary = new HashedByteDictionary();
         for (int i = 0; i < randomData.length; i+=(width)) {
             if (i+width < randomData.length) {
                 MultiByte multiByte = new MultiByte(width);
                 for (int j = 0; j < width; j++) {
                     multiByte.addData(randomData[i + j]);
                 }
                 
                 if (!dictionary.put(multiByte)) {
                     
                 }
             }
             
         }
     }
}