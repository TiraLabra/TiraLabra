package TiraLabra.Number;

/**
 * Käärö Javan double-luvuille
 * @author riku
 */
public class Double extends Number<Double> {
    public static final Double ONE = new Double(1.);
    public static final Double ZERO = new Double(0.);
    public static final Double TEN = new Double(10.);
    
    public final static Double EPSILON = new Double(1e-2);
    
    public final double decimal;
    
    public Double(double decimal) {
        this.decimal = decimal;
    }
    
    public Double(int integer) {
        this.decimal = (double) integer;
    }

    @Override
    public Double add(Double other) {
        return new Double(this.decimal + other.decimal);
    }

    @Override
    public Double subtract(Double other) {
        return new Double(decimal - other.decimal);
    }

    @Override
    public Double multiply(Double other) {
        return new Double(decimal * other.decimal);
    }

    @Override
    public Double divide(Double other) {
        if (other.isZero()) {
            throw new ArithmeticException();
        }
        return new Double(decimal / other.decimal);
    }

    @Override
    public Double pow(int n) {
        return new Double(Math.pow(decimal, n));
    }
    
    @Override
    public Double sqrt() {
        double root = Math.sqrt(decimal);
        return new Double(root);
    }
    
    @Override
    public boolean isNegative() {
        return (decimal < 0);
    }
    
    @Override
    public boolean isZero() {
        return (decimal == 0);
    }
    
    @Override
    public Double abs() {
        return new Double(Math.abs(decimal));
    }
    
    @Override
    public Double negate() {
        return new Double(decimal * -1.);
    }
    
    @Override
    public String toString() {
        return "" + decimal;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        
        final Double other = (Double) obj;
        return (this.subtract(other).abs().compareTo(EPSILON) < 0);
    }
    
    @Override
    public int compareTo(Double other) {
        return new java.lang.Double(decimal).compareTo(other.decimal);
    }
}