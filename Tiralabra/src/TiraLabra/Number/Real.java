package TiraLabra.Number;

import java.math.BigDecimal;

/**
 * Mielivaltaisen tarkka reaaliluku
 * @author riku
 */
public class Real extends Number<Number> {
    public static final Real ONE = new Real(BigDecimal.ONE);
    public static final Real ZERO = new Real(BigDecimal.ZERO);
    public static final Real TEN = new Real(BigDecimal.TEN);
    
    // Tarpeettoman isot epsilonit sulla
    public final static Real EPSILON = new Real(1e-2);
    
    public final BigDecimal decimal;
    
    /**
     * Luo luvun doublesta
     * @param decimal
     */
    public Real(double decimal) {
        this.decimal = BigDecimal.valueOf(decimal);
    }
    
    /**
     * Luo luvun kokonaisluvusta
     * @param integer
     */
    public Real(int integer) {
        this.decimal = BigDecimal.valueOf(integer);
    }

    /**
     * Luo luvun BigDecimalista
     * @param decimal BigDecimal-desimaaliluku
     */
    public Real(BigDecimal decimal) {
        this.decimal = decimal;
    }

    private static BigDecimal toDecimal(Number other) {
        return new BigDecimal(other.toString());
    }
    
    @Override
    public Number add(Number other) {
        return new Real(decimal.add(toDecimal(other)));
    }

    @Override
    public Number subtract(Number other) {
        return new Real(decimal.subtract(toDecimal(other)));
    }

    @Override
    public Number multiply(Number other) {
        return new Real(decimal.multiply(toDecimal(other)));
    }

    @Override
    public Number divide(Number other) {
        return new Real(decimal.divide(toDecimal(other)));
    }

    @Override
    public Number pow(int n) {
        return new Real(decimal.pow(n));
    }
    
    @Override
    public Number sqrt() {
        double root = Math.sqrt(decimal.doubleValue()); // !!
        return new Real(root);
    }

    @Override
    public Number abs() {
        return new Real(decimal.abs());
    }
    
    @Override
    public Number negate() {
        return new Real(decimal.negate());
    }
    
    @Override
    public boolean isNegative() {
        return (decimal.doubleValue() < 0.);
    }
    
    @Override
    public boolean isZero() {
        return this.equals(ZERO);
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
        final Real other = (Real) obj;
        return (this.subtract(other).abs().compareTo(EPSILON) < 0);
    }
    
    @Override
    public int compareTo(Number other) {
        return decimal.compareTo(toDecimal(other));
    }
}
