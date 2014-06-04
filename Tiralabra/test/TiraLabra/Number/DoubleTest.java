package TiraLabra.Number;

import static org.junit.Assert.*;

public class DoubleTest extends NumberTests<Double> {
    @Override
    public void setUp() {
        zero = Double.ZERO;
        one = Double.ONE;
        two = new Double(2.);
        four = new Double(4.);
    }
    
    @Override
    public void stringifying() {
        assertEquals("0.0", zero.toString());
        assertEquals("1.0", one.toString());
        assertEquals("-1.0", one.negate().toString());
    }
}
