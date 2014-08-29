/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 29.8.2014 
 */
package TestSuite.Algos;

import TestSuite.Arrays.Arraymaker;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Test All sorting algorithms
 *
 *
 * @author Marko <markoma@iki.fi>
 */
public class AlgoTest {

    Algo[] algos;
    Algo newAlgo;

    /**
     * Sets all algos for testing and new Algo extending class "newAlgo"
     */
    @Before
    public void setAll() {
        algos = new Algo[]{new Bubblesort(), new Insertionsort(), new Mergesort(), new Quicksort(), new Selectionsort()};
        newAlgo = new AlgoImpl();
    }

    @Test
    public void testAllAlgosReversedBigArray() {
        int[] sorted = new Arraymaker().sorted(1000);

        for (Algo a : algos) {
            int[] r = new Arraymaker().reversed(1000);
            a.sort(r);
            assertArrayEquals(r, sorted);
        }
    }

    @Test
    public void testAllAlgosSmallArray() {
        int[] sorted = new int[]{1, 2, 3, 4};

        for (Algo a : algos) {
            int[] r = new int[]{4, 2, 3, 1};
            a.sort(r);
            assertArrayEquals(r, sorted);
        }
    }

    @Test
    public void testAllAlgosTwoElements() {

        for (Algo a : algos) {
            int[] r = new int[]{1, 0};
            a.sort(r);
            assertArrayEquals(r, new int[]{0, 1});
        }
    }

    @Test
    public void testAllAlgosOneElement() {

        for (Algo a : algos) {
            int[] r = new int[]{1};
            a.sort(r);
            assertArrayEquals(r, new int[]{1});
        }
    }

    /**
     * Test of toString method, of class Algo.
     */
    @Test
    public void testNewAlgoToString() {
        assertEquals(newAlgo.toString(), "Hogwarts School of Witchcraft and Wizardry");
    }

    /**
     * Test of toString method, of class Algo.
     */
    @Test
    public void testNewAlgoArraySortMethod() {
        int[] a = new int[]{0, 1, 0, 0,1,1,10,33,3333333,12};
        newAlgo.sort(a);
        assertArrayEquals(a, new int[]{0, 0, 0, 1, 1, 1, 10, 12, 33, 3333333});
    }

    /**
     * new Algo implementation.
     *
     * Sorts with Arrays.sort
     */
    public class AlgoImpl extends Algo {

        @Override
        public void sort(int[] a) {
            Arrays.sort(a);
        }

        @Override
        public String toString() {
            return "Hogwarts School of Witchcraft and Wizardry";
        }
    }

}
