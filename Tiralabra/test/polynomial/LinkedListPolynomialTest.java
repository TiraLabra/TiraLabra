package polynomial;

import org.junit.Test;
import static org.junit.Assert.*;

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
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddTermNegativeDegree() {
        int characteristic = 5;
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);
        
        polynomial.addTerm(2, -3);
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
    public void testGetCoefficientOfLeadingTerm() {
        int characteristic = 7;        
        LinkedListPolynomial polynomial = new LinkedListPolynomial(characteristic);  
        
        assertEquals(0, polynomial.getCoefficientOfLeadingTerm());        
        
        polynomial.addTerm(6, 0);    
        assertEquals(6, polynomial.getNumberOfNonZeroCoefficients());      
        
        polynomial.addTerm(3, 2);             
        assertEquals(3, polynomial.getNumberOfNonZeroCoefficients());      
        
        polynomial.addTerm(4, 1);     
        assertEquals(3, polynomial.getNumberOfNonZeroCoefficients());         
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
    

}