package TiraLabra.Vector;

import static org.junit.Assert.*;
import org.junit.Test;

import TiraLabra.Number.Number;
import TiraLabra.Number.Integer;
import TiraLabra.Number.Real;

import TiraLabra.Number.NumberTests;

public class VectorTest extends NumberTests<Vector<Integer>> {
    private Vector<Integer> vector;

    private <T extends Number<T>> Vector<T> makeVector(Class<T> type,
            int... values) {
        T elements[] = (T[]) new Number[values.length];
        
        for (int i = 0; i < values.length; i++) {
            elements[i] = T.make(type, values[i]);
        }
        
        return new Vector<T>(elements);
    }
    
    private void vectorEquals(Class<? extends Number> type, int... values) {
        for (int i = 0; i < values.length; i++) {
            assertEquals(Number.make(type, values[i]), vector.get(i));
        }
    }
    
    @Override
    public void setUp() {
        vector = makeVector(Integer.class, 1, 2, 3, 4);
        
        zero = makeVector(Integer.class, 0, 0, 0, 0);
        one = makeVector(Integer.class, 1, 1, 1, 1);
        two = makeVector(Integer.class, 2, 2, 2, 2);
        four = makeVector(Integer.class, 4, 4, 4, 4);
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
        vector = vector.scale(Integer.TEN);
        vectorEquals(Integer.class, 10, 20, 30, 40);
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
        Vector<Real> v = makeVector(Real.class, 2, 2, 2, 2);
        assertEquals(new Real(4), v.length());
        
        v = v.normalize();
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
    
    @Override
    public void toStringWorks() {
        
    }
}
