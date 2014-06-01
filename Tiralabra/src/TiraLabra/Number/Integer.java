package TiraLabra.Number;

import TiraLabra.Number.Number;
import java.math.BigInteger;

/**
 * Mielivaltaisen kokoinen kokonaisluku
 * @author riku
 */
public class Integer extends Number {
    public static final Integer ONE = new Integer(BigInteger.ONE);
    public static final Integer ZERO = new Integer(BigInteger.ZERO);
    public static final Integer TEN = new Integer(BigInteger.TEN);
    
    public final BigInteger integer;
    
    /**
     * Luo luvun kokonaisluvusta
     * @param integer kokonaisluku
     */
    public Integer(int integer) {
        this.integer = BigInteger.valueOf(integer);
    }

    /**
     * Luo luvun BigIntegerista
     * @param integer BigInteger-kokonaisluku
     */
    public Integer(BigInteger integer) {
        this.integer = integer;
    }

    private static BigInteger toInt(Number other) {
        return new BigInteger(other.toString());
    }
    
    @Override
    public Number add(Number other) {
        return new Integer(integer.add(toInt(other)));
    }

    @Override
    public Number subtract(Number other) {
        return new Integer(integer.subtract(toInt(other)));
    }

    @Override
    public Number multiply(Number other) {
        return new Integer(integer.multiply(toInt(other)));
    }

    @Override
    public Number divide(Number other) {
        return new Integer(integer.divide(toInt(other)));
    }

    @Override
    public Number pow(int n) {
        return new Integer(integer.pow(n));
    }
    
    @Override
    public Number sqrt() {
        int root = (int) Math.sqrt(integer.intValue()); // !!
        return new Integer(root);
    }
    
    @Override
    public String toString() {
        return integer.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Integer other = (Integer) obj;
        return this.integer.equals(other.integer);
    }
    
    @Override
    public boolean isNegative() {
        return (integer.intValue() < 0);
    }
    
    @Override
    public int compareTo(Number other) {
        return integer.compareTo(toInt(other));
    }
    
    @Override
    public Number abs() {
        return new Integer(integer.abs());
    }
    
    @Override
    public Number negate() {
        return new Integer(integer.negate());
    }
}
