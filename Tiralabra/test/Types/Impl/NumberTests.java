package Types.Impl;

import Types.Number;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public abstract class NumberTests {
    protected Number one, two, four;
    
    @Before
    public abstract void setUp();

    @Test
    public void equalsWorks() {
        assertTrue(one.equals(one));
        assertFalse(one.equals(two));
        assertFalse(one.equals(""));
        assertFalse(one.equals(null));
    }
    
    @Test
    public void addition() {
        assertEquals(two, one.add(one));
    }

    @Test
    public void subtraction() {
        assertEquals(one, two.subtract(one));
    }

    @Test
    public void multiplication() {
        assertEquals(four, two.multiply(two));
    }
    
    @Test
    public void division() {
        assertEquals(two, four.divide(two));
    }
    
    @Test
    public void exponentiation() {
        assertEquals(four, two.pow(2));
    }
    
    @Test
    public void squareRoot() {
        assertEquals(two, four.sqrt());
    }
}
