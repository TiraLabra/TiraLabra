package math;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sebastian Björkqvist
 */
public class MathUtilTest {

    public MathUtilTest() {
    }

    @Test
    public void testAbs0() {
        assertEquals(0, MathUtil.abs(0));
    }

    @Test
    public void testAbs1() {
        assertEquals(1, MathUtil.abs(1));
    }

    @Test
    public void testAbsMinus1() {
        assertEquals(1, MathUtil.abs(-1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPowerNegativeExponent() {
        int base = 2;
        int exponent = -1;
        
        MathUtil.pow(base, exponent);
    }

    @Test
    public void testPowerb2_e3() {
        int base = 2;
        int exponent = 3;
        assertEquals(8, MathUtil.pow(base, exponent));
    }

    @Test
    public void testPowerbMinus1_e2() {
        int base = -1;
        int exponent = 2;
        assertEquals(1, MathUtil.pow(base, exponent));
    }

    @Test
    public void testPowerbMinus1_e5() {
        int base = -1;
        int exponent = 5;
        assertEquals(-1, MathUtil.pow(base, exponent));
    }

    @Test
    public void testPowerbMinus2_e5() {
        int base = -2;
        int exponent = 5;
        assertEquals(-32, MathUtil.pow(base, exponent));
    }

    @Test
    public void testPowerbMinus2_e4() {
        int base = -2;
        int exponent = 4;
        assertEquals(16, MathUtil.pow(base, exponent));
    }

    @Test
    public void testPowerb5_e0() {
        int base = 5;
        int exponent = 0;
        assertEquals(1, MathUtil.pow(base, exponent));
    }

    @Test
    public void testPowerb7_e3() {
        int base = 7;
        int exponent = 3;
        assertEquals(343, MathUtil.pow(base, exponent));
    }

    @Test
    public void testPowerb5_e4() {
        int base = 5;
        int exponent = 4;
        assertEquals(625, MathUtil.pow(base, exponent));
    }

}
