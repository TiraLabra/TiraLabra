package Vector;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import Number.Number;
import Number.Integer;
import Number.Real;

public class VectorTest {
    private Vector vector;

    @Before
    public void setUp() {
        Integer values[] = {
            new Integer(1), new Integer(2), new Integer(3), new Integer(4)
        };

        vector = new Vector(values);
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void getUnder() {
        vector.get(-1);
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void getOver() {
        vector.get(5);
    }

    private void vectorEquals(int... values) {
        for (int i = 0; i < values.length; i++) {
            assertEquals(new Integer(values[i]), vector.get(i));
        }
    }
    
    @Test
    public void multiplyScalar() {
        vector = vector.multiply(Integer.TEN);
        vectorEquals(10, 20, 30, 40);
    }
    
    @Test
    public void divideScalar() {
        vector = vector.multiply(Integer.TEN);
        
        vector = vector.divide(Integer.TEN);
        vectorEquals(1, 2, 3, 4);
    }
    
    @Test
    public void multiplyVector() {
        vector = vector.multiply(vector);
        vectorEquals(1, 4, 9, 16);
    }
    
    @Test
    public void subtractVector() {
        vector = vector.subtract(vector);
        vectorEquals(0, 0, 0, 0);
    }
    
    @Test
    public void addVector() {
        vector = vector.add(vector);
        vectorEquals(2, 4, 6, 8);
    }
    
    @Test
    public void length() {
        assertEquals(new Integer(5), vector.length());
    }
    
    @Test
    public void normalization() {
        Real values[] = {
            new Real(2), new Real(2), new Real(2), new Real(2)
        };
        
        vector = new Vector(values).normalize();
        assertEquals(Real.ONE, vector.length());
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public void generalizedCrossProduct() {
        Vector product = vector.cross(vector);
    }
    
    @Test
    public void crossProduct() {
        Integer v1[] = {new Integer(1), new Integer(2), new Integer(3)};
        Integer v2[] = {new Integer(3), new Integer(2), new Integer(1)};
        
        Vector a = new Vector(v1);
        Vector b = new Vector(v2);
        
        vector = a.cross(b);
        vectorEquals(-4, 8, -4);
    }
    
    @Test
    public void maximum() {
        Number max = vector.max();
        assertEquals(vector.get(3), max);
    }
}
