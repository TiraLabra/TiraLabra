/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sorters;

import java.util.Arrays;
import junit.framework.TestCase;

public class MergeSorterTest extends TestCase {

    private MergeSorter sorterToTest;
    private CommonMethods cmethods;

    public MergeSorterTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        sorterToTest = new MergeSorter();
        cmethods = new CommonMethods();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testThatSortingWorks() {
        int[] arrayToSort = new int[]{3, 9, 4, 6, 1, 2, 5, 7, 10, 8};
        int[] sortedArray = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        arrayToSort = sorterToTest.mergeSort(arrayToSort);
        assertTrue(Arrays.equals(arrayToSort, sortedArray));

    }

    public void testAfterMath() {
        int[] newArray = new int[5];
        int[] left = new int[]{1, 2, 3, 4, 5};
        int[] right = new int[0];
        int newArrayIndex = 0;
        int leftArrayIndex = 0;
        int rightArrayIndex = Integer.MAX_VALUE;

        newArray = sorterToTest.mergeSortAfterMath(newArray, left, right, newArrayIndex, leftArrayIndex, rightArrayIndex);
        assertTrue(Arrays.equals(newArray, left));
    }

    public void testMergeSortWithSortedArray() {
        int[] arrayToSort = cmethods.giveArrayInOrder();
        int[] sortedArray = cmethods.giveArrayInOrder();
        arrayToSort = sorterToTest.mergeSort(arrayToSort);
        assertTrue(Arrays.equals(arrayToSort, sortedArray));
    }

    public void testMergeSortWithReversedArray() {
        int[] arrayToSort = cmethods.giveArrayInReverseOrder();
        int[] sortedArray = cmethods.giveArrayInOrder();
        arrayToSort = sorterToTest.mergeSort(arrayToSort);
        assertTrue(Arrays.equals(arrayToSort, sortedArray));
    }

    public void testMergeSortWithOneLengthArray() {
        int[] arrayToSort = cmethods.giveArrayOfOne();
        int[] sortedArray = cmethods.giveArrayOfOneSolution();
        arrayToSort = sorterToTest.mergeSort(arrayToSort);
        assertTrue(Arrays.equals(arrayToSort, sortedArray));
    }

    public void testMergeSortWithNoLengthArray() {
        int[] arrayToSort = cmethods.giveArrayOfNone();
        int[] sortedArray = cmethods.giveArrayOfNoneSolution();
        arrayToSort = sorterToTest.mergeSort(arrayToSort);
        assertTrue(Arrays.equals(arrayToSort, sortedArray));
    }

    public void testMergeSortWithAllSameNumbersArray() {
        int[] arrayToSort = cmethods.giveArrayOfAllSameNumbers();
        int[] sortedArray = cmethods.giveArrayOfAllSameNumbers();
        arrayToSort = sorterToTest.mergeSort(arrayToSort);
        assertTrue(Arrays.equals(arrayToSort, sortedArray));
    }

    public void testMergeSortWithALargeArray() {

        int[] arrayToSort = cmethods.giveLargeArrayOfRandomNumbers();
        int[] sortedArray = arrayToSort.clone();
        Arrays.sort(sortedArray);
        arrayToSort = sorterToTest.mergeSort(arrayToSort);
        assertTrue(Arrays.equals(arrayToSort, sortedArray));

    }

    public void testSmoothSortWithHardCodedArray() {
        int[] arrayToSort = cmethods.giveAHardCodedArray();
        int[] sortedArray = cmethods.giveAHardCodedArraySolution();
        arrayToSort = sorterToTest.mergeSort(arrayToSort);
        assertTrue(Arrays.equals(arrayToSort, sortedArray));
    }

}
