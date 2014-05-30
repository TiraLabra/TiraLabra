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
        Number onePlusOne = one.add(one);
        assertEquals(two, onePlusOne);
    }

    @Test
    public void subtraction() {
        Number twoMinusOne = two.subtract(one);
        assertEquals(one, twoMinusOne);
    }

    @Test
    public void multiplication() {
        Number twoTimesTwo = two.multiply(two);
        assertEquals(four, twoTimesTwo);
    }
    
    @Test
    public void division() {
        Number fourOverTwo = four.divide(two);
        assertEquals(two, fourOverTwo);
    }
}
