/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 9.8.2014 
 */
package TestSuite.Algos;

import TestSuite.Arrays.Arraymaker;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marko <markoma@iki.fi>
 */
public class SelectionsortTest {

    public SelectionsortTest() {
    }

    /**
     * Test of sort method, of class Selectionsort.
     */
    @Test
    public void testSortingReverseOrder() {
        int[] a = new Arraymaker().reversed(1000);

        Algo ins = new Selectionsort();
        ins.sort(a);
        assertArrayEquals(a, new Arraymaker().sorted(1000));
    }

    /**
     * Test of sort method, of class selectionsort.
     */
    @Test
    public void testSortingSortedOrder() {

        int[] a = new Arraymaker().sorted(1000);
        Algo ins = new Selectionsort();
        ins.sort(a);
        assertArrayEquals(a, new Arraymaker().sorted(1000));
    }

    /**
     * Test size, of class Quicksort.
     */
    @Test
    public void testSize() {

        int[] a = new Arraymaker().reversed(100);
        Algo ins = new Selectionsort();
        ins.sort(a);
        assertEquals(100, a.length);
    }

    /**
     * Test of toString method, of class Selectionsort.
     */
    @Test
    public void testToString() {
        assertEquals("Selection sort", new Selectionsort().toString());
    }

}
