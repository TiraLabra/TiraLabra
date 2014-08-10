package polynomial.impl.linkedlist;

import org.junit.Test;
import static org.junit.Assert.*;
import polynomial.DivisionResult;
import polynomial.IPolynomial;

/**
 *
 * @author Sebastian Björkqvist
 */
public class LinkedListPolynomialTest {

    public LinkedListPolynomialTest() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeCharacteristic() {
        int characteristic = -1;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);
    }

    @Test
    public void testGetCharacteristic() {
        int characteristic = 3;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);

        assertEquals(characteristic, polynomial.getCharacteristic());
    }

    @Test
    public void testGetCharacteristic2() {
        int characteristic = 0;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);

        assertEquals(characteristic, polynomial.getCharacteristic());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCoefficientAtNegativeDegree() {
        int characteristic = 3;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);

        polynomial.addTerm(2, 0);
        polynomial.getCoefficientAtDegree(-1);
    }

    @Test
    public void testAddTerm() {
        int characteristic = 5;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);

        polynomial.addTerm(2, 0);
        polynomial.addTerm(3, 3);

        assertEquals(2, polynomial.getCoefficientAtDegree(0));
        assertEquals(3, polynomial.getCoefficientAtDegree(3));

        polynomial.addTerm(3, 3);

        assertEquals(1, polynomial.getCoefficientAtDegree(3));
    }

    @Test
    public void testAddTerm2() {
        int characteristic = 3;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);

        assertEquals(0, polynomial.getCoefficientAtDegree(0));
        polynomial.addTerm(2, 0);
        assertEquals(2, polynomial.getCoefficientAtDegree(0));
        polynomial.addTerm(0, 0);
        assertEquals(2, polynomial.getCoefficientAtDegree(0));
        polynomial.addTerm(-2, 0);
        assertEquals(0, polynomial.getCoefficientAtDegree(0));
    }

    @Test
    public void testAddTerm3() {
        int characteristic = 6;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);

        polynomial.addTerm(2, 1);
        assertEquals(2, polynomial.getCoefficientAtDegree(1));
        assertEquals(1, polynomial.getNumberOfNonZeroCoefficients());
        assertEquals(1, polynomial.getDegree());
        assertEquals(2, polynomial.getCoefficientOfLeadingTerm());

        polynomial.addTerm(3, 5);
        assertEquals(2, polynomial.getCoefficientAtDegree(1));
        assertEquals(3, polynomial.getCoefficientAtDegree(5));
        assertEquals(5, polynomial.getDegree());
        assertEquals(2, polynomial.getNumberOfNonZeroCoefficients());
        assertEquals(3, polynomial.getCoefficientOfLeadingTerm());

        polynomial.addTerm(4, 0);
        assertEquals(4, polynomial.getCoefficientAtDegree(0));
        assertEquals(2, polynomial.getCoefficientAtDegree(1));
        assertEquals(3, polynomial.getCoefficientAtDegree(5));
        assertEquals(5, polynomial.getDegree());
        assertEquals(3, polynomial.getNumberOfNonZeroCoefficients());
        assertEquals(3, polynomial.getCoefficientOfLeadingTerm());

        polynomial.addTerm(2, 3);
        assertEquals(4, polynomial.getCoefficientAtDegree(0));
        assertEquals(2, polynomial.getCoefficientAtDegree(1));
        assertEquals(2, polynomial.getCoefficientAtDegree(3));
        assertEquals(3, polynomial.getCoefficientAtDegree(5));
        assertEquals(5, polynomial.getDegree());
        assertEquals(4, polynomial.getNumberOfNonZeroCoefficients());
        assertEquals(3, polynomial.getCoefficientOfLeadingTerm());

        polynomial.addTerm(5, 3);
        assertEquals(4, polynomial.getCoefficientAtDegree(0));
        assertEquals(2, polynomial.getCoefficientAtDegree(1));
        assertEquals(1, polynomial.getCoefficientAtDegree(3));
        assertEquals(3, polynomial.getCoefficientAtDegree(5));
        assertEquals(5, polynomial.getDegree());
        assertEquals(4, polynomial.getNumberOfNonZeroCoefficients());
        assertEquals(3, polynomial.getCoefficientOfLeadingTerm());

        polynomial.addTerm(1, 4);
        assertEquals(4, polynomial.getCoefficientAtDegree(0));
        assertEquals(2, polynomial.getCoefficientAtDegree(1));
        assertEquals(1, polynomial.getCoefficientAtDegree(3));
        assertEquals(1, polynomial.getCoefficientAtDegree(4));
        assertEquals(3, polynomial.getCoefficientAtDegree(5));
        assertEquals(5, polynomial.getDegree());
        assertEquals(5, polynomial.getNumberOfNonZeroCoefficients());
        assertEquals(3, polynomial.getCoefficientOfLeadingTerm());

        polynomial.addTerm(2, 7);
        assertEquals(4, polynomial.getCoefficientAtDegree(0));
        assertEquals(2, polynomial.getCoefficientAtDegree(1));
        assertEquals(1, polynomial.getCoefficientAtDegree(3));
        assertEquals(1, polynomial.getCoefficientAtDegree(4));
        assertEquals(3, polynomial.getCoefficientAtDegree(5));
        assertEquals(2, polynomial.getCoefficientAtDegree(7));
        assertEquals(7, polynomial.getDegree());
        assertEquals(6, polynomial.getNumberOfNonZeroCoefficients());
        assertEquals(2, polynomial.getCoefficientOfLeadingTerm());

        polynomial.addTerm(-1, 4);
        assertEquals(4, polynomial.getCoefficientAtDegree(0));
        assertEquals(2, polynomial.getCoefficientAtDegree(1));
        assertEquals(1, polynomial.getCoefficientAtDegree(3));
        assertEquals(0, polynomial.getCoefficientAtDegree(4));
        assertEquals(3, polynomial.getCoefficientAtDegree(5));
        assertEquals(7, polynomial.getDegree());
        assertEquals(5, polynomial.getNumberOfNonZeroCoefficients());
        assertEquals(2, polynomial.getCoefficientOfLeadingTerm());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddTermNegativeDegree() {
        int characteristic = 5;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);

        polynomial.addTerm(4, -1);
    }

    @Test
    public void testRemoveTerm() {
        int characteristic = 5;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);

        polynomial.addTerm(2, 0);
        polynomial.addTerm(1, 3);

        assertEquals(2, polynomial.getCoefficientAtDegree(0));
        assertEquals(1, polynomial.getCoefficientAtDegree(3));

        polynomial.removeTerm(1);
        assertEquals(2, polynomial.getCoefficientAtDegree(0));
        assertEquals(1, polynomial.getCoefficientAtDegree(3));

        polynomial.removeTerm(3);
        assertEquals(2, polynomial.getCoefficientAtDegree(0));
        assertEquals(0, polynomial.getCoefficientAtDegree(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveTermNegativeDegree() {
        int characteristic = 5;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);

        polynomial.removeTerm(-1);
    }

    @Test
    public void testGetDegree() {
        int characteristic = 7;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);

        assertEquals(0, polynomial.getDegree());
        polynomial.addTerm(3, 2);
        assertEquals(2, polynomial.getDegree());
        polynomial.addTerm(6, 1);
        assertEquals(2, polynomial.getDegree());
    }

    @Test
    public void testGetNumberOfNonZeroCoefficients() {
        int characteristic = 7;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);

        assertEquals(0, polynomial.getNumberOfNonZeroCoefficients());

        polynomial.addTerm(3, 2);
        assertEquals(1, polynomial.getNumberOfNonZeroCoefficients());
        polynomial.addTerm(3, 1);
        assertEquals(2, polynomial.getNumberOfNonZeroCoefficients());
        polynomial.addTerm(1, 1);
        assertEquals(2, polynomial.getNumberOfNonZeroCoefficients());
        polynomial.addTerm(6, 0);
        assertEquals(3, polynomial.getNumberOfNonZeroCoefficients());
    }

    @Test
    public void testGetNumberOfNonZeroCoefficients2() {
        int characteristic = 11;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);

        assertEquals(0, polynomial.getNumberOfNonZeroCoefficients());

        polynomial.addTerm(3, 2);
        assertEquals(1, polynomial.getNumberOfNonZeroCoefficients());
        polynomial.addTerm(3, 1);
        assertEquals(2, polynomial.getNumberOfNonZeroCoefficients());
        polynomial.removeTerm(1);
        assertEquals(1, polynomial.getNumberOfNonZeroCoefficients());
        polynomial.addTerm(6, 0);
        assertEquals(2, polynomial.getNumberOfNonZeroCoefficients());
        polynomial.removeTerm(4);
        assertEquals(2, polynomial.getNumberOfNonZeroCoefficients());
    }

    @Test
    public void testGetCoefficientOfLeadingTerm() {
        int characteristic = 7;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);

        assertEquals(0, polynomial.getCoefficientOfLeadingTerm());

        polynomial.addTerm(6, 0);
        assertEquals(6, polynomial.getCoefficientOfLeadingTerm());

        polynomial.addTerm(3, 2);
        assertEquals(3, polynomial.getCoefficientOfLeadingTerm());

        polynomial.addTerm(4, 1);
        assertEquals(3, polynomial.getCoefficientOfLeadingTerm());
    }

    @Test
    public void testEvaluateCharacteristic0() {
        int characteristic = 0;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);

        assertEquals(0, polynomial.evaluate(0));
        assertEquals(0, polynomial.evaluate(1));
        assertEquals(0, polynomial.evaluate(-1));
        assertEquals(0, polynomial.evaluate(2));

        polynomial.addTerm(4, 0);
        assertEquals(4, polynomial.evaluate(0));
        assertEquals(4, polynomial.evaluate(1));
        assertEquals(4, polynomial.evaluate(-1));
        assertEquals(4, polynomial.evaluate(2));

        polynomial.addTerm(3, 1);
        assertEquals(4, polynomial.evaluate(0));
        assertEquals(7, polynomial.evaluate(1));
        assertEquals(1, polynomial.evaluate(-1));
        assertEquals(10, polynomial.evaluate(2));

        polynomial.addTerm(2, 2);
        assertEquals(4, polynomial.evaluate(0));
        assertEquals(9, polynomial.evaluate(1));
        assertEquals(3, polynomial.evaluate(-1));
        assertEquals(18, polynomial.evaluate(2));
    }

    @Test
    public void testEvaluateCharacteristic2() {
        int characteristic = 2;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);

        assertEquals(0, polynomial.evaluate(0));
        assertEquals(0, polynomial.evaluate(1));
        assertEquals(0, polynomial.evaluate(-1));
        assertEquals(0, polynomial.evaluate(2));

        polynomial.addTerm(1, 0);
        assertEquals(1, polynomial.evaluate(0));
        assertEquals(1, polynomial.evaluate(1));
        assertEquals(1, polynomial.evaluate(-1));
        assertEquals(1, polynomial.evaluate(2));

        polynomial.addTerm(1, 1);
        assertEquals(1, polynomial.evaluate(0));
        assertEquals(0, polynomial.evaluate(1));
        assertEquals(0, polynomial.evaluate(-1));
        assertEquals(1, polynomial.evaluate(2));

        polynomial.addTerm(2, 2);
        assertEquals(1, polynomial.evaluate(0));
        assertEquals(0, polynomial.evaluate(1));
        assertEquals(0, polynomial.evaluate(-1));
        assertEquals(1, polynomial.evaluate(2));

        polynomial.addTerm(1, 2);
        assertEquals(1, polynomial.evaluate(0));
        assertEquals(1, polynomial.evaluate(1));
        assertEquals(1, polynomial.evaluate(-1));
        assertEquals(1, polynomial.evaluate(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddWithDifferentCharacteristic() {
        int characteristic = 2;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);
        polynomial.addTerm(1, 1);

        int characteristic2 = 3;
        LinkedListPolynomial polynomial2 = new LinkedListPolynomial(characteristic2);
        polynomial.addTerm(1, 3);

        IPolynomial result = polynomial.add(polynomial2);
    }
    
    @Test(expected = IllegalArgumentException.class) 
    public void testAddNull() {
        int characteristic = 2;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);
        polynomial.addTerm(1, 1);
        
        IPolynomial result = polynomial.add(null);
    }

    @Test
    public void testAddZeroPolynomial() {
        int characteristic = 3;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);
        polynomial.addTerm(2, 0);
        polynomial.addTerm(1, 1);

        LinkedListPolynomial polynomial2 = new LinkedListPolynomial(characteristic);

        IPolynomial result = polynomial.add(polynomial2);

        assertEquals(2, result.getCoefficientAtDegree(0));
        assertEquals(1, result.getCoefficientAtDegree(1));
        assertEquals(1, result.getDegree());
        assertEquals(2, result.getNumberOfNonZeroCoefficients());
    }

    @Test
    public void testAddInverse() {
        int characteristic = 5;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);
        polynomial.addTerm(3, 0);
        polynomial.addTerm(1, 1);
        polynomial.addTerm(2, 3);

        LinkedListPolynomial polynomial2 = new LinkedListPolynomial(characteristic);
        polynomial.addTerm(-3, 0);
        polynomial.addTerm(-1, 1);
        polynomial.addTerm(-2, 3);

        IPolynomial result = polynomial.add(polynomial2);

        assertEquals(0, result.getDegree());
        assertEquals(0, result.getNumberOfNonZeroCoefficients());
    }

    @Test
    public void testAdd() {
        int characteristic = 7;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);
        polynomial.addTerm(2, 0);
        polynomial.addTerm(1, 1);
        polynomial.addTerm(3, 4);

        LinkedListPolynomial polynomial2 = new LinkedListPolynomial(characteristic);
        polynomial.addTerm(1, 1);
        polynomial.addTerm(2, 3);
        polynomial.addTerm(4, 6);

        IPolynomial result = polynomial.add(polynomial2);

        assertEquals(2, result.getCoefficientAtDegree(0));
        assertEquals(2, result.getCoefficientAtDegree(1));
        assertEquals(2, result.getCoefficientAtDegree(3));
        assertEquals(3, result.getCoefficientAtDegree(4));
        assertEquals(4, result.getCoefficientAtDegree(6));
        assertEquals(6, result.getDegree());
        assertEquals(5, result.getNumberOfNonZeroCoefficients());
    }

    @Test
    public void testAdd2() {
        int characteristic = 0;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);
        polynomial.addTerm(3, 0);
        polynomial.addTerm(2, 2);
        polynomial.addTerm(11, 11);
        polynomial.addTerm(5, 5);

        LinkedListPolynomial polynomial2 = new LinkedListPolynomial(characteristic);
        polynomial.addTerm(1, 1);
        polynomial.addTerm(4, 6);
        polynomial.addTerm(2, 3);
        polynomial.addTerm(3, 8);

        IPolynomial result = polynomial.add(polynomial2);

        assertEquals(3, result.getCoefficientAtDegree(0));
        assertEquals(1, result.getCoefficientAtDegree(1));
        assertEquals(2, result.getCoefficientAtDegree(2));
        assertEquals(2, result.getCoefficientAtDegree(3));
        assertEquals(5, result.getCoefficientAtDegree(5));
        assertEquals(4, result.getCoefficientAtDegree(6));
        assertEquals(3, result.getCoefficientAtDegree(8));
        assertEquals(11, result.getCoefficientAtDegree(11));
        assertEquals(11, result.getDegree());
        assertEquals(8, result.getNumberOfNonZeroCoefficients());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSubtractWithDifferentCharacteristic() {
        int characteristic = 5;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);
        polynomial.addTerm(1, 1);

        int characteristic2 = 11;
        LinkedListPolynomial polynomial2 = new LinkedListPolynomial(characteristic2);

        IPolynomial result = polynomial.subtract(polynomial2);
    }
    
    @Test(expected = IllegalArgumentException.class) 
    public void testSubtractNull() {
        int characteristic = 2;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);
        polynomial.addTerm(1, 1);
        
        IPolynomial result = polynomial.subtract(null);
    }

    @Test
    public void testSubtractZeroPolynomial() {
        int characteristic = 3;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);
        polynomial.addTerm(2, 0);
        polynomial.addTerm(1, 1);

        LinkedListPolynomial polynomial2 = new LinkedListPolynomial(characteristic);

        IPolynomial result = polynomial.subtract(polynomial2);

        assertEquals(2, result.getCoefficientAtDegree(0));
        assertEquals(1, result.getCoefficientAtDegree(1));
        assertEquals(1, result.getDegree());
        assertEquals(2, result.getNumberOfNonZeroCoefficients());
    }

    @Test
    public void testSubtractSelf() {
        int characteristic = 3;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);
        polynomial.addTerm(2, 1);
        polynomial.addTerm(1, 3);

        IPolynomial result = polynomial.subtract(polynomial);

        assertEquals(0, result.getDegree());
        assertEquals(0, result.getNumberOfNonZeroCoefficients());
    }

    @Test
    public void testSubtract() {
        int characteristic = 7;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);
        polynomial.addTerm(2, 0);
        polynomial.addTerm(3, 2);
        polynomial.addTerm(1, 1);

        LinkedListPolynomial polynomial2 = new LinkedListPolynomial(characteristic);
        polynomial2.addTerm(1, 0);
        polynomial2.addTerm(4, 2);

        IPolynomial result = polynomial.subtract(polynomial2);

        assertEquals(1, result.getCoefficientAtDegree(0));
        assertEquals(1, result.getCoefficientAtDegree(1));
        assertEquals(-1, result.getCoefficientAtDegree(2));
        assertEquals(2, result.getDegree());
        assertEquals(3, result.getNumberOfNonZeroCoefficients());
    }

    @Test
    public void testSubtract2() {
        int characteristic = 11;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);
        polynomial.addTerm(4, 4);
        polynomial.addTerm(2, 0);
        polynomial.addTerm(3, 3);

        LinkedListPolynomial polynomial2 = new LinkedListPolynomial(characteristic);
        polynomial2.addTerm(2, 1);
        polynomial2.addTerm(4, 4);

        IPolynomial result = polynomial.subtract(polynomial2);

        assertEquals(2, result.getCoefficientAtDegree(0));
        assertEquals(-2, result.getCoefficientAtDegree(1));
        assertEquals(3, result.getCoefficientAtDegree(3));
        assertEquals(3, result.getDegree());
        assertEquals(3, result.getNumberOfNonZeroCoefficients());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMultiplyWithDifferentCharacteristic() {
        int characteristic = 4;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);
        polynomial.addTerm(2, 6);

        int characteristic2 = 13;
        LinkedListPolynomial polynomial2 = new LinkedListPolynomial(characteristic2);

        IPolynomial result = polynomial.multiply(polynomial2);
    }
    
    @Test(expected = IllegalArgumentException.class) 
    public void testMultiplyNull() {
        int characteristic = 2;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);
        polynomial.addTerm(1, 1);
        
        IPolynomial result = polynomial.multiply(null);
    }

    @Test
    public void testMultiplyWithZeroPolynomial() {
        int characteristic = 0;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);
        polynomial.addTerm(1, 4);
        polynomial.addTerm(2, 3);
        polynomial.addTerm(5, 1);
        polynomial.addTerm(3, 0);

        LinkedListPolynomial polynomial2 = new LinkedListPolynomial(characteristic);
        
        IPolynomial result = polynomial.multiply(polynomial2);
        
        assertEquals(0, result.getDegree());
        assertEquals(0, result.getNumberOfNonZeroCoefficients());
    }

    @Test
    public void testMultiplyWithOne() {
        int characteristic = 0;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);
        polynomial.addTerm(1, 4);
        polynomial.addTerm(2, 2);
        polynomial.addTerm(5, 1);
        polynomial.addTerm(3, 0);

        LinkedListPolynomial polynomial2 = new LinkedListPolynomial(characteristic);
        polynomial2.addTerm(1, 0);
        
        IPolynomial result = polynomial.multiply(polynomial2);
        
        assertEquals(4, result.getDegree());
        assertEquals(4, result.getNumberOfNonZeroCoefficients());
        assertEquals(3, result.getCoefficientAtDegree(0));
        assertEquals(5, result.getCoefficientAtDegree(1));
        assertEquals(2, result.getCoefficientAtDegree(2));
        assertEquals(1, result.getCoefficientAtDegree(4));
    }

    @Test
    public void testMultiply() {
        int characteristic = 0;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);
        polynomial.addTerm(1, 2);
        polynomial.addTerm(2, 1);
        polynomial.addTerm(1, 0);

        LinkedListPolynomial polynomial2 = new LinkedListPolynomial(characteristic);
        polynomial2.addTerm(1, 3);
        polynomial2.addTerm(4, 1);
        
        IPolynomial result = polynomial.multiply(polynomial2);
        
        assertEquals(5, result.getDegree());
        assertEquals(5, result.getNumberOfNonZeroCoefficients());
        assertEquals(4, result.getCoefficientAtDegree(1));
        assertEquals(8, result.getCoefficientAtDegree(2));
        assertEquals(5, result.getCoefficientAtDegree(3));
        assertEquals(2, result.getCoefficientAtDegree(4));
        assertEquals(1, result.getCoefficientAtDegree(5));
    }

    @Test
    public void testMultiply2() {
        int characteristic = 5;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);
        polynomial.addTerm(1, 4);
        polynomial.addTerm(2, 3);
        polynomial.addTerm(1, 2);
        polynomial.addTerm(1, 1);
        polynomial.addTerm(3, 0);

        LinkedListPolynomial polynomial2 = new LinkedListPolynomial(characteristic);
        polynomial2.addTerm(2, 2);
        polynomial2.addTerm(1, 1);
        polynomial2.addTerm(4, 0);
        
        IPolynomial result = polynomial.multiply(polynomial2);
        
        assertEquals(6, result.getDegree());
        assertEquals(6, result.getNumberOfNonZeroCoefficients());
        assertEquals(2, result.getCoefficientAtDegree(0));
        assertEquals(2, result.getCoefficientAtDegree(1));
        assertEquals(1, result.getCoefficientAtDegree(2));
        assertEquals(1, result.getCoefficientAtDegree(3));
        assertEquals(3, result.getCoefficientAtDegree(4));
        assertEquals(2, result.getCoefficientAtDegree(6));
    }

    @Test
    public void testMultiply3() {
        int characteristic = 2;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);
        polynomial.addTerm(1, 5);
        polynomial.addTerm(1, 3);
        polynomial.addTerm(1, 2);
        polynomial.addTerm(1, 1);

        LinkedListPolynomial polynomial2 = new LinkedListPolynomial(characteristic);
        polynomial2.addTerm(1, 7);
        polynomial2.addTerm(1, 1);
        polynomial2.addTerm(1, 0);
        
        IPolynomial result = polynomial.multiply(polynomial2);
        
        assertEquals(12, result.getDegree());
        assertEquals(8, result.getNumberOfNonZeroCoefficients());
        assertEquals(1, result.getCoefficientAtDegree(1));
        assertEquals(1, result.getCoefficientAtDegree(4));
        assertEquals(1, result.getCoefficientAtDegree(5));
        assertEquals(1, result.getCoefficientAtDegree(6));
        assertEquals(1, result.getCoefficientAtDegree(8));
        assertEquals(1, result.getCoefficientAtDegree(9));
        assertEquals(1, result.getCoefficientAtDegree(10));
        assertEquals(1, result.getCoefficientAtDegree(12));

        
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivideWithDifferentCharacteristic() {
        int characteristic = 5;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);
        polynomial.addTerm(1, 4);

        int characteristic2 = 11;
        LinkedListPolynomial polynomial2 = new LinkedListPolynomial(characteristic2);
        polynomial.addTerm(-1, 4);

        DivisionResult result = polynomial.divide(polynomial2);
    }
    
    @Test(expected = IllegalArgumentException.class) 
    public void testDivideNull() {
        int characteristic = 2;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);
        polynomial.addTerm(1, 1);
        
        DivisionResult result = polynomial.divide(null);
    }
    
    @Test(expected = IllegalArgumentException.class) 
    public void testDivideZeroPolynomial() {
        int characteristic = 5;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);
        polynomial.addTerm(1, 1);
        
        LinkedListPolynomial divisor = new LinkedListPolynomial(characteristic);
        
        DivisionResult result = polynomial.divide(divisor);
    }
    
    @Test
    public void testDivideSquare() {
        int characteristic = 5;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);
        polynomial.addTerm(1, 2);
        polynomial.addTerm(2, 1);
        polynomial.addTerm(1, 0);
        
        LinkedListPolynomial divisor = new LinkedListPolynomial(characteristic);
        divisor.addTerm(1, 1);
        divisor.addTerm(1, 0);        
        
        DivisionResult result = polynomial.divide(divisor);
        
        IPolynomial quotient = result.quotient;
                
        assertEquals(1, quotient.getDegree());
        assertEquals(2, quotient.getNumberOfNonZeroCoefficients());        
        assertEquals(1, quotient.getCoefficientAtDegree(0));        
        assertEquals(1, quotient.getCoefficientAtDegree(1));
        
        IPolynomial remainder = result.remainder;
        
        assertEquals(0, remainder.getDegree());
        assertEquals(0, remainder.getNumberOfNonZeroCoefficients());          
    }
    
    @Test
    public void testDivideCube() {
        int characteristic = 5;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);
        polynomial.addTerm(1, 3);
        polynomial.addTerm(1, 2);
        polynomial.addTerm(2, 1);
        polynomial.addTerm(-2, 0);
        
        LinkedListPolynomial divisor = new LinkedListPolynomial(characteristic);
        divisor.addTerm(1, 2);
        divisor.addTerm(-1, 1);        
        divisor.addTerm(-1, 0);
        
        DivisionResult result = polynomial.divide(divisor);
        
        IPolynomial quotient = result.quotient;
                
        assertEquals(1, quotient.getDegree());
        assertEquals(2, quotient.getNumberOfNonZeroCoefficients());        
        assertTrue((2 - quotient.getCoefficientAtDegree(0)) % characteristic == 0);        
        assertTrue((1 - quotient.getCoefficientAtDegree(1)) % characteristic == 0);
        
        IPolynomial remainder = result.remainder;
        
        assertEquals(0, remainder.getDegree());
        assertEquals(0, remainder.getNumberOfNonZeroCoefficients());          
    }
    
    @Test
    public void testDivide() {
        int characteristic = 7;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);
        polynomial.addTerm(1, 3);
        polynomial.addTerm(1, 1);
        polynomial.addTerm(1, 0);
        
        LinkedListPolynomial divisor = new LinkedListPolynomial(characteristic);
        divisor.addTerm(2, 1);        
        divisor.addTerm(2, 0);
        
        DivisionResult result = polynomial.divide(divisor);
        
        IPolynomial quotient = result.quotient;
                
        assertEquals(2, quotient.getDegree());
        assertEquals(3, quotient.getNumberOfNonZeroCoefficients());        
        assertTrue((4 - quotient.getCoefficientAtDegree(2)) % characteristic == 0);        
        assertTrue((3 - quotient.getCoefficientAtDegree(1)) % characteristic == 0);
        assertTrue((1 - quotient.getCoefficientAtDegree(0)) % characteristic == 0);        
        
        IPolynomial remainder = result.remainder;
        
        assertEquals(0, remainder.getDegree());
        assertEquals(1, remainder.getNumberOfNonZeroCoefficients()); 
        assertTrue((-1 - remainder.getCoefficientAtDegree(0)) % characteristic == 0); 
    }
    
    @Test
    public void testDivide2() {
        int characteristic = 2;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);
        polynomial.addTerm(1, 5);
        polynomial.addTerm(1, 3);
        polynomial.addTerm(1, 1);
        polynomial.addTerm(1, 0);
        
        LinkedListPolynomial divisor = new LinkedListPolynomial(characteristic);
        divisor.addTerm(1, 2);        
        divisor.addTerm(1, 0);
        
        DivisionResult result = polynomial.divide(divisor);
        
        IPolynomial quotient = result.quotient;
                
        assertEquals(3, quotient.getDegree());
        assertEquals(1, quotient.getNumberOfNonZeroCoefficients());        
        assertEquals(1, quotient.getCoefficientAtDegree(3));        
        
        IPolynomial remainder = result.remainder;
        
        assertEquals(1, remainder.getDegree());
        assertEquals(2, remainder.getNumberOfNonZeroCoefficients()); 
        assertEquals(1, remainder.getCoefficientAtDegree(0)); 
        assertEquals(1, remainder.getCoefficientAtDegree(1)); 
    }

    @Test
    public void testToStringEmptyPolynomial() {
        int characteristic = 5;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);

        String toString = polynomial.toString();

        assertNotNull(toString);
        assertEquals("", toString);
    }

    @Test
    public void testToString() {
        int characteristic = 2;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);
        polynomial.addTerm(1, 1);
        polynomial.addTerm(1, 3);

        String toString = polynomial.toString();

        assertEquals("x^3 + x", toString);

    }

    @Test
    public void testToString2() {
        int characteristic = 5;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);
        polynomial.addTerm(2, 0);
        polynomial.addTerm(1, 1);
        polynomial.addTerm(3, 2);

        String toString = polynomial.toString();

        assertEquals("3x^2 + x + 2", toString);
    }

    @Test
    public void testToString3() {
        int characteristic = 0;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);
        polynomial.addTerm(-2, 0);
        polynomial.addTerm(11, 1);
        polynomial.addTerm(2, 2);
        polynomial.addTerm(-2, 5);
        polynomial.addTerm(1, 7);

        String toString = polynomial.toString();

        assertEquals("x^7 - 2x^5 + 2x^2 + 11x - 2", toString);
    }
}
