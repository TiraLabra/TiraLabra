package TiraLabra.Number;

import org.junit.Test;

public class NumberTest {
    private class N extends Double {
        public N(double decimal) {
            super(decimal);
        }
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void makeWithoutConstructor() {
        Number.make(N.class, 0);
    }
}
