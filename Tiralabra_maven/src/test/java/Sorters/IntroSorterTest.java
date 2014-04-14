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
    
    public void testInsertionSortWithRandomArrays(){
        for (int i = 0; i < 10; i++) {
            int[] arrayToSort = cmethods.giveRandomArray();
            int[] sortedArray = arrayToSort.clone();
            Arrays.sort(sortedArray);
            introsort.intro_InsertionSort(arrayToSort, 0, arrayToSort.length);
            assertTrue(Arrays.equals(arrayToSort, sortedArray));
        }
    }
    
    public void testHeapSortWithRandomArrays(){
        for (int i = 0; i < 10; i++) {
            int[] arrayToSort = cmethods.giveRandomArray();
            int[] sortedArray = arrayToSort.clone();
            Arrays.sort(sortedArray);
            introsort.intro_HeapSort(arrayToSort, 0, arrayToSort.length);
            assertTrue(Arrays.equals(arrayToSort, sortedArray));
        }
    }
    
    public void testIntroSortWithRandomArrays(){
        for (int i = 0; i < 10; i++) {
            int[] arrayToSort = cmethods.giveRandomArray();
            int[] sortedArray = arrayToSort.clone();
            Arrays.sort(sortedArray);
            introsort.introSort(arrayToSort);
            assertTrue(Arrays.equals(arrayToSort, sortedArray));
        }
    }
    
    public void testInsertionSortWithSortedArray(){
        int[] arrayToSort = cmethods.giveArrayInOrder();
        int[] sortedArray = cmethods.giveArrayInOrder();
        introsort.intro_InsertionSort(arrayToSort, 0, arrayToSort.length);
        assertTrue(Arrays.equals(arrayToSort, sortedArray));
    }
    
    public void testInsertionSortWithReversedArray(){
        int[] arrayToSort = cmethods.giveArrayInReverseOrder();
        int[] sortedArray = cmethods.giveArrayInOrder();
        introsort.intro_InsertionSort(arrayToSort, 0, arrayToSort.length);
        assertTrue(Arrays.equals(arrayToSort, sortedArray));
    }
    
    public void testInsertionSortWithOneLengthArray(){
        int[] arrayToSort = cmethods.giveArrayOfOne();
        int[] sortedArray = cmethods.giveArrayOfOneSolution();
        introsort.intro_InsertionSort(arrayToSort, 0, arrayToSort.length);
        assertTrue(Arrays.equals(arrayToSort, sortedArray));
    }
    
    public void testInsertionSortWithNoLengthArray(){
        int[] arrayToSort = cmethods.giveArrayOfNone();
        int[] sortedArray = cmethods.giveArrayOfNoneSolution();
        introsort.intro_InsertionSort(arrayToSort, 0, arrayToSort.length);
        assertTrue(Arrays.equals(arrayToSort, sortedArray));
    }
    
    public void testInsertionSortWithAllSameNumbersArray(){
        int[] arrayToSort = cmethods.giveArrayOfAllSameNumbers();
        int[] sortedArray = cmethods.giveArrayOfAllSameNumbers();
        introsort.intro_InsertionSort(arrayToSort, 0, arrayToSort.length);
        assertTrue(Arrays.equals(arrayToSort, sortedArray));
    }
    
    public void testInsertionSortWithHardCodedArray(){
        int[] arrayToSort = cmethods.giveAHardCodedArray();
        int[] sortedArray = cmethods.giveAHardCodedArraySolution();
        introsort.intro_InsertionSort(arrayToSort, 0, arrayToSort.length);
        assertTrue(Arrays.equals(arrayToSort, sortedArray));
    }
    
    public void testHeapSortWithSortedArray(){
        int[] arrayToSort = cmethods.giveArrayInOrder();
        int[] sortedArray = cmethods.giveArrayInOrder();
        introsort.intro_HeapSort(arrayToSort, 0, arrayToSort.length);
        assertTrue(Arrays.equals(arrayToSort, sortedArray));
    }
    
    public void testHeapSortWithReversedArray(){
        int[] arrayToSort = cmethods.giveArrayInReverseOrder();
        int[] sortedArray = cmethods.giveArrayInOrder();
        introsort.intro_HeapSort(arrayToSort, 0, arrayToSort.length);
        assertTrue(Arrays.equals(arrayToSort, sortedArray));
    }
    
    public void testHeapSortWithOneLengthArray(){
        int[] arrayToSort = cmethods.giveArrayOfOne();
        int[] sortedArray = cmethods.giveArrayOfOneSolution();
        introsort.intro_HeapSort(arrayToSort, 0, arrayToSort.length);
        assertTrue(Arrays.equals(arrayToSort, sortedArray));
    }
    
    public void testHeapSortWithNoLengthArray(){
        int[] arrayToSort = cmethods.giveArrayOfNone();
        int[] sortedArray = cmethods.giveArrayOfNoneSolution();
        introsort.intro_HeapSort(arrayToSort, 0, arrayToSort.length);
        assertTrue(Arrays.equals(arrayToSort, sortedArray));
    }
    
    public void testHeapSortWithAllSameNumbersArray(){
        int[] arrayToSort = cmethods.giveArrayOfAllSameNumbers();
        int[] sortedArray = cmethods.giveArrayOfAllSameNumbers();
        introsort.intro_HeapSort(arrayToSort, 0, arrayToSort.length);
        assertTrue(Arrays.equals(arrayToSort, sortedArray));
    }
    
    public void testHeapSortWithHardCodedArray(){
        int[] arrayToSort = cmethods.giveAHardCodedArray();
        int[] sortedArray = cmethods.giveAHardCodedArraySolution();
        introsort.intro_HeapSort(arrayToSort, 0, arrayToSort.length);
        assertTrue(Arrays.equals(arrayToSort, sortedArray));
    }
    
    public void testIntroSortWithSortedArray(){
        int[] arrayToSort = cmethods.giveArrayInOrder();
        int[] sortedArray = cmethods.giveArrayInOrder();
        introsort.introSort(arrayToSort);
        assertTrue(Arrays.equals(arrayToSort, sortedArray));
    }
    
    public void testIntroSortWithReversedArray(){
        int[] arrayToSort = cmethods.giveArrayInReverseOrder();
        int[] sortedArray = cmethods.giveArrayInOrder();
        introsort.introSort(arrayToSort);
        assertTrue(Arrays.equals(arrayToSort, sortedArray));
    }
    
    public void testIntroSortWithOneLengthArray(){
        int[] arrayToSort = cmethods.giveArrayOfOne();
        int[] sortedArray = cmethods.giveArrayOfOneSolution();
        introsort.introSort(arrayToSort);
        assertTrue(Arrays.equals(arrayToSort, sortedArray));
    }
    
    public void testIntroSortWithNoLengthArray(){
        int[] arrayToSort = cmethods.giveArrayOfNone();
        int[] sortedArray = cmethods.giveArrayOfNoneSolution();
        introsort.introSort(arrayToSort);
        assertTrue(Arrays.equals(arrayToSort, sortedArray));
    }
    
    public void testIntroSortWithAllSameNumbersArray(){
        int[] arrayToSort = cmethods.giveArrayOfAllSameNumbers();
        int[] sortedArray = cmethods.giveArrayOfAllSameNumbers();
        introsort.introSort(arrayToSort);
        assertTrue(Arrays.equals(arrayToSort, sortedArray));
    }
    
    public void testIntroSortWithALargeArray() {
        
        int[] arrayToSort = cmethods.giveLargeArrayOfRandomNumbers();
        int[] sortedArray = arrayToSort.clone();
        Arrays.sort(sortedArray);
        introsort.introSort(arrayToSort);
        assertTrue(Arrays.equals(arrayToSort, sortedArray));

    }
    
    public void testIntroSortWithHardCodedArray(){
        int[] arrayToSort = cmethods.giveAHardCodedArray();
        int[] sortedArray = cmethods.giveAHardCodedArraySolution();
        introsort.introSort(arrayToSort);
        assertTrue(Arrays.equals(arrayToSort, sortedArray));
    }
  
}
