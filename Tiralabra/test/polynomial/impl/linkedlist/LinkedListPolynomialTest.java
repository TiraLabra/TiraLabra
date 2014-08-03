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
    public void testSubtractWithDifferentCharacteristic() {
        int characteristic = 5;        
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);
        polynomial.addTerm(1, 1);        
        
        int characteristic2 = 11;        
        LinkedListPolynomial polynomial2 = new LinkedListPolynomial(characteristic2);  
        
        IPolynomial result = polynomial.subtract(polynomial2);        
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
    public void testDivideWithDifferentCharacteristic() {
        int characteristic = 5;        
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);
        polynomial.addTerm(1, 4);        
        
        int characteristic2 = 11;        
        LinkedListPolynomial polynomial2 = new LinkedListPolynomial(characteristic2);  
        polynomial.addTerm(-1, 4);             
        
        DivisionResult result = polynomial.divide(polynomial2);         
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