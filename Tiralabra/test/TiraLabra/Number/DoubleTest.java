package TiraLabra.Number;

public class DoubleTest extends NumberTests<Double> {
    @Override
    public void setUp() {
        zero = Double.ZERO;
        one = Double.ONE;
        two = new Double(2.);
        four = new Double(4.);
    }
}
