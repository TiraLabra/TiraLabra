package Types;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ASDIntegerTest {
    private ASDInteger one, two, four;
    
    @Before
    public void setUp() {
        two = new ASDInteger(2);
        one = new ASDInteger(1);
        four = new ASDInteger(4);
    }

    @Test
    public void addition() {
        ASDInteger onePlusOne = one.add(one);
        assertEquals(two, onePlusOne);
    }

    @Test
    public void subtraction() {
        ASDInteger twoMinusOne = two.subtract(one);
        assertEquals(one, twoMinusOne);
    }

    @Test
    public void multiplication() {
        ASDInteger twoTimesTwo = two.multiply(two);
        assertEquals(four, twoTimesTwo);
    }
    
    @Test
    public void division() {
        ASDInteger fourOverTwo = four.divide(two);
        assertEquals(two, fourOverTwo);
    }
}
