package TiraLabra.Number;

import org.junit.Test;
import static org.junit.Assert.*;

public class IntegerTest extends NumberTests<Integer> {
    private static final Integer overflow = new Integer(46340);
    private static final Integer oneToOverflow = new Integer(46339);
    
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
        assertEquals("46340", overflow.toString());
        assertEquals("46341", oneOverOverflow.toString());
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
        assertEquals(oneToOverflow, overflow.subtract(one));
    }
    
    @Test
    public void subtractToOverflow() {
        assertEquals(overflow, oneOverOverflow.subtract(one));
    }
    
    @Test
    public void multiplyToOverflow() {
        Integer halfRadix = new Integer(23170);
        assertEquals(overflow, halfRadix.multiply(two));
    }
    
    @Test
    public void multiplyOverflow() {
        assertEquals("92681", one.add(overflow).add(overflow).toString());
        assertEquals("92681", one.add(overflow.add(overflow)).toString());
        assertEquals("92681", overflow.add(overflow).add(one).toString());
        
        assertEquals("92680", overflow.multiply(two).toString());
        assertEquals("2147395600", overflow.multiply(overflow).toString());
        
        assertEquals("99510312104000", overflow.multiply(overflow)
                .multiply(overflow).toString());
        
        assertEquals("92680", two.multiply(overflow).toString());
    }
    
    @Test
    public void powOverflow() {
        assertEquals("1073741824", two.pow(30).toString());
        assertEquals("99510312104000", overflow.pow(3).toString());
    }
}
