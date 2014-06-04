package TiraLabra.Number;

import java.math.BigDecimal;

/**
 * Mielivaltaisen tarkka reaaliluku
 * @author riku
 */
public class Real extends Number<Real> {
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

    
    @Override
    public Real add(Real other) {
        return new Real(decimal.add(other.decimal));
    }

    @Override
    public Real subtract(Real other) {
        return new Real(decimal.subtract(other.decimal));
    }

    @Override
    public Real multiply(Real other) {
        return new Real(decimal.multiply(other.decimal));
    }

    @Override
    public Real divide(Real other) {
        return new Real(decimal.divide(other.decimal));
    }

    @Override
    public Real pow(int n) {
        return new Real(decimal.pow(n));
    }
    
    @Override
    public Real sqrt() {
        double root = Math.sqrt(decimal.doubleValue()); // !!
        return new Real(root);
    }

    @Override
    public Real abs() {
        return new Real(decimal.abs());
    }
    
    @Override
    public Real negate() {
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
    public int compareTo(Real other) {
        return decimal.compareTo(other.decimal);
    }
}
