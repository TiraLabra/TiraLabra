package TiraLabra.Number;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public abstract class NumberTests<T extends Number> {
    protected T zero, one, two, four;
    
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
    public void absoluteValue() {
        assertEquals(one, one.subtract(two).abs());
    }
    
    @Test
    public void negation() {
        assertEquals(one.subtract(two), one.negate());
    }
}
