/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 10.8.2014 
 */
package TestSuite.Arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marko <markoma@iki.fi>
 */
public class ReversedTest {

    int[] reversed;

    public ReversedTest() {
        reversed = new AllmostReversed(1000).get();
    }

    /**
     * Test of get method for duplicates, of class Reversed.
     */
    @Test
    public void testDuplicates() {
        Set<Integer> lump = new HashSet<Integer>();
        for (int i = 0; i < reversed.length; i++) {
            assertFalse(lump.contains(reversed[i]));
            lump.add(reversed[i]);
        }
    }

    /**
     * Test of get method for array size, of class Reversed.
     */
    @Test
    public void testSize() {
        assertTrue(reversed.length == 1000);
    }
}
