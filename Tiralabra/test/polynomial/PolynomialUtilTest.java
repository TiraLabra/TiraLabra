package polynomial;

import static org.junit.Assert.*;
import org.junit.Test;
import polynomial.impl.array.ArrayPolynomial;
import polynomial.impl.linkedlist.LinkedListPolynomial;

/**
 *
 * @author Sebastian Björkqvist
 */
public class PolynomialUtilTest {

    public PolynomialUtilTest() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGCDNullFirst() {
        IPolynomial a = null;
        IPolynomial b = new ArrayPolynomial(3);
        
        PolynomialUtil.gcd(a, b);
    }    
    
    @Test(expected = IllegalArgumentException.class)
    public void testGCDNullSecond() {
        IPolynomial a = new ArrayPolynomial(3);
        IPolynomial b = null;
        
        PolynomialUtil.gcd(a, b);
    }       
    
    @Test(expected = IllegalArgumentException.class)
    public void testGCDDifferentCharacteristic() {
        IPolynomial a = new ArrayPolynomial(2);
        IPolynomial b = new ArrayPolynomial(3);
        
        PolynomialUtil.gcd(a, b);
    }

    @Test
    public void testGCDWithSelf() {
        IPolynomial a = new ArrayPolynomial(2);
        
        a.addTerm(1, 1);
        
        IPolynomial gcd = PolynomialUtil.gcd(a, a);
        
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
        
        IPolynomial gcd = PolynomialUtil.gcd(a, b);
        
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
        
        IPolynomial gcd = PolynomialUtil.gcd(a, b);
        
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
        
        IPolynomial gcd = PolynomialUtil.gcd(a, b);
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
        
        IPolynomial gcd = PolynomialUtil.gcd(a, b);
        
        IPolynomial expected = new ArrayPolynomial(5);
        expected.addTerm(1, 3);
        expected.addTerm(1, 2);
        expected.addTerm(1, 0);
                
        assertEquals(5, gcd.getCharacteristic());
        assertEquals(3, gcd.getDegree());
        assertEquals(3, gcd.getNumberOfNonZeroCoefficients());
        
        // The gcd is unique only up to an invertible constant, so we must multiply
        // the polynomial with all constants and check if it equals the expected result.
        
        for (int i = 1; i < characteristic; i++) {
            IPolynomial constant = new ArrayPolynomial(characteristic);
            constant.addTerm(i, 0);
            
            IPolynomial candidate = gcd.multiply(constant);
            
            boolean candidateEqualsExpected = true;
            
            for (int j = 0; j <= expected.getDegree(); j++) {
                if ((candidate.getCoefficientAtDegree(j) - expected.getCoefficientAtDegree(j)) % characteristic != 0) {
                    candidateEqualsExpected = false;
                }
            }
            
            if (candidateEqualsExpected) {
                // Found correct polynomial
                return;
            }
        }
        
        fail("Polynomial " + gcd + " isn't the same as the expected polynomial " + expected + " up to an invertible constant");
    }
    
