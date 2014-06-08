package TiraLabra.Vector;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import TiraLabra.Number.Number;
import TiraLabra.Number.Integer;
import TiraLabra.Number.Real;

public class VectorTest {
    private Vector<Integer> vector;

    private Vector makeVector(Class<? extends Number> type, int... values) {
        Number elements[] = new Number[values.length];
        
        for (int i = 0; i < values.length; i++) {
            elements[i] = Number.make(type, values[i]);
        }
        
        return new Vector(elements);
    }
    
    private void vectorEquals(Class<? extends Number> type, int... values) {
        for (int i = 0; i < values.length; i++) {
            assertEquals(Number.make(type, values[i]), vector.get(i));
        }
    }
    
    @Before
    public void setUp() {
        vector = makeVector(Integer.class, 1, 2, 3, 4);
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void getUnder() {
        vector.get(-1);
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void getOver() {
        vector.get(5);
    }
    
    @Test
    public void multiplyScalar() {
        vector = vector.multiply(Integer.TEN);
        vectorEquals(Integer.class, 10, 20, 30, 40);
    }
    
    @Test
    public void divideScalar() {
        vector = vector.multiply(Integer.TEN);
        
        vector = vector.divide(Integer.TEN);
        vectorEquals(Integer.class, 1, 2, 3, 4);
    }
    
    @Test
    public void multiplyVector() {
        vector = vector.multiply(vector);
        vectorEquals(Integer.class, 1, 4, 9, 16);
    }
    
    @Test
    public void subtractVector() {
        vector = vector.subtract(vector);
        vectorEquals(Integer.class, 0, 0, 0, 0);
    }
    
    @Test
    public void addVector() {
        vector = vector.add(vector);
        vectorEquals(Integer.class, 2, 4, 6, 8);
    }
    
    @Test
    public void length() {
        assertEquals(new Integer(5), vector.length());
    }
    
    @Test
    public void normalization() {
        Vector v = makeVector(Real.class, 2, 2, 2, 2).normalize();
        assertEquals(Real.ONE, v.length());
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public void generalizedCrossProduct() {
        vector.cross(vector);
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public void mismatchedCrossProduct() {
        Vector a = makeVector(Integer.class, 1, 2);
        Vector b = makeVector(Integer.class, 1, 2, 3);
        
        b.cross(a);
    }
    
    @Test
    public void crossProduct() {
        Vector a = makeVector(Integer.class, 1, 2, 3);
        Vector b = makeVector(Integer.class, 3, 2, 1);
        
        vector = a.cross(b);
        vectorEquals(Integer.class, -4, 8, -4);
    }
    
    @Test
    public void maximum() {
        Vector<Integer> v = makeVector(Integer.class, 1, 3, 2, 4);
        assertEquals(new Integer(4), v.max());
    }
}
