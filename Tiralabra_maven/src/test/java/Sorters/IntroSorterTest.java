/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Sorters;

import java.util.Arrays;
import static junit.framework.Assert.assertTrue;
import junit.framework.TestCase;

/**
 *
 * @author Admin
 */
public class IntroSorterTest extends TestCase {
    IntroSorter introsort;
    CommonMethods cmethods;
    
    public IntroSorterTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        introsort = new IntroSorter();
        cmethods = new CommonMethods();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testMedianOfThree() {
        int[] taulukko1 = new int[]{1,2,3};
        int[] taulukko2 = new int[]{2,4,3};
        int[] taulukko3 = new int[]{2,1,3};
        int[] taulukko4 = new int[]{3,1,2};
        int[] taulukko5 = new int[]{3,2,1};
        assertEquals(2,introsort.medianOfThree(taulukko1, 0, 1, 2));
        assertEquals(3,introsort.medianOfThree(taulukko2, 0, 1, 2));
        assertEquals(2,introsort.medianOfThree(taulukko3, 0, 1, 2));
        assertEquals(2,introsort.medianOfThree(taulukko4, 0, 1, 2));
        assertEquals(2,introsort.medianOfThree(taulukko5, 0, 1, 2));
    }
    
    public void testInsertionSort(){
        for (int i = 0; i < 10; i++) {
            int[] arrayToSort = cmethods.giveRandomArray();
            int[] sortedArray = arrayToSort.clone();
            Arrays.sort(sortedArray);
            introsort.intro_InsertionSort(arrayToSort, 0, arrayToSort.length);
            for (int j = 0; j < arrayToSort.length; j++) {
                System.out.print("[" + arrayToSort[j] + "], ");
            }
            assertTrue(cmethods.checkThatArraysAreTheSame(arrayToSort, sortedArray));
        }
    }
    
    public void testHeapSort(){
        for (int i = 0; i < 10; i++) {
            int[] arrayToSort = cmethods.giveRandomArray();
            int[] sortedArray = arrayToSort.clone();
            Arrays.sort(sortedArray);
            introsort.intro_HeapSort(arrayToSort, 0, arrayToSort.length);
            for (int j = 0; j < arrayToSort.length; j++) {
                System.out.print("[" + arrayToSort[j] + "], ");
            }
            assertTrue(cmethods.checkThatArraysAreTheSame(arrayToSort, sortedArray));
        }
    }
    
    public void testIntroSort(){
        for (int i = 0; i < 10; i++) {
            int[] arrayToSort = cmethods.giveRandomArray();
            int[] sortedArray = arrayToSort.clone();
            Arrays.sort(sortedArray);
            introsort.introSort(arrayToSort);
            for (int j = 0; j < arrayToSort.length; j++) {
                System.out.print("[" + arrayToSort[j] + "], ");
            }
            assertTrue(cmethods.checkThatArraysAreTheSame(arrayToSort, sortedArray));
        }
    }
    
}
