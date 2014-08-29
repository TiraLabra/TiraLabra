/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 29.8.2014 
 */
package TestSuite.Arrays;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Marko <markoma@iki.fi>
 */
public class ArrTest {

    Arr testArr;

    @Before
    public void ArrTest() {
        testArr = new ArrImpl();
    }

    /**
     * Test of get method, of class Arr.
     */
    @Test
    public void testGetMethod() {
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6}, testArr.get());
    }

    /**
     * Test of toString method, of class Arr.
     */
    @Test
    public void testToString() {
        assertEquals(testArr.toString(), "TestArr");
    }

    public class ArrImpl extends Arr {

        public int[] get() {
            return new int[]{1, 2, 3, 4, 5, 6};
        }

        public String toString() {
            return "TestArr";
        }
    }

}
