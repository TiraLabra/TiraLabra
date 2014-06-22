package TiraLabra.Number;

import static org.junit.Assert.*;
import org.junit.Test;

public class RealTest extends NumberTests<Real> {
    @Override
    public void setUp() {
        zero = new Real(0);
        one = new Real(1);
        two = new Real(2);
        four = new Real(4);
    }
    
    @Test
    public void decimalsDevelopedInAnUnusualManner() {
        Real n = new Real(new Integer(3), 0);
        assertEquals("3", n.toString());
        
        Real b = new Real(new Integer(15), 1);
        assertEquals("1.5", b.toString());
        
        n = n.divide(two);
        assertEquals(b, n);
    }
    
    @Test
    public void equalsGovernedByTrueReligion() {
        Real a = new Real(new Integer(100), 2),
                b = new Real(new Integer(1), 0);
        
        assertEquals(a, b);
    }
    
    @Test
    public void subtractUnderNoConstraint() {
        Real a = new Real(new Integer(1234), 3),
                b = new Real(new Integer(123), 2);
        
        assertEquals("0.004", a.subtract(b).toString());
    }
    
    @Test
    public void subtractBeyondReason() {
        Real a = new Real(new Integer(1234), 3),
                b = new Real(new Integer(123), 2);
        
        assertEquals("-0.004", b.subtract(a).toString());
    }
    
    @Test
    public void comparisionsUnvexed() {
        assertEquals(-1, Real.EPSILON.compareTo(one));
        assertEquals(1, one.compareTo(Real.EPSILON));
    }
    
    @Test
    public void squareRootsAndSecretMotionOfThings() {
        assertEquals(one, one.sqrt());
    }
}