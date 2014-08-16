package math;

import java.util.Random;
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

    @Test
    public void testExtendedEuclideanTrivialCase() {
        int a = 11;
        int b = 0;

        ExtendedEuclideanResult result = MathUtil.extendedEuclideanAlgorithm(a, b);
        assertEquals(a, result.gcd);
        assertEquals(1, result.x);
        assertEquals(0, result.y);
    }

    @Test
    public void testExtendedEuclideanTrivialCase2() {
        int a = 11;
        int b = 1;

        ExtendedEuclideanResult result = MathUtil.extendedEuclideanAlgorithm(a, b);
        assertEquals(1, result.gcd);
        assertEquals(0, result.x);
        assertEquals(1, result.y);
    }

    @Test
    public void testExtendedEuclidean() {
        int a = 99;
        int b = 78;

        ExtendedEuclideanResult result = MathUtil.extendedEuclideanAlgorithm(a, b);
        assertEquals(3, result.gcd);
        assertEquals(-11, result.x);
        assertEquals(14, result.y);
    }

    @Test
    public void testExtendedEuclidean2() {
        int a = 120;
        int b = 23;

        ExtendedEuclideanResult result = MathUtil.extendedEuclideanAlgorithm(a, b);
        assertEquals(1, result.gcd);
        assertEquals(-9, result.x);
        assertEquals(47, result.y);
    }

    @Test
    public void testExtendedEuclidean3() {
        int a = 64;
        int b = 18;

        ExtendedEuclideanResult result = MathUtil.extendedEuclideanAlgorithm(a, b);
        assertEquals(2, result.gcd);
        assertEquals(2, result.x);
        assertEquals(-7, result.y);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsPrimeOne() {
        MathUtil.isPrime(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsPrimeZero() {
        MathUtil.isPrime(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsPrimeNegative() {
        MathUtil.isPrime(-1);
    }

    @Test
    public void testIsPrime2() {
        assertEquals(true, MathUtil.isPrime(2));
    }

    @Test
    public void testIsPrime3() {
        assertEquals(true, MathUtil.isPrime(3));
    }

    @Test
    public void testIsPrime4() {
        assertEquals(false, MathUtil.isPrime(4));
    }

    @Test
    public void testIsPrime9() {
        assertEquals(false, MathUtil.isPrime(9));
    }

    @Test
    public void testIsPrime11() {
        assertEquals(true, MathUtil.isPrime(11));
    }

    @Test
    public void testIsPrime15() {
        assertEquals(false, MathUtil.isPrime(15));
    }

    @Test
    public void testIsPrime25() {
        assertEquals(false, MathUtil.isPrime(25));
    }

    @Test
    public void testIsPrime79() {
        assertEquals(true, MathUtil.isPrime(79));
    }

    @Test
    public void testIsPrime169() {
        assertEquals(false, MathUtil.isPrime(169));
    }

    @Test
    public void testIsPrime983() {
        assertEquals(true, MathUtil.isPrime(983));
    }

    @Test
    public void testIsPrime2923() {
        assertEquals(false, MathUtil.isPrime(2923));
    }

    @Test
    public void testIsPrime10427() {
        assertEquals(true, MathUtil.isPrime(10427));
    }

    @Test
    public void testIsPrime1172719() {
        assertEquals(false, MathUtil.isPrime(1172719));
    }

    @Test
    public void testGetInverse2_5() {
        assertTrue((3 - MathUtil.getInverseModP(2, 5)) % 5 == 0);
    }

    @Test
    public void testGetInverse1_7() {
        assertTrue((1 - MathUtil.getInverseModP(1, 7)) % 7 == 0);
    }

    @Test
    public void testGetInverse2_4() {
        assertEquals(-1, MathUtil.getInverseModP(2, 4));
    }

    @Test
    public void testGetInverse5_11() {
        assertTrue((9 - MathUtil.getInverseModP(5, 11)) % 11 == 0);
    }

    @Test
    public void testGetInverse4_16() {
        assertEquals(-1, MathUtil.getInverseModP(4, 16));
    }

    // Testing log2 method:
    @Test(expected = IllegalArgumentException.class)
    public void log2ExpectedExceptionTest() {
        MathUtil.log2(-1);
    }

    @Test
    public void log2Test1() {
        assertEquals(3, MathUtil.log2(8));
    }

    @Test
    public void log2Test2() {
        assertEquals(3, MathUtil.log2(13));
    }

    @Test
    public void log2Test3() {
        assertEquals(1, MathUtil.log2(2));
    }

    @Test
    public void log2Test4() {
        assertEquals(0, MathUtil.log2(1));
    }

    @Test
    public void log2Test5() {
        assertEquals(7, MathUtil.log2(170));
    }

    // Testing isOne method:
    @Test(expected = IllegalArgumentException.class)
    public void isOneExpectedExceptionTest1() {
        MathUtil.isOne(-1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isOneExpectedExceptionTest2() {
        MathUtil.isOne(3, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isOneExpectedExceptionTest3() {
        MathUtil.isOne(3, 32);
    }

    @Test
    public void isOneTest1() {
        assertEquals(false, MathUtil.isOne(0, 0));
    }

    @Test
    public void isOneTest2() {
        assertEquals(false, MathUtil.isOne(4, 0));
    }

    @Test
    public void isOneTest3() {
        assertEquals(false, MathUtil.isOne(8, 2));
    }

    @Test
    public void isOneTest4() {
        assertEquals(true, MathUtil.isOne(17, 0));
    }

    @Test
    public void isOneTest5() {
        assertEquals(true, MathUtil.isOne(32, 5));
    }

    @Test
    public void isOneTest6() {
        assertEquals(true, MathUtil.isOne(130, 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculatorExpectedExceptionTestModulo() {
        MathUtil.powModulo(1, 1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculatorExpectedExceptionTestExponent() {
        MathUtil.powModulo(1, -1, 2);
    }

    @Test
    public void calculatorModuloTest1() {
        assertEquals(3, MathUtil.powModulo(2, 3, 5));
    }

    @Test
    public void calculatorModuloTest2() {
        assertEquals(3, MathUtil.powModulo(7, 3, 4));
    }

    @Test
    public void calculatorModuloTest3() {
        assertEquals(5, MathUtil.powModulo(170, 393, 47));
    }

    @Test
    public void calculatorModuloTest4() {
        assertEquals(2069, MathUtil.powModulo(6758, 3393, 8693));
    }

    @Test
    public void calculatorModuloTest5() {
        assertEquals(190, MathUtil.powModulo(-215, 1172, 495));
    }

    @Test
    public void calculatorModuloTest6() {
        assertEquals(550, MathUtil.powModulo(-25860, 55535, 1235));
    }

    @Test
    public void calculatorModuloTest7() {
        assertEquals(59657, MathUtil.powModulo(10, 1485, 72799));
    }

    @Test
    public void calculatorModuloTest8() {
        assertEquals(9855, MathUtil.powModulo(165, 4485, 32799));
    }    
}
