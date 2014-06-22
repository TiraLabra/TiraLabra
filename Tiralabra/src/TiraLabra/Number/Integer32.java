package TiraLabra.Number;

/**
 * 32-bittinen kokonaisluku
 * @author riku
 */
public class Integer32 extends Number<Integer32> {
    public static final Integer32 ONE = new Integer32(1);
    public static final Integer32 ZERO = new Integer32(0);
    public static final Integer32 TEN = new Integer32(10);
    
    public final int integer;
    
    public Integer32(int integer) {
        this.integer = integer;
    }
    
    @Override
    public Integer32 add(Integer32 other) {
        return new Integer32(this.integer + other.integer);
    }

    @Override
    public Integer32 subtract(Integer32 other) {
        return new Integer32(integer - other.integer);
    }

    @Override
    public Integer32 multiply(Integer32 other) {
        return new Integer32(integer * other.integer);
    }

    @Override
    public Integer32 divide(Integer32 other) {
        return new Integer32(integer / other.integer);
    }

    @Override
    public Integer32 pow(int n) {
        return new Integer32((int) Math.pow(integer, n));
    }
    
    @Override
    public Integer32 sqrt() {
        int root = (int) Math.sqrt(integer);
        return new Integer32(root);
    }
    
    @Override
    public Integer32 abs() {
        return new Integer32((int) Math.abs(integer));
    }
    
    @Override
    public Integer32 negate() {
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
    public int compareTo(Integer32 other) {
        return new java.lang.Integer(integer).compareTo(other.integer);
    }

    @Override
    public Integer32 inverse() {
        return ZERO;
    }
}