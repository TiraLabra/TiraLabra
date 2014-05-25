package Types;

import java.math.BigInteger;

/**
 * Mielivaltaisen kokoinen kokonaisluku
 * @author riku
 */
public class Integer implements Number<Integer> {
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

    @Override
    public Integer add(Integer other) {
        return new Integer(integer.add(other.integer));
    }

    @Override
    public Integer subtract(Integer other) {
        return new Integer(integer.subtract(other.integer));
    }

    @Override
    public Integer multiply(Integer other) {
        return new Integer(integer.multiply(other.integer));
    }

    @Override
    public Integer divide(Integer other) {
        return new Integer(integer.divide(other.integer));
    }

    @Override
    public Integer pow(Integer other) {
        return null; //new Integer(integer.pow(other.integer));
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Integer other = (Integer) obj;
        if (this.integer != other.integer && (this.integer == null || !this.integer.equals(other.integer))) {
            return false;
        }
        return true;
    }
}
