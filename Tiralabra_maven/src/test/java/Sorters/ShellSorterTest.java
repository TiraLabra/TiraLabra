/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sorters;

import java.util.Arrays;
import java.util.Random;
import junit.framework.TestCase;

/**
 *
 * @author Admin
 */
public class ShellSorterTest extends TestCase {

    private ShellSorter sorterToTest;
    private CommonMethods cmethods;
    private Random randomizer;

    public ShellSorterTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        sorterToTest = new ShellSorter();
        cmethods = new CommonMethods();
        randomizer = new Random();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testThatSortingWorksWithSmallArray() {
        int[] arrayToSort = new int[]{3, 9, 4, 6, 1, 2, 5, 7, 10, 8};
        int[] sortedArray = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        arrayToSort = sorterToTest.shellSort(arrayToSort);
        for (int i = 0; i < arrayToSort.length; i++) {
            System.out.print("[" + arrayToSort[i] + "], ");
        }
        assertTrue(cmethods.checkThatArraysAreTheSame(arrayToSort, sortedArray));

    }

    public void testRandomArrays() {
        for (int i = 0; i < 10; i++) {
            int[] arrayToSort = cmethods.giveRandomArray();
            int[] sortedArray = arrayToSort.clone();
            Arrays.sort(sortedArray);
            arrayToSort = sorterToTest.shellSort(arrayToSort);
            for (int j = 0; j < arrayToSort.length; j++) {
                System.out.print("[" + arrayToSort[j] + "], ");
            }
            assertTrue(cmethods.checkThatArraysAreTheSame(arrayToSort, sortedArray));
        }
    }
    
    public void testRandomGaps(){
        for(int i = 0; i < 100; i++){
            int[] gapArray = sorterToTest.countGaps(randomizer.nextInt(10000009));
            System.out.println("["+gapArray[gapArray.length-1]+"]");
            assertEquals(1, gapArray[gapArray.length-1]);
        }
    }
    
    public void testGapsWithNoLength(){
        int[] gapArray = sorterToTest.countGaps(0);
        assertEquals(1, gapArray[0]);
    }

}
