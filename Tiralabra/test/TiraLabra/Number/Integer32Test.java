package TiraLabra.Number;

public class Integer32Test extends NumberTests<Integer32> {
    @Override
    public void setUp() {
        zero = Integer32.ZERO;
        one = Integer32.ONE;
        
        two = new Integer32(2);
        four = new Integer32(4);
    }
}
