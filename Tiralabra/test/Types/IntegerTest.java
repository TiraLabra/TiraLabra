package Types;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IntegerTest {
    private Integer one, two, four;
    
    @Before
    public void setUp() {
        one = new Integer(1);
        two = new Integer(2);
        four = new Integer(4);
    }

    @Test
    public void addition() {
        Integer onePlusOne = one.add(one);
        assertEquals(two, onePlusOne);
    }

    @Test
    public void subtraction() {
        Integer twoMinusOne = two.subtract(one);
        assertEquals(one, twoMinusOne);
    }

    @Test
    public void multiplication() {
        Integer twoTimesTwo = two.multiply(two);
        assertEquals(four, twoTimesTwo);
    }
    
    @Test
    public void division() {
        Integer fourOverTwo = four.divide(two);
        assertEquals(two, fourOverTwo);
    }
}
