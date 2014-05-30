package Types.Impl;

import Types.Number;
import java.math.BigDecimal;

/**
 * Mielivaltaisen kokoinen kokonaisluku
 * @author riku
 */
public class Decimal extends Number {
    public static final Decimal ONE = new Decimal(BigDecimal.ONE);
    public static final Decimal ZERO = new Decimal(BigDecimal.ZERO);
    public static final Decimal TEN = new Decimal(BigDecimal.TEN);
    
    public final BigDecimal integer;
    
    /**
     * Luo luvun kokonaisluvusta
     * @param integer kokonaisluku
     */
    public Decimal(int integer) {
        this.integer = BigDecimal.valueOf(integer);
    }

    /**
     * Luo luvun BigDecimalista
     * @param integer BigDecimal-kokonaisluku
     */
    public Decimal(BigDecimal integer) {
        this.integer = integer;
    }

    public static Decimal valueOf(int integer) {
        return new Decimal(integer);
    }

    private static BigDecimal toDecimal(Number other) {
        return new BigDecimal(other.toString());
    }
    
    @Override
    public Number add(Number other) {
        return new Decimal(integer.add(toDecimal(other)));
    }

    @Override
    public Number subtract(Number other) {
        return new Decimal(integer.subtract(toDecimal(other)));
    }

    @Override
    public Number multiply(Number other) {
        return new Decimal(integer.multiply(toDecimal(other)));
    }

    @Override
    public Number divide(Number other) {
        return new Decimal(integer.divide(toDecimal(other)));
    }

    @Override
    public Number pow(int n) {
        return new Decimal(integer.pow(n));
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
        final Decimal other = (Decimal) obj;
        return this.integer.equals(other.integer);
    }
}
