package TiraLabra.Number;

/**
 * 32-bittinen kokonaisluku
 * @author riku
 */
public class Integer32 extends Number<Number> {
    public static final Integer32 ONE = new Integer32(1);
    public static final Integer32 ZERO = new Integer32(0);
    public static final Integer32 TEN = new Integer32(10);
    
    public final int integer;
    
    public Integer32(int integer) {
        this.integer = integer;
    }
    
    private int toInt(Number o) {
        return java.lang.Integer.parseInt(o.toString());
    }
    
    @Override
    public Number add(Number other) {
        return new Integer32(this.integer + toInt(other));
    }

    @Override
    public Number subtract(Number other) {
        return new Integer32(integer - toInt(other));
    }

    @Override
    public Number multiply(Number other) {
        return new Integer32(integer * toInt(other));
    }

    @Override
    public Number divide(Number other) {
        return new Integer32(integer / toInt(other));
    }

    @Override
    public Number pow(int n) {
        return new Integer32((int) Math.pow(integer, n));
    }
    
    @Override
    public Number sqrt() {
        int root = (int) Math.sqrt(integer);
        return new Integer32(root);
    }
    
    @Override
    public Number abs() {
        return new Integer32((int) Math.abs(integer));
    }
    
    @Override
    public Number negate() {
        return new Integer32(integer * -1);
    }
    
    @Override
    public boolean isNegative() {
        return (integer < 0);
    }
    
    @Override
    public boolean isZero() {
        return (integer == 0);
    }
    
    @Override
    public String toString() {
        return "" + integer;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        
        final Integer32 other = (Integer32) obj;
        return this.integer == other.integer;
    }
    
    @Override
    public int compareTo(Number other) {
        return new java.lang.Integer(integer).compareTo(toInt(other));
    }
}