package math;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ydna
 */
public class ComplexTest {

    private final static double EPSILON = 1e-8;

    @Test
    public void testConstructors() {
        Complex temp = new Complex(1);
        assertEquals(1.0, temp.re(), EPSILON);
        assertEquals(0.0, temp.im(), EPSILON);
        temp = new Complex(1, 1);
        assertEquals(1.0, temp.re(), EPSILON);
        assertEquals(1.0, temp.im(), EPSILON);
    }

    @Test
    public void testString() {
        Complex first = new Complex(1, 0);
        Complex second = new Complex(0, 1);
        Complex third = new Complex(1, 1);
        Complex fourth = new Complex(1, -1);
        assertEquals("1.0", first.toString());
        assertEquals("1.0i", second.toString());
        assertEquals("1.0+1.0i", third.toString());
        assertEquals("1.0-1.0i", fourth.toString());
    }

    @Test
    public void testConjugate() {
        Complex temp = new Complex(1, 1);
        assertEquals(1.0, temp.conjugate().re(), EPSILON);
        assertEquals(-1.0, temp.conjugate().im(), EPSILON);
    }

    @Test
    public void testReciprocal() {
        Complex temp = new Complex(2, 1);
        assertEquals(0.4, temp.reciprocal().re(), EPSILON);
        assertEquals(-0.2, temp.reciprocal().im(), EPSILON);
    }

    @Test
    public void testComplexAddition() {
        Complex first = new Complex(1, 1);
        Complex second = new Complex(-0.5, 0.5);
        Complex sum = first.add(second);
        assertEquals(0.5, sum.re(), EPSILON);
        assertEquals(1.5, sum.im(), EPSILON);
    }

    @Test
    public void testComplexSubtraction() {
        Complex first = new Complex(1, 1);
        Complex second = new Complex(0.5, 0.5);
        Complex diff = first.subtract(second);
        assertEquals(0.5, diff.re(), EPSILON);
        assertEquals(0.5, diff.im(), EPSILON);
    }

    @Test
    public void testComplexMultiplication() {
        Complex first = new Complex(1, 2);
        Complex second = new Complex(2, 1);
        Complex prod = first.multiply(second);
        assertEquals(0.0, prod.re(), EPSILON);
        assertEquals(5.0, prod.im(), EPSILON);
    }

    @Test
    public void testComplexDivision() {
        Complex first = new Complex(1, 2);
        Complex second = new Complex(2, 1);
        Complex quot = first.divide(second);
        assertEquals(0.8, quot.re(), EPSILON);
        assertEquals(0.6, quot.im(), EPSILON);
    }

    @Test
    public void testRealAddition() {
        Complex temp = new Complex(1, 1);
        assertEquals(3.0, temp.add(2).re(), EPSILON);
        assertEquals(1.0, temp.add(2).im(), EPSILON);
    }

    @Test
    public void testRealSubtraction() {
        Complex temp = new Complex(1, 1);
        assertEquals(-1.0, temp.subtract(2).re(), EPSILON);
        assertEquals(1.0, temp.subtract(2).im(), EPSILON);
    }

    @Test
    public void testRealMultiplication() {
        Complex temp = new Complex(0.5, 0.5);
        assertEquals(1.0, temp.multiply(2).re(), EPSILON);
        assertEquals(1.0, temp.multiply(2).im(), EPSILON);
    }

    @Test
    public void testRealDivision() {
        Complex temp = new Complex(1, 1);
        assertEquals(0.5, temp.divide(2).re(), EPSILON);
        assertEquals(0.5, temp.divide(2).im(), EPSILON);
    }

    @Test
    public void testAbsoluteValue() {
        Complex temp = new Complex(1, 2);
        assertEquals(Math.sqrt(5), temp.abs(), EPSILON);
    }
}
