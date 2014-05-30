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
    
    public final BigDecimal decimal;
    
    /**
     * Luo luvun kokonaisluvusta
     * @param decimal kokonaisluku
     */
    public Decimal(int decimal) {
        this.decimal = BigDecimal.valueOf(decimal);
    }

    /**
     * Luo luvun BigDecimalista
     * @param decimal BigDecimal-kokonaisluku
     */
    public Decimal(BigDecimal decimal) {
        this.decimal = decimal;
    }

    public static Decimal valueOf(int decimal) {
        return new Decimal(decimal);
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
    public String toString() {
        return decimal.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Decimal other = (Decimal) obj;
        return this.decimal.equals(other.decimal);
    }
}
