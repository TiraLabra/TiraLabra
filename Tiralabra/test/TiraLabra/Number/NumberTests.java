package TiraLabra.Number;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public abstract class NumberTests<T extends Number<T>> {
    protected T zero, one, two, four;
    
    @Before
    public abstract void setUp();

    @Test
    public void equalsWorks() {
        assertTrue(one.equals(one));
        assertFalse(one.equals(two));
        
        assertFalse(one.equals(""));
        assertFalse(one.equals(null));
        
        assertFalse(zero.equals(one));
        assertFalse(one.equals(zero));
        assertTrue(zero.equals(zero));
        
        assertFalse(one.equals(one.negate()));
        assertFalse(one.negate().equals(one));
        assertTrue(one.negate().equals(one.negate()));
    }
    
    @Test
    public void addZero() {
        assertEquals(one, one.add(zero));
    }
    
    @Test
    public void addToZero() {
        assertEquals(one, zero.add(one));
    }
    
    @Test
    public void addPositive() {
        assertEquals(two, one.add(one));
    }
    
    @Test
    public void addNegative() {
        assertEquals(one.subtract(one), one.add(one.negate()));
    }
    
    @Test
    public void addToNegative() {
        assertEquals(one.subtract(one), one.negate().add(one));
    }
    
    @Test
    public void addNegativeToNegative() {
        assertEquals(two.negate(), one.negate().add(one.negate()));
    }
    
    @Test
    public void subtractZero() {
        assertEquals(one, one.subtract(zero));
    }
    
    @Test
    public void subtractFromZero() {
        assertEquals(one.negate(), zero.subtract(one));
    }
    
    @Test
    public void subtractPositive() {
        assertEquals(one, two.subtract(one));
    }
    
    @Test
    public void subtractNegative() {
        assertEquals(one.add(two), two.subtract(one.negate()));
    }
    
    @Test
    public void subtractFromNegative() {
        assertEquals(two.add(one).negate(), two.negate().subtract(one));
    }

    @Test
    public void subtractNegativeFromNegative() {
        assertEquals(one.negate(), two.negate().subtract(one.negate()));
        assertEquals(zero, one.negate().subtract(one.negate()));
    }
    
    @Test
    public void subtractToNegative() {
        assertEquals(one.negate(), one.subtract(two));
    }
    
    @Test
    public void multiplyByZero() {
        assertEquals(zero, two.multiply(zero));
    }
    
    @Test
    public void multiplyZero() {
        assertEquals(zero, zero.multiply(two));
    }
    
    @Test
    public void multiplyByPositive() {
        assertEquals(four, two.multiply(two));
    }
    
    @Test
    public void multiplyByNegative() {
        assertEquals(four.negate(), two.multiply(two.negate()));
    }
    
    @Test
    public void multiplyNegativeByNegative() {
        assertEquals(four, two.negate().multiply(two.negate()));
    }
    
    @Test
    public void divideZero() {
        assertEquals(zero, zero.divide(two));
    }
    
    @Test(expected=ArithmeticException.class)
    public void divideByZero() {
        one.divide(zero);
    }
    
    @Test
    public void dividePositive() {
        assertEquals(two, four.divide(two));
    }
    
    @Test
    public void divideNegative() {
        assertEquals(two.negate(), four.negate().divide(two));
    }
    
    @Test
    public void exponentiation() {
        assertEquals(four, two.pow(2));
    }
    
    @Test
    public void squareRoot() {
        assertEquals(two, four.sqrt());
        assertEquals(four, four.pow(2).sqrt());
    }
    
    @Test
    public void comparision() {
        assertTrue(four.compareTo(one) > 0);
    }
    
    @Test
    public void negative() {
        assertTrue(one.subtract(two).isNegative());
        assertFalse(two.subtract(one).isNegative());
    }
    
    @Test
    public void isZeroWorks() {
        assertTrue(zero.isZero());
        assertFalse(one.isZero());
    }
    
    @Test
    public void absoluteValue() {
        assertEquals(one, one.subtract(two).abs());
    }
    
    @Test
    public void negation() {
        assertEquals(one.subtract(two), one.negate());
    }
    
    @Test
    public void toStringWorks() {
        assertEquals("0", zero.toString());
        assertEquals("1", one.toString());
        assertEquals("-1", one.negate().toString());
    }
    
    @Test
    public void compareToZero() {
        assertTrue(zero.compareTo(one) < 0);
        assertTrue(zero.compareTo(zero) == 0);
        assertTrue(one.compareTo(zero) > 0);
    }
    
    @Test
    public void compareNegatives() {
        assertTrue(zero.compareTo(one.negate()) > 0);
        assertTrue(one.negate().compareTo(zero) < 0);
        
        assertTrue(one.negate().compareTo(one) < 0);
        assertTrue(one.negate().compareTo(one.negate()) == 0);
        assertTrue(one.compareTo(one.negate()) > 0);
    }
}
