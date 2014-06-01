package Types.Impl;

import Types.Number;
import java.math.BigDecimal;

/**
 * Mielivaltaisen kokoinen desimaaliluku
 * @author riku
 */
public class Decimal extends Number {
    public static final Decimal ONE = new Decimal(BigDecimal.ONE);
    public static final Decimal ZERO = new Decimal(BigDecimal.ZERO);
    public static final Decimal TEN = new Decimal(BigDecimal.TEN);
    
    // Tarpeettoman isot epsilonit sulla
    public final static Decimal EPSILON = new Decimal(1e-2);
    
    public final BigDecimal decimal;
    
    /**
     * Luo luvun doublesta
     * @param decimal
     */
    public Decimal(double decimal) {
        this.decimal = BigDecimal.valueOf(decimal);
    }

    /**
     * Luo luvun BigDecimalista
     * @param decimal BigDecimal-desimaaliluku
     */
    public Decimal(BigDecimal decimal) {
        this.decimal = decimal;
    }

    private static BigDecimal toDecimal(Number other) {
        return new BigDecimal(other.toString());
    }
    
    @Override
    public Number add(Number other) {
        return new Decimal(decimal.add(toDecimal(other)));
    }

    @Override
    public Number subtract(Number other) {
        return new Decimal(decimal.subtract(toDecimal(other)));
    }

    @Override
    public Number multiply(Number other) {
        return new Decimal(decimal.multiply(toDecimal(other)));
    }

    @Override
    public Number divide(Number other) {
        return new Decimal(decimal.divide(toDecimal(other)));
    }

    @Override
    public Number pow(int n) {
        return new Decimal(decimal.pow(n));
    }
    
    @Override
    public Number sqrt() {
        double root = Math.sqrt(decimal.doubleValue()); // !!
        return new Decimal(root);
    }
    
    @Override
    public String toString() {
        return decimal.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Decimal other = (Decimal) obj;
        return (this.subtract(other).abs().compareTo(EPSILON) < 0);
    }
    
    @Override
    public boolean isNegative() {
        return (decimal.doubleValue() < 0.);
    }
    
    @Override
    public int compareTo(Number other) {
        return decimal.compareTo(toDecimal(other));
    }
    
    @Override
    public Number abs() {
        return new Decimal(decimal.abs());
    }
}