    @Test
    public void testGCDCharacteristic7() {
        
        int characteristic = 7;
        
        IPolynomial polynomial = new ArrayPolynomial(characteristic);
        
        polynomial.addTerm(1, 0);
        polynomial.addTerm(1, 1);
        polynomial.addTerm(1, 3);
        
        IPolynomial polynomial2 = new ArrayPolynomial(characteristic);
        
        polynomial2.addTerm(1, 0);
        polynomial2.addTerm(2, 2);
        polynomial2.addTerm(1, 3);        
                
        IPolynomial polynomial3 = new ArrayPolynomial(characteristic);
        
        polynomial3.addTerm(1, 0);
        polynomial3.addTerm(2, 2);
        polynomial3.addTerm(-1, 3);        
        
        IPolynomial polynomial4 = polynomial.multiply(polynomial2);
        IPolynomial polynomial5 = polynomial.multiply(polynomial3);
        
        IPolynomial gcd = PolynomialUtil.gcd(polynomial4, polynomial5);        
        IPolynomial expected = polynomial;
        
        for (int i = 1; i < characteristic; i++) {
            IPolynomial constant = new ArrayPolynomial(characteristic);
            constant.addTerm(i, 0);
            
            IPolynomial candidate = gcd.multiply(constant);
            
            boolean candidateEqualsExpected = true;
            
            for (int j = 0; j <= expected.getDegree(); j++) {
                if ((candidate.getCoefficientAtDegree(j) - expected.getCoefficientAtDegree(j)) % characteristic != 0) {
                    candidateEqualsExpected = false;
                }
            }
            
            if (candidateEqualsExpected) {
                // Found correct polynomial
                return;
            }
        }
        
        fail("Polynomial " + gcd + " isn't the same as the expected polynomial " + expected + " up to an invertible constant");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testIsReducibleNull() {
        PolynomialUtil.isReducible(null, false);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testIsReducibleCharacteristic0() {
        int characteristic = 0;
        
        IPolynomial polynomial = new ArrayPolynomial(characteristic);
        
        PolynomialUtil.isReducible(polynomial, false);
    }
    
    @Test
    public void testIsReducible() {
        int characteristic = 2;
        
        IPolynomial polynomial = new ArrayPolynomial(characteristic);
        polynomial.addTerm(1, 0);
        
        assertFalse(PolynomialUtil.isReducible(polynomial, false));
    }
    
    @Test
    public void testIsReducible2() {
        int characteristic = 7;
        
        IPolynomial polynomial = new LinkedListPolynomial(characteristic);
        polynomial.addTerm(1, 0);
        polynomial.addTerm(1, 1);
        
        assertFalse(PolynomialUtil.isReducible(polynomial, false));
    }
    
    @Test
    public void testIsReducible3() {
        int characteristic = 5;
        
        IPolynomial polynomial = new ArrayPolynomial(characteristic);
        polynomial.addTerm(1, 0);
        polynomial.addTerm(2, 1);
        polynomial.addTerm(1, 2);        
        
        assertTrue(PolynomialUtil.isReducible(polynomial, false));
    }
    
    @Test
    public void testIsReducible4() {
        int characteristic = 2;
        
        IPolynomial polynomial = new ArrayPolynomial(characteristic);
        polynomial.addTerm(1, 0);
        polynomial.addTerm(1, 2);        
        
        assertTrue(PolynomialUtil.isReducible(polynomial, false));
    }
    
    @Test
    public void testIsReducible5() {
        int characteristic = 7;
        
        IPolynomial polynomial = new ArrayPolynomial(characteristic);
        polynomial.addTerm(1, 0);
        polynomial.addTerm(1, 1);
        polynomial.addTerm(1, 3);      
        
        assertFalse(PolynomialUtil.isReducible(polynomial, false));
    }
    
    @Test
    public void testIsReducible6() {
        int characteristic = 7;
        
        IPolynomial polynomial = new LinkedListPolynomial(characteristic);
        polynomial.addTerm(1, 0);
        polynomial.addTerm(2, 2);
        polynomial.addTerm(-1, 3);     
        
        assertFalse(PolynomialUtil.isReducible(polynomial, false));
    }
    
    @Test
    public void testIsReducible7() {
        int characteristic = 2;
        
        IPolynomial polynomial = new ArrayPolynomial(characteristic);
        polynomial.addTerm(1, 0);
        polynomial.addTerm(1, 1);
        polynomial.addTerm(1, 4);     
        
        assertFalse(PolynomialUtil.isReducible(polynomial, false));
    }
    
    @Test
    public void testIsReducible8() {
        int characteristic = 2;
        
        IPolynomial polynomial = new ArrayPolynomial(characteristic);
        polynomial.addTerm(1, 0);
        polynomial.addTerm(1, 1);
        polynomial.addTerm(1, 5);     
        
        assertTrue(PolynomialUtil.isReducible(polynomial, false));
    }

}