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
    public void squareRoot() {
        assertEquals(two, four.sqrt());
    }
    
}
