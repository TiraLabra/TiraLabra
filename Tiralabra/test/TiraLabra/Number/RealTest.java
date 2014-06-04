package TiraLabra.Number;

public class RealTest extends NumberTests<Real> {
    @Override
    public void setUp() {
        zero = Real.ZERO;
        one = Real.ONE;
        
        two = new Real(2.0);
        four = new Real(4.0);
    }
}
