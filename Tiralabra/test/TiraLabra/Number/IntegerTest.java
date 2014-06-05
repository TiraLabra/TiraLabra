package TiraLabra.Number;

import org.junit.Test;
import static org.junit.Assert.*;

public class IntegerTest extends NumberTests<Integer> {
    private static final Integer overflow =
            new Integer(java.lang.Integer.MAX_VALUE);

    private static final Integer oneToOverflow =
            new Integer(java.lang.Integer.MAX_VALUE - 1);
    
    private Integer oneOverOverflow;
    
    @Override
    public void setUp() {
        zero = Integer.ZERO;
        one = Integer.ONE;
        
        two = new Integer(2);
        four = new Integer(4);
        
        final int v[] = {1, 1};
        oneOverOverflow = new Integer(v, false);
    }
    
    @Test
    public void overflowEquals() {
        assertFalse(overflow.equals(one));
        assertFalse(one.equals(overflow));
    }
    
    @Test
    public void overflowCompares() {
        assertTrue(zero.compareTo(overflow) < 0);
        assertTrue(one.compareTo(overflow) < 0);
        
        assertTrue(overflow.compareTo(one) > 0);
        
        assertTrue(oneOverOverflow.compareTo(one) > 0);
        assertTrue(oneOverOverflow.compareTo(overflow) > 0);
    }
    
    @Test
    public void overflowToString() {
        assertEquals("2147483647", overflow.toString());
        assertEquals("2147483648", oneOverOverflow.toString());
    }
    
    @Test
    public void overflowFromAddition() {
        assertEquals(overflow, oneToOverflow.add(one));
    }
    
    @Test
    public void addToOverflow() {
        assertEquals(oneOverOverflow, overflow.add(one));
    }
    
    @Test
    public void subtractFromOverflow() {
        assertEquals("2147483646", overflow.subtract(one).toString());
    }
    
    @Test
    public void subtractToOverflow() {
        assertEquals(overflow, oneOverOverflow.subtract(one));
    }
}
