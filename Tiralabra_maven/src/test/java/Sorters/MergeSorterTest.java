/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Sorters;

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
        int[] arrayToSort = new int[]{3,9,4,6,1,2,5,7,10,8};
        int[] sortedArray = new int[]{1,2,3,4,5,6,7,8,9,10};
        arrayToSort = sorterToTest.mergeSort(arrayToSort);
        for(int i = 0; i < arrayToSort.length; i++){
            System.out.print("["+arrayToSort[i]+"], ");
        }
        assertTrue(cmethods.checkThatArraysAreTheSame(arrayToSort, sortedArray));
        
    }
    
    
    
}
