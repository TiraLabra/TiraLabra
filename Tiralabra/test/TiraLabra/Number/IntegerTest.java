package TiraLabra.Number;

import org.junit.Test;
import static org.junit.Assert.*;

public class IntegerTest extends NumberTests<Integer> {
    private static final Integer radix = new Integer(java.lang.Integer.MAX_VALUE);
    private Integer overflow, oneOverOverflow;
    
    @Override
    public void setUp() {
        zero = Integer.ZERO;
        one = Integer.ONE;
        
        two = new Integer(2);
        four = new Integer(4);
        
        int v[] = {0, 1};
        overflow = new Integer(v, false);
        
        int v2[] = {1, 1};
        oneOverOverflow = new Integer(v2, false);
    }
    
    @Test
    public void overflowCompares() {
        assertTrue(zero.compareTo(radix) < 0);
        assertTrue(radix.compareTo(one) > 0);
        assertTrue(oneOverOverflow.compareTo(one) > 0);
        assertTrue(oneOverOverflow.compareTo(overflow) > 0);
    }
    
    @Test
    public void overflowToString() {
        assertEquals("2147483647", overflow.toString());
    }
    
    @Test
    public void overOverflowToString() {
        assertEquals("2147483648", oneOverOverflow.toString());
    }
    
    @Test
    public void overflowEquals() {
        assertEquals(overflow, overflow);
    }
    
    @Test
    public void overflowFromAddition() {
        assertEquals(overflow, radix.add(one));
    }
    
    @Test
    public void addToOverflow() {
        assertEquals(oneOverOverflow, overflow.add(one));
    }
    
    @Test
    public void subtractFromOverflow() {
        assertEquals("2147483646", radix.subtract(one).toString());
    }
    
    @Test
    public void subtractToOverflow() {
        assertEquals(overflow, oneOverOverflow.subtract(one));
    }
}
