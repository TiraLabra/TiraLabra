/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tiralabra.Utilities;

import MultiByteEntities.MultiByte;
import Utilities.ArrayUtilities;
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
public class ArrayUtilitiesTest {
    
    Random random;
    
    public ArrayUtilitiesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        random = new Random(2719);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testMultiByteArrayContraction(){
        MultiByte[] array = new MultiByte[90];
        MultiByte[] contractedArray = ArrayUtilities.contractMultiByteArray(array, 10);
        assertEquals("Contracted array wrong size", 10, contractedArray.length);
    }
    
    @Test
    public void testCopyingIntoArray(){
        byte[] data = new byte[]{new Byte("2"), new Byte("4"), new Byte("9")};
        byte[] dataArray = new byte[8];
        ArrayUtilities.encodeIntoArray(data, dataArray, 0);
        for (int i = 0; i < dataArray.length; i++) {
            if (i==data.length){
                break;
            }
            assertEquals("Differing data found in array", data[i], dataArray[i]);
        }
    }
    
    @Test
    public void testArrayExpansion(){
        byte[] array = new byte[10];
        random.nextBytes(array);
        byte[] expandedArray = ArrayUtilities.expandByteArray(array);
        assertEquals("Original array not expanded to twice its size", array.length*2, expandedArray.length);
        for (int i = 0; i < array.length; i++) {
            assertEquals("Some other byte found in expanded array", array[i], expandedArray[i]);
        }
    }
    
    @Test
    public void testMultiByteArrayExpansion(){
        MultiByte[] mbArray = new MultiByte[19];
        MultiByte[] expandedMbArray = ArrayUtilities.expandMultiByteArray(mbArray);
        assertEquals("Original array not expanded to twice its size", mbArray.length*2, expandedMbArray.length);
    }
    
    @Test
    public void testMakingMultiByte(){
        byte[] dataArray = new byte[]{new Byte("2"), new Byte("3"), new Byte("4")};
        MultiByte mb = ArrayUtilities.makeMultiByte(0, dataArray, 3);
        byte[] multiByteData = mb.getBytes();
        for (int i = 0; i < multiByteData.length; i++) {
            assertEquals("Sometihng else found in multibytes' data", dataArray[i], multiByteData[i]);
        }
    }
    
    @Test
    public void testRemovingZeros(){
        byte[] encodedData = new byte[80];
        random.nextBytes(encodedData);
        byte[] arrayWithLeadingZerosRemovedAtIndex = ArrayUtilities.removeTrailingZeros(encodedData, 8);
        assertEquals("Data array has not its trailing zeros removed", 8, arrayWithLeadingZerosRemovedAtIndex.length);
        
        for (int i = 0; i < arrayWithLeadingZerosRemovedAtIndex.length; i++) {
            assertEquals("Some other byte found in array with leading zeros removed", encodedData[i], arrayWithLeadingZerosRemovedAtIndex[i]);
        }
    }
    
}