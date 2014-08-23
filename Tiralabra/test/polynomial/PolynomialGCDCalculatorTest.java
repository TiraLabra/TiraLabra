package polynomial;

import org.junit.Test;
import static org.junit.Assert.*;
import polynomial.impl.array.ArrayPolynomial;

/**
 *
 * @author Sebastian Björkqvist
 */
public class PolynomialGCDCalculatorTest {

    public PolynomialGCDCalculatorTest() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDifferentCharacteristic() {
        IPolynomial a = new ArrayPolynomial(2);
        IPolynomial b = new ArrayPolynomial(3);
        
        PolynomialGCDCalculator.gcd(a, b);
    }

    @Test
    public void testGCDWithSelf() {
        IPolynomial a = new ArrayPolynomial(2);
        
        a.addTerm(1, 1);
        
        IPolynomial gcd = PolynomialGCDCalculator.gcd(a, a);
        
        assertEquals(2, gcd.getCharacteristic());
        assertEquals(1, gcd.getDegree());
        assertEquals(1, gcd.getCoefficientOfLeadingTerm());
        assertEquals(1, gcd.getNumberOfNonZeroCoefficients());
    }

    @Test
    public void testGCDWithOne() {
        IPolynomial a = new ArrayPolynomial(5);
        
        a.addTerm(1, 2);
        a.addTerm(1, 1);
        
        IPolynomial b = new ArrayPolynomial(5);  
        b.addTerm(1, 0);
        
        IPolynomial gcd = PolynomialGCDCalculator.gcd(a, b);
        
        assertEquals(5, gcd.getCharacteristic());
        assertEquals(0, gcd.getDegree());
        assertEquals(1, gcd.getCoefficientOfLeadingTerm());
        assertEquals(1, gcd.getNumberOfNonZeroCoefficients());
    }

    @Test
    public void testGCDWithZero() {
        IPolynomial a = new ArrayPolynomial(5);
        
        a.addTerm(1, 2);
        a.addTerm(1, 1);
        
        IPolynomial b = new ArrayPolynomial(5);  
        
        IPolynomial gcd = PolynomialGCDCalculator.gcd(a, b);
        
        assertEquals(5, gcd.getCharacteristic());
        assertEquals(2, gcd.getDegree());
        assertEquals(1, gcd.getCoefficientAtDegree(2));
        assertEquals(1, gcd.getCoefficientAtDegree(1));
        assertEquals(2, gcd.getNumberOfNonZeroCoefficients());
    }

    @Test
    public void testGCDCharacteristic2() {
        IPolynomial a = new ArrayPolynomial(2);  
        a.addTerm(1, 4);
        a.addTerm(1, 3);
        a.addTerm(1, 2);
        a.addTerm(1, 0);
        
        IPolynomial b = new ArrayPolynomial(2);
        b.addTerm(1, 3);
        b.addTerm(1, 0);        
        
        IPolynomial gcd = PolynomialGCDCalculator.gcd(a, b);
        assertEquals(2, gcd.getCharacteristic());
        assertEquals(1, gcd.getDegree());
        assertEquals(1, gcd.getCoefficientAtDegree(1));
        assertEquals(1, gcd.getCoefficientAtDegree(0));
        assertEquals(2, gcd.getNumberOfNonZeroCoefficients());
    }

    @Test
    public void testGCDCharacteristic5() {
        int characteristic = 5;
        IPolynomial a = new ArrayPolynomial(characteristic);  
        a.addTerm(1, 5);
        a.addTerm(1, 4);
        a.addTerm(2, 3);
        a.addTerm(-2, 2);
        a.addTerm(2, 0);
        
        IPolynomial b = new ArrayPolynomial(characteristic);
        b.addTerm(1, 6);
        b.addTerm(2, 5);        
        b.addTerm(-1, 3);
        b.addTerm(-1, 1); 
        b.addTerm(-1, 0);
        
        IPolynomial gcd = PolynomialGCDCalculator.gcd(a, b);
        
        IPolynomial expected = new ArrayPolynomial(5);
        expected.addTerm(1, 3);
        expected.addTerm(1, 2);
        expected.addTerm(1, 0);
                
        assertEquals(5, gcd.getCharacteristic());
        assertEquals(3, gcd.getDegree());
        assertEquals(3, gcd.getNumberOfNonZeroCoefficients());
        
        // The gcd is unique only up to an invertible constant, so we must multiply
        // the polynomial with all constants and check if it equals the expected result.
        
        for (int i = 1; i < 5; i++) {
            IPolynomial constant = new ArrayPolynomial(characteristic);
            constant.addTerm(i, 0);
            
            IPolynomial candidate = gcd.multiply(constant);
            
            if (((candidate.getCoefficientAtDegree(3) - 1) % characteristic == 0) &&
                    ((candidate.getCoefficientAtDegree(2) - 1) % characteristic == 0) &&
                    ((candidate.getCoefficientAtDegree(0) - 1) % characteristic == 0)) {
                // Found correct polynomial
                return;
            }
        }
        
        fail("Polynomial " + gcd + " isn't the same as the expected polynomial " + expected + " up to an invertible constant");
    }

}